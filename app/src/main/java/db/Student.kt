package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Relation

@Entity data class Student(@Id(assignable = true) var id: Long, var name: String?)