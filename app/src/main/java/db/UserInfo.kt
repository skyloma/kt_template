package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class UserInfo (@Id   var id: Long=0){
    var name: String?=null
    var age: Int = 0
    constructor():this(0)


}



