1 > 创建Git仓库目录
mkdir git
2 > 创建一个裸仓库
git init --bare shuang.git
3 > 改变仓库文件所有的所有者和组名
chown -R admin:staff shuang.git

4 > 使用不用协议(FTP,SSH,HTTP,FILE)clone一个仓库
git clone https://github.com/Shuang15091035/shuangtest.git
