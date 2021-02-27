package com.roshastudio.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//add tbl to database
@Entity(tableName = "book_marks_tbl")

data class BookMarks(@PrimaryKey val newsid:Int?)
