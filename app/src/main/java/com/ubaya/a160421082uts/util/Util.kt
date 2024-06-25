package com.ubaya.a160421082uts.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.a160421082uts.model.ModelDatabase


val DB_NAME = "newmodeldb"


fun buildDb(context: Context):ModelDatabase {
    val db = Room.databaseBuilder(context,
        ModelDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_2_3)
        .build()

    return db
}


val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE 'User' ('uid' INTEGER, 'username' TEXT, 'firstName' TEXT, 'lastName' TEXT, 'email' TEXT,'password' TEXT, PRIMARY KEY('uid')")
        database.execSQL(
            "CREATE TABLE 'News' ('id' INTEGER, 'title' TEXT, 'description' TEXT, 'imageURL' TEXT, 'date' TEXT,'content' TEXT,'author_name' TEXT, PRIMARY KEY('uid')")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT TABLE 'News' (title, description,imageURL,date,content,author_name) VALUES ( 'Delicious Chocolate Cake','Indulge in the rich and decadent flavors of a chocolate cake.','https://sallysbakingaddiction.com/wp-content/uploads/2013/04/triple-chocolate-cake-4.jpg','2024-04-10','Discover the secret recipe for the most delicious chocolate cake.','Catherine')")

        database.execSQL(
            "INSERT TABLE 'News' (title, description,imageURL,date,content,author_name) VALUES ( 'Classic Vanilla Cake','Experience the timeless taste of a classic vanilla cake.','https://www.seriouseats.com/thmb/aJRoRhN0yFe8LXFz1CGJ_pekjp0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__recipes__images__2017__05__20170412-vanilla-layer-cake-vicky-wasik-23-5da8c6517dcd43da91f048a75f6e8bc5.jpg','2024-05-10','Discover the secret recipe for the most delicious vanilla cake.','Valerin')")

        database.execSQL(
            "INSERT TABLE 'News' (title, description,imageURL,date,content,author_name) VALUES ( 'Choco Banana Cake','Cara membuat banana cake yang dipadu dengan coklat.','https://www.recipetineats.com/wp-content/uploads/2021/09/Lemon-Cake-with-Lemon-Frosting_85-SQ.jpg','2024-06-10','Discover the secret recipe for the most delicious banana choco cake.','Vincent')")

        database.execSQL(
            "INSERT TABLE 'News' (title, description,imageURL,date,content,author_name) VALUES ( 'Creamy Coffee Cake','Apakah kopi dapat dijadikan rasa kue? Coba tips satu ini!','https://asset-a.grid.id/crop/0x0:0x0/700x465/photo/sasefoto/original/44336_black-forest.JPG','2023-04-10','Kopi, kopi apa yang pahit.','Citra')")

        database.execSQL(
            "INSERT TABLE 'News' (title, description,imageURL,date,content,author_name) VALUES ( 'Baking 101','Step by step membuat kue bahkan bayi dapat mempelajari step yang akan diajarkan ini.','https://sallysbakingaddiction.com/wp-content/uploads/2013/04/triple-chocolate-cake-4.jpg','2020-04-10','Oreo diputar dijilat dicelupin.','Erin')")
    }
}


