package db

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by loma on 2018/2/26.
 */
@Entity
class Customer {
    @Id(assignable = true)
    var id: Long = 0
    var name:String?=null
    @Backlink
    lateinit var orders: List<Order>
}