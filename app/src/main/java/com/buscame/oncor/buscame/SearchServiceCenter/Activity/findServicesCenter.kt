package com.buscame.oncor.buscame.SearchServiceCenter.Activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import com.buscame.oncor.buscame.GPSTracker
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.SearchServiceCenter.Activity.Object.PopupAdapter
import com.buscame.oncor.buscame.SearchServiceCenter.Model.ServiceCenter
import com.buscame.oncor.buscame.SearchServiceCenter.Presenter.presenterSearchService
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.CityFindAllResponse
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.ServiceCenterFindAllByRadiusAndCityRequest
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.ServiceCenterFindAllByRadiusAndCityResponse
import com.buscame.oncor.buscame.SearchServiceCenter.SearchServiceCenterMVP
import com.buscame.oncor.buscame.Util.DataCache
import com.buscame.oncor.buscame.Util.Util
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_find_services_center.*
import kotlinx.android.synthetic.main.info_marker_window.view.*


class findServicesCenter : AppCompatActivity(), OnMapReadyCallback ,SearchServiceCenterMVP.View{


    private lateinit var mMap: GoogleMap

    var MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

    var myLocation = Location("")
    val presenter = presenterSearchService(this)
    //var citys = arrayListOf("")

    var citys : HashMap<String,Int> = HashMap()
    var serviceId = 0
    var isFilterForCity = true
    var gps: GPSTracker? = null
    var arrayMarket = arrayListOf<MarkerOptions>()


    var cityId = 0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_services_center)


       val accessToken = DataCache.readData(this,"accessToken")
      // val deviceFindAllByUserResponseJson = DataCache.readData(this,"deviceFindAllByUserResponse")

        // validate permissions of Gps

        validatePermissionGps()
        presenter.citys(this,accessToken)


        serviceId = intent.extras.getInt("ID_SERVICE")
        loadSpinnerCitys(citys)



        btnFindServiceCenter.setOnClickListener {


            if (myLocation != null){

                val customMarket = findViewById<FrameLayout>(R.id.customMarket)


                var imagenUser:Bitmap = Util.viewToBitmap(customMarket,customMarket.width,customMarket.height)


                LocaleMe(
                        myLocation.latitude,
                        myLocation.longitude,
                        imagenUser
                )
                presenter.serviceCenterPoints(
                        validateSearch(txtRadio.text.isNullOrEmpty(),txtRadio.text.toString(),serviceId),
                        accessToken
                )
            }

        }

    }



    private fun LocaleMe(latitude:Double,longitude:Double,bitmap:Bitmap) {

        // Add a marker in Sydney and move the camera
        clearMap()
        printMarket(latitude,longitude,bitmap,0)
    }

    private fun clearMap() {

        if(mMap != null){
            mMap.clear()
            arrayMarket.clear()


        }




    }

    private fun printMarket(latitude: Double, longitude: Double, bitmap: Bitmap,position:Int) {


        val myPoint = LatLng(latitude, longitude)

        arrayMarket.add(position, MarkerOptions().position(myPoint).title("Marker in Sydney"))

        mMap.addMarker(arrayMarket[position])
                .setIcon(BitmapDescriptorFactory.fromBitmap(bitmap))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPoint))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15f))







    }

    private fun validateSearch(b: Boolean,  radius: String, serviceId: Int): ServiceCenterFindAllByRadiusAndCityRequest? {

        val request = ServiceCenterFindAllByRadiusAndCityRequest()

        isFilterForCity = b

        request.isFilterForCity = isFilterForCity
        request.serviceId = serviceId
        request.lat = myLocation.latitude
        request.lng = myLocation.longitude
        request.cityId = cityId
        request.radius = if (b) 0 else radius.toInt()

        return request
    }

    private fun loadSpinnerCitys(cities: HashMap<String, Int>) {

        val arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, cities.keys.toList())
        spinnerCitys.setAdapter(arrayAdapter)

        spinnerCitys.setOnItemClickListener { parent, view, position, id ->


            cityId = citys.get(citys.keys.toList().get(position))!!

            Toast.makeText(this,citys.keys.toList().get(position)+" = "+cityId,Toast.LENGTH_SHORT).show()



        }

    }

    val serviceCenterPoints : ArrayList<ServiceCenter> = arrayListOf()

    override fun loadServiceCenterPointsInMap(response: ServiceCenterFindAllByRadiusAndCityResponse?) {


        if (response != null) {


            var json = Gson().toJson(response)
            Log.e("json",json)
            if (response.serviceCenters.isNotEmpty()){





                val view = layoutInflater.inflate(R.layout.info_marker_window, null)




                        response.serviceCenters.forEachIndexed { indexServiceCenter, serviceCenter ->



                            serviceCenter.locations.forEachIndexed { index, sede ->


                                val data =Gson().toJson(sede)
                                val latitude = sede[0]
                                val longitude = sede[1]
                                val address = sede[2]

                                var zoomMap = 15f
                                if(isFilterForCity){
                                    zoomMap = 11f
                                }





                                val customMarket = findViewById<FrameLayout>(R.id.customMarket)
                                var photoPointMarket = customMarket.findViewById<CircleImageView>(R.id.photoPointMarket)
                                photoPointMarket.setImageBitmap(Util.base64ToBitmap(serviceCenter.image))
                                var imagenUser:Bitmap = Util.viewToBitmap(customMarket,customMarket.width,customMarket.height)

                                printMarket(latitude.toDouble(),longitude.toDouble(),imagenUser,( index +1 ))






                            }//


                        }




               mMap.setInfoWindowAdapter( PopupAdapter(view,response.serviceCenters))



            }


        }

    }

    override fun message(message: String?) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }


    private fun validatePermissionGps() {




        val permissionCheck = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)

        // Here, thisActivity is the current activity
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {



            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.


                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
                // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            } else {

                // No explanation needed, we can request the permission.
                Toast.makeText(this,resources.getString(R.string.message_permission_gps_need_permissions),Toast.LENGTH_LONG).show()




            }
        } else{


            getLastPosition()



        }

    }

    override fun loadCitys(CityFindAllResponse: CityFindAllResponse?) {


        if (CityFindAllResponse != null) {



            CityFindAllResponse.cities.forEachIndexed { index, city ->
                citys.put(city.name,city.id)
            }

            loadSpinnerCitys(citys)
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap




    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] === PackageManager.PERMISSION_GRANTED) {


                    getLastPosition()
                     gps = GPSTracker(this)


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    Toast.makeText(this,resources.getString(R.string.message_permission_gps_denied_disable),Toast.LENGTH_LONG).show()
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

    @SuppressLint("MissingPermission")
    private fun getLastPosition() {

        Toast.makeText(this,"possicion",Toast.LENGTH_LONG).show()

       var gps = GPSTracker(this)

       if (  gps.getLocation() != null){

         myLocation = gps.getLocation()

           // Obtain the SupportMapFragment and get notified when the map is ready to be used.
           val mapFragment = supportFragmentManager
                   .findFragmentById(R.id.mapServices) as SupportMapFragment
           mapFragment.getMapAsync(this)

       }


    }


}


