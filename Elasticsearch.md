文档元数据：
	_index: 文档存放位置
	_type: 文档表示对象类别
	_id:	文档唯一标识

搜索： 评分查询，非评分查询
	处理Null：
		select * 
		from posts
		where tags is NOT NULL;
		Get /my_index/posts/_search
		{
			"query": {
				"constnt_score": {
					"filter": {
						"exists": {"field": "tags"}
					}
				}
			}
		}
		/*
	官方文档废弃了missing方法：
		{
			"query" : {
		        "constant_score" : {
		            "filter": {
		                "missing" : { "field" : "tags" }
		            }
	        }
	    }
		*/
		select * 
		from posts
		where tags IS NULL;
		Get /my_index/posts/_search
		{
			"query": {
				"bool": {
					"must_not": {
						"exists": { "field": "tags"}
					}
				}
			}
		}
	###  布尔查询
			"bool": {
				should: [
					{
						{"bool":
							"must": [
							]
						}
					},
					{"bool":
							"must_not": {},
							"must": {
								"term":{}
							}
						}
					}
				]

			}
	### Match 指定操作方式，控制精度
		"query": {
		"match": {
			"title": {
				"query": "BROWN DOG!",
				"operator": "and"
			}
			}
		}
		最小匹配参数：
			{
				"query": {
					"match": {
						"title": {
							"query": "quick brown dog",
							"minimum_should_match": "75%"
					}
				}
			}
		多次查询：
			{
				"match": {"title": "brown fox"}
			}
			<==>
			{
				"bool": {
					"should": [
						{"term": {"title":"brown"}},
						{"term": {"title":"fox"}}
					]
				}
			}
	### 组合查询

	### 为my_type添加新字段
		http://10.8.0.34:9200/my_index/_mapping/my_type?include_type_name=true
		{
			"properties": {
				"english_title": {
					"type": "text",
					"analyzer": "english"
				}
			}	
		}
	多值查询：
		{"query":{
		    "multi_match": {
		        "query":                "Quick brown fox",
		        "type":                 "best_fields", 
		        "fields":               [ "title", "body" ],
		        "tie_breaker":          0.3,
		        "minimum_should_match": "30%" 
		    }
		}}

为每个文档评分:
	用 term 查询计算每个文档相关度评分 _score ，这是种将 词频（term frequency，即词 quick 在相关文档的 title 字段中出现的频率）和反向文档频率（inverse document frequency，即词 quick 在所有文档的 title 字段中出现的频率），以及字段的长度（即字段越短相关度越高）相结合的计算方式。


curl -X PUT "10.8.0.34:9200/my_index/_mapping/my_type" -H 'Content-Type: application/json' -d'
{
    "my_type": {
        "properties": {
            "english_title": {
                "type":     "string",
                "analyzer": "english"
            }
        }
    }
}
'

