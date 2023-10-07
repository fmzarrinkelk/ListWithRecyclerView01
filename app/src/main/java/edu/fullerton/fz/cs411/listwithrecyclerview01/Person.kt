package edu.fullerton.fz.cs411.listwithrecyclerview01

import java.util.UUID

data class Person(val id: UUID = UUID.randomUUID()) {
    var name: String = ""
    var age: Int = 0
    var isStudent: Boolean = false
}