package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by loma on 2018/2/7.
 */
class Department {

    @Id
    var id: Long = 0
    var description: String? = null
    var name: String? = null
}
