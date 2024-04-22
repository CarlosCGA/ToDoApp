package com.cazulabs.todoapp.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.todoapp.addtasks.domain.AddTaskUseCase
import com.cazulabs.todoapp.addtasks.domain.DeleteTaskUseCase
import com.cazulabs.todoapp.addtasks.domain.GetTasksUseCase
import com.cazulabs.todoapp.addtasks.domain.UpdateTaskUseCase
import com.cazulabs.todoapp.addtasks.ui.TasksUiState.*
import com.cazulabs.todoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTaskAdded(task: String) {
        onDialogClose()

        viewModelScope.launch {
            addTaskUseCase.invoke(TaskModel(task = task))
        }
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(isDone = !taskModel.isDone))
        }
    }

    fun onRemoveTask(taskModel: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(taskModel)
        }
    }
}