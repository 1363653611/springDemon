对容器的感知：

	容器管理的Bean一般不需要了解容器的状态和直接使用容器，但是在某些情况下，是需要在Bean中直接对IOC容器进行操作的，这时候，
就需要在Bean中设定对容器的感知。Spring IOC容器也提供了该功能，它是通过特定的Aware接口来完成的。

aware接口有以下这些：

	BeanNameAware，可以在Bean中得到它在IOC容器中的Bean的实例的名字。

	BeanFactoryAware，可以在Bean中得到Bean所在的IOC容器，从而直接在Bean中使用IOC容器的服务。

	ApplicationContextAware，可以在Bean中得到Bean所在的应用上下文，从而直接在Bean中使用上下文的服务。

	MessageSourceAware，在Bean中可以得到消息源。

	ApplicationEventPublisherAware，在bean中可以得到应用上下文的事件发布器，从而可以在Bean中发布应用上下文的事件。

	ResourceLoaderAware，在Bean中可以得到ResourceLoader，从而在bean中使用ResourceLoader加载外部对应的Resource资源。


bean 的生命周期：

Bean实例生命周期的执行过程如下：

	Spring对bean进行实例化，默认bean是单例；

	Spring对bean进行依赖注入；

	如果bean实现了BeanNameAware接口，spring将bean的id传给setBeanName()方法；

	如果bean实现了BeanFactoryAware接口，spring将调用setBeanFactory方法，将BeanFactory实例传进来；

	如果bean实现了ApplicationContextAware接口，它的setApplicationContext()方法将被调用，将应用上下文的引用传入到bean中；

	如果bean实现了BeanPostProcessor接口，它的postProcessBeforeInitialization方法将被调用；

	如果bean实现了InitializingBean接口，spring将调用它的afterPropertiesSet接口方法，类似的如果bean使用了init-method属性声明了初始化方法，该方法也会被调用；

	如果bean实现了BeanPostProcessor接口，它的postProcessAfterInitialization接口方法将被调用；

	此时bean已经准备就绪，可以被应用程序使用了，他们将一直驻留在应用上下文中，直到该应用上下文被销毁；

	若bean实现了DisposableBean接口，spring将调用它的distroy()接口方法。同样的，如果bean使用了destroy-method属性声明了销毁方法，则该方法被调用；


原文链接：https://blog.csdn.net/iechenyb/article/details/83788338
原文链接：https://blog.csdn.net/ilovejava_2010/article/details/7953582