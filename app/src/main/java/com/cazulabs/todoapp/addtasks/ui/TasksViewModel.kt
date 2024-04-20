package com.cazulabs.todoapp.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cazulabs.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor(): ViewModel() {

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
    }

}