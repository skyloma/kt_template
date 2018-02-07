package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class Permissions {
    var name: String? = null
    var description: String? = null
    var action: String? = null
    @Id(assignable = true)
    var id: Long = 0
    var category: String? = null
}