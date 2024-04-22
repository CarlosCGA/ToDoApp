package com.cazulabs.todoapp.addtasks.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cazulabs.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {

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
        Log.d("CARLOS", "NEW SIZE -> ${_tasks.size}. LAST ELEMENT -> ${_tasks.last().task}")
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let { task ->
            task.copy(isDone = !task.isDone)
        }
        Log.d("CARLOS", "ELEMENT $index -> ${_tasks[index].task} is ${_tasks[index].isDone}")
    }

    fun onRemoveTask(taskModel: TaskModel) {
        Log.d("CARLOS", "onRemoveTask task.size -> ${_tasks.size}")
        Log.d("CARLOS", "onRemoveTask task to remove -> $taskModel")
        val task = _tasks.find { task -> task.id == taskModel.id }

        if (task != null)
            Log.d("CARLOS", "onRemoveTask TRUE")
        else
            Log.d("CARLOS", "onRemoveTask FALSE")

        var removeTask = TaskModel(task = "NULL")
        for(task: TaskModel in _tasks)
            if (task.id == taskModel.id) {
                Log.d("CARLOS", "FIND")
                removeTask = task
            }
        Log.d("CARLOS", "onRemoveTaskV2 -> ${removeTask.task}")

        _tasks.remove(task)
    }
}