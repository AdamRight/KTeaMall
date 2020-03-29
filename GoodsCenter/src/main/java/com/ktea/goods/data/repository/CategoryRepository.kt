package com.ktea.goods.data.repository

import com.ktea.base.data.net.RetrofitFactory
import com.ktea.base.data.protocol.BaseResp
import com.ktea.goods.data.api.CategoryApi
import com.ktea.goods.data.protocol.Category
import com.ktea.goods.data.protocol.GetCategoryReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/29.
 */
class CategoryRepository  @Inject constructor() {

    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java)
                .getCategory(GetCategoryReq(parentId))
    }
}