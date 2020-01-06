package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

/**
 * Created by loma on 2018/2/26.
 */
@Entity
class Order {
    @Id(assignable = true)
    var id: Long = 0
    var name:String?=null
    lateinit var customer: ToOne<Customer>
}