package com.ktea.goods.data.api

import com.ktea.base.data.protocol.BaseResp
import com.ktea.goods.data.protocol.Category
import com.ktea.goods.data.protocol.GetCategoryReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by jiangtea on 2020/3/29.
 */
interface CategoryApi {
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}