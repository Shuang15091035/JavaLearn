# JavaLearn (总结)

项目开发使用 tomcat + maven + java

###WEB-INF目录服务器是不能直接访问的，需要使用Controller指定(转发访问)，js,css,image不要存放到此目录
###.通过maven构建项目，启动项目后默认进入的域名为：
http://localhost:8080/项目名称/WEB-INF/web.xml？
---程序启动后：默认的跳转页面是到项目的web.xml配置文件中去查找

