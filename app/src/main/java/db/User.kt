package db

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Transient
import io.objectbox.relation.ToOne

@Entity
class User {

    var role: Long? = null
    var icon: String? = null
    var name: String? = null
    var tel: String? = null
    @Id(assignable = true)
    var id: Long = 0
    var avatar: String? = null
    var department: String? = null
    var job: String? = null
    var pwd: String? = null
    var email: String? = null
    var username: String? = null


}

