package com.example.to_dolistusingvmandroom.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_dolistusingvmandroom.viewmodel.TodoItem
import com.example.to_dolistusingvmandroom.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    // Collecting state from ViewModel
    val todoList by viewModel.todoList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp)
            .padding(16.dp),
        // ✅ Added full padding for better spacing
        verticalArrangement = Arrangement.Top // ✅ Aligned content to start
    ) {
        var text by remember { mutableStateOf("") }

        // ✅ Text input field
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Enter task") },
            modifier = Modifier.fillMaxWidth() // ✅ Better width utilization
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ✅ Add Task Button
        Button(
            onClick = {
                if (text.isNotBlank()) { // ✅ Prevent adding empty tasks
                    viewModel.addTodo(text)
                    text = ""
                }
            },
            modifier = Modifier.fillMaxWidth() // ✅ Full-width button for better UI
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ LazyColumn for smooth scrolling & efficient rendering
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // ✅ Added spacing between items
        ) {
            items(todoList) { item -> // ✅ Correct way to display a list in Jetpack Compose
                TodoItem(
                    item = item,
                    onToggle = viewModel::toogleTodo,
                    onDelete = viewModel::removeTodo
                )
            }
        }
    }
}
