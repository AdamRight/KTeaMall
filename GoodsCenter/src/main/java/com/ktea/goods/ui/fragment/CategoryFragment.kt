package com.ktea.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.ktea.base.ext.startLoading
import com.ktea.base.ui.adapter.BaseRecyclerViewAdapter
import com.ktea.base.ui.fragment.BaseMvpFragment
import com.ktea.goods.R
import com.ktea.goods.common.GoodsConstant
import com.ktea.goods.data.protocol.Category
import com.ktea.goods.injection.component.DaggerCategoryComponent
import com.ktea.goods.injection.module.CategoryModule
import com.ktea.goods.presenter.CategoryPresenter
import com.ktea.goods.presenter.view.CategoryView
import com.ktea.goods.ui.activity.GoodsActivity
import com.ktea.goods.ui.adapter.SecondCategoryAdapter
import com.ktea.goods.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * Created by jiangtea on 2020/3/29.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()

        inventDataParent()
        inventDataItem(0)
    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context!!)
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()
                loadData(item.id)

                inventDataItem(position % 2)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsActivity>(Pair(GoodsConstant.KEY_CATEGORY_ID, item.id))
            }
        })
    }


    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }
        mPresenter.getCategory(parentId)
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder()
                .activityComponent(mActivityComponent)
                .categoryModule(CategoryModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /**
     * 获取商品分类回调
     */
    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.visibility = View.VISIBLE
                mCategoryTitleTv.visibility = View.VISIBLE
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.visibility = View.GONE
            mCategoryTitleTv.visibility = View.GONE
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /*********************************************************** 虚拟数据  开始 ***********************************************************/
    private fun inventDataParent() {
        var category1 = Category(1, "电脑办公", "", 0, false)
        var category2 = Category(2, "手机数码", "", 0, false)
        var category3 = Category(3, "男装", "", 0, false)
        var category4 = Category(4, "女装", "", 0, false)
        var category5 = Category(5, "家用电器", "", 0, false)
        var category6 = Category(6, "食品生鲜", "", 0, false)
        var category7 = Category(7, "酒水饮料", "", 0, false)
        var category8 = Category(8, "玩具乐器", "", 0, false)
        var category9 = Category(9, "汽车用品", "", 0, false)
        var category10 = Category(10, "家具家装", "", 0, false)
        var category11 = Category(11, "礼品鲜花", "", 0, false)
        var category12 = Category(12, "生活旅行", "", 0, false)
        var category13 = Category(13, "二手商品", "", 0, false)

        var category14 = Category(14, "办公打印", "", 0, false)
        var category15 = Category(15, "清洁用品", "", 0, false)
        var category16 = Category(16, "保鲜收纳", "", 0, false)
        var category17 = Category(17, "宠物用品", "", 0, false)
        var category18 = Category(18, "床上用品", "", 0, false)
        var category19 = Category(19, "车载电器", "", 0, false)
        var category20 = Category(20, "厨卫大电", "", 0, false)
        var category21 = Category(21, "时尚女包", "", 0, false)

        var result: MutableList<Category> = mutableListOf(category1, category2, category3, category4, category5, category6, category7,
                category8, category9, category10, category11, category12, category13,
                category14, category15, category16, category17, category18, category19, category20, category21)
        onGetCategoryResult(result)
    }

    private fun inventDataItem(position: Int) {

        var category14 = Category(14, "Apple", "https://img13.360buyimg.com/n7/jfs/t2590/338/4222589387/237425/25e40fb/57b12ac6N61374a75.jpg", 1, false)
        var category15 = Category(15, "ThinkPad", "https://img14.360buyimg.com/n7/jfs/t3556/223/2158676156/110226/59267230/584b5678Nbc9f1e70.jpg", 1, false)
        var category16 = Category(16, "惠普", "https://img10.360buyimg.com/n7/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg", 1, false)
        var category17 = Category(17, "戴尔", "https://img14.360buyimg.com/n7/jfs/t5971/255/1793524379/148460/f42e1432/59362fc2Nf55191b9.jpg", 1, false)
        var category18 = Category(18, "华硕", "https://img12.360buyimg.com/n7/jfs/t5878/352/2479795637/201591/d23a1872/5931182fN31cfa389.jpg", 1, false)
        var category19 = Category(19, "神舟", "https://img13.360buyimg.com/n7/jfs/t3052/126/5163722736/217313/f05d9003/5864c7dfNcfbc5e94.jpg", 1, false)
        var category20 = Category(20, "外星人", "https://img11.360buyimg.com/n7/jfs/t4687/130/1245474410/58260/f12a15bd/58db17dbNf5371a02.jpg", 1, false)
        var category21 = Category(21, "微星", "https://img12.360buyimg.com/n7/jfs/t6709/106/1129266372/226149/ab5f4641/594b8094Nb07793ab.jpg", 1, false)
        var category22 = Category(22, "宏基", "https://img11.360buyimg.com/n7/jfs/t5737/312/4822081047/162369/70bbd1b2/5954b785N1787db72.jpg", 1, false)
        var category23 = Category(23, "雷神", "https://img11.360buyimg.com/n7/jfs/t5671/2/2653805329/277901/cf0384f7/5932281fNeb08da02.jpg", 1, false)


        var category24 = Category(24, "Apple", "https://img14.360buyimg.com/n7/jfs/t3268/124/2646283367/114153/f5704b88/57e4a358N9ccc6645.jpg", 2, false)
        var category25 = Category(25, "华为", "https://img10.360buyimg.com/n7/jfs/t5890/341/1320350439/127171/2f9c4ddd/592535e0N2e102c09.jpg", 2, false)
        var category26 = Category(26, "小米", "https://img14.360buyimg.com/n7/jfs/t5215/252/15502760/100416/cb06f1da/58f709adN45511018.jpg", 2, false)
        var category27 = Category(27, "魅族", "https://img10.360buyimg.com/n7/jfs/t4366/71/2045605853/291379/56c87b03/58ca4dc5N1c303706.jpg", 2, false)
        var category28 = Category(28, "三星", "https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg", 2, false)
        var category29 = Category(29, "OPPO", "https://img10.360buyimg.com/n7/jfs/t5785/24/2243796048/134801/923e11/592ea14fNec6d33c4.jpg", 2, false)
        var category30 = Category(30, "vivo", "https://img11.360buyimg.com/n7/jfs/t5998/69/1052614141/116889/2f5ba58a/592f8ed9N49d8f07b.jpg", 2, false)
        var category31 = Category(31, "HTC", "https://img13.360buyimg.com/n7/jfs/t5659/277/3541677944/291221/28bb44f8/593e10c9Nc3783014.jpg", 2, false)
        var category32 = Category(32, "摩托罗拉", "https://img12.360buyimg.com/n7/jfs/t3109/185/1064081632/117451/2dba5e92/57c558e2N38a9e617.jpg", 2, false)
        var category33 = Category(33, "索尼", "https://img10.360buyimg.com/n7/jfs/t5191/190/2535818793/70090/78c559f5/591ba9f0Nd3a41fcb.jpg", 2, false)

        var result: MutableList<Category> = if (position == 0)
            mutableListOf(category14, category15, category16, category17, category18, category19, category20, category21, category22, category23,
                    category24, category25, category26, category27, category28, category29, category30, category31, category32, category33)
        else
            mutableListOf(category24, category25, category26, category27, category28, category29, category30, category31, category32, category33,
                    category14, category15, category16, category17, category18, category19, category20, category21, category22, category23)
        onGetCategoryResult(result)
    }

    /*********************************************************** 虚拟数据 结束  ***********************************************************/
}