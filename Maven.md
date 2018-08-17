Maven 生命周期:(生命周期是抽象的，实际行为由插件完成)(项目的清理，初始化，编译，测试，打包，集成测试，验证，部署，站点生成)
package: maven-jar-plugin
test:maven-surefire
Maven三套完整的声明周期：

    clean生命周期：
    pre-clean: 清理前需要完成
    clean: 上一次构建生成的文件
    post-clean:清理后完成工作
    default生命周期：
    generate-sources->process-sources->generate-resources->process-resources->
    compile->process-classes->测试一套完整以上流程-test（单元测试框架运行测试，不会打包发布）
    -prepare-package->package->integration->verify->install->depoly(将打包好的东西部署到远程仓库)
    site生命周期：
