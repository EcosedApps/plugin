# EcosedPlugin
Android插件化开发框架，像编写Flutter插件一样编写Android原生代码，将功能分插件实现，提高项目可维护性。

使用方法：

1.在settings.gradle.kts中添加

repositories { maven("https://jitpack.io") }

2.在模块的build.gradle.kts中添加

dependencies { implementation("com.github.ecosed:plugin:[![](https://jitpack.io/v/ecosed/plugin.svg)](https://jitpack.io/#ecosed/plugin)") }

3.点击Sync Now同步即可完成工程设定。

4.创建插件，点击查看[示例代码](https://github.dev/ecosed/EcosedPlugin/blob/master/app/src/main/java/io/ecosed/plugin_example/ExamplePlugin.kt)

5.在Activity中初始化并添加插件，点击查看[示例代码](https://github.dev/ecosed/EcosedPlugin/blob/master/app/src/main/java/io/ecosed/plugin_example/MainActivity.kt)

Don't ask me why it's all in Chinese, because this is our own open source third-party library. It is recommended that developers from other countries learn Chinese first.