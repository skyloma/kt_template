package xui

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

inline fun <reified T : Any> String.getObject(): T? {


    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, T::class.java)

}
inline fun <reified T : Any> JsonElement.getObject(): T? {


    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, T::class.java)

}

inline fun <reified T : Any> String.getList(): List<T> {

    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, object: TypeToken<List<T>>() {}.type)

}

inline fun <reified T : Any> JsonElement.getList(): List<T>  {

    return  GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, object: TypeToken<List<T>>() {}.type)


}
