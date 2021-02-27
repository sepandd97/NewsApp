package com.roshastudio.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.roshastudio.newsapp.database.NewsDataBase.NewsRoomDatabase
import com.roshastudio.newsapp.model.News
import com.roshastudio.newsapp.repository.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllNewsVM(private val app: Application) : AndroidViewModel(app) {
    private val repository: NewsRepo
     var allNews: LiveData<List<News>>

    init {
        val newsDao = NewsRoomDatabase.getDatabase(app, viewModelScope).NewsDao()
        repository = NewsRepo(newsDao)

            allNews = repository.allNews

    }
    fun insert(News: News) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(News)
    }

}