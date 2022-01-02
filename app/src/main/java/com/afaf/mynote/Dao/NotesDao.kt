package com.afaf.mynote.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.afaf.mynote.Model.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes ")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("Delete From Notes WHERE id=:id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(notes: Notes)


}
