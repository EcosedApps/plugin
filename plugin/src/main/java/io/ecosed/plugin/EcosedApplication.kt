package io.ecosed.plugin

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/15
 * 描述: 应用程序接口
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
interface EcosedApplication {

    /** 获取应用程序主机 */
    val getEngineHost: EcosedHost
}