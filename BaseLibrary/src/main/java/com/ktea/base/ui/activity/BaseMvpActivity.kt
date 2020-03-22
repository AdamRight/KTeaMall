package com.ktea.base.ui.activity

import android.os.Bundle
import com.ktea.base.common.BaseApplication
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.base.injection.component.DaggerActivityComponent
import com.ktea.base.injection.module.ActivityModule
import com.ktea.base.injection.module.LifecycleProviderModule
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.presenter.view.BaseView
import com.ktea.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by jiangtea on 2019/12/21.
 */
open abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mActivityComponent: ActivityComponent

    //Presenter泛型，Dagger注入
    @Inject
    lateinit var mPresenter: T

    lateinit var mProgressLoading: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
        //初始化加载框
        mProgressLoading = ProgressLoading.create(this)
        //ARouter注册
        //ARouter.getInstance().inject(this)
    }

    /**
     * Dagger注册
     */
    protected abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {
        mProgressLoading.showLoading()
    }

    override fun hideLoading() {
        mProgressLoading.hideLoading()
    }

    override fun onError(error: String) {
        toast(error)
    }


}