package com.ktea.goods.service

import com.ktea.goods.data.protocol.Category
import io.reactivex.Observable

/**
 * Created by jiangtea on 2020/3/29.
 */
interface CategoryService {
    fun getCategory(parentId: Int): Observable<MutableList<Category>?>

}