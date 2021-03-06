package com.buscame.oncor.buscame.Login.Login.Activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import com.buscame.oncor.buscame.DashBoard.Activity.DashBoard
import com.buscame.oncor.buscame.Device.Activity.FirstNewDevice
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse
import com.buscame.oncor.buscame.Login.Login.Login
import com.buscame.oncor.buscame.Login.Login.Presenter.LoginPresenter
import com.buscame.oncor.buscame.Profile.Model.Account
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Register.Activity.FormCreateNewUser
import com.buscame.oncor.buscame.Util.DataCache

import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), LoaderCallbacks<Cursor> , Login.View {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private var mAuthTask: UserLoginTask? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // Set up the login form.
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        btnLogin.setOnClickListener { attemptLogin() }

        btnRegister.setOnClickListener { goToRegister() }

    }



    /**
     * Callback received when a permissions request has been completed.
     */
    var presenter = LoginPresenter(this)

    private fun Login(mEmail: String, mPassword: String) {

        presenter.loginUser(mEmail,mPassword,this)
    }


    /**
     *  print a response message of server
     */
    override fun showMessage(result: String?) {

        Toast.makeText(this,result,Toast.LENGTH_SHORT).show()
    }


    /**
     *
     * This method in case that user not have devices associate
     * will go to the activity that allow to associate a new device
     * **/
    override fun goToFirstNewDevice(accessToken: String?) {


        DataCache.writeData(this,"accessToken",accessToken)
        var intent = Intent(this, FirstNewDevice::class.java)
        startActivity(intent)
        finish()
    }

    /**
     *
     * This method will start dashboard
     * **/
    override fun goToDashBoard(accessToken: String?, response: DeviceFindAllByUserResponse?, account: Account?, refreshToken: String?) {


        saveDataInThePreferences(account,response,accessToken,refreshToken)

        var intent = Intent(this, DashBoard::class.java)
        startActivity(intent)
        finish()

    }

    /**
     *
     * This method bring the data from the server and store it in the preferences
     * **/
    private fun saveDataInThePreferences(account: Account?, response: DeviceFindAllByUserResponse?, accessToken: String?, refreshToken: String?) {

        if (account != null) {
            account.password = password.text.toString()
            DataCache.setAccount(account,this)
        }


        if (response != null){

            DataCache.setDevices(response,this)
        }

        DataCache.writeData(this,"accessToken",accessToken)
        DataCache.writeData(this,"refreshToken",refreshToken)
    }

    /**
     * The user will be redirect at activity register
     */
    private fun goToRegister(){

        var intentGoToRegister = Intent(this, FormCreateNewUser::class.java)
        startActivity(intentGoToRegister)

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        email.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        } else if (isEmailValid(emailStr)) {
            email.error = getString(R.string.error_invalid_email)
            focusView = email
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            mAuthTask = UserLoginTask(emailStr, passwordStr)
            mAuthTask!!.execute(null as Void?)
        }
    }

    /**
     *
     * Validate that  be a valid email
     */
    private fun isEmailValid(email: String): Boolean {

        return email.contains("@")
    }

    /**
     *
     * This method Validate that password size be greater than 4
     */
    private fun isPasswordValid(password: String): Boolean {

        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()



            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            loginLogo.visibility = if (show) View.VISIBLE else View.VISIBLE
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE

                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE

        }
    }

    /**
     *
     * method generate for android studio
     */
    override fun onCreateLoader(i: Int, bundle: Bundle?): Loader<Cursor> {
        return CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE + " = ?", arrayOf(ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE),

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC")
    }

    /**
     *
     * method generate for android studio
     */
    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
        val emails = ArrayList<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS))
            cursor.moveToNext()
        }

        addEmailsToAutoComplete(emails)
    }

    /**
     *
     * method generate for android studio
     */
    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {}

    /**
     *
     * method generate for android studio
     */
    private fun addEmailsToAutoComplete(emailAddressCollection: List<String>) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        val adapter = ArrayAdapter(this@LoginActivity,
                android.R.layout.simple_dropdown_item_1line, emailAddressCollection)

        email.setAdapter(adapter)
    }


    object ProfileQuery {

        val PROJECTION = arrayOf(
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY)
        val ADDRESS = 0
        val IS_PRIMARY = 1
    }
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.

            try {

                // it call the method that request the data in the server
                Login(mEmail,mPassword)

            } catch (e: InterruptedException) {
                return false
            }

            return DUMMY_CREDENTIALS
                    .map { it.split(":") }
                    .firstOrNull { it[0] == mEmail }
                    ?.let {
                        // Account exists, return true if the password matches.
                        it[1] == mPassword
                    }
                    ?: true
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
              //  finish()
            } else {
                password.error = getString(R.string.error_incorrect_password)
                password.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }

    }


    companion object {

        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")

    }
    override fun goToLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
