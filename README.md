## Pisces Lfs

[![](https://img.shields.io/badge/%E5%BC%80%E5%8F%91%E8%BF%9B%E5%BA%A6-%E5%BC%80%E5%8F%91%E4%B8%AD-brightgreen?style=flat-square)]() [![](https://img.shields.io/badge/license-MIT-green?style=flat-square)](https://github.com/besscroft/pisces-lfs/blob/master/LICENSE) [![](https://img.shields.io/badge/release-v0.0.2-orange?style=flat-square)]() ![GitHub repo size](https://img.shields.io/github/repo-size/besscroft/pisces-lfs?style=flat-square&color=328657)

### 简介

pisces-lfs 是一个基于 SpringBoot3 + PostgreSQL + Vue + Element UI 的快速启动框架，可基于此框架快速构建系统！
框架提供了基础的基于 RBAC 思想开发的权限管理模块，可以让您专注于业务开发。

### 预览地址

[演示站](https://lfs.besscroft.com/)

```
账号：admin
密码：666666
```

## 文档

[文档中心](https://developer.besscroft.com/lfs/)

## 环境搭建

### 开发环境

pisces-lfs 的需要以下程序才能正常的安装和运行：

- Git
- nodejs 16.x LTS
- PostgreSQL 12.x+
- open/oracleJDK 17
- nginx 1.16+

pisces-lfs 支持安装在 LNMP、宝塔面板 等集成环境中, Docker、HeroKu 等容器环境中, 支持大部分能够运行 Java 的平台。

### 代码贡献

[提出新想法 & 提交 Bug](https://github.com/besscroft/pisces-lfs/issues/new) | [Fork & Pull Request](https://github.com/besscroft/pisces-lfs/fork)

pisces-lfs 欢迎各种贡献，包括但不限于改进，新功能，文档和代码改进，问题和错误报告。

### 在线开发

你可以使用 Gitpod 进行在线开发：

<p><a href="https://gitpod.io/#https://github.com/besscroft/pisces-lfs" rel="nofollow"><img src="https://camo.githubusercontent.com/1eb1ddfea6092593649f0117f7262ffa8fbd3017/68747470733a2f2f676974706f642e696f2f627574746f6e2f6f70656e2d696e2d676974706f642e737667" alt="Open in Gitpod" data-canonical-src="https://gitpod.io/button/open-in-gitpod.svg" style="max-width:100%;"></a></p>

或者克隆到本地开发:

```bash
git clone https://github.com/besscroft/pisces-lfs.git
```
### 项目构建（管理平台的前端）

推荐 [yarn](https://github.com/yarnpkg/yarn)

```bash
# 克隆项目
git clone https://github.com/besscroft/pisces-lfs.git

# 进入项目目录
cd pisces-lfs/lfs-web

# 安装依赖
npm install

# 建议不要用 cnpm 安装 会有各种诡异的bug 可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npmmirror.com

# 本地开发 启动项目
npm run dev
```

如果您有任何建议，欢迎反馈！

我的开源项目都部署在 `DigitalOcean` ，如果你愿意走我的邀请链接注册，可以获得100美元的信用额度。

<a href="https://www.digitalocean.com/?refcode=6841be7284cc&utm_campaign=Referral_Invite&utm_medium=Referral_Program&utm_source=badge"><img src="https://web-platforms.sfo2.cdn.digitaloceanspaces.com/WWW/Badge%201.svg" alt="DigitalOcean Referral Badge" /></a>
