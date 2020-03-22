# KTeaMall

Kotlin项目

## 框架配置

### 1、模块切换

动态切换模块是library还是application。设置gradle.properties中isUserModule。

### 2、引入id

使用 `apply plugin: 'kotlin-android-extensions'`。

### 3、Anko的使用

    https://github.com/Kotlin/anko

工具库

    Intents、Dialogs and toasts 、Logging、Resources and dimensions、Anko Layouts、Anko SQLite (wiki)

### 4、MVP

### 5、RxKotlin和RxAndroid

    https://github.com/ReactiveX/RxKotlin
    https://github.com/ReactiveX/RxAndroid

### 6、retrofit

    https://github.com/square/retrofit

### 7、dagger2

    https://github.com/google/dagger

Inject、Component、Module、Provides、Scope、Singleton、Qualifier、Named

### RxLifecycle

解决Rx内存泄漏

    https://github.com/trello/RxLifecycle

## 通用组件封装和工具类

	AppManager：Activity管理器
	HeaderBar:通用标题头部
	ProgressLoading：加载过程动画视图
	VerifyButton：获取验证码按钮，带倒计时

	BaseRecyclerViewAdapter：RecyclerViewAdapter基类
	AppPrefsUtils：sp存储
	DateUtils：时间日期工具类
	GlideUtils：Glide工具类
	NetWorkUtils：检查网络工具

## 用户模块开发



## 其他

    //标题titleText不为空，设置值，并作为参数传进{}
    titleText?.let {
        mTitleTv.text = it
    }




