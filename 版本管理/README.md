Git push 需要输入用户名密码问题
	git config credential.helper store
	git push origin
	用户名/密码
	或指定有效期
	git config --global credential.helper 'cache --timeout 7200'





### GitHub上查找靠谱的开源项目
按照项目名/仓库名搜索(大小写不敏感)
	in:name xxx
按照README搜索
	in:readme xxx
按照description搜索
	in:description xxx
starts数大于xx
	start:>xxx
forks数大于xxx
	forks:xxx
language:xx
	language:xx
最新更新时间晚于YYYY-MM-DD
	pushed:>YYYY-MM-DD
	
