package db

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany


@Entity   class Menus {

    var name: String? = null
    var icon: String? = null
    @Id(assignable = true)
    var id: Long = 0
    var sort: Int = 0
    var href: String? = null
    var parentId: String? = null
    var isShow: Boolean? = null
}

