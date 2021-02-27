package com.roshastudio.newsapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.roshastudio.newsapp.model.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_tbl")
    fun getNews(): LiveData<List<News>>
    @Query("SELECT * FROM news_tbl WHERE id=:id")
    fun getFullNews(id: Int): LiveData<News>
    @Query("SELECT * FROM news_tbl WHERE id=:id")
    suspend fun getBookmarkNews(id: Int): News
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(news: News)
    @Query("DELETE FROM news_tbl")
    suspend fun deleteAll()
}