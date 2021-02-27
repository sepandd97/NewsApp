package com.roshastudio.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//add tbl to database
@Entity(tableName = "news_tbl")

data class News(@PrimaryKey val id:Int, val img:Int, val title:String, val desc:String) {
}