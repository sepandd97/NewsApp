package com.roshastudio.newsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roshastudio.newsapp.database.NewsDataBase.NewsRoomDatabase
import com.roshastudio.newsapp.model.BookMarks
import com.roshastudio.newsapp.model.News
import com.roshastudio.newsapp.repository.BookMarksRepo
import com.roshastudio.newsapp.repository.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BookMarkVm(private val app: Application) : AndroidViewModel(app) {
    private val repository: NewsRepo
    private val bookMarksRepository: BookMarksRepo
    var allBookMarks: LiveData<List<BookMarks>>
    var descNews = arrayListOf<News>()

    init {
        val newsDao = NewsRoomDatabase.getDatabase(app, viewModelScope).NewsDao()
        repository = NewsRepo(newsDao)
        val bookMarkDao = NewsRoomDatabase.getDatabase(app, viewModelScope).BookMarkDao()
        bookMarksRepository = BookMarksRepo(bookMarkDao)
        allBookMarks = bookMarksRepository.allBookMarks
    }


   suspend fun getFullNews(bookMarks: List<BookMarks>): List<News> {
        val descNews = mutableListOf<News>()

            bookMarks.forEach {
                descNews += repository.getBookmarkNews(it.newsid!!)
         }


        return descNews

    }

    fun saveBookmarks(BookMark: BookMarks) = viewModelScope.launch(Dispatchers.IO) {
        bookMarksRepository.insert(BookMark)
    }

    fun delete(BookMark: BookMarks) = viewModelScope.launch(Dispatchers.IO) {
        bookMarksRepository.delete(BookMark)
    }

}


