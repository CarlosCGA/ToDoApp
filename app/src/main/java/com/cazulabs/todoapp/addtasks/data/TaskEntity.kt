package com.cazulabs.todoapp.addtasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey/*(autoGenerate = true) It could be generated automatically*/
    val id: Int,
    var task: String,
    var isDone: Boolean = false
)
