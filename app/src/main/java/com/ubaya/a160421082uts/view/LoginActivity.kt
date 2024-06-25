package com.ubaya.a160421082uts.view


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ubaya.a160421082uts.R
import com.ubaya.a160421082uts.databinding.ActivityLoginBinding
import com.ubaya.a160421082uts.viewmodel.UserViewModel
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity(),UserLoginClick {
//    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var shared: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var dataBinding: ActivityLoginBinding
    private lateinit var loginListener: UserLoginClick


    var userId : Int = 0
    var email : String = ""
    var pass : String = ""
    var username : String = ""
    var firstname : String = ""
    var lastname : String = ""

    companion object{
        val EMAIL = "EMAIL"
        val USERID = "USERID"
        val USERNAME = "USERNAME"
        val PASS = "PASS"
        val FIRSTNAME = "FIRSTNAME"
        val LASTNAME = "LASTNAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        val view = binding.root
        dataBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view =  dataBinding.root
        setContentView(view)

        var sharedFile = "com.ubaya.a160421082uts"
        shared = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        var checkLogin = shared.getInt("USERID", -1)
        if (checkLogin != -1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        dataBinding.loginlistener = this

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)


//        dataBinding.btnLogIn.setOnClickListener {
//            username = dataBinding.txtUserName.text.toString()
//            pass = dataBinding.txtPass.text.toString()
//            if (username != "" && pass != "") {
//                viewModel.login(pass, username)
//                observeViewModel()
//
//            } else {
//                Toast.makeText(
//                    this,
//                    "Email and Password must not be empty",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }

//
        dataBinding.txtRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onLoginClick(v: View) {
        username = dataBinding.txtUserName.text.toString()
        pass = dataBinding.txtPass.text.toString()
        if (username != "" && pass != "") {
            viewModel.login(username, pass)
            observeViewModel()

        } else {
            Toast.makeText(
                this,
                "Email and Password must not be empty",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun observeViewModel() {
        viewModel.userLD.observe(this, Observer {User->
            if (User != null) {
                userId = User.uid?.toInt() ?: -1
                email = User.email.toString()
                pass = User.password.toString()
                username = User.username.toString()
                firstname = User.firstName.toString()
                lastname = User.lastName.toString()


                editor = shared.edit()
                editor.putInt(USERID, userId)
                editor.putString(EMAIL, email)
                editor.putString(USERNAME, username)
                editor.putString(PASS, pass)
                editor.putString(FIRSTNAME, firstname)
                editor.putString(LASTNAME, lastname)
                editor.apply()


                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            }
            else
            {
                Toast.makeText(application, "Ini kalau pass salah", Toast.LENGTH_SHORT).show()
            }

        })

    }

}


