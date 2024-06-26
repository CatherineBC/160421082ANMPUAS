package com.ubaya.a160421082uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160421082uts.model.News
import com.ubaya.a160421082uts.util.buildDb
import com.ubaya.a160421082uts.view.DetailBeritaFragmentArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application, savedStateHandle: SavedStateHandle):
    AndroidViewModel(application), CoroutineScope {
    val newsLD = MutableLiveData<News>()
    private val job = Job()
//    val TAG = "volleyTag"

    var id = DetailBeritaFragmentArgs.fromSavedStateHandle(savedStateHandle).newsId

//    private var queue: RequestQueue? = null

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch() {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/ANMP_UTS/cakenews.php?id=${id}"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url, {
//                val sType = object : TypeToken<News>() {}.type
//                val result = Gson().fromJson<News>(it, sType)
//                val news1 = result as News
//
//                newsLD.value = news1
//
//                Log.d("show_volley", it)
//            },
//            {
//                Log.e("show_volley", it.toString())
//            }
//        )
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }
        launch {
            val db = buildDb(getApplication())
            newsLD.postValue(db.NewsDao().selectNews(id.toInt()))
        }

    }
}