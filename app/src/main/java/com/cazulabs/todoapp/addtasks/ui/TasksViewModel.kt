package com.cazulabs.todoapp.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.todoapp.addtasks.domain.AddTaskUseCase
import com.cazulabs.todoapp.addtasks.domain.GetTasksUseCase
import com.cazulabs.todoapp.addtasks.ui.TasksUiState.*
import com.cazulabs.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTaskAdded(task: String) {
        onDialogClose()
        _tasks.add(TaskModel(task = task))

        viewModelScope.launch {
            addTaskUseCase.invoke(TaskModel(task = task))
        }
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let { task ->
            task.copy(isDone = !task.isDone)
        }
    }

    fun onRemoveTask(taskModel: TaskModel) {
        val task = _tasks.find { task -> task.id == taskModel.id }
        _tasks.remove(task)
    }
}