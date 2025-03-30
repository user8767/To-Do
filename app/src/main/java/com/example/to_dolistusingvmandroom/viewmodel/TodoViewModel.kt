package com.example.to_dolistusingvmandroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_dolistusingvmandroom.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class TodoItem(val id: Int, val text: String, val isDone: Boolean = false)

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoRepository(application)

    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>> = _todoList

    private var nextId = 1

    init {
        // ✅ Load saved tasks from DataStore
        viewModelScope.launch {
            repository.todoList.collect { savedTasks ->
                _todoList.value = savedTasks.mapIndexed { index, text ->
                    TodoItem(id = index + 1, text = text, isDone = false)
                }
                nextId = _todoList.value.size + 1 // ✅ Ensures unique IDs
            }
        }
    }

    fun addTodo(text: String) {
        if (text.isNotBlank()) {
            val newList = _todoList.value + TodoItem(nextId++, text)
            _todoList.value = newList
            saveTasks()
        }
    }

    fun toggleTodo(id: Int) {
        _todoList.value = _todoList.value.map {
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }
        saveTasks()
    }

    fun removeTodo(id: Int) {
        _todoList.value = _todoList.value.filter { it.id != id }
        saveTasks()
    }

    private fun saveTasks() {
        viewModelScope.launch {
            repository.saveTasks(_todoList.value.map { it.text })
        }
    }
}
