//package com.ubaya.a160421082uts.view
//
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.NavDirections
//import androidx.navigation.Navigation
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
//import com.squareup.picasso.Picasso
//import com.ubaya.a160421082uts.R
//import com.ubaya.a160421082uts.databinding.FragmentLoginBinding
//import com.ubaya.a160421082uts.viewmodel.ListViewModel
//import com.ubaya.a160421082uts.viewmodel.UserViewModel
//import org.json.JSONObject
//
//class LoginFragment : Fragment() {
//    private lateinit var binding: FragmentLoginBinding
//    private lateinit var viewModel: UserViewModel
////    var status = "success"
//
//    var userId : Int = 0
//    var email : String = ""
//    var pass : String = ""
//    var username : String = ""
//    var firstname : String = ""
//    var lastname : String = ""
//
//    companion object{
//        val EMAIL = "EMAIL"
//        val USERID = "USERID"
//        val USERNAME = "USERNAME"
//        val PASS = "PASS"
//        val FIRSTNAME = "FIRSTNAME"
//        val LASTNAME = "LASTNAME"
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentLoginBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//
////        val checkLogin = shared.getInt(LoginFragment.USERID, -1)
////        if (checkLogin != -1) {
////            val action = LoginFragmentDirections.actionHomeFragment()
////            Navigation.findNavController(view).navigate(action)
////        }
//
//        binding.btnLogIn.setOnClickListener{
//            username = binding.txtUserName.text.toString()
//            pass = binding.txtPass.text.toString()
//            if (username != "" && pass != "") {
//                viewModel.login(pass,username)
//                observeViewModel()
////                if(status =="success")
////                {
////                    val action = LoginFragmentDirections.actionHomeFragment()
////                    Navigation.findNavController(view).navigate(action)
////                }
////                else{
////                    Toast.makeText(requireContext(),"Username dan Password Salah",Toast.LENGTH_SHORT).show()
////                }
//
//
//            }
//            else {
//                Toast.makeText(requireContext(), "Email and Password must not be empty", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        binding.txtRegister.setOnClickListener {
//            val action = LoginFragmentDirections.actionRegistration()
//            Navigation.findNavController(it).navigate(action)
//        }
//
//    }
//    private fun observeViewModel() {
//
//        viewModel.userLD.observe(viewLifecycleOwner, Observer {
//            var User = it
//
//            val sharedFile = "com.ubaya.a160421082"
//            val shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
//
//            if(User != null)
//            {
//                userId = User.id?.toInt() ?: -1
//                email = User.email.toString()
//                pass = User.password.toString()
//                username = User.username.toString()
//                firstname = User.firstName.toString()
//                lastname = User.lastName.toString()
//
//
//                val editor: SharedPreferences.Editor = shared.edit()
//                editor.putInt(USERID, userId)
//                editor.putString(EMAIL, email)
//                editor.putString(USERNAME, username)
//                editor.putString(PASS, pass)
//                editor.putString(FIRSTNAME, firstname)
//                editor.putString(LASTNAME, lastname)
//                editor.apply()
//
////                status = "success"
//                val action = LoginFragmentDirections.actionHomeFragment()
//                Navigation.findNavController(requireView()).navigate(action)
//
//            }
//            else{
////                status ="failed"
//                Toast.makeText(requireContext(),"Username dan Password Salah",Toast.LENGTH_SHORT).show()
//            }
//
//        })
//
//    }
//
//
//}