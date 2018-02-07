package xui

import com.google.gson.*
import com.google.gson.reflect.TypeToken

inline fun <reified T : Any> String.getObject(): T? {


    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, T::class.java)

}

inline fun <reified T : Any> JsonElement.getObject(): T? {


    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, T::class.java)

}

inline fun <reified T : Any> String.getList(): List<T> {
    val json = JsonObject()
    val jsonArray = json.getAsJsonArray(this)
    val list = ArrayList<T>()
    if (jsonArray.size() > 0) {
        var gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        jsonArray.forEach {
            list.add(gson.fromJson(it, T::class.java))
        }
    }


    return list

}

inline fun <reified T : Any> JsonElement.getList(): List<T> {


    val list = ArrayList<T>()
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
    if (this is JsonArray) {
        this.forEach {
            list.add(gson.fromJson(it, T::class.java))
        }
    }

    return list


}

inline fun Any.toJson(): String {

    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(this)


}
