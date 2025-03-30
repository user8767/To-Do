package com.example.to_dolistusingvmandroom.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class TodoItem(val id: Int, val text: String, val isDone: Boolean = false)
class TodoViewModel : ViewModel(){
    private var _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
val todoList: StateFlow<List<TodoItem>> = _todoList

    private var nextId = 1

    fun addTodo(text: String){
        if(text.isNotBlank()){
            _todoList = _todoList.value + TodoItem(nextId++, text)
        }
    }



    fun toogleTodo(id: Int){
        _todoList.value = _todoList.value.map{
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }
    }
fun removeTodo(id: Int){
    _todoList.value = _todoList.value.filter { it.id != id }
}




}
