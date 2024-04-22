package com.cazulabs.todoapp.addtasks.data

import com.cazulabs.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDAO: TaskDAO) {

    val tasks: Flow<List<TaskModel>> =
        taskDAO.getTasks().map { items -> items.map { TaskModel(it.id, it.task, it.isDone) } }

    suspend fun addTask(taskModel: TaskModel) {
        taskDAO.addTask(taskModel.toData())
    }

    suspend fun updateTask(taskModel: TaskModel) {
        taskDAO.updateTask(taskModel.toData())
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDAO.deleteTask(taskModel.toData())
    }
}

fun TaskModel.toData(): TaskEntity {
    return TaskEntity(this.id, this.task, this.isDone)
}