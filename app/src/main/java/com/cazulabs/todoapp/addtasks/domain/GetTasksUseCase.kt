package com.cazulabs.todoapp.addtasks.domain

import com.cazulabs.todoapp.addtasks.data.TaskRepository
import com.cazulabs.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}