package com.buscame.oncor.buscame.Util;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class Permisions {

    private final static int REQUEST_READ_EXTERNAL_STORAGE = 7;

    public static boolean messagePermission(Activity context, String permissionManifest) {


        // Here, thisActivity is the current activity
        boolean premission = false;
        int gra= PackageManager.PERMISSION_GRANTED;
        if (ContextCompat.checkSelfPermission(context, permissionManifest)!= gra) {


            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permissionManifest)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
//                Toast.makeText(context,"Desves")
                ActivityCompat.requestPermissions(context,new String[]{permissionManifest},
                        REQUEST_READ_EXTERNAL_STORAGE);

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(context,
                        new String[]{permissionManifest},
                        REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{

            return true;
        }


        return premission;
    }


    public static boolean requestPermissionsResult(Context context,int requestCode, String[] permissions, int[] grantResults){

      boolean granted = true;
        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    solicitarPermisosManual(context);
                    //  Log.e(ContentValues.TAG,"permission denied")
                    //   Toast.makeText(this,"Para poder cargar una imagen de perfil debe ceder permisos de almacenamiento", Toast.LENGTH_LONG).show()
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    return false;
                }

            }
        }

        // permission was granted, yay! Do the
        // contacts-related task you need to do.
        return granted;
    }


    private static void solicitarPermisosManual(final Context context) {

         final String[] opciones =new String[]{"si", "no"};
        AlertDialog.Builder alertOpciones =new AlertDialog.Builder(context);
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (opciones[which] == "si") {
                    Intent intent =new Intent();
                    intent.setAction( Settings.ACTION_APPLICATION_DETAILS_SETTINGS );
                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                    intent.setData(uri);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Los permisos no fueron aceptados", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        alertOpciones.show();



        /*
        DialogInterface.OnClickListener { dialogInterface, i ->
            if (opciones[i] == "si") {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", this.packageName, null)
                intent.data = uri
                startActivity(intent)
            } else {
                Toast.makeText(this, "Los permisos no fueron aceptados", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }
        }
         */
    }
}
