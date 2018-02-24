## ModularizationExample
android组件化学习

1：lib_ext: 作为一个app开发的公共类（各种utils）

2：lib_uikit（依赖 lib_ext）: 一个app公用的控件,比如RecyclerViewWithFooter和tablayout

3: lib_uiframeword (依赖上面两个库): 作为页面的基础类（BaseActivity, BaseFragment, MvpActivity）

4: lib_imageloader: 图片加载代码代码隔离

5: lib_pictures: 图片浏览组件，里面会依赖一些第三方的库（Rxjava, RxAndroid, RxPermissions, Glide）使用的时候请注意

