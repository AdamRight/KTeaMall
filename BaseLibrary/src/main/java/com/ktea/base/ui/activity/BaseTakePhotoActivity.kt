package com.ktea.base.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.ktea.base.common.BaseApplication
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.base.injection.component.DaggerActivityComponent
import com.ktea.base.injection.module.ActivityModule
import com.ktea.base.injection.module.LifecycleProviderModule
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.presenter.view.BaseView
import com.ktea.base.utils.DateUtils
import com.ktea.base.widgets.ProgressLoading
import org.devio.takephoto.app.TakePhoto
import org.devio.takephoto.app.TakePhotoImpl
import org.devio.takephoto.compress.CompressConfig
import org.devio.takephoto.model.TResult
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/28.
 */
abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView,
        TakePhoto.TakeResultListener {

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File

    @Inject
    lateinit var mPresenter: T

    private lateinit var mLoadingDialog: ProgressLoading
    lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

        mLoadingDialog = ProgressLoading.create(this)
    }

    protected abstract fun injectComponent()


    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }


    override fun showLoading() {
        mLoadingDialog.showLoading()
    }


    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }


    override fun onError(error: String) {
        toast(error)
    }


    protected fun showAlertView() {
        AlertView("选择图片", null, "取消", null, arrayOf("拍照", "相册"),
                this, AlertView.Style.ActionSheet,
                OnItemClickListener { o, position ->
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                    when (position) {
                        0 -> {
                            createTempFile()
                            //TODO 需要权限
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }
                        1 -> {
                            mTakePhoto.onPickFromGallery()
                        }
                    }
                })
                .show()
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto", result?.image?.compressPath)
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("takePhoto", msg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }
}