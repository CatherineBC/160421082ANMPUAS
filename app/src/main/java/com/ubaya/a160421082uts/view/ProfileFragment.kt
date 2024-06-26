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
import com.ubaya.a160421082uts.databinding.FragmentProfileBinding
import com.ubaya.a160421082uts.model.User
import com.ubaya.a160421082uts.viewmodel.UserViewModel

class ProfileFragment : Fragment(), UserLogoutClick, UserUpdateProfileClick {
    private lateinit var dataBinding: FragmentProfileBinding
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
    var sharedFile= "com.ubaya.a160421082uts"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentProfileBinding.inflate(inflater,container, false)
        return dataBinding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shared = this.requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        editor = shared.edit()

        val password = shared.getString(LoginActivity.PASS, "").toString()
        val username = shared.getString(LoginActivity.USERNAME, "").toString()
        dataBinding.txtUsernameProf.setText(username)
        dataBinding.txtOldPass.setText(password)
        dataBinding.txtUsernameProf.isEnabled = false
        dataBinding.txtOldPass.isEnabled = false

        dataBinding.updateListener = this
        dataBinding.logoutListener = this

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                dataBinding.user = user
            }
        })

        userId = shared.getInt(LoginActivity.USERID, -1)
        viewModel.display(userId)
    }



    fun observeViewModel() {
        viewModel.getStatusLiveData().observe(viewLifecycleOwner, Observer { apiResult ->
            viewModel.display(userId)
            displayObserveViewModel()
        })
    }

    fun displayObserveViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer { User ->
            if (User != null) {
                editor.putInt(LoginActivity.USERID, User.uid ?: -1)
                editor.putString(LoginActivity.EMAIL, User.email)
                editor.putString(LoginActivity.USERNAME, User.username)
                editor.putString(LoginActivity.PASS, User.password)
                editor.putString(LoginActivity.FIRSTNAME, User.firstName)
                editor.putString(LoginActivity.LASTNAME, User.lastName)
                editor.apply()

                dataBinding.txtUsernameProf.setText(User.username)
                dataBinding.txtOldPass.setText(User.password)
                dataBinding.txtUsernameProf.isEnabled = false
                dataBinding.txtOldPass.isEnabled = false
                dataBinding.txtFirstNameProfile.setText("")
                dataBinding.txtLastProf.setText("")
                dataBinding.txtPasswordProfille.setText("")
            } else {
                Toast.makeText(requireContext(), "Failed to update data", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    override fun onUserUpdateProfile(v: View, obj: User) {
////        shared= requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
////
////        userId = shared.getInt(LoginActivity.USERID,-1)
////        password = shared.getString(LoginActivity.PASS,"").toString()
////        username = binding.txtUsernameProf.text.toString()
////        passwordprof = binding.txtPasswordProfille.text.toString()
////        firstname = binding.txtFirstNameProfile.text.toString()
////        lastname = binding.txtLastProf.text.toString()
//
//
////        viewModel.update(userId)
////        observeViewModel()
//
////        val userId = shared.getInt(LoginActivity.USERID, -1)
////        // Update user logic here
////        viewModel.update(userId)
////        observeViewModel()
//
//        val userId = shared.getInt(LoginActivity.USERID, -1)
//        val newPassword = dataBinding.txtPasswordProfille.text.toString()
//        val firstName = dataBinding.txtFirstNameProfile.text.toString()
//        val lastName = dataBinding.txtLastProf.text.toString()
//
//        viewModel.update(userId, newPassword, firstName, lastName)
//        observeViewModel()
//    }

    override fun onUserUpdateProfile(v: View, obj: User) {
        val userId = shared.getInt(LoginActivity.USERID, -1)
        val newPassword = dataBinding.txtPasswordProfille.text.toString()
        val firstName = dataBinding.txtFirstNameProfile.text.toString()
        val lastName = dataBinding.txtLastProf.text.toString()

        viewModel.update(userId, newPassword, firstName, lastName)
        observeViewModel()
    }

    override fun onUserLogout(v: View) {
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


}