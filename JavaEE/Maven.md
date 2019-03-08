### Maven生命周期
+ Maven 生命周期:(生命周期是抽象的，实际行为由插件完成)(项目的清理，初始化，编译，测试，打包，集成测试，验证，部署，站点生成)
package: maven-jar-plugin
test:maven-surefire
- Maven三套完整的声明周期：

    + clean生命周期：
    pre-clean: 清理前需要完成
    clean: 上一次构建生成的文件
    post-clean:清理后完成工作
    + default生命周期：
    generate-sources->process-sources->generate-resources->process-resources->
    compile->process-classes->测试一套完整以上流程-test（单元测试框架运行测试，不会打包发布）
    -prepare-package->package->integration->verify->install->depoly(将打包好的东西部署到远程仓库)
    + site生命周期：
    pre-site()->site(生成项目站点文档)->post-site->site-deploy(将生成的站点发布到服务器上)

## 钰茂项目实战：
+ maven多环境配置：
``` 
  <profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <build.env>dev</build.env>
        </properties>
    </profile>
    <profile>
        <id>test</id>
        <properties>
            <build.env>test</build.env>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <build.env>prod</build.env>
        </properties>
    </profile>
</profiles>
```
+ Maven资源拷贝
```
    <build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <targetPath>${project.build.directory}/classes</targetPath>
                <directory>${project.basedir}/src/main/env/${build.env}/</directory>
                <includes>
                    <include>*</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <targetPath>${project.build.directory}/classes</targetPath>
                <directory>${project.basedir}/src/main/resources/</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```
+ springBoot项目通过application.yml文件指定spring.profiles.active=@build.env@指定激活的环境

