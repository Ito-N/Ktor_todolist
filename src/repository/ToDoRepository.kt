package com.practice.repository

import com.practice.entities.ToDo

interface ToDoRepository {

    fun getAllToDos(): List<ToDo>

    fun getToDo(id: Int): ToDo?

}