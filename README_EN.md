# AnswerIt - A simple Q&A plugin in Minecraft

# Select your language

<h3><a href=".\README.md">简体中文</a> <a href=".\README_EN.md">English</a></h3>

![](https://img.shields.io/badge/Spigot%2FPaper-1.13%2B-orange)
![](https://img.shields.io/github/license/MinecraftProgrammingTeam/AnswerIt)
![](https://img.shields.io/badge/made%20in-MPT-important)

# Intro

Inspiration comes from video [《你 问 我 打 ！》](https://www.bilibili.com/video/BV13V4y1W7X6/)

It is an upgraded version of the plugin in the video, and the selection issue is not just yes or no, multiple options can be customized

I also added a question and answer for filling in the blank, allowing players from all servers to jointly determine whether it is correct or not

# Usage

Download the latest version in [Release](https://github.com/MinecraftProgrammingTeam/AnswerIt/releases/latest) and put the jar file in a Minecraft server with BukkitAPI in plugins folder, and restart the server to apply it.

Because the package `net.md_5.bungee.api.chat.BaseComponent` is no longer support in the newest(1.20+) API

So the latest version the plugin can support is 1.19:

1.16.5 ≤ Suggested server version ≤ 1.19

Test Environment("Server + Client"):  `Paper1.16.5 + Minecraft 1.16.5(NO MODS AND MODLOADER)`

PS: If you have found a bug that happened isn't the same as my Test Environment, you could try to find it in my Test Environment

If it's happened again, please upload it in Issues

Or you can upload it directly if you are too lazy to build a Test Environment(Please note the environment you used)

Issues Format:
- Questions & Bugs
- How to find it
- Your Environment(Ignore if it's the same as mine)
- Environment Format: Server Version + Client Version(Mods + ModLoader)
- eg. `Paper1.16.5 + Minecraft 1.16.5(NO MODS AND MODLOADER)`

# To operator

This repository uses  `Apache License 2.0` license

ADDITIONAL CONDITIONS：

- Prohibition of Commercial Use

## Build environment

Clone the repository into your own drive

Use `IntelliJ IDEA` opens the folder of this project

Set project structure (sometimes automatically)

The loading Maven project will pop up in the lower right corner (sometimes automatically)

Click the upper right corner `Edit Configuration` and new a `Maven` project

Then write the commands `clean package`, and apply it.

The reward and punishment system's location: `top.mpt.huihui.answerit.prize.prize`

There are not many code comments, but there is still no problem understanding them.

ItemUtils are imported but not used, originally intended as a reward and punishment system, but i'm too lazy~

## Simply Logic Relation Diagram

![](https://user-images.githubusercontent.com/64721484/214585912-42e1b229-573d-4269-adc0-1c85048b9b98.png)


## TODOs

- [x] Select mode 
- [x] Write mode 
- [x] Vote System
- [x] Reward And Punishment System

## Issues:

Welcome to ask questions in Issues

## 咕咕咕

