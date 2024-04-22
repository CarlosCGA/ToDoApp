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
        taskDAO.addTask(TaskEntity(taskModel.id, taskModel.task, taskModel.isDone))
    }

    suspend fun updateTask(taskModel: TaskModel) {
        taskDAO.updateTask(TaskEntity(taskModel.id, taskModel.task, taskModel.isDone))
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDAO.deleteTask(TaskEntity(taskModel.id, taskModel.task, taskModel.isDone))
    }
}

