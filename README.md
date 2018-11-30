# Android客户端项目测试方案

## 方案
1. monkey + jenkins : 自动构建任务，跑master/dev分支最新代码的稳定性
* 目前集成到Jenkins的monkey二次开发方案 ： [Maxim](https://github.com/zhangzhao4444/Maxim)
* 编译打包会去跑所有的JUnit + UIAUTOMATOR

## 目录

* [sample1](https://github.com/cxMax/AndroidJunit/tree/master/sample1/AndroidJunit) :
    * primary focus on the usage of [Robolctric](https://github.com/robolectric/robolectric-samples)
    * Robolctric's learning path plz [click here](https://github.com/cxMax/AndroidJunit/blob/master/sample1/AndroidJunit/README.md)


* [sample2](https://github.com/cxMax/AndroidJunit/tree/master/sample2/Junit-sample) :
    * primary focus on the usage of [mockito](http://mockito.org/)
    * Mockito's learning path plz [click here](https://github.com/cxMax/AndroidJunit/blob/master/sample2/Junit-sample/README.md)


* [sample3](https://github.com/cxMax/AndroidJunit/tree/master/sample3/Espresso-sample) :
    * primary focus on the usage of [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) and [Uiautomator](https://github.com/googlesamples/android-testing/tree/master/ui/uiautomator/BasicSample)
    * Espresso and Uiautomator's learning path plz [click here](https://github.com/cxMax/AndroidJunit/blob/master/sample3/Espresso-sample/README.md)

## License
   Copyright (C) 2017 cxMax
   Copyright (C) 2017 AndroidJunit
