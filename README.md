## springDemon ##

 - spring study example
 - spring 模块的功能学习和演示，主要涵盖了spring 框架功能的作用和基本的示例代码。
 - spring 版本号：`4.1.4.RELEASE`
 
### 已学习的模块 ###
 
   * __xml文件__：
   
      - Spring bean 的配置：
         - bean 的配置
         - beanFactory 的配置
      - aop：
         - aspectj （ @Log 自定义注解实现日志打印）
         - introduction 在不改变原有方法的基础上却可以增加新的方法
         - selfScope 自定义作用域
      - aware：通过实现*Aware 接口，让业务代码对Spring 容器有感知：获取spring 框架的一些特殊功能类。
         - ApplicationContextAware
         - ApplicationEventPulisherWare
         - BeanFactoryAware
         - BeanNameAware
         - EmbeddedValueResolverAware
         - EnvironmentAware
         - MessageSourceAware
         - ResourceLoaderAware
      - environment 环境变量：（自定义环境变量）
      - event 事件驱动模型：发布订阅模式	
      - global 国际化定义
      - init bean 生命周期中的一些方法执行顺序问题。
      - lookUpMethod 方法注入：通过方法获取指定的对象
      - processor bean定义的一些处理器
         - BeanPostProcessor
         - BeanFactoryPostProcessor
         - BeanDefinitionRegistryPostProcessor
         - SmartLifecycle
      - propertyEditor 属性编辑器
      - replace-method 方法替换
      - task 定时任务
         - quartz
         - scheduled
         - springTask
         - Timer
         
   * __annotation方式__：
   
       - postConstructor
       - aop
