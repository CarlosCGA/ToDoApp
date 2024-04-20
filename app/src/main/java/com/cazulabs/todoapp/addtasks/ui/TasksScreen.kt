package com.cazulabs.todoapp.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(initial = false)

        FABAddTask(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            tasksViewModel
        )

        DialogAddTask(
            showDialog,
            onDismiss = { tasksViewModel.onDialogClose() },
            onTaskAdded = { newTask -> tasksViewModel.onTaskAdded(newTask) }
        )
    }
}

@Composable
fun FABAddTask(modifier: Modifier, tasksViewModel: TasksViewModel) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { tasksViewModel.onShowDialogClick() }, shape = CircleShape
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add task")
    }
}

@Composable
fun DialogAddTask(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onTaskAdded: (String) -> Unit
) {
    var newTask by rememberSaveable {
        mutableStateOf("")
    }

    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss.invoke() }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .clip(RoundedCornerShape(8.dp))
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Add task", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.size(24.dp))

                    TextField(
                        modifier = Modifier,
                        value = newTask,
                        onValueChange = { newText -> newTask = newText },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    Button(onClick = { onTaskAdded.invoke(newTask) }) {
                        Text(text = "Add task")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDialogAddTask() {
    DialogAddTask(true, {}, {})
}