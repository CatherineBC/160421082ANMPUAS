package com.ubaya.a160421082uts.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.a160421082uts.R
import com.ubaya.a160421082uts.databinding.FragmentLoginBinding
import com.ubaya.a160421082uts.viewmodel.ListViewModel
import org.json.JSONObject

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedFile = "com.ubaya.a160421082"
        val shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

//        val checkLogin = shared.getInt(LoginFragment.USERID, -1)
//        if (checkLogin != -1) {
//            val action = LoginFragmentDirections.actionHomeFragment()
//            Navigation.findNavController(view).navigate(action)
//        }

        binding.btnLogIn.setOnClickListener{
            username = binding.txtUserName.text.toString()
            pass = binding.txtPass.text.toString()
            if (username != "" && pass != "") {
                val q = Volley.newRequestQueue(requireContext())
                val url = "http://10.0.2.2/ANMP_UTS/login.php"
                val stringRequest = object : StringRequest(
                    Method.POST, url,
                    Response.Listener<String> { response ->
                        Log.d("apiresult", response)
                        val obj = JSONObject(response)
                        if (obj.getString("result") == "OK") {
                            val data = obj.getJSONObject("data")
                            userId = data.getInt("id")
                            email = data.getString("email")
                            pass = data.getString("password")
                            username = data.getString("username")
                            firstname = data.getString("firstName")
                            firstname = data.getString("lastName")

                            val editor: SharedPreferences.Editor = shared.edit()
                            editor.putInt(USERID, userId)
                            editor.putString(EMAIL, email)
                            editor.putString(USERNAME, username)
                            editor.putString(PASS, pass)
                            editor.putString(FIRSTNAME, firstname)
                            editor.putString(LASTNAME, lastname)
                            editor.apply()

                            val action = LoginFragmentDirections.actionHomeFragment()
                            Navigation.findNavController(view).navigate(action)

                        } else {
                            val msg = obj.getString("msg")
                            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                        }
                    },
                    Response.ErrorListener {
                        Log.e("apiresult", it.message.toString())
                    })

                {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["username"] = username
                        params["pass"] = pass
                        return params
                    }
                }
                q.add(stringRequest)
            }
            else {
                Toast.makeText(requireContext(), "Email and Password must not be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionRegistration()
            Navigation.findNavController(it).navigate(action)
        }

    }


}