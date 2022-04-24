package com.charusat.sqlitedemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInsert.setOnClickListener {
            var name=edtFrName.text.toString()
            var des=edtFrDes.text.toString()
            var price=edtFrPrice.text.toString().toInt()
            var fruit=Fruit(name,des,price)
            var db=DBHelper(this)   //This Line :  Databace and table Are Created
            var flag = db.Insert(fruit)
            if (flag)
            {
                Toast.makeText(this,"Record Inserted Successfully",Toast.LENGTH_LONG).show()
            }
        }
        btnViewAll.setOnClickListener {
            var intent = Intent(this,ViewAll::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.newmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id=item.itemId
        if(id.equals(R.id.menuLogout))
        {
            var prefer:SharedPreferences=getSharedPreferences("mypref", MODE_PRIVATE)
            var pr_edit=prefer.edit()
            pr_edit.clear()
            pr_edit.commit()

            var intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}