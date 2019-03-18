## ModularizationExample
android组件化学习

1：lib_ext: 作为一个app开发的公共类（各种utils）

2：lib_uikit（依赖 lib_ext）: 一个app公用的控件,比如RecyclerViewWithFooter和tablayout

3: lib_uiframework (依赖上面两个库): 作为页面的基础类（BaseActivity, BaseFragment, MvpActivity）

4: lib_imageloader: 图片加载代码代码隔离

5: lib_pictures: 图片浏览组件，里面会依赖一些第三方的库（Rxjava, RxAndroid, RxPermissions, Glide）使用的时候请注意

6: lib_live: 是一个rxjava和官方框架中的LiveData类似

7: lib_network_rx_retrofit: 简单的网络库和文件下载及其监听提供（MrService、DownLoadManager）

