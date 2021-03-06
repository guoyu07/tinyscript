最新版本号：
=========================
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.tinygroup/tinyscript/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.tinygroup/tinyscript)

#tinyscript是一款基于Java的脚本语言，完美兼容Java，同时拥有脚本语言的灵活简洁，可以以二分之一甚至三分之一的代码量实现Java代码块。

特点：
1.  无缝与java结合，支持创建、调用java对象和方法。
2.  可以与Spring结合，直接访问bean对象
3.  语法简洁，避免用户编写复杂的java代码或SQL语句
4.  强大的IDE工具，支持用户直接写脚本，运行得到结果
5.  灵活的扩展机制，用户可以通过扩展数据模型、函数和相关处理器，增加脚本语言的功能
6.  友好的语法提示信息，用户可以准确定位语法错误位置及运行异常原因
7.  日志和错误提示支持国际化

更详细完整的介绍和使用，请参见文档：http://www.tinygroup.org/docs/1478841256976022140


#项目子工程简介

	├── org.tinygroup.tinyscriptbase                  //脚本语言核心工程，包含词法语法解析，执行引擎设计等
	├── org.tinygroup.tinyscript.collection           //数据模型扩展，包含Array、List、Set和Map
	├── org.tinygroup.tinyscript.dataset              //数据模型扩展，包含数据集(序表)
	├── org.tinygroup.tinyscript.tree                 //数据模型扩展，包含树
	├── org.tinygroup.tinyscript.database             //功能强化，实现数据集在数据库方面的扩展
	├── org.tinygroup.tinyscript.excel                //功能强化，实现数据集在Excel方面的扩展
	├── org.tinygroup.tinyscript.text                 //功能强化，实现数据集在文本方面的扩展
	├── org.tinygroup.tinyscript.datasetwithtree      //数据模型转换，实现数据集和树间的转换
	├── org.tinygroup.tinyscript.template             //Tiny模板语言扩展
	├── org.tinygroup.tinyscript                      //TinyScript具体实现
	├── tinyscripttest                                //脚本示例工程，提供《新手指南》完整的tsf示例
	