package com.buscame.oncor.buscame.DashBoard.Activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.buscame.oncor.buscame.DashBoard.Activity.Objects.ListOfDeviceFunctionsAdapter;
import com.buscame.oncor.buscame.DashBoard.Presenter.DashBoardPresenter;
import com.buscame.oncor.buscame.Device.Activity.FirstNewDevice;
import com.buscame.oncor.buscame.Device.Activity.ListDevices;
import com.buscame.oncor.buscame.Device.Activity.NewDevice;
import com.buscame.oncor.buscame.Device.DeviceMVP;
import com.buscame.oncor.buscame.Device.Model.Device;
import com.buscame.oncor.buscame.Device.Presenter.DevicePresenter;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse;
import com.buscame.oncor.buscame.GPSTracker;
import com.buscame.oncor.buscame.Historical.Activity.Historical;
import com.buscame.oncor.buscame.Profile.Activity.Profile;
import com.buscame.oncor.buscame.Profile.Model.Account;
import com.buscame.oncor.buscame.Profile.Model.Command;
import com.buscame.oncor.buscame.R;
import com.buscame.oncor.buscame.Services.Activity.TypeServices;
import com.buscame.oncor.buscame.Util.DataCache;
import com.buscame.oncor.buscame.Util.Permisions;
import com.buscame.oncor.buscame.Util.Util;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, com.buscame.oncor.buscame.DashBoard.DashBoard.View, View.OnClickListener, DeviceMVP.View {


    private DevicePresenter presenterDevice = new DevicePresenter(this);
    private String positionDevice = "";
    private View viewHeaderDrawer;
    private TextView textViewNameDrawer;
    private TextView textViewUserDrawer;
    private CircleImageView imgDrawer;
    private static GoogleMap mMap = null;
    private int ADD_DEVICE_RESULT = 2;
    private Long DURATION_ANIMATIN = 300L;
    boolean menuActionsIsVisible = true;
    private DashBoardPresenter presenter = new DashBoardPresenter(this);
    public static FloatingActionMenu menu;
    public  Activity context = this;
    public static LocationManager locationManager = null;
    private Intent intent = new Intent();
    private DrawerLayout drawer_layout;
    private LinearLayout showAndHideMenuActions;
    private LinearLayout containnerMenuActions;
    public static ImageView btnBuscame;
    public static PulsatorLayout pulsator;
    private RecyclerView listOfDeviceFunctionsRecyclerView;
    private Toolbar toolbar;
    private NavigationView nav_view_drawer;
    private android.support.design.widget.FloatingActionButton fabLocateMe;
    public static GPSTracker gps;
    private Device device;
    private List<Command> commands;
    private boolean activeBuscame = false;
    public static Bitmap imgMarket;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        //inicializar elementos
       initializeVariables();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.Map);
        mapFragment.getMapAsync(this);

        nav_view_drawer.setNavigationItemSelectedListener(this);
        showAndHideMenuActions.setOnClickListener(this);
        showAndHideMenuActions.setOnClickListener(this);


        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.txtdashboard));



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer_layout.addDrawerListener(toggle);

        toggle.syncState();


        positionDevice = DataCache.readData(this, "position_device");
        final String accessToken = DataCache.readData(this, "accessToken");
        if (positionDevice.isEmpty()) {
            DataCache.writeData(this, "position_device", "0");
            positionDevice = "0";
        }

        DeviceFindAllByUserResponse data = DataCache.getDevices(this);

        validateNumberDevice(data);


        loadDataProfileDrawer();
        loadMenuFloatingDevices( data.getDevices() );
        gps = new GPSTracker(context);
        loadCommands( device);

        changeCategoryTheme(device);


        fabLocateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gps != null){

                    gps.StartLocation();
                    locateMe(context);
                }else{
                    gps = new GPSTracker(context);
                }

            }
        });


        btnBuscame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!activeBuscame){
                    presenter.model.commandDevice(9,device.getCode(),gps.getLatitude(),gps.getLongitude(),accessToken);

                    sendSMS(device.getNumberSimcard(), "+XT:3001,1,1");
                    activeBuscame = true;
                    pulsator.start();
                }else{

                    sendSMS(device.getNumberSimcard(), "+XT:3001,60,1");
                    activeBuscame = false;
                    pulsator.stop();
                }
            }
        });


    }

    /**
     *
     * @param data
     */
    private void validateNumberDevice(DeviceFindAllByUserResponse data) {

        if (data.getDevices().size() > 0){

            try {

                device = data.getDevices().get(Integer.parseInt(positionDevice));
                commands = device.getModel().getCommands();
            }catch (Exception e){

            }
        }else {
            intent = new Intent(this, FirstNewDevice.class);
            startActivity(intent);
            finish();
            return;
        }
    }

    private void initializeVariables() {

        drawer_layout = findViewById(R.id.drawer_layout);
        showAndHideMenuActions = findViewById(R.id.showAndHideMenuActions);
        containnerMenuActions = findViewById(R.id.containnerMenuActions);
        btnBuscame = findViewById(R.id.btnBuscame);
        pulsator = findViewById(R.id.pulsator);
        listOfDeviceFunctionsRecyclerView = findViewById(R.id.listOfDeviceFunctionsRecyclerView);
        toolbar = findViewById(R.id.toolbar);
        nav_view_drawer = findViewById(R.id.nav_view_drawer);
        fabLocateMe = findViewById(R.id.fabLocateMe);
        viewHeaderDrawer = nav_view_drawer.getHeaderView(0);
        textViewNameDrawer = viewHeaderDrawer.findViewById(R.id.txtNameDrawer);
        textViewUserDrawer = viewHeaderDrawer.findViewById(R.id.txtUserDrawer);
        imgDrawer = viewHeaderDrawer.findViewById(R.id.profileImageDrawer);
        DrawerLayout drawer_layout = findViewById(R.id.drawer_layout);
        menu = findViewById(R.id.menuDeviceFloating);
    }


    /**
     *
     *
     * @param b
     */
    public static void showAndHideButtonBuscame(final boolean b){


        DashBoard.runOnUI(new Runnable() {
            public void run() {

                if (b){

                    pulsator.setVisibility(View.VISIBLE);
                    pulsator.stop();

                }else {
                    pulsator.setVisibility(View.GONE);
                }
            }
        });


    }

    /**
     *
     *
     * @param device
     * @param position
     * @param context
     */
    private void loadSelectedDeviceInFront( Device device,int position, Context context) {

        DataCache.writeData(context, "position_device", position+"");

       Intent intent = new Intent(this,DashBoard.class);
        changeCategoryTheme(device);

        ActivityOptions option  = ActivityOptions.makeCustomAnimation(context, R.anim.left_out, R.anim.left_in);

        startActivity(intent, option.toBundle());
        finish();
    }

    /**
     *
     *
     * @param latLng
     * @param provider
     * @param context
     * @param bitmap
     */
    public static void printMarketPosition(final LatLng latLng, final String provider, final Context context, final Bitmap bitmap) {

        DashBoard.runOnUI(new Runnable() {
            public void run() {

                if (mMap != null && latLng != null) {
                    mMap.clear();
                    if (bitmap != null){

                        mMap.addMarker(new MarkerOptions().position(latLng).title(context.getResources().getString(R.string.title_my_ubication)))
                                .setIcon(BitmapDescriptorFactory.fromBitmap( bitmap));
                    }else{

                        mMap.addMarker(new MarkerOptions().position(latLng).title(context.getResources().getString(R.string.title_my_ubication)));
                    }

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.zoomTo(15f));
                    gps.stopUsingGPS();
                }
            }
        });

    }

    /**
     *
     *
     * @param device
     */
    private void loadCommands(Device device) {

        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        listOfDeviceFunctionsRecyclerView.setLayoutManager( layoutManager );
        listOfDeviceFunctionsRecyclerView.setHasFixedSize(true);
        ListOfDeviceFunctionsAdapter commandsAdapter =new ListOfDeviceFunctionsAdapter(this, device, presenter,gps);
        listOfDeviceFunctionsRecyclerView.setAdapter(commandsAdapter);
    }

    /**
     *
     *
     * @param device
     */
    private void changeCategoryTheme(Device device) {

        toolbar.setBackgroundColor(Color.parseColor(device.getCategory().getColor()));
        menu.setMenuButtonColorNormal(Color.parseColor(device.getCategory().getColor()));
        nav_view_drawer.getHeaderView(0).setBackgroundColor(Color.parseColor(device.getCategory().getColor()));
        getWindow().setStatusBarColor(Color.parseColor(device.getCategory().getColor()));
       String st = device.getCategory().getImage();
        Bitmap imgBtnBuscameBitmat = Util.Companion.decodeBase64(st);
        btnBuscame.setBackground(Util.Companion.BitmatToDrawable(imgBtnBuscameBitmat,this));

    }


    /**
     *
     *
     */
    private void loadDataProfileDrawer() {

         Account account = DataCache.getAccount(this);
        CircleImageView profileImageDrawer = imgDrawer.findViewById(R.id.profileImageDrawer);
        try {

            profileImageDrawer.setImageBitmap(Util.Companion.base64ToBitmap(account.getImage()));
            textViewNameDrawer.setText(account.getName());
            textViewUserDrawer.setText( account.getUsername());

        }catch (Exception e){


        }

    }

    /**
     *
     *
     * @param context
     * @param comand
     * @return
     */
    public static AlertDialog.Builder dialogConfirmCommand( final Context context, String comand) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Confirmaciòn")
                .setMessage("Confirme si desea activar "+ comand);


        return builder;
    }

    /**
     *
     *
     * @param devices
     */
    private void loadMenuFloatingDevices(final List<Device> devices) {


        menu.removeAllMenuButtons();

        if (!devices.isEmpty()) {

            //sortList(devices.toList())
            final int[] position = {0};
            for (final Device device :devices  ) {


                FloatingActionButton floatingDevice =new FloatingActionButton(this);
                floatingDevice.setButtonSize( FloatingActionButton.SIZE_MINI );

                 String label = ( !Util.Companion.isEmptyOrNull(device.getAlias()) ) ? device.getAlias() : device.getModel().getName();
                floatingDevice.setLabelText(label);

                if (!Util.Companion.isEmptyOrNull(device.getImage())) {

                    floatingDevice.setImageBitmap(Util.Companion.decodeBase64(device.getImage()));
                } else {

                    floatingDevice.setBackground( ResourcesCompat.getDrawable(getResources(), R.drawable.producto_sin_imagen, null));
                }
                floatingDevice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadSelectedDeviceInFront(device, position[0],context);
                        position[0]++;
                    }
                });

                menu.addMenuButton(floatingDevice);

                FirebaseMessaging.getInstance().subscribeToTopic(device.getCode());


            }

            menu.setVisibility( View.VISIBLE );
        }

    }

    /**
     *
     * @param view
     * @param toFloat
     */
    private void slideMenuActionsRight( View view,Float toFloat) {

        view.setVisibility( View.VISIBLE );

        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", -view.getWidth() + view.getWidth());
        animation.setDuration(DURATION_ANIMATIN);
        animation.start();

    }

    /**
     *
     *
     * @param view
     * @param btnShowAndHideWith
     */
    private void slideMenuActionsLeft( View view,  Float btnShowAndHideWith) {

        view.setVisibility( View.VISIBLE );

        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", -view.getWidth() + btnShowAndHideWith);
        animation.setDuration(DURATION_ANIMATIN);
        animation.start();


    }


    /**
     *
     *
     * @param dashBoard
     */
    public void locateMe(Activity dashBoard) {

        if (Permisions.messagePermission(dashBoard, Manifest.permission.SEND_SMS ))
        if (Permisions.messagePermission(dashBoard, Manifest.permission.ACCESS_FINE_LOCATION )){

            try {

                LatLng latLng = new LatLng(gps.getLocation().getLatitude(), gps.getLocation().getLongitude() );
                Log.e("posicion",latLng.toString());
                Log.e("posicion2",gps.getLocation().toString());
                Log.e("posicion3",gps.getLocation().getLatitude()+" "+gps.getLongitude());
                printMarketPosition(latLng,"gps traker",this, null);

            }catch (Exception e){

            }

        }


    }


    /**
     *
     *
     * @return
     */
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    /**
     *
     *
     * @return
     */
    private boolean checkLocation() {
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    /**
     *
     *
     */
    private void showAlert() {

    }

    /**
     *
     *
     * @param phoneNumber
     * @param message
     */
    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }


    /**
     *
     *
     */
    @Override
    protected void onStart() {
        super.onStart();
        loadMenuFloatingDevices(DataCache.getDevices(this).getDevices());
        loadDataProfileDrawer();
    }

    /**
     *
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.showAndHideMenuActions: {

                if (menuActionsIsVisible) {
                    slideMenuActionsLeft(containnerMenuActions, (float) showAndHideMenuActions.getWidth());
                    // slideMenuActionsLeft(btnShowAndHideMenuDeviceActions)
                    menuActionsIsVisible = false;
                } else {
                    slideMenuActionsRight(containnerMenuActions, (float) showAndHideMenuActions.getWidth());
                    // slideMenuActionsRight(btnShowAndHideMenuDeviceActions)
                    menuActionsIsVisible = true;

                }
            }
        }
    }

    /**
     *
     *
     * @param message
     */
    @Override
    public void message(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     *
     *
     * @param device
     */
    @Override
    public void newDevice(Device device) {

        DataCache.addDevice(this,device);
        DeviceFindAllByUserResponse data =  DataCache.getDevices(this);
        loadMenuFloatingDevices(data.getDevices());
    }

    /**
     *
     *
     * @param device
     */
    @Override
    public void updateRenderDevice(Device device) {

    }

    /**
     *
     *
     * @param position
     */
    @Override
    public void removeDevice(int position) {

    }

    /**
     *
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (Permisions.messagePermission(this, Manifest.permission.ACCESS_FINE_LOCATION ))
            gps = new GPSTracker(this);
            locateMe(this);

    }

    /**
     *
     *
     * @param hasCapture
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /**
     *
     *
     * @param message
     */
    @Override
    public void notification(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        //mensaje
    }

    /**
     *
     *
     * @param error
     */
    @Override
    public void notificationError(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (Permisions.requestPermissionsResult(this,requestCode,permissions,grantResults));
           // getLastPosition();
    }

    /**
     *
     *
     */
    @Override
    public void onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       switch (item.getItemId()){
           case R.id.action_settings:{

           }
           case R.id.nav_add_new_device:{

               intent =new Intent(this, NewDevice.class);
               startActivityForResult(intent, ADD_DEVICE_RESULT);
               return true;
           }
           default:{
               return super.onOptionsItemSelected(item);
           }
       }

    }

    /**
     *
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.



        switch (item.getItemId()){
            case R.id.nav_modules:{
               Intent  intent =new Intent(this, ListDevices.class);
                startActivity(intent);

                break;
            }
            case R.id.nav_add_device:{

                Intent intent =new Intent(this, NewDevice.class);
                startActivityForResult(intent, ADD_DEVICE_RESULT);
                break;
            }
            case R.id.nav_profile:{

                Intent intent =new Intent(this, Profile.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_historical:{

                Intent intent =new Intent(this, Historical.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_services:{

                Intent intent =new Intent(this, TypeServices.class);
                startActivity(intent);
                break;
            }
        }



        drawer_layout.closeDrawer(GravityCompat.START);
        return false;


    }

    /**
     *
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_DEVICE_RESULT) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("result_code");
                String accessToken =   DataCache.readData(this,"accessToken");
                presenterDevice.addDevice(result,accessToken,this);
            }

        }
    }

    public static Handler UIHandler;

    static
    {
        UIHandler = new Handler(Looper.getMainLooper());
    }
    public static void runOnUI(Runnable runnable) {
        UIHandler.post(runnable);
    }






}
