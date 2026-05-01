# 血液与骨髓捐献管理系统

基于 Spring Boot 3.2 的血液和骨髓捐献业务管理系统，提供 Web 界面和 RESTful API。

## 技术栈

- Java 17 + Spring Boot 3.2.0
- Spring Data JPA + Hibernate
- MySQL 8.x
- Spring Security + JWT
- Thymeleaf + Bootstrap 5
- SpringDoc OpenAPI (Swagger)

## 快速启动

### 环境要求

- JDK 17+
- MySQL 8.0+
- Node.js 18+（前端客户端）
- Maven 3.6+（或使用项目自带的 mvnw）

### 数据库配置

```sql
CREATE DATABASE donor_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

修改 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/donor_management?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
```

### 启动后端（管理后台）

```bash
cd C:\Users\32601\Desktop\毕业设计\MaWan-System\MaWan-BY
.\mvnw.cmd spring-boot:run
```

### 启动前端（捐献者客户端）

```bash
cd C:\Users\32601\Desktop\毕业设计\MaWan-System\donor-frontend
npm install
npm run dev
npm.cmd run dev
```

### 访问地址

| 地址 | 说明 |
|------|------|
| http://localhost:8081 | 管理后台（Thymeleaf） |
| http://localhost:5173 | 捐献者客户端（Vue 3） |
| http://localhost:8081/swagger-ui.html | API 文档 |
| http://localhost:8081/api-docs | OpenAPI JSON |

### 停止服务

```bash
# 查看占用端口的进程
netstat -ano | findstr :8081
netstat -ano | findstr :5173

# 结束进程（替换 <PID> 为实际进程号）
taskkill /PID <8081> /F
```

### 默认账户

**管理后台：**
- 用户名：`admin`
- 密码：`admin123`

**捐献者客户端：** 首次使用请点击"立即注册"创建捐献者账号

## 功能模块

- 捐献者管理（支持扫码枪条形码录入）
- 体检记录管理
- 捐献记录管理（计划、完成、取消流程）
- 库存管理（血液/血浆/血小板/骨髓样本）
- 预约管理
- 统计报表（捐献类型统计、库存按血型统计）
- 用户权限管理（RBAC）
- 多语言支持（中文/英文/俄语）

## 系统角色

| 角色 | 权限 |
|------|------|
| ADMIN | 全部权限 |
| DOCTOR | 体检、捐献记录管理 |
| LAB_TECH | 体检、库存管理 |
| REGISTRAR | 捐献者、预约管理 |
| REPORT_VIEWER | 查看报表 |
| DONOR | 捐献者自助注册、预约、查看个人记录 |

## 常用命令

```bash
# 清理并编译
.\mvnw.cmd clean compile

# 打包（跳过测试）
.\mvnw.cmd clean package -DskipTests

# 运行
.\mvnw.cmd spring-boot:run

# 查看依赖树
.\mvnw.cmd dependency:tree
```

## 目录结构

```
src/main/
├── java/com/example/donormanagement/
│   ├── config/          # 配置类
│   ├── controller/      # REST 控制器
│   ├── dto/             # 数据传输对象
│   ├── entity/          # JPA 实体
│   ├── exception/       # 异常处理
│   ├── mapper/          # 对象映射
│   ├── repository/      # 数据访问层
│   ├── security/        # 安全配置（JWT）
│   ├── service/         # 业务逻辑
│   └── util/            # 工具类
└── resources/
    ├── templates/       # Thymeleaf 模板
    ├── static/
    │   ├── css/         # 样式文件
    │   └── js/          # JavaScript（含 i18n 国际化）
    ├── application.yml  # 配置文件
    └── data.sql         # 初始数据
```

## 配置说明

| 配置项 | 默认值 | 说明 |
|--------|--------|------|
| server.port | 8081 | 服务端口 |
| jwt.secret | ... | JWT 密钥（生产环境必须修改） |
| jwt.expiration | 86400000 | Token 有效期（24小时） |

## 数据库迁移

迁移脚本位于 `sql/` 目录：

- `migration-v1.1-barcode-id.sql` - 条形码 ID 字段迁移
- `test-data.sql` - 测试数据

## 注意事项

1. 生产环境部署前修改 JWT 密钥和默认密码
2. 日志文件已加入 .gitignore，不会提交到版本库
3. IDE 配置文件（.idea/、.vscode/）不提交
