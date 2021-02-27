package com.roshastudio.newsapp.repository

import androidx.lifecycle.LiveData
import com.roshastudio.newsapp.NewsDao
import com.roshastudio.newsapp.model.News

// Pass the DAO instead of the whole database
class NewsRepo(private val newsDao: NewsDao) {

    val allNews: LiveData<List<News>> = newsDao.getNews()
    fun getFullNews(id: Int): LiveData<News> {
        return newsDao.getFullNews(id)
    }

    suspend fun getBookmarkNews(id: Int): News {
        return newsDao.getBookmarkNews(id)
    }

    suspend fun insert(News: News) {
        newsDao.insert(News)
    }
}