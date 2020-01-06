package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class Teacher constructor( @Id(assignable = true)   var id: Long, var name: String,var students:  List<Student>? ){

}
