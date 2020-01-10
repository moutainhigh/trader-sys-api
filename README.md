### 编辑器记得安装lombok插件

### FEBS-Shiro 2.0
![https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springboot-2.2.1-yellow.svg?style=flat-square](https://img.shields.io/badge/springboot-2.2.1-yellow.svg?style=flat-square)
![https://img.shields.io/badge/shiro-1.4.2-orange.svg?longCache=true&style=flat-square](https://img.shields.io/badge/shiro-1.4.2-orange.svg?longCache=true&style=flat-square)

![ https://img.shields.io/badge/Vue-2.x-green ]( https://img.shields.io/badge/Vue-2.x-green )![]( https://img.shields.io/badge/ElementUI-2.x-green )

 [trader-sys-api](https://github.com/xhmily/trader-sys-api) 是一款简单高效的交易系统Api管理系统，使用Spring Boot，Shiro和Vue构建。

 [trader-sys-api](https://github.com/xhmily/trader-sys-api) 集成了 亚马逊Api，速卖通Api，Hdl Api以及Ups Api

### 演示地址

[https://q.lucent.blog](https://q.lucent.blog)


本地部署账号密码：

| 账号  | 密码  | 权限                             |
| ----- | ----- | -------------------------------- |
| admin | admin | 超级管理员，拥有所有增删改查权限 |

### 系统模块
系统功能模块组成如下所示：
```
├─系统管理
│  ├─用户管理
│  ├─角色管理
│  ├─菜单管理
│  └─部门管理
├─系统监控
│  ├─在线用户
│  ├─系统日志
│  ├─登录日志
│  ├─请求追踪
│  ├─系统信息
│  │  ├─JVM信息
│  │  ├─TOMCAT信息
│  │  └─服务器信息
├─任务调度
│  ├─定时任务
│  └─调度日志
├─代码生成
│  ├─生成配置
│  ├─代码生成
```
### 系统特点

1. 前后端请求参数校验

2. 支持Excel导入导出

3. 前端页面布局多样化，主题多样化

4. 支持多数据源，代码生成

5. 多Tab页面，适合企业应用

6. 用户权限动态刷新

8. 代码简单，结构清晰

### 技术选型

#### 后端
- [Spring Boot 2.2.1](http://spring.io/projects/spring-boot/)
- [Mybatis-Plus](https://mp.baomidou.com/guide/)
- [MySQL 5.7.x](https://dev.mysql.com/downloads/mysql/5.7.html#downloads)
- [Shiro 1.4.2](http://shiro.apache.org/)

#### 前端
- [ElementUI2.13](https://github.com/ElemeFE/element/releases/tag/v2.13.0)]( https://element.eleme.cn/ )
- [formSelects 4.x 多选框](https://hnzzmsf.github.io/example/example_v4.html)
- [eleTree 树组件](https://layuiextend.hsianglee.cn/eletree/)
- [formSelect.js树形下拉](https://wujiawei0926.gitee.io/treeselect/docs/doc.html)
- [Apexcharts图表](https://apexcharts.com/)

### 系统截图

#### PC端

![](https://test-1251540275.cos.ap-shanghai.myqcloud.com/img/20200110122639.png)

![](https://test-1251540275.cos.ap-shanghai.myqcloud.com/img/20200110122738.png)

### 浏览器兼容
|[<img src="https://raw.github.com/alrra/browser-logos/master/src/archive/internet-explorer_9-11/internet-explorer_9-11_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |[<img src="https://raw.github.com/alrra/browser-logos/master/src/opera/opera_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Opera
| --------- | --------- | --------- | --------- | --------- |--------- |
|IE 10+| Edge| last 15 versions| last 15 versions| last 10 versions| last 15 versions

### 作者博客
[lucent.blog](lucent.blog)