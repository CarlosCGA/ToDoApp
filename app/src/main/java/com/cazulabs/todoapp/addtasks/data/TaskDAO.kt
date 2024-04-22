package com.cazulabs.todoapp.addtasks.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {
    @Query("SELECT * from TaskEntity")
    fun getTasks() : Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)
}