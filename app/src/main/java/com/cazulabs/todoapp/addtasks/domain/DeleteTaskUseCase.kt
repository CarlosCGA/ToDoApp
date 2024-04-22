package com.cazulabs.todoapp.addtasks.domain

import com.cazulabs.todoapp.addtasks.data.TaskRepository
import com.cazulabs.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.deleteTask(taskModel)
    }
}