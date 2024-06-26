package com.ubaya.a160421082uts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.a160421082uts.model.User
import com.ubaya.a160421082uts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope {
//    val userLD = MutableLiveData<User?>()
    private val statusLD: MutableLiveData<String?> = MutableLiveData()
//    val TAG = "volleyTag"
//
//
//    private var queue: RequestQueue? = null

    val userLD = MutableLiveData<User?>()
//    val userLoadErrorLD = MutableLiveData<Boolean>()
//    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun login(username: String, password: String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.UserDao().login(username,password))
        }
    }
    fun regis(user: User) { //bisa pake list
        launch {
            val db = buildDb(
                getApplication()
            )
            db.UserDao().regisUser(user)
        }
    }

    fun update(id: Int, password: String, firstName: String, lastName: String) {
        launch {
            val db = buildDb(getApplication())
            db.UserDao().ubahPass(id, password, firstName, lastName)
        }
    }

//    fun display(uid: Int){
//        launch {
//            val db = buildDb(
//                getApplication()
//            )
//            db.UserDao().display(uid)
//        }
//    }

    fun display(uid: Int) {
        launch {
            val db = buildDb(getApplication())
            val user = db.UserDao().display(uid)
            userLD.postValue(user)
        }
    }

//    fun fetchUser(uid: Int) {
//        launch {
//            val db = buildDb(getApplication())
//            val user = db.UserDao().display(uid)
//            userLD.postValue(user)
//        }
//    }


//    fun update(id:String, password:String, firstname :String, lastname:String) {
//        val q = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/ANMP_UTS/update.php"
//        var stringRequest = object : StringRequest(
//            Method.POST, url,
//            Response.Listener<String> { response ->
//                Log.d("apiresult", response)
//                val obj = JSONObject(response)
//                if (obj.getString("result") == "OK") {
//                    statusLD.value = obj.toString()
//                } else {
//                    val msg = obj.getString("msg")
//                    Log.d("error_volley", msg)
//                    statusLD.value = null
//                }
//            },
//            Response.ErrorListener {
//                Log.e("apiresult", it.message.toString())
//            }) {
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["password"] = password
//                params["firstname"] = firstname
//                params["lastname"] = lastname
//                params["userid"] = id
//
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        q.add(stringRequest)
//    }
//
//
//    fun login(password: String, username: String) {
//        val q = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/ANMP_UTS/login.php"
//        val stringRequest = object : StringRequest(
//            Method.POST, url,
//            Response.Listener<String> { response ->
//                Log.d("apiresult", response)
//                val obj = JSONObject(response)
//                if (obj.getString("result") == "OK") {
//                    val data = obj.getJSONObject("data")
//                    val sType = object : TypeToken<User>() {}.type
//                    val result = Gson().fromJson<User>(data.toString(), sType)
//                    val user1 = result as User
//
//                    userLD.value = user1
//
//                    Log.d("show_volley", result.toString())
//                } else {
//                    val msg = obj.getString("msg")
//                    Log.d("error_volley", msg)
//                    userLD.value = null
//                }
//            },
//            Response.ErrorListener {
//                Log.e("apiresult", it.message.toString())
//            }) {
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["username"] = username
//                params["pass"] = password
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        q.add(stringRequest)
//    }
//
//    fun regis(email:String, firstname:String, lastname:String, repassword: String, username: String) {
//        val q = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/ANMP_UTS/registrasi.php"
//        var stringRequest = object : StringRequest(
//            Method.POST, url,
//            Response.Listener<String> { response ->
//                Log.d("apiresult", response)
//                val obj = JSONObject(response)
//                if (obj.getString("result") == "OK") {
//                    statusLD.value = obj.toString()
//                } else {
//                    val msg = obj.getString("msg")
//                    Log.d("error_volley", msg)
//                    statusLD.value = null
//                }
//            },
//            Response.ErrorListener {
//                Log.e("apiresult", it.message.toString())
//            }) {
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["username"] = username
//                params["email"] = email
//                params["pass"] = repassword
//                params["firstname"] = firstname
//                params["lastname"] = lastname
//
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        q.add(stringRequest)
//    }
//
//    fun display_user(id: String){
//        val q = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/ANMP_UTS/display_user.php"
//        val stringRequest = object : StringRequest(
//            Method.POST, url,
//            Response.Listener<String> { response ->
//                Log.d("cekparams", response)
//                val obj = JSONObject(response)
//                if (obj.getString("result") == "OK") {
//                    val data = obj.getJSONObject("data")
//                    val sType = object : TypeToken<User>() {}.type
//                    val result = Gson().fromJson<User>(data.toString(), sType)
//                    val user1 = result as User
//
//                    userLD.value = user1
//
//                    Log.d("show_volley", result.toString())
//
//                } else {
//                    val msg = obj.getString("msg")
//                    Log.d("error_volley", msg)
//                    userLD.value = null
//                }
//            },
//            Response.ErrorListener {
//                Log.e("apiresult", it.message.toString())
//            }) {
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["userid"] = id
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        q.add(stringRequest)
//    }
//
    fun getStatusLiveData(): MutableLiveData<String?> {
        return statusLD
    }


//
}