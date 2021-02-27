package com.roshastudio.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.roshastudio.newsapp.database.NewsDataBase.NewsRoomDatabase
import com.roshastudio.newsapp.model.BookMarks
import com.roshastudio.newsapp.model.News
import com.roshastudio.newsapp.repository.BookMarksRepo
import com.roshastudio.newsapp.repository.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsDescVM(private val app: Application) : AndroidViewModel(app) {
    private val repository: NewsRepo
    private val bookMarksRepository: BookMarksRepo
    lateinit var descNews: LiveData<News>
    lateinit var ifNewsExist: LiveData<Boolean>
    init {
        val newsDao = NewsRoomDatabase.getDatabase(app, viewModelScope).NewsDao()
        repository = NewsRepo(newsDao)
        val bookMarkDao = NewsRoomDatabase.getDatabase(app, viewModelScope).BookMarkDao()
        bookMarksRepository = BookMarksRepo(bookMarkDao)
    }

    fun getFullNews(id: Int): LiveData<News> {
        descNews = repository.getFullNews(id)
        return descNews
    }
    //Cheek existence of the news id in book_mark_tbl
    fun ifExist(id:Int):LiveData<Boolean>{
        ifNewsExist=  bookMarksRepository.ifExist(id)
      return  ifNewsExist
    }

    fun saveBookmarks(BookMark: BookMarks) = viewModelScope.launch(Dispatchers.IO) {
        bookMarksRepository.insert(BookMark)
    }

    fun delete(BookMark: BookMarks) = viewModelScope.launch(Dispatchers.IO) {
        bookMarksRepository.delete(BookMark)
    }
}