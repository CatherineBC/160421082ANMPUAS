package com.ubaya.a160421082uts.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160421082uts.R
import com.ubaya.a160421082uts.databinding.ActivityLoginBinding
import com.ubaya.a160421082uts.databinding.ActivityRegisterBinding
import com.ubaya.a160421082uts.model.User
import com.ubaya.a160421082uts.viewmodel.UserViewModel
import org.json.JSONObject

class RegisterActivity : AppCompatActivity(), UserRegisClick {
    private lateinit var dataBinding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    var email : String = ""
    var password : String = ""
    var repassword : String = ""
    var username : String = ""
    var firstname : String = ""
    var lastname : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = dataBinding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        dataBinding.user = User("","","","","")
        dataBinding.regislistener = this

//        binding.btnRegist.setOnClickListener {
//            val username = binding.txtUserReg.text.toString()
//            val email= binding.txtEmail.text.toString()
//            val password = binding.txtPassword.text.toString()
//            val repassword= binding.txtRePassword.text.toString()
//            val firstname = binding.txtFirstName.text.toString()
//            val lastname= binding.txtLastNameProf.text.toString()
//
//            if(password != repassword)
//            {
//                Toast.makeText(application, "Password dan Re-Password Tidak Sama", Toast.LENGTH_SHORT).show()
//            }
//            else if(password == "" && repassword == "")
//            {
//                Toast.makeText(application, "Password dan Re-Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                viewModel.regis(email,firstname,lastname,repassword,username)
//                observeViewModel()
//            }
//        }
    }



//    private fun observeViewModel() {
//
//        viewModel.getStatusLiveData().observe(this, Observer {apiResult->
//
//            if (apiResult != null) {
//                val obj = JSONObject(apiResult)
//                if (obj.getString("result") == "OK") {
//                    Toast.makeText(application, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, LoginActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Toast.makeText(application, "Registrasi tidak berhasil", Toast.LENGTH_SHORT).show()
//                }
//            }
//            else {
//                Toast.makeText(application, "Registrasi tidak berhasil", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

    override fun onRegisClick(v: View) {
            val password = dataBinding.txtPassword.text.toString()
            val repassword= dataBinding.txtRePassword.text.toString()

            if(password != repassword)
            {
                Toast.makeText(application, "Password dan Re-Password Tidak Sama", Toast.LENGTH_SHORT).show()
            }
            else if(password == "" && repassword == "")
            {
                Toast.makeText(application, "Password dan Re-Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }
            else {
                viewModel.regis(dataBinding.user!!)
                Toast.makeText(application, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

}


