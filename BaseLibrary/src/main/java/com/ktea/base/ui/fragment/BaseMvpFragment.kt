package com.ktea.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktea.base.common.BaseApplication
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.base.injection.component.DaggerActivityComponent
import com.ktea.base.injection.module.ActivityModule
import com.ktea.base.injection.module.LifecycleProviderModule
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.presenter.view.BaseView
import com.ktea.base.widgets.ProgressLoading
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

open abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    lateinit var mActivityComponent: ActivityComponent
    //Presenter泛型，Dagger注入
    @Inject
    lateinit var mPresenter: T

    lateinit var mProgressLoading: ProgressLoading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initActivityInjection()
        injectComponent()
        mProgressLoading = ProgressLoading.create(activity!!)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * Dagger注册
     */
    protected abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent
                .builder()
                .appComponent((activity?.application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(activity!!))
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