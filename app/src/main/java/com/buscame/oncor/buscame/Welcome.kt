package com.buscame.oncor.buscame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.buscame.oncor.buscame.Register.Activity.termsAndConditions
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setTitle("")

        // Al presionar el boton de ingrese su codigo
        btnGoToVerificationCode.setOnClickListener {
            // se muestra el formulario  codigo de verificacion
            showAndHideFormActivationCode(true)

        }

        // Al presionar el boton  terminos y condiciones
        btnCheckCode.setOnClickListener {

            checkCode(txtVerificationCode.text.toString())
        }

        btnGoToTermsAndConditions.setOnClickListener {

            var goToTermsAndConditions: Intent = Intent(this, termsAndConditions::class.java)
            startActivity(goToTermsAndConditions)

        }

    }

    private fun checkCode(code: String) {

        if (!code.equals("") && code != null){

            // se oculta el formulario  codigo de verificacion
            showAndHideFormActivationCode(false)
            // conectar con api de verificacion
        }else{
            txtVerificationCode.setError(resources.getString(R.string.message_error_txtVerificationCode))
        }
    }

    private fun showAndHideFormActivationCode(showForm: Boolean) {


        if (showForm){
            // Oculta los botones y muestra los campos del formulario
            btnGoToVerificationCode.visibility = View.GONE
            btnGoToTermsAndConditions.visibility = View.GONE
            txtVerificationCode.visibility = View.VISIBLE
            btnCheckCode.visibility = View.VISIBLE

        }else{
            // Muestra los botones y oculta los campos del formulario
            btnGoToVerificationCode.visibility = View.VISIBLE
            btnGoToTermsAndConditions.visibility = View.VISIBLE
            txtVerificationCode.visibility = View.GONE
            btnCheckCode.visibility = View.GONE
        }
    }
}
