package com.charusat.sqlitedemo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_view_all.*
import kotlinx.android.synthetic.main.custom_dialogbox.*

class ViewAll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        updateRecyclerView()
    }

    public fun delete(position:Int){
        var db=DBHelper(this)
        var arr=db.retriveAll()
        var fruit=arr.get(position)
        var id=fruit.id
        var flag=db.delete(id)
        if(flag)
            Toast.makeText(this,"Record Deleted !",Toast.LENGTH_LONG).show()
            updateRecyclerView()
    }

    public fun update(position: Int){
        var db=DBHelper(this)
        var arr=db.retriveAll()
        var fruit=arr.get(position)
        var id=fruit.id
        var name=fruit.Fr_Name
        var des=fruit.Fr_Des
        var price=fruit.Fr_price
        var dialog=Dialog(this)
        dialog.setContentView(R.layout.custom_dialogbox)
        dialog.tvID.setText(id.toString())
        dialog.edtdialogName.setText(name)
        dialog.edtdialogDes.setText(des)
        dialog.edtDialogPrice.setText(price.toString())
        dialog.btndialogUpdate.setOnClickListener({
            var id=dialog.tvID.text.toString().toInt()
            var name=dialog.edtdialogName.text.toString()
            var des=dialog.edtdialogDes.text.toString()
            var price=dialog.edtDialogPrice.text.toString().toInt()
            var fruit=Fruit(id,name,des,price)
            var flag=db.update(fruit)
            if (flag)
            {
                Toast.makeText(this,"Record Updated..",Toast.LENGTH_LONG).show()
                dialog.dismiss()
                updateRecyclerView()
            }

        })
        dialog.show()

    }

    private fun updateRecyclerView() {
       var db=DBHelper(this)
       var arr=db.retriveAll()
       var fruitadapter=FruitAdapter(this,arr)
        myrecycle.adapter=fruitadapter
    }
}