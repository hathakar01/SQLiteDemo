package com.charusat.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object{
        private var DB_NAME ="FruitDB"
        private var TB_NAME ="FruitDetails"
        private var DB_VERSION =1
        private var FR_NAME ="Fr_Name"
        private var FR_ID = "id"
        private var FR_PRICE = "Fr_price"
        private var FR_DES = "Fr_Des"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var query = "Create Table $TB_NAME ($FR_ID INTEGER PRIMARY KEY AUTOINCREMENT, $FR_NAME TEXT, $FR_PRICE INTEGER,$FR_DES TEXT)"
        p0?.execSQL(query);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var query = "Drop Table $TB_NAME If Exists"
        p0?.execSQL(query);
    }
    fun Insert(fruit: Fruit):Boolean {
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(FR_NAME, fruit.Fr_Name)
        cv.put(FR_PRICE, fruit.Fr_price)
        cv.put(FR_DES, fruit.Fr_Des)
        var flag = db.insert(TB_NAME, null, cv)
        return flag > 0

    }
    fun retriveAll():ArrayList<Fruit>
    {
        var db = readableDatabase
        var cursor=db.query(TB_NAME,null,null,null,null,null,null,null)
        var arr= arrayListOf<Fruit>()  //Create Fruit Array
        while (cursor.moveToNext())
        {
            var id=cursor.getInt(0)                 //Itrected record 1 by 1
            var name=cursor.getString(1)
            var price=cursor.getInt(2)
            var des=cursor.getString(3)
            var fruit=Fruit(id,name,des,price)   // Friut object
            arr.add(fruit)      // add friut obj of array list
        }
        return arr
    }
    fun delete(id:Int):Boolean{
            var db=readableDatabase
            var flag=db.delete(TB_NAME,"$FR_ID=$id",null)
            if (flag>0)
                return true
            else
                return false
    }
    fun update(fruit: Fruit):Boolean{
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(FR_NAME, fruit.Fr_Name)
        cv.put(FR_DES, fruit.Fr_Des)
        cv.put(FR_PRICE, fruit.Fr_price)
        var flag=db.update(TB_NAME,cv,"$FR_ID=${fruit.id}",null)
        return flag > 0
    }
}