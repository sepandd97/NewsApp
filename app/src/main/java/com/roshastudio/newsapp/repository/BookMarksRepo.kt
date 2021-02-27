package com.roshastudio.newsapp.repository

import androidx.lifecycle.LiveData
import com.roshastudio.newsapp.BookMarkDao
import com.roshastudio.newsapp.NewsDao
import com.roshastudio.newsapp.model.BookMarks
import com.roshastudio.newsapp.model.News

class BookMarksRepo(private val bookMarksDao: BookMarkDao) {
    val allBookMarks: LiveData<List<BookMarks>> = bookMarksDao.getNewsId()

    fun ifExist(id: Int): LiveData<Boolean> {
        return bookMarksDao.ifExists(id)
    }

    suspend fun delete(BookMarks: BookMarks) {
        bookMarksDao.deleteAll(BookMarks)
    }

    suspend fun insert(BookMarks: BookMarks) {
        bookMarksDao.insert(BookMarks)
    }
}