package com.charusat.sqlitedemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        btnLogin.setOnClickListener{
            var u_name=edtLoginUserName.text.toString()
            var password=edtLoginPass.text.toString()
            if(u_name=="admin" || password=="admin")
            {
                var shredpref:SharedPreferences=getSharedPreferences("mypref", MODE_PRIVATE)
                var prefedit=shredpref.edit()
                prefedit.putString("username",u_name)
                prefedit.commit()//save

                //main activity
               var intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }
}