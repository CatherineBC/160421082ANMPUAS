package com.ubaya.a160421082uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160421082uts.model.News
import com.ubaya.a160421082uts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application): AndroidViewModel(application),CoroutineScope {
    val newsLD = MutableLiveData<List<News>>()
    val newsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        newsLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            newsLD.postValue(db.NewsDao().selectAllNews())
            loadingLD.postValue(false)
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/ANMP_UTS/cakenews.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<List<News>>() { }.type
//                val result = Gson().fromJson<List<News>>(it, sType)
//                newsLD.value = result as ArrayList<News>?
//                loadingLD.value = false
//
//                Log.d("showvoley", result.toString())
//
//            },
//            {
//                Log.d("showvoley", it.toString())
//                newsLoadErrorLD.value = false
//                loadingLD.value = false
//            })
//
//        stringRequest.tag = TAG // kasi identitas ke string request, objek yang akan dijalankan sama queue buat jalanin di server.
//        queue?.add(stringRequest)

    }
    override fun onCleared() {
        super.onCleared()
    }

}