package com.roshastudio.newsapp

import androidx.lifecycle.LiveData
import androidx.room.*
import com.roshastudio.newsapp.model.BookMarks

@Dao
interface BookMarkDao {
    @Query("SELECT * FROM book_marks_tbl")
    fun getNewsId(): LiveData<List<BookMarks>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(BookMarks: BookMarks)
    @Delete
    suspend fun deleteAll(BookMarks: BookMarks)
    @Query("SELECT EXISTS (SELECT 1 FROM book_marks_tbl WHERE newsid=:id)")
    fun ifExists(id: Int): LiveData<Boolean>
}