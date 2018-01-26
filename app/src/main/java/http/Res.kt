package http

import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * Created by loma on 2018/1/18.
 */

data class Res  constructor( var Code: Int  ,
                            var Msg: String?  ,
                            var Content: JsonElement? ) {
    constructor() : this(0,"",null)

//    override fun toString(): String {
//        return "{\"Code\":${Code},\"Content\": ${Content},\"msg\":\"${Msg}\"}"
//    }

}
