package com.buscame.oncor.buscame.DashBoard.Activity.Objects

import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buscame.oncor.buscame.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.cardview_command_device.view.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.telephony.SmsManager
import android.util.Base64
import android.util.Log
import com.buscame.oncor.buscame.DashBoard.Activity.DashBoard
import com.buscame.oncor.buscame.DashBoard.Presenter.DashBoardPresenter
import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.GPSTracker
import com.buscame.oncor.buscame.Util.DataCache
import com.buscame.oncor.buscame.Util.Util
import com.google.android.gms.maps.model.LatLng


class ListOfDeviceFunctionsAdapter(private var context: Context, private var device: Device, private var presenter: DashBoardPresenter, var gps: GPSTracker): RecyclerView.Adapter<ListOfDeviceFunctionsAdapter.CustomViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_command_device, null)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {


        val item = device.getModel().getCommands()[position]


        val bitmap = decodeBase64(item.image)



        holder.btnCommand!!.setImageBitmap(bitmap)
        var accessToken =   DataCache.readData(context,"accessToken")

        holder.itemView.setOnClickListener {

            var IMEI = device.code
           // Log.e("datos command",IMEI+" "+gps.latitude+" "+gps.longitude+" "+ item.id)


            DashBoard.btnBuscame.visibility = View.VISIBLE
            DashBoard.pulsator.visibility = View.VISIBLE
            DashBoard.imgMarket = Util.base64ToBitmap(item.image)
            var latLng = LatLng(gps.latitude,gps.longitude)
            DashBoard.printMarketPosition(latLng, "gps traker",context, Util.base64ToBitmap(item.image))
            DashBoard.dialogConfirmCommand(context,item.name)
                    .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->

                        if (item.id != 10){
                            if (!item.active){

                                presenter.model.commandDevice(item.id,IMEI,gps.latitude,gps.longitude,accessToken)

                                sendSMS(device.numberSimcard, item.commandCodeActive)
                                item.active = true
                            }else{

                                try {

                                    sendSMS(device.numberSimcard, item.commandCodeDesactive)
                                    item.active = false
                                }catch (e:Exception){

                                }
                            }
                        }else{

                            presenter.model.commandDevice(item.id,IMEI,gps.latitude,gps.longitude,accessToken)

                            sendSMS(device.numberSimcard, item.commandCodeActive)
                        }


                var imgBtnBuscameBitmat = Util.Companion.decodeBase64(item.image);
                DashBoard.btnBuscame.setBackground(Util.Companion.BitmatToDrawable(imgBtnBuscameBitmat,context));
                DashBoard.pulsator.start()


            }).setNegativeButton("CANCELAR",DialogInterface.OnClickListener { dialog, which ->

            }).show()

        }



    }


    /*
    setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bitmap imgBtnBuscameBitmat = Util.Companion.decodeBase64(st);
                                btnBuscame.setBackground(Util.Companion.BitmatToDrawable(imgBtnBuscameBitmat,context));
                                pulsator.start();

                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
     */


    private fun sendSMS(phoneNumber: String, message: String) {
        val sms = SmsManager.getDefault()
        sms.sendTextMessage(phoneNumber, null, message, null, null)
    }



    override fun getItemCount(): Int {
        return  device.getModel().getCommands()?.size ?: 0
    }





    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view){

        var btnCommand: CircleImageView? = view.btnCommandDevice

    }


    fun decodeBase64(input: String): Bitmap {
        val decodedBytes = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }




}