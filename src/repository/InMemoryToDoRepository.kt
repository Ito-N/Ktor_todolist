package com.practice.repository

import com.practice.entities.ToDo

class InMemoryToDoRepository : ToDoRepository {

    private val todos = listOf<ToDo>(
        ToDo(1, "Plan content for video #2", true),
        ToDo(2, "Record video #2", true),
        ToDo(3, "Upload video #2", true)
    )

    override fun getAllToDos(): List<ToDo> {
        return todos
    }

    override fun getToDo(id: Int): ToDo? {
        return todos.firstOrNull { it.id == id }
    }


}