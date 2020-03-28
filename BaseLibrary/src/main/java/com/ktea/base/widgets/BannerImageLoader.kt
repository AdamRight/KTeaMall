package com.ktea.base.widgets

import android.content.Context
import android.widget.ImageView
import com.ktea.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * Created by jiangtea on 2020/3/28.
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        GlideUtils.loadUrlImage(context!!, path.toString(), imageView!!)
    }
}