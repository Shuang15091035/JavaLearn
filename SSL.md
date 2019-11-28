Let`s Encrypt 免费SSL证书申请，自动续期，永久免费
官网地址：https://letsencrypt.org/docs/
生成证书工具：https://certbot.eff.org

TLS：传输层安全协议 Transport Layer Security的缩写

SSL：安全套接字层 Secure Socket Layer的缩写

TLS与SSL对于不是专业搞安全的开发人员来讲，可以认为是差不多的，这二者是并列关系，详细差异见 http://kb.cnblogs.com/page/197396/

KEY 通常指私钥。

CSR 是Certificate Signing Request的缩写，即证书签名请求，这不是证书，可以简单理解成公钥，生成证书时要把这个提交给权威的证书颁发机构。

CRT 即 certificate的缩写，即证书。

X.509 是一种证书格式.对X.509证书来说，认证者总是CA或由CA指定的人，一份X.509证书是一些标准字段的集合，这些字段包含有关用户或设备及其相应公钥的信息。

X.509的证书文件，一般以.crt结尾，根据该文件的内容编码格式，可以分为以下二种格式：

PEM - Privacy Enhanced Mail,打开看文本格式,以"-----BEGIN..."开头, "-----END..."结尾,内容是BASE64编码.
Apache和*NIX服务器偏向于使用这种编码格式.

DER - Distinguished Encoding Rules,打开看是二进制格式,不可读.
Java和Windows服务器偏向于使用这种编码格式

OpenSSL 相当于SSL的一个实现，如果把SSL规范看成OO中的接口，那么OpenSSL则认为是接口的实现。接口规范本身是安全没问题的，但是具体实现可能会有不完善的地方，比如之前的"心脏出血"漏洞，就是OpenSSL中的一个bug.


证书生成：
	三方机构购买
	免费申请（Let`s Encrypt）
	自签证书
创建秘钥文件：
	openssl genrsa -out shuang.ca.key 4096
创建csr证书：
	openssl req -new -key shuang.ca.key -out shuang.ca.csr
##去除秘钥密码：
	cp shuang.key shuang.key.bak
	openssl rsa -in shuang.ca.key.bak -out shuang.ca.key
生成crt证书：
	openssl x509 -req -days 3650 -in shuang.ca.csr -signkey shuang.ca.key -out shuang.ca.crt