# EcosedPlugin
Android插件化开发框架

[![](https://jitpack.io/v/ecosed/EcosedPlugin.svg)](https://jitpack.io/#ecosed/EcosedPlugin)

使用方法：

1.在settings.gradle.kts中添加

repositories { maven("https://jitpack.io") }

2.在模块的build.gradle.kts中添加

dependencies { implementation("com.github.ecosed:EcosedPlugin:1.0.0-beta01") }

3.点击Sync Now同步即可完成工程设定。

4.创建插件，点击查看[示例代码](https://github.dev/ecosed/EcosedPlugin/blob/master/app/src/main/java/io/ecosed/plugin_example/ExamplePlugin.kt)

5.在Activity中初始化并添加插件，点击查看[示例代码](https://github.dev/ecosed/EcosedPlugin/blob/master/app/src/main/java/io/ecosed/plugin_example/MainActivity.kt)