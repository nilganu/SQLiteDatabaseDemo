package com

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(val context:Context):
    SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {

        companion object{
            private var DB_NAME="PersonDB"
            private var DB_VERSION=1
            private var TB_NAME="Person"
            private var ID="id"
            private var NAME="name"
            private var AGE="age"
        }

    override fun onCreate(p0: SQLiteDatabase?) {
        var query="CREATE TABLE $TB_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NAME TEXT, $AGE INTEGER)"
        p0?.execSQL(query)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var query="DROP TABLE $TB_NAME IF EXISTS"
        p0?.execSQL(query)
        onCreate(p0)

    }
    fun insert(p:Person):Long
    {
        var db=writableDatabase
        var cv=ContentValues()
        cv.put(NAME,p.name)
        cv.put(AGE,p.age)
        var flag=db.insert(TB_NAME,null,cv)
        return flag
    }
    fun retriveAll():ArrayList<Person>
    {
        var arr=ArrayList<Person>()
        var db=readableDatabase
        var cursor=db.query(TB_NAME,null,null,null,null,null,null)
        while(cursor.moveToNext())
        {

            var id=cursor.getInt(0)
            var name=cursor.getString(1)
            var age=cursor.getInt(2)
            var p=Person(id,name,age)
            arr.add(p)
        }
        return arr

    }
    fun delete(id:Int)
    {
        var db=writableDatabase
        db.delete(TB_NAME,"$ID=$id",null)
        db.close()
    }
    fun update(p:Person)
    {
        var db=writableDatabase
        var cv=ContentValues()
        cv.put(NAME,p.name)
        cv.put(AGE,p.age)
        var flag=db.update(TB_NAME,cv,"$ID=${p.id}",
            null)
        db.close()
    }
}