package com.cazulabs.todoapp.addtasks.ui.model

data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    val task: String,
    val isDone: Boolean = false
) {
}