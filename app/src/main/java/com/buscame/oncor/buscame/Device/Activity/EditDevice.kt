package com.buscame.oncor.buscame.Device.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.buscame.oncor.buscame.Device.DeviceMVP
import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.Device.Presenter.DevicePresenter
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Util.DataCache
import com.buscame.oncor.buscame.Util.Util
import kotlinx.android.synthetic.main.activity_edit_device.*
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.buscame.oncor.buscame.Util.PhotoUtils
import android.os.PersistableBundle


class EditDevice : AppCompatActivity(),DeviceMVP.View {
    var newDevice = Device()


    private lateinit var mImageUri:Uri

    private val ACTIVITY_SELECT_IMAGE :Int= 1020
    private val ACTIVITY_SELECT_FROM_CAMERA = 1040
    private val ACTIVITY_SHARE = 1030
    private var photoUtils: PhotoUtils? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_edit_device)

         val deviceCode = intent.extras.getString("DEVICE_CODE")


        val device = DataCache.getDevice(deviceCode,this)

        newDevice = device

        loadDataDevice(device)



         photoUtils =  PhotoUtils(this)

        // Get intent, action and MIME type
        val intent = intent
        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND == action && type != null) {
            if ("text/plain" == type) {
                val fromShare = true
            } else if (type.startsWith("image/")) {
                val fromShare = true
                mImageUri = intent
                        .getParcelableExtra(Intent.EXTRA_STREAM) as Uri
                getImage(mImageUri)
            }
        }

       btnChangeImageDevice.setOnClickListener {


            val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, ACTIVITY_SELECT_IMAGE)

        }



        btnEditDataDevice.setOnClickListener {

            val codeDevice = device.code
            val bitmapImageDevice: Bitmap = Util.convertToBitmap(img_device_edit.background,img_device_edit.width,img_device_edit.height)
            validateData(aliasDevice.text.toString(),Util.bitmapToBase64(bitmapImageDevice),codeDevice)

        }

    }
   fun getImage(uri: Uri) {

        val bounds = photoUtils?.getImage(uri)
        if (bounds != null) {
            setImageBitmap(bounds)
        } else {

            showErrorToast()

        }
    }


    private fun showErrorToast() {


    }



    private fun setImageBitmap(bitmap: Bitmap) {
        img_device_edit.setImageBitmap(bitmap)
    }

    private fun loadDataDevice(device: Device) {

        val alias = if (!device.alias.isNullOrEmpty()) device.alias else device.name
        if (!device.image.isNullOrEmpty()){

            img_device_edit.setImageBitmap(Util.decodeBase64(device.image))
        }

        aliasDevice.setText(alias)
    }


    private fun validateData(alias: String, image: String, codeDevice: String) {

        if (!alias.isNullOrEmpty()){

            val accessToken = DataCache.readData(this,"accessToken")
            val presenter = DevicePresenter(this)


                newDevice.alias = alias
                newDevice.image = image


            presenter.updateDataDevice(newDevice,accessToken)


        }
    }


    override fun message(message: String?) {

        Toast.makeText(this,message+"datos guardados",Toast.LENGTH_SHORT).show()


    }

    override fun updateRenderDevice(device: Device?) {

        val returnIntent = Intent()
         DataCache.updateDevice(this,device)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }


    private val PICK_IMAGE = 100

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)


        startActivityForResult(gallery, PICK_IMAGE)
    }
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {

        if (mImageUri != null)
            if (outState != null) {
                outState.putString("Uri", mImageUri.toString())
            }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("Uri")) {
                mImageUri = Uri.parse(savedInstanceState.getString("Uri"));
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == ACTIVITY_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                mImageUri = data.getData()
            };
            getImage(mImageUri)
        } else if (requestCode == ACTIVITY_SELECT_FROM_CAMERA
                && resultCode == RESULT_OK) {
            getImage(mImageUri)
        }
    }

    override fun newDevice(device: Device?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeDevice(position: Int) {

    }
}
