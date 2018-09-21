package com.buscame.oncor.buscame.Register.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.buscame.oncor.buscame.R
import kotlinx.android.synthetic.main.activity_form_create_new_user.*
import android.graphics.Rect
import android.net.Uri
import android.widget.CheckBox
import android.widget.EditText
import com.buscame.oncor.buscame.Register.Register
import com.buscame.oncor.buscame.Register.Presenter.RegisterPresenter
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Checked
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password


class FormCreateNewUser : AppCompatActivity(), Register.View, Validator.ValidationListener{


    /**
     *
     * form fields with annotation validation saripaar
     */
    @NotEmpty(message = "Campo obligatorio")
    lateinit var txtName : EditText
    @NotEmpty(message = "Campo obligatorio")
    lateinit var txtPhone : EditText
    @NotEmpty(message = "Campo obligatorio")
    lateinit var txtAdress : EditText
    @NotEmpty(message = "Campo obligatorio")
    lateinit var txtUser : EditText
    @NotEmpty(message = "Campo obligatorio")
    @Password
    lateinit var txtPassword : EditText
    @NotEmpty(message = "Campo obligatorio")
    @Email(message = "Correo no valido")
    lateinit var txtEmail : EditText
    @Checked(message = "Acepte Terminos y condiciones")
    lateinit var TermsAndConditions : CheckBox


    private  var presenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_create_new_user)



      var validator =  Validator(this)
        validator.setValidationListener(this)

        txtName = name
        txtPassword = password
        txtEmail = email
        txtPhone = phone
        txtUser = user
        txtAdress = adress
        TermsAndConditions = checkTermsAndConditions


        textTermsAndConditions.setOnClickListener {

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
            startActivity(browserIntent)
        }

        // boton inferior que al presionar crea un nuevo usuario
        btnNewAcount.setOnClickListener {

            validator.validate()

        }

        // boton flotante que al presionar crea un nuevo usuario
        fab_next.setOnClickListener{

            validator.validate()

        }


        // oculta o muestra el boton inferior cuando app_bar se oculta
        app_bar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->


            if(verticalOffset >= -470){
                btnNewAcount.visibility = View.GONE
            }else{
                btnNewAcount.visibility = View.VISIBLE
            }
        }



        // oculta  boton inferior cuando el teclado se muestra
        coordinator.getViewTreeObserver().addOnGlobalLayoutListener(
                 {
                    val r = Rect()
                    // r will be populated with the coordinates of your view
                    // that area still visible.
                    coordinator.getWindowVisibleDisplayFrame(r)


                    val heightDiff = coordinator.getRootView().getHeight() - (r.bottom - r.top)
                    //if(hightDiff>100) --> It may be keyboard.
                    if (heightDiff != 256 ){
                        btnNewAcount.visibility = View.GONE
                    }
                })

    }



    /**
     *
     * This method will start presentation slider
     *
     */
    override fun goToNewDevice() {

        var goToWelcome = Intent(this, Slider::class.java)
            startActivity( goToWelcome )
    }

    /**
     *
     *
     * Print a response message of server
     */
    override fun showMessageError(result: String?) {
        Toast.makeText(this,result,Toast.LENGTH_LONG).show()
    }


    /**
     *
     * This method  in case that validation  be successful
     * Create a new user
     * method of library saripaar
     */
    override fun onValidationSucceeded() {

        presenter.createNewUser(
                   txtName.text.toString(),
                   txtPhone.text.toString(),
                   txtAdress.text.toString(),
                   txtUser.text.toString(),
                   txtPassword.text.toString(),
                   txtEmail.text.toString()
           )
    }

    /**
     *
     * This method make validation and show errors in the form
     * method of library saripaar
     */
    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        if (errors != null) {
            for (error in errors) {
                val view = error.getView()
                val message = error.getCollatedErrorMessage(this)

                // Display error notification_layout ;)
                if (view is EditText) {
                    (view as EditText).error = message
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}
