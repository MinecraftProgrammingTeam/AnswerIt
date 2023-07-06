# AnswerIt - 简约的问答插件


# Select your language

<h3><a href=".\README.md">简体中文</a> <a href=".\README_EN.md">English</a></h3>

![](https://img.shields.io/badge/Spigot%2FPaper-1.13%2B-orange)
![](https://img.shields.io/github/license/MinecraftProgrammingTeam/AnswerIt)
![](https://img.shields.io/badge/made%20in-MPT-important)

# 介绍

灵感源自 [《你 问 我 打 ！》](https://www.bilibili.com/video/BV13V4y1W7X6/)

是视频里插件的升级版，选择问题并不只有是与不是，可以自定义多个选项

还追加了填空题问答，全服务器玩家共同判断正误



# 用户悉知

下载最新的 [Release](https://github.com/MinecraftProgrammingTeam/AnswerIt/releases/latest) 放入有BukkitAPI服务端的plugins文件夹之后重启服务器即可

由于1.20的API删除了 `net.md_5.bungee.api.chat.BaseComponent` 包

所以本插件最大支持的版本为1.19

1.13 ≤ 建议服务端版本 ≤ 1.19

插件测试环境： `Paper1.16.5 + Minecraft 1.16.5 纯净端`

只要不是在我的插件测试环境上出现的问题，请换成我的测试环境后，用你触发问题的相同步骤走一遍，如果还有问题，请发布Issues

当然如果你懒得构建测试环境，也可以直接发布Issue（需要在提交Issue的时候写上你自己的服务端与客户端，还有加的所有mod与ModLoader）

Issues 发布格式:
- 您的问题
- 您是如何触发此问题的（请简述步骤）
- 您的触发环境（如果和我测试环境一样就不用写）
- 触发环境格式: 服务端版本 + 客户端版本(所有Mods + ModLoader)
- eg. `Paper1.16.5 + Minecraft 1.16.5(NO MODS AND MODLOADER)`

# 开发者悉知

本仓库使用 `Apache License 2.0` 协议开源

附加条件：

- 禁止商用

## 构建环境：

将项目clone到本地

使用 `IntelliJ IDEA` 打开项目文件夹

设置项目结构（有时候会自动设置）

右下角会弹出加载Maven项目（有时候可能设置了自动加载）

右上角 `编辑配置` 处新建一个 `Maven`

在右边 `运行` 处输入 `clean package` ，应用即可

奖罚系统在： `top.mpt.huihui.answerit.prize.prize`

代码注释不算多，但是看懂还是没问题的。

ItemUtils引入了但是没用上，本来想算作奖罚系统的，但是咕咕咕

## 简单的逻辑图（不太会画）

![](https://user-images.githubusercontent.com/64721484/214585912-42e1b229-573d-4269-adc0-1c85048b9b98.png)

## TODOs

- [x] Select模式判定
- [x] Write模式判定
- [x] 投票系统
- [x] 奖罚系统

## 目前已知问题：

欢迎在Issues提出哦qwq

## 咕咕咕

