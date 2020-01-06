package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Relation

@Entity class Cname {
    @Id   var id: Long = 0
     var  name:String?=null
    var cnameId: Long  = 0
}

