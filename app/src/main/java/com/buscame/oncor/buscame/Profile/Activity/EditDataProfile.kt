package com.buscame.oncor.buscame.Profile.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.buscame.oncor.buscame.Profile.Model.Account
import com.buscame.oncor.buscame.Profile.Presenter.ProfilePresenter
import com.buscame.oncor.buscame.Profile.ProfileMVP
import com.buscame.oncor.buscame.R
import android.widget.Toast
import com.buscame.oncor.buscame.Util.DataCache
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password
import kotlinx.android.synthetic.main.activity_edit_data_profile.*
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.cast_intro_overlay.view.*
import kotlinx.android.synthetic.main.dialog_confirm_password.view.*


class EditDataProfile : AppCompatActivity(),ProfileMVP.View, Validator.ValidationListener {


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

    val presenter = ProfilePresenter(this)
    var validator =  Validator(this)

    var accessToken = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data_profile)


         accessToken =   DataCache.readData(this,"accessToken")

        validator.setValidationListener(this)

        txtName = name
        txtPassword = password
        txtEmail = email
        txtAdress = adress
        txtPhone = phone
        txtUser = user
        txtAdress = adress

        val account:Account = DataCache.getAccount(this)

        loadDataProfileInForm(account)


        btnEditPasswordProfile.setOnClickListener {

            password.performClick()
            showDialogConfirmPassword(account)
        }



    }

    private fun loadDataProfileInForm(account: Account?) {


        if (account != null) {
            txtName.setText(account.name)
            txtPassword.setText(account.password)
            txtEmail.setText(account.mail)
            txtPhone.setText(account.phone)
            txtUser.setText(account.username)
            txtAdress.setText(account.address)
        }
    }

    private fun showDialogConfirmPassword(account: Account) {

        // Get the layout inflater
        val inflater = this.getLayoutInflater()

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        val viewDialog = inflater.inflate(R.layout.dialog_confirm_password, null)


        val alertDialog = AlertDialog.Builder(this)
                .setView(viewDialog)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .show()

        val b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        b.setOnClickListener {


            val a = account.password
            val b = viewDialog.confirmPassword.text.toString()

            if (a.equals( b )){


                password.isFocusableInTouchMode = true
                password.isEnabled = true
                alertDialog.dismiss()
            }else{

                viewDialog.confirmPassword.error = resources.getString(R.string.error_incorrect_password)

            }
        }

    }


    override fun notification(message: String?) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun notificationError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun updateDataAccount(account: Account?) {

        DataCache.setAccount(account,this)
        loadDataProfileInForm(account)
    }

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

    override fun onValidationSucceeded() {

        val account = DataCache.getAccount(this)

        account.name = name.text.toString()
        account.phone = phone.text.toString()
        account.username = user.text.toString()
        account.password = password.text.toString()
        account.address = adress.text.toString()
        account.mail = email.text.toString()


        presenter.editDataProfile(accessToken,account)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        getMenuInflater().inflate(R.menu.edit_profile, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update_data_profile) {


            validator.validate()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
