Git push 需要输入用户名密码问题
	git config credential.helper store
	git push origin
	用户名/密码
	或指定有效期
	git config --global credential.helper 'cache --timeout 7200'