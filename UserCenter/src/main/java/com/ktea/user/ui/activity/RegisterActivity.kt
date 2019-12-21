package com.ktea.user.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ktea.user.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister1.setOnClickListener {
            //Anko
            startActivity(intentFor<AnkoLayoutActivity>("id" to 8))
        }

        btnRegister2.setOnClickListener {
            //Anko
            startActivity<AnkoLayoutActivity>("id" to 15)
        }

    }
}
