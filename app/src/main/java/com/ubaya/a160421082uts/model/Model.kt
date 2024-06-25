package com.ubaya.a160421082uts.model

import com.google.gson.annotations.SerializedName
import java.util.Date
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//data class News(
//    val id:String,
//    val title:String,
//    val imageURL : String,
//    val date : Date,
//    val description: String,
//    val content : List<String>,
//    val author_name : String,
//)

@Entity
data class User(
    @ColumnInfo(name="username")
    var username:String?,
    @ColumnInfo(name="firstname")
    var firstName:String?,
    @ColumnInfo(name="lastname")
    var lastName: String?,
    @ColumnInfo(name="email")
    var email:String?,
    @ColumnInfo(name="password")
    var password:String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class News(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="imageURL")
    var imageURL:String,
    @ColumnInfo(name="date")
    var date: String,
    @ColumnInfo(name="description")
    var description:String,
    @ColumnInfo(name="content")
    var content:List<String>,
    @ColumnInfo(name="author_name")
    var author_name:String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

