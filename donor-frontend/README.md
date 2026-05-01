# 献血者管理系统前端阅读文档

## 一、项目概述

本项目是献血者管理系统的前端部分，主要面向献血者用户，提供用户注册登录、个人资料维护、献血预约、献血记录查询、体检记录查询等功能。

项目采用 Vue 3 作为前端框架，使用 Vite 作为开发与构建工具，页面组件主要基于 Element Plus 实现。系统通过 Axios 与后端接口通信，并使用 Vue Router 管理页面路由，使用 Pinia 管理登录状态，使用 vue-i18n 实现多语言支持。

## 二、技术栈说明

| 技术 | 作用 |
| --- | --- |
| Vue 3 | 前端页面开发框架 |
| Vite | 前端开发服务器与构建工具 |
| Vue Router | 页面路由管理 |
| Pinia | 全局状态管理 |
| Element Plus | UI 组件库 |
| Axios | HTTP 请求封装 |
| vue-i18n | 多语言国际化 |
| dayjs | 日期格式化处理 |

## 三、运行环境

运行本项目需要准备以下环境：

1. Node.js
2. npm
3. 现代浏览器，例如 Chrome、Edge
4. 后端服务，默认地址为 `http://localhost:8081`

当前项目已经包含 `package-lock.json`，建议使用 npm 安装依赖，保证依赖版本相对稳定。

## 四、启动方式

进入项目根目录：

```powershell
cd "C:\Users\32601\Desktop\毕业设计\donor-frontend"
```

安装依赖：

```powershell
npm install
```

启动开发服务器：

```powershell
npm run dev
```

如果在 Windows PowerShell 中出现 `npm.ps1` 被禁止执行的问题，可以改用：

```powershell
npm.cmd run dev
```

启动成功后，浏览器访问：

```text
http://localhost:5173
```

## 五、项目目录结构

项目主要目录如下：

```text
donor-frontend/
  public/              公共静态资源
  src/                 前端源码目录
    api/               后端接口请求封装
    assets/            项目静态资源
    locales/           多语言配置
    router/            路由配置
    stores/            Pinia 状态管理
    utils/             工具函数
    views/             页面组件
    App.vue            根组件
    main.js            应用入口文件
  index.html           页面入口 HTML
  vite.config.js       Vite 配置文件
  package.json         项目依赖与脚本配置
```

各目录作用说明：

| 目录或文件 | 说明 |
| --- | --- |
| `src/main.js` | 创建 Vue 应用，注册 Element Plus、Pinia、Router 和 i18n |
| `src/App.vue` | 应用根组件，承载路由页面 |
| `src/views` | 存放登录、注册、首页、预约、记录等页面 |
| `src/api` | 统一封装后端接口调用 |
| `src/router/index.js` | 配置系统路由与登录权限拦截 |
| `src/stores` | 存放登录用户等全局状态 |
| `src/utils` | 存放 token、格式化等通用工具函数 |
| `src/locales` | 存放中文、英文、俄文语言包 |

## 六、开发服务器与代理配置

项目的 Vite 配置文件为 `vite.config.js`。

开发服务器端口配置为：

```js
server: {
  port: 5173
}
```

接口代理配置为：

```js
proxy: {
  '/api': {
    target: 'http://localhost:8081',
    changeOrigin: true
  }
}
```

因此，在开发环境中，前端请求 `/api` 开头的接口时，会被 Vite 自动代理到后端服务 `http://localhost:8081`。例如前端请求：

```text
/api/auth/login
```

实际会转发到：

```text
http://localhost:8081/api/auth/login
```

如果页面可以打开但登录、注册、预约等接口无法使用，需要优先检查后端服务是否已经启动，并确认后端端口是否为 `8081`。

## 七、路由设计

路由配置文件位于 `src/router/index.js`。

系统主要路由如下：

| 路径 | 页面 | 说明 |
| --- | --- | --- |
| `/login` | LoginView | 用户登录页面 |
| `/register` | RegisterView | 献血者注册页面 |
| `/home` | HomeView | 系统首页 |
| `/profile` | ProfileView | 个人资料页面 |
| `/appointments` | AppointmentsView | 献血预约页面 |
| `/donations` | DonationHistoryView | 献血记录页面 |
| `/medical-checks` | MedicalChecksView | 体检记录页面 |

系统使用路由守卫控制访问权限。登录页和注册页属于公开页面，其他页面需要用户登录后才能访问。

权限判断逻辑如下：

1. 如果用户访问非公开页面，但本地没有 token，则跳转到 `/login`。
2. 如果用户已经登录，再访问登录页或注册页，则自动跳转到 `/home`。
3. 其他情况正常进入目标页面。

## 八、接口请求封装

接口请求统一封装在 `src/api/request.js` 中。

该文件主要负责：

1. 创建 Axios 实例。
2. 设置请求超时时间。
3. 在请求头中自动携带登录 token。
4. 统一处理后端返回的业务错误。
5. 根据 HTTP 状态码进行错误提示和登录状态处理。

其中，token 会通过请求拦截器加入请求头：

```js
config.headers.Authorization = `Bearer ${token}`
```

响应处理逻辑包括：

| 状态或场景 | 处理方式 |
| --- | --- |
| 业务返回 `success === false` | 显示后端错误信息 |
| HTTP 401 | 清除 token 并跳转到登录页 |
| HTTP 403 | 提示无权限 |
| HTTP 404 | 提示资源不存在 |
| HTTP 500 及以上 | 提示服务器错误 |

## 九、主要业务模块

### 1. 用户认证模块

相关文件：

```text
src/views/LoginView.vue
src/views/RegisterView.vue
src/api/auth.js
src/utils/auth.js
src/stores/auth.js
```

主要功能：

1. 用户登录。
2. 献血者注册。
3. 获取当前用户信息。
4. 保存和清除登录 token。
5. 根据登录状态控制页面访问。

接口示例：

```text
POST /api/auth/login
POST /api/auth/donor-register
GET  /api/users/me
```

### 2. 个人资料模块

相关文件：

```text
src/views/ProfileView.vue
src/api/donor.js
```

主要功能：

1. 查询献血者个人资料。
2. 修改献血者个人资料。

接口示例：

```text
GET /api/donors/{id}
PUT /api/donors/{id}
```

### 3. 献血预约模块

相关文件：

```text
src/views/AppointmentsView.vue
src/api/appointment.js
```

主要功能：

1. 创建献血预约。
2. 查询预约列表。
3. 修改预约信息。

接口示例：

```text
POST /api/appointments
GET  /api/appointments
PUT  /api/appointments/{id}
```

### 4. 献血记录模块

相关文件：

```text
src/views/DonationHistoryView.vue
src/api/donation.js
```

主要功能：

1. 查询献血历史记录。
2. 展示献血时间、地点、献血量等信息。

接口示例：

```text
GET /api/donations
```

### 5. 体检记录模块

相关文件：

```text
src/views/MedicalChecksView.vue
src/api/medicalCheck.js
```

主要功能：

1. 查询体检记录。
2. 展示体检结果、体检时间等信息。

接口示例：

```text
GET /api/medical-checks
```

## 十、多语言支持

项目使用 vue-i18n 实现多语言支持，语言文件位于 `src/locales` 目录。

主要文件：

```text
src/locales/index.js
src/locales/zh.js
src/locales/en.js
src/locales/ru.js
```

其中：

| 文件 | 说明 |
| --- | --- |
| `zh.js` | 中文语言包 |
| `en.js` | 英文语言包 |
| `ru.js` | 俄文语言包 |
| `index.js` | i18n 实例配置 |

系统中的菜单、按钮、表单提示、错误信息等文本可以通过语言包进行统一管理，便于后续扩展更多语言。

## 十一、构建与部署

生成生产环境构建文件：

```powershell
npm run build
```

构建完成后，会生成 `dist` 目录。

本地预览构建结果：

```powershell
npm run preview
```

部署时，可以将 `dist` 目录中的文件部署到 Nginx、Apache 或其他静态资源服务器。

如果生产环境后端接口与前端不在同一域名下，需要额外配置后端跨域，或在 Nginx 中配置反向代理，将 `/api` 请求转发到后端服务。

## 十二、常见问题

### 1. 页面可以打开，但接口请求失败

可能原因：

1. 后端服务没有启动。
2. 后端服务端口不是 `8081`。
3. 后端接口路径与前端请求路径不一致。
4. 浏览器控制台存在跨域或网络错误。

解决方式：

1. 确认后端服务已启动。
2. 确认后端服务地址为 `http://localhost:8081`。
3. 检查 `vite.config.js` 中的代理配置。

### 2. PowerShell 中无法执行 npm

如果出现 `npm.ps1` 被禁止执行的问题，可以使用：

```powershell
npm.cmd run dev
```

也可以使用 `cmd` 终端执行：

```cmd
npm run dev
```

### 3. 登录后访问页面仍然跳回登录页

可能原因：

1. token 没有正确保存。
2. 后端登录接口没有返回前端需要的数据。
3. 请求拦截器没有正确携带 Authorization 请求头。

可以检查：

```text
src/utils/auth.js
src/stores/auth.js
src/api/request.js
```

### 4. 修改接口地址后不生效

开发环境下，接口代理配置位于 `vite.config.js`。修改代理地址后，需要重新启动开发服务器。

## 十三、总结

本项目是一个基于 Vue 3 的献血者管理系统前端，整体结构清晰，功能模块围绕献血者的注册登录、个人信息、预约、献血记录和体检记录展开。项目通过 Vite 提供开发环境，通过 Axios 封装后端接口，通过 Vue Router 实现页面导航和权限控制，通过 Element Plus 提供统一的界面组件。

阅读本项目时，可以按照以下顺序理解代码：

1. 先查看 `package.json`，了解项目依赖和启动命令。
2. 再查看 `vite.config.js`，了解开发端口和后端代理。
3. 然后查看 `src/main.js`，理解应用初始化过程。
4. 接着查看 `src/router/index.js`，理解页面结构和权限控制。
5. 最后结合 `src/views` 和 `src/api`，理解各业务页面如何调用后端接口。
