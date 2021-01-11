package com.example.myapplication.utils

import androidx.room.Room
import com.example.myapplication.app.MyApp
import com.shop.test.AppDatabase

class DataBaseUtils {
    companion object{
        var db = Room.databaseBuilder(MyApp.instance!!.baseContext,AppDatabase::class.java,"shop")
            .allowMainThreadQueries()  //允许在主线程中查询数据
            .build()
    }
}