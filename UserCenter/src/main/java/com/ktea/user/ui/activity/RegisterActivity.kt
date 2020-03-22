package com.ktea.user.ui.activity

import android.os.Bundle
import com.ktea.base.ui.activity.BaseMvpActivity
import com.ktea.user.R
import com.ktea.user.presenter.RegisterPresenter
import com.ktea.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onError(error: String) {
        toast(error)
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ankoDemo()
        mvpDemo()

    }

    /**
     * mvp的使用
     */
    private fun mvpDemo() {
        mPresenter = RegisterPresenter()
        mPresenter.mView = this

        btnRegister3.setOnClickListener {
            //Anko
            mPresenter.register("", "", "")
        }
    }

    /**
     * Anko的使用
     */
    private fun ankoDemo() {
        btnRegister1.setOnClickListener {
            //Anko
            //startActivity(intentFor<AnkoLayoutActivity>("id" to 8))
        }

        btnRegister2.setOnClickListener {
            //Anko
            //startActivity<AnkoLayoutActivity>("id" to 15)
        }
    }

}
