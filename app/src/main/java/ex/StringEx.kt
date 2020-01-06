package xui

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

inline fun <reified  T:Any > String.getObject( ): T {
    val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ,Locale.CHINA)
    return ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setDateFormat( fmt).readValue<T>(this,T::class.java)


}



inline fun <reified T : Any> String.getList(): List<T> {
    val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ,Locale.CHINA)
    val json = JSONArray(this)
    var data: MutableList<T> = mutableListOf()
    if (json.length()>0){

        //until半闭区间运算符 从a到b范围内所有的值,包括a和不包括b
        for (i in 0 until json.length()) {
            val job = json.get(i)  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            data.add(job.toString().getObject())
        }
    }


    return data


//    return ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setDateFormat(fmt).readValue<List<T>>(this,object :TypeReference<List<T>>(){})





}






inline fun Any.toJson(): String {

    if (this is JSONObject){
        return   this.toString()
    }else{
        val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        return ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setDateFormat(fmt).writeValueAsString(this)
    }




}
