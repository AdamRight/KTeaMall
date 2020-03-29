package com.ktea.goods.service.impl

import com.ktea.base.ext.convert
import com.ktea.goods.data.protocol.Category
import com.ktea.goods.data.repository.CategoryRepository
import com.ktea.goods.service.CategoryService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/29.
 */
class CategoryServiceImpl  @Inject constructor() : CategoryService {

    @Inject
    lateinit var respository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return respository.getCategory(parentId).convert()
    }
}