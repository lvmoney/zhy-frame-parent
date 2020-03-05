# frame

#### 介绍

分布式后台常用技术完全整合架构。通过maven依赖简单的引入即可使用各种常用技术。

#### 使用方式

通过maven依赖的方式引用到项目模块即可。把frame作为所有项目的parent，子项目一般都要引入common，其他模块根据系统需要引入，详见各个module的readme.md。
#### 服务支持
访问安全认证
zhy-frame-authentication    
缓存框架
zhy-frame-cache  
统一配置中心
zhy-frame-config   
定时任务管理器
zhy-frame-job   
统一日志收集框架（本地收集、远程收集）
zhy-frame-log   
异步消息处理
zhy-frame-mq    




#### 技术罗列



#### 可行性

大部分技术已用到正式项目环境，以校验整体技术框架的正确性和可行性

#### 代码风格

开发的时候用了阿里代码的扫描工具，除某些测试代码均需通过扫描工具的验证

#### 支持力度

整个技术实现正在不断的完善


#### 参与贡献

框架构架和开发人员:  
lvmoney  
1300515928  

https://gitee.com/lvmoney/zhy-frame-parent

