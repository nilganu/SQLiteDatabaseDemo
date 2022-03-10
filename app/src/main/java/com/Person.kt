package com

data class Person (var name:String, var age:Int)
{
    var id=0
    constructor(id:Int,name:String,age:Int):this(name,age)
    {
        this.id=id
    }
}