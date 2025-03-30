package com.example.to_dolistusingvmandroom.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// ✅ Create DataStore instance
private val Context.dataStore by preferencesDataStore(name = "todo_prefs")

class TodoRepository(private val context: Context) {
    private val TODO_KEY = stringPreferencesKey("todo_list")

    // ✅ Load saved tasks as a list of strings
    val todoList: Flow<List<String>> = context.dataStore.data.map { preferences ->
        preferences[TODO_KEY]?.split("|") ?: emptyList()
    }

    // ✅ Save tasks in DataStore
    suspend fun saveTasks(tasks: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[TODO_KEY] = tasks.joinToString("|")
        }
    }
}
