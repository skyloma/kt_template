package db


    class Role {
    var valid: Boolean = false
    var parent: String? = null
    var eName: String? = null
    var permissions: List<Permissions>? = null
    var name: String? = null

    var id: Long = 0
    var menus: List<Menus>? = null


}
