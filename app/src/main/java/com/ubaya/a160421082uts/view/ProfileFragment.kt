package com.ubaya.a160421082uts.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160421082uts.R
import com.ubaya.a160421082uts.databinding.FragmentProfileBinding
import com.ubaya.a160421082uts.viewmodel.UserViewModel
import org.json.JSONObject

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var shared: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    var userId : Int = 0
    var username : String = ""
    var firstname : String = ""
    var lastname : String = ""
    var email : String = ""
    var password : String = ""
    var passwordprof : String = ""




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var sharedFile= "com.ubaya.a160421082uts"
        shared = this.requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        editor  = shared.edit()

        password = shared.getString(LoginActivity.PASS,"").toString()
        username = shared.getString(LoginActivity.USERNAME,"").toString()
        binding.txtUsernameProf.setText(username)
        binding.txtOldPass.setText(password)
        binding.txtUsernameProf.isEnabled = false
        binding.txtOldPass.isEnabled = false


        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnLogout.setOnClickListener {

            editor.putInt(LoginActivity.USERID, -1)
            editor.putString(LoginActivity.EMAIL, "")
            editor.putString(LoginActivity.PASS, "")
            editor.putString(LoginActivity.LASTNAME, "")
            editor.putString(LoginActivity.FIRSTNAME, "")
            editor.putString(LoginActivity.PASS, "")

            editor.apply()
            Log.d("userid", LoginActivity.USERID)

            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            this.requireActivity().finish()
        }

        binding.btnUpdate.setOnClickListener {
            shared= requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

            userId = shared.getInt(LoginActivity.USERID,-1)
            password = shared.getString(LoginActivity.PASS,"").toString()
            username = binding.txtUsernameProf.text.toString()
            passwordprof = binding.txtPasswordProfille.text.toString()
            firstname = binding.txtFirstNameProfile.text.toString()
            lastname = binding.txtLastProf.text.toString()


            viewModel.update(userId.toString(),passwordprof,firstname,lastname)
            observeViewModel()

        }

    }



    fun observeViewModel(){
        userId = shared.getInt(LoginActivity.USERID,-1)

        viewModel.getStatusLiveData().observe(viewLifecycleOwner, Observer {apiResult->

            if (apiResult != null && apiResult.isNotEmpty()) {
                val obj = JSONObject(apiResult)
                if (obj.getString("result") == "OK") {
                    Toast.makeText(requireContext(), "Update Berhasil", Toast.LENGTH_SHORT).show()
                    Log.d("userid",LoginActivity.USERID)
                    viewModel.display_user(userId.toString())
                    displayObserveViewModel()

                } else {

                    Log.d("Update","meow meow")
                }
            }
            else {

                Log.d("Update","meow meow")
            }

        })

    }

    fun displayObserveViewModel()
    {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {User->

            if (User != null) {
                userId = User.id?.toInt() ?: -1
                email = User.email.toString()
                password = User.password.toString()
                username = User.username.toString()
                firstname = User.firstName.toString()
                lastname = User.lastName.toString()


                editor = shared.edit()
                editor.putInt(LoginActivity.USERID, userId)
                editor.putString(LoginActivity.EMAIL, email)
                editor.putString(LoginActivity.USERNAME, username)
                editor.putString(LoginActivity.PASS, passwordprof)
                editor.putString(LoginActivity.FIRSTNAME, firstname)
                editor.putString(LoginActivity.LASTNAME, lastname)
                editor.apply()

                password = shared.getString(LoginActivity.PASS,"").toString()
                username = shared.getString(LoginActivity.USERNAME,"").toString()
                binding.txtUsernameProf.setText(username)
                binding.txtOldPass.setText(password)
                binding.txtUsernameProf.isEnabled = false
                binding.txtOldPass.isEnabled = false
                binding.txtFirstNameProfile.setText("")
                binding.txtLastProf.setText("")
                binding.txtPasswordProfille.setText("")


            } else {
                Toast.makeText(requireContext(), "Gagal update data", Toast.LENGTH_SHORT).show()
            }

        })

    }



}