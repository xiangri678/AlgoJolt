# AlgoJolt - 算法学习项目

![Java](https://img.shields.io/badge/Java-17-orange)
![Maven](https://img.shields.io/badge/Maven-3.x-blue)
![Spring](https://img.shields.io/badge/Spring-6.1.4-green)

一个综合性的算法学习和练习项目，包含LeetCode题解、Java语言特性演示、各类算法实现。

## 📁 项目结构

```
AlgoJolt/
├── src/main/java/com/algo/jolt/           # 主要源码包
│   ├── App.java                           # 项目入口文件
│   ├── javaLanguage/                      # Java语言特性与网络编程
│   ├── leetcode/                          # LeetCode相关题解
│   ├── model/                             # 数据模型类
│   └── SXL_algorithm/                     # 算法分类实现
├── src/test/java/                         # 测试代码
├── target/                                # Maven构建输出目录
├── pom.xml                                # Maven项目配置文件
└── README.md                              # 项目说明文档
```

## 🚀 技术栈

- **Java 17** - 主要开发语言
- **Maven** - 项目构建和依赖管理
- **Spring Framework 6.1.4** - 依赖注入框架
- **Lombok** - 代码简化工具
- **SLF4J + Logback** - 日志框架
- **JUnit 4** - 单元测试框架

## 📚 模块详解

### 1. 🌐 Java Language (`javaLanguage/`)

Java语言特性和网络编程示例：

- **网络编程**

  - `InetAddressDemo.java` - IP地址操作示例
  - `TCPClientDemo.java` / `TCPServerDemo.java` - TCP通信示例
  - `UDPClientDemo.java` / `UDPServerDemo.java` - UDP通信示例
- **并发编程**

  - `CreateThreads.java` - 多种线程创建方式演示
  - `ExecutorServiceDemo.java` - 线程池使用示例
- **边学边补充**

### 2. 💡 LeetCode (`leetcode/`)

LeetCode刷题计划和题解：

#### Study Plans (`studyplan/`)

- `premium_algo_100.java` - LeetCode Plus《尊享面试100题》面试高频算法题解集合
- `premium_sql_50.java` -LeetCode Plus SQL 50题精选

### 3. 🔄 SXL Algorithm (`SXL_algorithm/`)

按算法类型分类的实现，基于《代码随想录》学习路径：

- **基础数据结构**

  - `ArraysAlgo.java` - 数组算法（166行）
  - `LinkedListAlgo.java` - 链表算法（181行）
  - `StringAlgo.java` - 字符串算法（84行）
  - `HashAlgo.java` - 哈希表算法（149行）
- **高级数据结构**

  - `StackQueueAlgo.java` - 栈与队列（182行）
  - `BinaryTreeAlgo.java` - 二叉树算法（111行）
  - `GraphTheoryAlgo.java` - 图论算法（204行）
- **算法技巧**

  - `SortAlgo.java` - 排序算法（172行）
  - `DynamicProgrammingAlgo.java` - 动态规划（258行）
  - `GreedyAlgo.java` - 贪心算法（292行）
  - `backtrackAlgo.java` - 回溯算法（609行）
  - `MonotonicAlgo.java` - 单调栈算法
- **专项练习**

  - `CodeTop.java` - 来自 `CodeTop` 网站，岗位高频面试题
  - `ExtraProblems.java` - 随想录网站的额外练习题部分

## 🏃‍♂️ 快速开始

### 环境要求

- Java 17 或更高版本
- Maven 3.6 或更高版本

### 运行项目

```bash
# 克隆项目
git clone <repository-url>
cd AlgoJolt

# 编译项目
mvn clean compile

# 运行主程序
mvn exec:java -Dexec.mainClass="com.algo.jolt.App"

# 运行特定示例（如线程池演示）
mvn exec:java -Dexec.mainClass="com.algo.jolt.javaLanguage.ExecutorServiceDemo"

# 运行测试
mvn test
```

## 📋 学习建议

### 1. 初学者路径

1. 从 `javaLanguage/` 开始，掌握Java基础特性
2. 学习 `SXL_algorithm/ArraysAlgo.java` 和 `LinkedListAlgo.java`
3. 配合 `SXLWebsite/` 中的PDF教程进行理论学习

### 2. 面试准备

1. 重点关注 `SXL_algorithm/` 中的各类算法实现
2. 刷 `leetcode/studyplan/premiumAlgo100/` 中的面试高频题
3. 练习 `CodeTop.java` 中的企业真题

### 3. 进阶学习

1. 深入学习动态规划、回溯算法等高级技巧
2. 掌握图论算法和复杂数据结构
3. 研究算法的时间复杂度和空间复杂度优化

## 📝 代码风格

项目遵循以下代码规范：

- 使用Java标准命名约定
- 每个算法方法都有详细注释
- 复杂算法提供时间/空间复杂度说明
- 使用Lombok简化样板代码

## 🤝 贡献指南

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交变更 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目仅用于学习目的。

## 📞 联系方式

如有问题或建议，请通过以下方式联系：

- 创建 Issue
- 发送邮件至 xiangri678@outlook.com
