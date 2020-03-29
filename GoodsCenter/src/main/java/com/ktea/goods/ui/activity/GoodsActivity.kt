package com.ktea.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.ktea.base.ext.startLoading
import com.ktea.base.ui.activity.BaseMvpActivity
import com.ktea.base.ui.adapter.BaseRecyclerViewAdapter
import com.ktea.goods.R
import com.ktea.goods.common.GoodsConstant
import com.ktea.goods.data.protocol.Goods
import com.ktea.goods.data.protocol.GoodsSku
import com.ktea.goods.injection.component.DaggerGoodsComponent
import com.ktea.goods.injection.module.GoodsModule
import com.ktea.goods.presenter.GoodsListPresenter
import com.ktea.goods.presenter.view.GoodsListView
import com.ktea.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*
import kotlinx.android.synthetic.main.fragment_category.mMultiStateView
import org.jetbrains.anko.startActivity

/**
 * Created by jiangtea on 2020/3/29.
 */
class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private lateinit var mGoodsAdapter: GoodsAdapter
    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter

        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods> {
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(Pair(GoodsConstant.KEY_GOODS_ID, item.id))
            }
        })
    }

    /**
     * 初始化刷新视图
     */
    private fun initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this)
        //设置下拉刷新和上拉加载更多的风格   参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        // 设置整个加载更多控件的背景颜色资源 id
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        // 设置下拉刷新控件的背景颜色资源 id
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), mCurrentPage)
        } else {
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)
        }

        inventGoodsListResult()
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(mActivityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /**
     * 获取列表后回调
     */
    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1) {
                mGoodsAdapter.setData(result)
            } else {
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /**
     *上拉加载更多
     */
    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            true
        } else {
            false
        }
    }

    /**
     *下拉加载第一页
     */
    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

    /*********************************************************** 虚拟数据  开始 ***********************************************************/
    private fun inventGoodsListResult() {
        var sku = GoodsSku(1,"", listOf("666"))
        var goodSku :List<GoodsSku> = listOf(sku)
        var goods1 = Goods(1, 14,"Apple MacBook Air 13.3英寸笔记本电脑 银色(Core i5 处理器/8GB内存/128GB SSD闪存 MMGF2CH/A)","https://img11.360buyimg.com/n7/jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg"
        ,100,"1","",222,333,"","","",goodSku,2)
        var result: MutableList<Goods> = mutableListOf(goods1,goods1,goods1,goods1,goods1,goods1,goods1,goods1,goods1,goods1)
        onGetGoodsListResult(result)
    }

    /*(1, 14, '', '', '', 'https://img11.360buyimg.com/n1/s450x450_jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg,https://img13.360buyimg.com/n1/s450x450_jfs/t2632/157/4193453761/92922/2adb5ebc/57ad88f0Nb286ec7a.jpg,https://img11.360buyimg.com/n1/s450x450_jfs/t2977/86/2412624329/68019/dbe32c1f/57ad8846N64ac3c79.jpg', 'https://img20.360buyimg.com/vc/jfs/t3034/151/748569500/226790/d6cd86a2/57b15612N81dc489d.jpg', 'https://img20.360buyimg.com/vc/jfs/t2683/60/4222930118/169462/233c7678/57b15616N1e285f09.jpg', 5000, 10000, '10000000001', '1.6GHz i5处理器,GB内存/128GB SSD,1件'),
    (2, 14, 'Apple MacBook Pro 13.3英寸笔记本电脑 银色(Core i5 处理器/8GB内存/128GB SSD闪存/Retina屏 MF839CH/A)', 'https://img13.360buyimg.com/n7/jfs/t2590/338/4222589387/237425/25e40fb/57b12ac6N61374a75.jpg', '2', 'https://img13.360buyimg.com/n1/s450x450_jfs/t2590/338/4222589387/237425/25e40fb/57b12ac6N61374a75.jpg,https://img13.360buyimg.com/n1/s450x450_jfs/t2632/157/4193453761/92922/2adb5ebc/57ad88f0Nb286ec7a.jpg,https://img11.360buyimg.com/n1/s450x450_jfs/t2977/86/2412624329/68019/dbe32c1f/57ad8846N64ac3c79.jpg', 'https://img20.360buyimg.com/vc/jfs/t2218/160/1600609450/672652/12ef3837/56600724N28581935.jpg', 'https://img20.360buyimg.com/vc/jfs/t2305/205/1525153851/648458/79706530/56600727N1022ecd8.jpg', 13530, 800, '10000000002', '13.3英寸/双核 i5/8G/128G闪存,1件'),
    (3, 14, 'Apple MacBook Pro 15.4英寸笔记本电脑 深空灰色（Multi-Touch Bar/Core i7/16GB/256GB MLH32CH/A）', 'https://img10.360buyimg.com/n7/jfs/t3499/165/739574790/179345/251c51d4/58126465Na27a9bf0.jpg', '1', 'https://img13.360buyimg.com/n1/s450x450_jfs/t2590/338/4222589387/237425/25e40fb/57b12ac6N61374a75.jpg,https://img13.360buyimg.com/n1/s450x450_jfs/t2632/157/4193453761/92922/2adb5ebc/57ad88f0Nb286ec7a.jpg,https://img11.360buyimg.com/n1/s450x450_jfs/t2977/86/2412624329/68019/dbe32c1f/57ad8846N64ac3c79.jpg', 'https://img12.360buyimg.com/cms/jfs/t3760/42/740411238/146541/be42da9e/581266b4N7360a7e4.jpg', 'https://img13.360buyimg.com/cms/jfs/t3367/226/740508746/107595/414c65e6/581266b7Nea97b88f.jpg', 1350, 8200, '10000000003', '银色,Core i5/8G内存/256G闪存,1件'),
    (4, 14, 'Apple 苹果 MacBook Air 13.3英寸笔记本电脑 MMGF2CH/A银色 Core i5/8G内存/128G闪存', 'https://img13.360buyimg.com/n1/jfs/t5821/151/4295371754/246251/29179c11/594a3eabNbf7fceec.jpg', '3.00', 'https://img13.360buyimg.com/n1/jfs/t5821/151/4295371754/246251/29179c11/594a3eabNbf7fceec.jpg,https://img13.360buyimg.com/n1/jfs/t3157/218/9378001544/103166/c8bb88da/58d0e987Nb3a34bfa.jpg,https://img13.360buyimg.com/n1/jfs/t4639/313/514186457/88273/6d83eae9/58d0e988N0009a10c.jpg', 'https://img30.360buyimg.com/popWaterMark/jfs/t5746/329/4801800135/218275/4a67f0ef/5954a40fNf471b55d.jpg', 'https://img30.360buyimg.com/popWareDetail/jfs/t6532/96/2593310/189936/1c5b2d94/5937a66aN594f4851.jpg', 2350, 3400, '10000000005', 'MMGF2CH/A银色,Core i5/8G内存/128G闪存,1件'),
    (5, 14, 'Apple 苹果 MacBook Pro 笔记本电脑 16年新款 15英寸 Multi-Touch Bar 256G 深空灰色', 'https://img14.360buyimg.com/n5/s450x450_jfs/t3397/55/762020838/184157/7e507d32/58131c17Nb108ca54.jpg', '8.00', 'https://img14.360buyimg.com/n5/s450x450_jfs/t3397/55/762020838/184157/7e507d32/58131c17Nb108ca54.jpg,https://img14.360buyimg.com/n5/s450x450_jfs/t3427/1/761535388/238533/b605b9f2/58130206Ncf90e695.jpg,https://img14.360buyimg.com/n5/s450x450_jfs/t3490/164/628752282/118644/88a1c360/58130288Ne2b8683e.jpg,https://img14.360buyimg.com/n5/s450x450_jfs/t3715/86/798415057/312693/b777b279/58131c16Ncba81f8b.jpg', 'https://img10.360buyimg.com/imgzone/jfs/t3082/24/2402653567/223529/1a6b04b/57e0d2f8N10eb5602.jpg', 'https://img30.360buyimg.com/popWaterMark/jfs/t6241/302/1026912919/130228/685bcc83/5949c952N88858337.jpg', 150, 900, '10000000006', '13英寸 Core i5 8G内存 256G闪存,深空灰色,1件'),
    (6, 14, 'Apple MacBook Air 13.3英寸笔记本电脑 银色(Core i5 处理器/8GB内存/128GB SSD闪存 MMGF2CH/A)', 'https://img11.360buyimg.com/n7/jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg', '1', 'https://img11.360buyimg.com/n1/s450x450_jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg,https://img13.360buyimg.com/n1/s450x450_jfs/t2632/157/4193453761/92922/2adb5ebc/57ad88f0Nb286ec7a.jpg,https://img11.360buyimg.com/n1/s450x450_jfs/t2977/86/2412624329/68019/dbe32c1f/57ad8846N64ac3c79.jpg', 'https://img20.360buyimg.com/vc/jfs/t3034/151/748569500/226790/d6cd86a2/57b15612N81dc489d.jpg', 'https://img20.360buyimg.com/vc/jfs/t2683/60/4222930118/169462/233c7678/57b15616N1e285f09.jpg', 5000, 10000, '10000000001', '1.6GHz i5处理器,GB内存/128GB SSD,1件'),
    (7, 14, 'Apple 苹果 MacBook Air 13.3英寸笔记本电脑 MMGF2CH/A银色 Core i5/8G内存/128G闪存', 'https://img13.360buyimg.com/n1/jfs/t5821/151/4295371754/246251/29179c11/594a3eabNbf7fceec.jpg', '3.00', 'https://img13.360buyimg.com/n1/jfs/t5821/151/4295371754/246251/29179c11/594a3eabNbf7fceec.jpg,https://img13.360buyimg.com/n1/jfs/t3157/218/9378001544/103166/c8bb88da/58d0e987Nb3a34bfa.jpg,https://img13.360buyimg.com/n1/jfs/t4639/313/514186457/88273/6d83eae9/58d0e988N0009a10c.jpg', 'https://img30.360buyimg.com/popWaterMark/jfs/t5746/329/4801800135/218275/4a67f0ef/5954a40fNf471b55d.jpg', 'https://img30.360buyimg.com/popWareDetail/jfs/t6532/96/2593310/189936/1c5b2d94/5937a66aN594f4851.jpg', 2350, 3400, '10000000005', 'MMGF2CH/A银色,Core i5/8G内存/128G闪存,1件'),
    (8, 14, '国行Apple/苹果 MacBook Pro MF839CH/A 13.3英寸 商务笔记本电脑', 'https://img.alicdn.com/imgextra/i3/1669409267/TB2bMSccXXXXXbVXXXXXXXXXXXX_!!1669409267.jpg_430x430q90.jpg', '5.00', 'https://img.alicdn.com/imgextra/i3/1669409267/TB2bMSccXXXXXbVXXXXXXXXXXXX_!!1669409267.jpg_430x430q90.jpg,https://img.alicdn.com/imgextra/i2/1669409267/TB2WUa4qpXXXXaQXpXXXXXXXXXX_!!1669409267.jpg_430x430q90.jpg,https://img.alicdn.com/imgextra/i2/1669409267/TB2ZnUysXXXXXXOXXXXXXXXXXXX_!!1669409267.jpg_430x430q90.jpg', 'https://gdp.alicdn.com/imgextra/i1/1669409267/TB25Yfnmr_0UKFjy1XaXXbKfXXa_!!1669409267.jpg', 'https://img.alicdn.com/imgextra/i4/1669409267/TB28SC_vdFopuFjSZFHXXbSlXXa_!!1669409267.jpg', 1660, 1200, '10000000007', '8G 128G,银白色,1件'),*/

    /*********************************************************** 虚拟数据 结束  ***********************************************************/

}