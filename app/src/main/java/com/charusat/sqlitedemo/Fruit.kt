package com.charusat.sqlitedemo

data class Fruit(var Fr_Name:String,var Fr_Des: String,var Fr_price:Int)
{
    var id:Int=0
    constructor(id:Int,Fr_Name: String,Fr_Des: String,Fr_price: Int)
    :this(Fr_Name,Fr_Des,Fr_price)
    {
        this.id=id
    }
}