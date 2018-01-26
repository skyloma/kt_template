package data

import java.io.Serializable
import java.util.Date

/**
 * Created by loma on 2018/1/19.
 */

data class Project constructor(var id: Int?, var date: Date?,
                               var description: String?,
                               var icon: String?,
                               var name: String?,
                               var progress: Int = 0,
                               var vesion: String?,
                               var type: String?,
                               var testURL: String?,
                               var devURL: String?,
                               var isPc: Boolean = false,
                               var isAndroid: Boolean = false,
                               var isIos: Boolean = false,
                               var endDate: Date?,
                               var src_vcs: String?) {
    constructor():this(0,null,null,null,null,0,null,null,null,null,false,false,false,null,null)




}
