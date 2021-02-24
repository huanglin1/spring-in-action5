
##[Spring](https://spring.io/) 的IOC 和AOP
**Spring重要模块**  
![Spring组件](Spring组件.png)
### IOC 面向控制反转
在Spring 我们常说的控制反转和依赖注入其实是一体两面，都是IOC的表现形式，**控制反转(IOC)**，是指对象的创建和配置的控制权从调用方转移给Spring Bean容器。  
有了IOC容器，我们可以将对象交由对象管理，交由容器管理后的对象称之为Bean.调用方不再负责组件的创建，要使用组件时直接获取Bean即可：
```
@Component
public class UserServiceImpl implements UserService{
    @Autowired // 获取 Bean
    private UserDao userDao;
}
```
从另一方面讲，调用方只需要按照约定声明依赖项，所需要的Bean就自动配置完毕了，就好像在调用方外部注入了一个依赖项给其使用，所以这种方法称之为依赖注入（Dependency Injection,DI）
#### 常见的四种Bean注解
我们一般使用@Autowired 注解自动装备Bean,要想把类标识成可用于@Autowired 注解自动装配的bean的类，采用以下注解：
- **@Component:** 通用的注解，可标注任意类为Spring组件。如果一个Bean不知道属于哪个层，可以使用@Component注解标注。
- **@Repository:** 对应持久层即Dao层，主要用于数据库相关操作
- **@Service:** 对应服务层，主要涉及一些服务的逻辑，需要用到Dao层
- **@Controller:** 对应Spring MVC控制层，主要用户接受用户请求并调用Service 层返回数据给前端页面

### AOP 面向切面
**面向对象编程的局限性**
面向对象编码(Object-oriented programming,OOP)的三大特性：封装、继承、多态，大家也是用炉火纯青。OOP的好处也是无需多言，我们来了解下OOP的局限性。  
 当有重复代码出现时，可以就将其封装出来然后复用。我们通过分层、分包、分类来规划不同的逻辑和职责，就像之前讲解的三层架构。但这里的复用的都是核心业务逻辑，
 并不能复用一些辅助逻辑，比如：日志记录、性能统计、安全校验、事务管理，等等。这些边缘逻辑往往贯穿你整个核心业务，传统 OOP 很难将其封装：
```java
public class UserServiceImpl implements UserService {
    @Override
    public void doService() {
        System.out.println("---安全校验---");
        System.out.println("---性能统计 Start---");
        System.out.println("---日志打印 Start---");
        System.out.println("---事务管理 Start---");

        System.out.println("业务逻辑");

        System.out.println("---事务管理 End---");
        System.out.println("---日志打印 End---");
        System.out.println("---性能统计 End---");
    }
}
```
 OOP 是至上而下的编程方式，犹如一个树状图，A调用B、B调用C，或者A继承B、B继承C。
 这种方式对于**业务逻辑**来说是合适的，通过调用或继承以复用。而辅助逻辑就像一把闸刀横向贯穿所有方法，如下图所示  
 ![Spring三层结构](Spring三层结构.jpg)  
 这一条条横线仿佛切开了 OOP 的树状结构，犹如一个大蛋糕被切开多层，每一层都会执行相同的辅助逻辑，
 所以大家将这些辅助逻辑称为层面或者切面。代理模式用来增加或增强原有功能再适合不过了，但切面逻辑的难点不是不修改原有业务，而是对所有业务生效。
 对一个业务类增强就得新建一个代理类，对所有业务增强，每个类都要新建代理类，这无疑是一场灾难。
 而且这里只是演示了一个日志打印的切面逻辑，如果我再加一个性能统计切面，就得新建一个切面代理类来代理日志打印的代理类，一旦切面多起来这个代理类嵌套就会非常深。
 面向切面编程（Aspect-oriented programming，缩写为 AOP）正是为了解决这一问题而诞生的技术。
 
 **2. 面向切面编程AOP** 
 不是 OOP 的对立面，它是对 OOP 的一种补充。OOP 是纵向的，AOP 是横向的，两者相结合方能构建出良好的程序结构。
 [AOP技术](https://blog.csdn.net/yhl_jxy/article/details/78815636)，让我们能够不修改原有代码，便能让切面逻辑在所有业务逻辑中生效。我们只需声明一个切面，写上切面逻辑：@Aspect // 声明一个切面
 ```java
 @Component
 public class MyAspect {
     // 原业务方法执行前
     @Before("execution(public void com.rudecrab.test.service.*.doService())")
     public void methodBefore() {
         System.out.println("===AspectJ 方法执行前===");
     }
 
     // 原业务方法执行后
     @AfterReturning("execution(* com.rudecrab.test.service..doService(..))")
     public void methodAddAfterReturning() {
         System.out.println("===AspectJ 方法执行后===");
     }
 }
 ```
 无论你有一个业务方法，还是一万个业务方法，对我们开发者来说只需编写一次切面逻辑，就能让所有业务方法生效，极大提高了我们的开发效率。
## 关于Thymeleaf
### 学习资料
### 相关知识点






## 源码
[在线电子书](https://potoyang.gitbook.io/spring-in-action-v5/)  
[gitlab源码地址](https://github.com/levinzhang1981/spring-in-action-5-samples)

## 必要的前端知识
[html](https://www.runoob.com/html/html-tutorial.html)  
[js](https://www.runoob.com/js/js-tutorial.html)  
[css](https://www.runoob.com/css/css-tutorial.html)  

## 常见的前端
[thymeleaf官网](https://www.thymeleaf.org/documentation.html)
[thymeleaf中文教程](https://www.docs4dev.com/docs/zh/thymeleaf/3.0/reference/using_thymeleaf.html#introducing-thymeleaf)  
[element-ui](https://element.eleme.cn/#/zh-CN)  
[vue](https://cn.vuejs.org/v2/guide/team.html)

##Tips
### @Controller 和@RestController 的区别
### 将⼀个类声明为Spring的 bean 的注解有哪些?
一般使用@Autowired 注解自动装配bean,要想把类标识成可用于@Autowired 注解自动装配的bean的类，采用以下注解可实现：
- **@Component :**通用的注解，可标记任意类为Spring组件。如果一个Bean不知道属于哪个层，可以使用@Component注解标注。
- **@Repository :**对应持久层即Dao层，主要用于数据库相关操作（CURD）。
- **@Service :** 对应服务层，主要涉及一些复杂的逻辑，需要用到Dao层。
- **@Controller : **对应Spring MVC控制层，主要用于接受用户请求并调用Service层返回数据给前端页面。

### Spring DevTools 配置参考
[devtools实现热部署](https://www.cnblogs.com/lspz/p/6832358.html)

### Lombok 配置参考
[lombok配置参考](https://blog.csdn.net/zhglance/article/details/54931430)  
[lombok详解指南](https://mp.weixin.qq.com/s/R-OplNkv6cfXmSn--VuH3w)
