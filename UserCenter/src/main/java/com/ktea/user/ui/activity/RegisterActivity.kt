package com.ktea.user.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ktea.user.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            btnRegister.text = "注册成功啦"
        }
    }
}
