package com.buscame.oncor.buscame.Profile.Activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse
import com.buscame.oncor.buscame.Login.Login.Activity.LoginActivity
import com.buscame.oncor.buscame.Login.Login.Login
import com.buscame.oncor.buscame.Login.Login.Presenter.LoginPresenter
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Util.DataCache
import com.buscame.oncor.buscame.Util.Util
import kotlinx.android.synthetic.main.activity_profile.*
import com.buscame.oncor.buscame.Profile.Model.Account
import com.buscame.oncor.buscame.Profile.Presenter.ProfilePresenter
import com.buscame.oncor.buscame.Profile.ProfileMVP
import com.buscame.oncor.buscame.SettingsActivity
import com.buscame.oncor.buscame.Util.Permisions
import com.buscame.oncor.buscame.Util.PhotoUtils
import de.hdodenhof.circleimageview.CircleImageView


class Profile : AppCompatActivity(),Login.View , ProfileMVP.View{


    private val COD_SELECCIONA = 10
    private lateinit var  profile_image: CircleImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profile_image = findViewById(R.id.profile_image)

        loadDataProfile()

        var accessToken =   DataCache.readData(this,"accessToken")


        btnCloseSession.setOnClickListener {

            LoginPresenter(this).closeSession(accessToken,this)

        }

        btnSetting.setOnClickListener {

            val intent = Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }

        btnDocumentation.setOnClickListener {

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://35.237.43.125:3001"))
            startActivity(browserIntent)
        }

        btnAboutUs.setOnClickListener {

            val intent = Intent(this, AboutUs::class.java)
            startActivity(intent)
        }

        btnEditDataProfile.setOnClickListener {

            val intent = Intent(this, EditDataProfile
            ::class.java)
            startActivity(intent)

        }

        btnEditPhoto.setOnClickListener {

           // profileChangePhoto()

            if (Permisions.messagePermission(this, Manifest.permission.READ_EXTERNAL_STORAGE))
                uploadImage()
        }



    }

    private fun loadDataProfile() {

        val account =   DataCache.getAccount(this)
        try {

            profile_image.setImageBitmap(Util.base64ToBitmap(account.image))
            imgBackground.setImageBitmap(Util.base64ToBitmap(account.image))
            txtNameUser.text = account.name
        }catch (e:Exception){

        }


    }


    override fun onStart() {
        super.onStart()
        loadDataProfile()
    }

    override fun goToLogin() {

        var intent = Intent(this, LoginActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        ActivityCompat.finishAffinity(this);
        DataCache.writeData(this,"accessToken","")
        DataCache.clear(this)
        startActivity(intent)


    }


    private fun uploadImage() {

        val opciones = arrayOf<CharSequence>( "Cargar Imagen", "Cancelar")
        val alertOpciones = AlertDialog.Builder(this)
        alertOpciones.setTitle("Seleccione una Opción")
        alertOpciones.setItems(opciones) { dialogInterface, i ->
            if (opciones[i] == "Cargar Imagen") {


                if (opciones[i] == "Cargar Imagen") {
                    val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                    intent.type = "image/"

                    startActivityForResult(Intent.createChooser(intent, "Seleccione la Aplicación"), COD_SELECCIONA)

                } else {
                    dialogInterface.dismiss()
                }
            }
        }
        alertOpciones.show()

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        if (Permisions.requestPermissionsResult(this,requestCode,permissions,grantResults))
            uploadImage()

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {
                COD_SELECCIONA -> {
                    var miPath = data!!.data
                    renderImageProfile(miPath)


                }
            }
        }
    }


    private fun renderImageProfile(miPath: Uri?) {


        val presenter = ProfilePresenter(this)
        val bitmap = PhotoUtils.getThumbnail(miPath,this)


         val account = DataCache.getAccount(this)
         val accessToken = DataCache.readData(this,"accessToken")

        account.image = Util.bitmapToBase64(bitmap)
        presenter.editDataProfile(accessToken,account)





    }

    override fun showMessage(result: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun goToDashBoard(accessToken: String?, response: DeviceFindAllByUserResponse?, account: Account?, refreshToken: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToFirstNewDevice(accessToken: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notification(message: String?) {
      Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun notificationError(error: String?) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    }

    override fun updateDataAccount(account: Account?) {
        DataCache.setAccount(account,this)
        loadDataProfile()


    }




}
