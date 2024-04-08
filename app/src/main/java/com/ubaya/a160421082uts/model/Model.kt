package com.ubaya.a160421082uts.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class User(
    val id:String?,
    val username:String?,
    val firstName:String?,
    val lastName:String?,
    val email:String?,
    val password:String,
)

data class News(
    val id:String?,
    val title:String,
    val imageURL : String,
    val date : Date,
    val description: String,
    val content : List<String>,
    val author_id : String,
)
