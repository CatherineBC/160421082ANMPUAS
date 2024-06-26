//package com.ubaya.a160421082uts.view
//
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.navigation.Navigation
//import com.ubaya.a160421082uts.R
//import com.ubaya.a160421082uts.databinding.FragmentLoginBinding
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
//import com.ubaya.a160421082uts.databinding.FragmentRegistrasiBinding
//import org.json.JSONObject
//
//
//class RegistrasiFragment : Fragment() {
//    private lateinit var binding:FragmentRegistrasiBinding
//
//    var email : String = ""
//    var password : String = ""
//    var repassword : String = ""
//    var username : String = ""
//    var firstname : String = ""
//    var lastname : String = ""
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentRegistrasiBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.btnRegist.setOnClickListener {
//            val username = binding.txtUserReg.text.toString()
//            val email= binding.txtEmail.text.toString()
//            val password = binding.txtPassword.text.toString()
//            val repassword= binding.txtRePassword.text.toString()
//            val firstname = binding.txtFirstName.text.toString()
//            val lastname= binding.txtLastName.text.toString()
//
//            if(password != repassword)
//            {
//                Toast.makeText(requireContext(), "Password dan Re-Password Tidak Sama", Toast.LENGTH_SHORT).show()
//            }
//            else if(password == "" && repassword == "")
//            {
//                Toast.makeText(requireContext(), "Password dan Re-Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
//            }
//            else{
//
//                var q = Volley.newRequestQueue(requireContext())
//                val url = "http://10.0.2.2/ANMP_UTS/registrasi.php"
//                var stringRequest = object: StringRequest(
//                    Request.Method.POST,url,
//                    Response.Listener{
//                        Log.d("coba", it)
//                        val obj = JSONObject(it)
//                        if(obj.getString("result") == "OK"){
//                            Toast.makeText(requireContext(), "Sign Up Success", Toast.LENGTH_SHORT).show()
//                            val action = RegistrasiFragmentDirections.actionLogin()
//                            Navigation.findNavController(requireView()).navigate(action)
//                        }
//                        else{
//                            val coba = obj.getString("msg")
//                            Toast.makeText(requireContext(), coba, Toast.LENGTH_SHORT).show()
//                        }
//
//                    },
//                    Response.ErrorListener {
//                        Log.e("apiresult", it.message.toString())
//                    })
//                {
//                    override fun getParams(): MutableMap<String, String> {
//                        val params = HashMap<String, String>()
//                        params["username"] = username
//                        params["email"] = email
//                        params["pass"] = repassword
//                        params["firstname"] = firstname
//                        params["lastname"] = lastname
//
//                        return params
//                    }
//                }
//                q.add(stringRequest)
//            }
//        }
//
//    }
////}