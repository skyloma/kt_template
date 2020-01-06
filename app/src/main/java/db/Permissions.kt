package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

class Permissions {
    var name: String? = null
    var description: String? = null
    var action: String? = null
    var id: Long = 0
    var category: String? = null
}