package com.loma

import com.loma.project.model.Project
import org.junit.Test

import org.json.JSONObject
import xui.getList


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

     var j=   JSONObject("{\"Code\":0,\"Content\":[{\"id\":0,\"date\":\"2017-09-30 17:05:13\",\"description\":\"1.项目经理=>账号密码(l，123456 )建立项目\\n2.项目经理根据客户需求，生成功能模块，\",\"icon\":\"upload/1507617741357QQ图片20171010144205.png\",\"name\":\"项目管理平台\",\"progress\":0,\"vesion\":\"1.0\",\"type\":\"PC\",\"testURL\":\"http://192.168.1.108:8081\",\"devURL\":\"\",\"pc\":false,\"android\":false,\"ios\":false,\"endDate\":\"2017-11-30T16:00:00.000Z\",\"src_vcs\":null},{\"id\":1,\"date\":\"2017-09-26 12:15:22\",\"description\":\"物业管理平台\",\"icon\":\"upload/150762805172902-切图.jpg\",\"name\":\"物业云物业版\",\"progress\":1,\"vesion\":\"1.0\",\"type\":\"本公司项目\",\"testURL\":\"http://localhost:8081\",\"devURL\":\"http://localhost:8080\",\"pc\":true,\"android\":true,\"ios\":true,\"endDate\":\"2018-10-24T16:00:00.000Z\",\"src_vcs\":null}],\"msg\":null}")

      var list =  j.get("Content").toString().getList<Project>()

        println(list.get(0).name )

        var data: MutableList<Project> = mutableListOf()
data.addAll(list)

        println(data.get(0).name )
    }
}