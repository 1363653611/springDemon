#  环境切换(dev/prod)
- 测试入口： `com.zbcn.web.env.prod.ProdServiceDemonTest`

# 拦截器（Interceptor）
## 全局异常处理
- @ControllerAdvice注解，这个是spring3.2提供的新注解，从名字上可以看出大体意思是控制器增强。
### 全局异常处理注意事项
1. 要根据不同的异常返回合适的HTTP状态码
2. 异常结果返回时，不要将详细的异常堆栈信息由接口返回给前端（这可能会暴漏一些敏感信息，造成安全隐患，再者说前端开发根本不会关心这些）
3. 约定HTTP状态码为500时，都是后端程序上的错误，一定要修复掉
4. 对于系统的错误，一定要详细的打印好日志，要将异常的详细堆栈上下文信息都打印出来（有的同学只打印e.getMessage() -_-|| ），方便线上排查问题

## 接口响应体格式封装
- ControllerAdvice、ResponseBodyAdvice这两个类是本功能的关键使用类，用于接口的响应体增强，其中supports方法用于判断是否需要做增强转化，beforeBodyWrite方法用于增加逻辑实现
### 实现思路：
1. Result 是返回格式类的父接口（所有返回格式类都需要继承它）
2. PlatformResult 通用返回结果格式（我们上面说的第二种返回结果）
3. DefaultErrorResult 全局错误返回结果（我们上面说的第一种错误时的返回结果）
4. GlobalExceptionHandler全局异常处理
5. ResponseResult 注解类（用于在Controller上指定返回值格式类）
6. ResponseResultInterceptor 拦截器（主要用于将ResponseResult注解类的标记信息传入ResponseResultHandler中）
7. ResponseResultHandler 响应体格式处理器（主要转换逻辑都在这里

## 登陆校验
- 我们需要一个Java注解，使用这个注解标记在Controller的类或某个方法上时，就代表着该类下所有方法或某个注解标记的方法需要登录后才可以进行访问。
### 实现思路
- 我们需要一个注解，名字定义为@LoginedAuth，然后需要使用拦截器，名字就定为LoginedAuthInterceptor，在调用controller方法前该拦截器根据调用类的基本信息判定是否标记了登录鉴权注解，如果在所在方法或该方法所在类上有标记，则进行登录鉴权逻辑。
- 我们约定用户的身份信息通过http请求头参数方式传递，其参数字段名字为X-Token，当然在你的系统中也可以设计为放入cookie+header两种并行的方式。

## 跨域问题解决
- 跨域访问，简单来说就是 A 网站的 javascript 代码试图访问 B 网站，包括对后端数据的增删改查都会引起。由于安全原因，浏览器统一遵循了一种策略，这个策略就是同源策略，同源策略也是浏览器最核心、最基本的安全功能
### 什么情况下回跨域?
|前端页面地址|	后端服务地址|	跨域原因	|备注|
|----------|--------------|------------|-----|
| http://www.zm.com	|http://www.zhuma.com	|不能域名	|主域相同，子域名不同也会出现跨域|
|http://www.zm.com	|http://192.168.10.19	|不同域名	|域名和该域名对应ip也是不允许的|
|http://www.zm.com	|https://www.zm.com	|不同协议|	
|http://www.zm:80.com|	https://www.zm:8080.com	|不同端口|

从上面的表格中我们可以看出，协议、域名、端口三者之间任意一与当前页面地址不同都会引起跨域问题。

### 解决方案
- 在我们的实际开发过程中，我们会使用 CORS方式，自己定义个拦截器 对开发、测试环境解决跨域问题，线上环境如果出现跨域问题会使用nginx做个代理中转的这种方式，这里说明下线上环境尽量保证 接口、页面 同一域名

#### CORS方式
- 这是W3C提供的另一种跨域方式，作为一项标准的跨域规范，我们使用我们自己定义的拦截器进行统一设置实现解决测试环境上的跨域问题

#### 后端服务做代理
- 使用nginx这种服务器，在后端做一个中转。本文我们不是重点讲这种方式，所以想了解的自行百度下吧
#### JSONP 
- 这种方式缺点是只能支持GET方法的请求，我看到一些公司为了支持JSONP将后端的添加、修改、删除功能接口都改为GET方式，这种是很不建议的，这样会存在安全隐患。如果你用的是spring boot的话可以参考下AbstractJsonpResponseBodyAdvice这个类的使用。


## 统一参数校验（全局参数，请求头中）
－　在对外开放接口的时候，我们的调用端是很多的，比如：APP/PC/WECHAT公众号or小程序 等等，当线上环境某一个用户出现问题时，如果这个问题仅仅是后端还好，但是如果是前后端需要配合解决的错误，我们就需要更多的调用客户端的一些信息，这个时候你去问客户app什么版本、什么手机这显然是不妥的，所以我们应该收集更多的调用信息，以便我们做后续业务处理、日志记录等等的一些操作，通常需要对客户端统一收集的信息比如 调用来源、app版本号、api的版本号、安全验证信息 等等。
### 解决方式
我们将这些信息放入头信息（HTTP HEAD中），下面给出在参数命名的例子：

1. X-Token 用户的登录token（用于兑换用户登录信息）
2. Api-Version api的版本号
3. App-Version app版本号
4. Call-Source 调用来源(IOS、ANDROID、PC、WECHAT、WEB)
5. Authorization 安全校验参数

# ASPECTJ（面向切面）

## 请求 统一日志打印
- 响应体的日志打印功能，这在企业开发中也是很有必要的哦，可以减少我们浪费在日志打印上的一些时间，也可以统一日志的打印格式，以后在使用ELK这种日志搜索服务时，你也将会得到很不错的使用体验。

###　思路
我们使用@Aspect标识一个切面类，使用@Around注解标识在你打印日志的方法里，主要捕捉@RestController带这个注解的类方法，也就是我们的reset controller下的方法，这样就可以在joinPoint.proceed() 程序的处理前后打印上日志信息，我们目前打印的主要信息有，请求者、请求的类.方法名字、请求的参数（参数敏感信息需要用 “**”替换）、请求者IP、调用源、App版本号、Api版本号、客户端的userAgent信息、响应的结果（敏感词过滤）、最后请求结束后所花费的时间。

## 参数校验
- 在企业开发过程中，我们比较烦的也就是参数校验这一环节了，但是这一步又是不能省略掉的.
hibernate validator + org.springframework.util.Assert 两种方式结合,可以帮助我们方便的校验请求参数.

### hibernate validator的使用
- validation-api-xx.xx.xx.Final.jar是JDK的接口，是一套校验参数的统一规范；
- hibernate-validator-xx.xx.xx.Final.jar是对上述接口的实现

## 参考:
- 参数校验:https://www.iteye.com/blog/jinnianshilongnian-1990081
- zhuma: https://github.com/zhumaer/zhuma