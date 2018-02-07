package db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class Project {
    @Id(assignable = true)
    var id: Long = 0
    var date: String? = null
    var vesion: String? = null
    var endDate: String? = null
    var android: Boolean = false
    var icon: String? = null
    var description: String? = null
    var testURL: String? = null
    var type: String? = null
    var ios: Boolean = false
    var devURL: String? = null
    var pc: Boolean = false
    var name: String? = null
    var progress: Int = 0


    var src_vcs: String? = null
}
