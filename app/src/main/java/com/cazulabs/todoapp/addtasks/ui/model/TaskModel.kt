package com.cazulabs.todoapp.addtasks.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    val isDone: Boolean = false
) {
}