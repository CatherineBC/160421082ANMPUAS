package com.ubaya.a160421082uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.a160421082uts.util.MIGRATION_1_2



@Database(entities = arrayOf(News::class, User::class), version =  1)
abstract class ModelDatabase:RoomDatabase() {
    abstract fun NewsDao(): NewsDao
    abstract fun UserDao(): UserDao

    companion object {
        @Volatile private var instance: ModelDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext,
            ModelDatabase::class.java, "newmodeldb")
            .addMigrations(MIGRATION_1_2)
            .build()


        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}