package com.ubaya.a160421082uts.model

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news:News)

    @Query("SELECT * FROM News")
    fun selectAllNews(): List<News>

    @Query("SELECT * FROM News WHERE id= :id")
    fun selectNews(id:Int): News

    @Query("UPDATE News SET title = title WHERE id = :id")
    fun updateNews(id:Int)

    @Delete
    fun deleteNews(news: News)
}

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun regisUser(vararg user:User)

    @Query("SELECT * FROM User WHERE username = :username and password = :password")
    fun login(username: String, password: String): User

    @Query("SELECT * FROM User WHERE uid = :uid")
    fun display(uid: Int): User

    @Query("UPDATE user SET password = :password, firstName = :firstName, lastName = :lastName WHERE uid = :id")
    fun ubahPass(id: Int, password: String, firstName: String, lastName: String) //ini harus dijalankan setelah database dimigrasi. baru bole update habis migrasi gitu itu gunanya suspend

    @Delete
    fun deleteUser(user: User)

}

