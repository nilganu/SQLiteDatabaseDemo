package com.cahrusat.sqlitedemodiv1

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.DBHelper
import com.Person
import com.PersonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refreshRecycler()
        btnAdd.setOnClickListener {
            var name=edtName.text.toString()

            var age=edtAge.text.toString().toInt()
            var p= Person(name,age)
            var db=DBHelper(this)
            var flag=db.insert(p)
            if(flag>0)
            {
                Toast.makeText(this,"Record Inserted!!",
                    Toast.LENGTH_LONG).show()
                edtName.setText("")
                edtAge.setText("")
                refreshRecycler()
            }

        }

    }

    fun refreshRecycler()
    {
        var db=DBHelper(this)
        var arr:ArrayList<Person> = db.retriveAll()
        var adapter=PersonAdapter(this,arr)
        myrecycle.adapter=adapter
    }
    fun delete(position:Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Person> = db.retriveAll()
        var person=arr.get(position)
        db.delete(person.id)
        refreshRecycler()
    }
    fun update(position: Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Person> = db.retriveAll()
        var person=arr.get(position)
        var dialog=Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.txtUpadateId.setText(person.id.toString())
        dialog.edtUpadeName.setText(person.name)
        dialog.edtUpdateAge.setText(person.age.toString())
        dialog.btnUpdate.setOnClickListener {
            var id=dialog.txtUpadateId.text.toString().toInt()
            var name=dialog.edtUpadeName.text.toString()
            var age=dialog.edtUpdateAge.text.toString().toInt()
            var p=Person(id,name,age)
            db.update(p)
            dialog.dismiss()
            refreshRecycler()
        }
        dialog.show()
    }
}