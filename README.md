﻿# signup-signin

帮助您在搭建网站时快速实现用户注册及登录功能

您需要：一台装有MySQL的服务器

导入jar包后可调用以下方法：

setIp(String ip) 设置用户注册、登录连接的MySQL数据库

signUp(String uesrname, String password, String rpsw, String verifyCode, boolean repeatable) 注册

signIn(String username, String password, String verifyCode)登录

