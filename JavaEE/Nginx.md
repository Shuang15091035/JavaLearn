Nginx Location 表达式类型：
     ~：表示执行一个正则表达式，区分大小写
     ~*：执行一个正则表达式，不区分大小写
     ^~:表示普通字符串匹配，使用前缀匹配，如果匹配成功，则不在匹配其他location
     =：进行普通字符串精确匹配，也就是完全匹配
     @：定义一个命名的location，使用在内部定向时，例如error_page,try_files
 location优先级说明：
    = > ^~ > (~ ~*) > 常规字符串匹配类型，按前缀匹配 多个location正则能匹配的话，使用正则表达式最长的那个

Proxy_pass后：后端url（request_url）分析
server {

    listen 80;
    server_name www.test.com

    # 情形A
    # 访问 http://www.test.com/testa/aaaa
    # 后端的request_uri为: /testa/aaaa
    location ^~ /testa/ {
        proxy_pass http://127.0.0.1:8801;
    }
    
    # 情形B
    # 访问 http://www.test.com/testb/bbbb
    # 后端的request_uri为: /bbbb
    location ^~ /testb/ {
        proxy_pass http://127.0.0.1:8801/;
    }

    # 情形C
    # 下面这段location是正确的
    location ~ /testc {
        proxy_pass http://127.0.0.1:8801;
    }

    # 情形D
    # 下面这段location是错误的
    #
    # nginx -t 时，会报如下错误: 
    #
    # nginx: [emerg] "proxy_pass" cannot have URI part in location given by regular 
    # expression, or inside named location, or inside "if" statement, or inside 
    # "limit_except" block in /opt/app/nginx/conf/vhost/test.conf:17
    # 
    # 当location为正则表达式时，proxy_pass 不能包含URI部分。本例中包含了"/"
    location ~ /testd {
        proxy_pass http://127.0.0.1:8801/;   # 记住，location为正则表达式时，不能这样写！！！
    }

    # 情形E
    # 访问 http://www.test.com/ccc/bbbb
    # 后端的request_uri为: /aaa/ccc/bbbb
    location /ccc/ {
        proxy_pass http://127.0.0.1:8801/aaa$request_uri;
    }

    # 情形F
    # 访问 http://www.test.com/namea/ddd
    # 后端的request_uri为: /yongfu?namea=ddd
    location /namea/ {
        rewrite    /namea/([^/]+) /yongfu?namea=$1 break;
        proxy_pass http://127.0.0.1:8801;
    }

    # 情形G
    # 访问 http://www.test.com/nameb/eee
    # 后端的request_uri为: /yongfu?nameb=eee
    location /nameb/ {
        rewrite    /nameb/([^/]+) /yongfu?nameb=$1 break;
        proxy_pass http://127.0.0.1:8801/;
    }

}



钰茂服务nginx配置 
http{
    # 金正接口
    upstream jzfront-gm {
        server 192.168.30.99:8050;
        server 192.168.20.101:8060;
    }
    server {
        location ^~ /kdwis {
                proxy_pass   http://jzfront-gm;
                proxy_redirect off;
        }
    }
}

访问方式：
程序内部：配置请求地址
    WEBAPI_REQ_URL=http://192.168.20.101:8060/kdwis/ServiceServlet
问题？
nginx错误日志记录：

2019/05/20 16:15:53 [error] 204#4228: *5 upstream timed out (10060: A connection attempt failed because the connected party did not properly respond after a period of time, or established connection failed because connected host has failed to respond) while connecting to upstream, client: 192.168.30.200, server: 192.168.30.200, request: "POST /kdwis/ServiceServlet HTTP/1.1", upstream: "http://192.168.20.101:8060/kdwis/ServiceServlet", host: "192.168.30.200:8080"
2019/05/20 16:15:53 [error] 204#4228: *5 CreateFile() "D:\jpym\nginx-1.13.8/html/50x.html" failed (2: The system cannot find the file specified), client: 192.168.30.200, server: 192.168.30.200, request: "POST /kdwis/ServiceServlet HTTP/1.1", upstream: "http://192.168.20.101:8060/kdwis/ServiceServlet", host: "192.168.30.200:8080"
2019/05/20 16:16:15 [error] 204#4228: *11 upstream timed out (10060: A connection attempt failed because the connected party did not properly respond after a period of time, or established connection failed because connected host has failed to respond) while connecting to upstream, client: 192.168.30.200, server: 192.168.30.200, request: "POST /kdwis/ServiceServlet HTTP/1.1", upstream: "http://192.168.20.101:8060/kdwis/ServiceServlet", host: "192.168.30.200:8080"
2019/05/20 16:16:15 [error] 204#4228: *11 CreateFile() "D:\jpym\nginx-1.13.8/html/50x.h


服务日志记录：
