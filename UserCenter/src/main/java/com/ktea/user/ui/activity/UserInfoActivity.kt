package com.ktea.user.ui.activity

import android.os.Bundle
import android.util.Log
import com.ktea.base.common.BaseConstant
import com.ktea.base.ext.onClick
import com.ktea.base.ui.activity.BaseTakePhotoActivity
import com.ktea.base.utils.AppPrefsUtils
import com.ktea.base.utils.GlideUtils
import com.ktea.provider.common.ProviderConstant
import com.ktea.user.R
import com.ktea.user.data.protocol.UserInfo
import com.ktea.user.injection.component.DaggerUserComponent
import com.ktea.user.presenter.UserInfoPresenter
import com.ktea.user.presenter.view.UserInfoView
import com.ktea.user.utils.UserPrefsUtils
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.devio.takephoto.model.TResult
import org.jetbrains.anko.toast

/**
 * Created by jiangtea on 2020/3/28.
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView {

    private val mUploadManager: UploadManager by lazy { UploadManager() }
    private var mLocalFileUrl: String? = null
    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
        initData()
    }

    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }
        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!,
                    mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString() ?: ""
            )
        }
    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon

        if (mUserIcon != "") {
            GlideUtils.loadImage(this, mUserIcon!!, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile
        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun takeSuccess(result: TResult?) {
        super.takeSuccess(result)
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    /**
     *   获取上传凭证回调
     */
    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl, null, result, { key, info, response ->
            mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
            Log.d("test", mRemoteFileUrl)
            GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
        }, null)
    }

    /**
      *  编辑用户资料回调
     */
    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }
}