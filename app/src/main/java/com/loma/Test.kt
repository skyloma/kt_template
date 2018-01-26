package com.loma

fun main(args: Array<String>) {
    val user = User("Kotlin", 1, "1111111")

    val result = user.run {
        println("my name is $name, I am $age years old, my phone number is $phoneNum")
        1000
    }
    println("result: $result")
}
data class User constructor(var name:String,var age:Int,var phoneNum:String)