package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity   class Role {
    var valid: Boolean = false
    var parent: String? = null
    var eName: String? = null
    var permissions: ToMany<Permissions>? = null
    var name: String? = null
    @Id(assignable = true)
    var id: Long = 0
    var menus: ToMany<Menus>? = null


}
