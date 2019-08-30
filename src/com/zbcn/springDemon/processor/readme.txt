BeanPostProcessor:
	1. 改变bean的定义(BeanFactoryPostProcessor接口) ，可以想象成修改了class文件，这样实例化出来的每个对象都变了； 
	2. 只改变实例化的对象(BeanPostProcessor接口)；
对BeanPostProcessor有关的源码分析就完成了，小结一下： 
1. 初始化时，spring容器有特别处理，会直接调用beanFactory.addBeanPostProcessor进行注册（例如AbstractApplicationContext类的prepareBeanFactory方法中就有）； 
2. 找出所有实现了BeanPostProcessor接口的bean，注册到容器，注册顺序如下： 
第一：实现了PriorityOrdered接口的，排序后； 
第二：实现了Ordered接口的，排序后； 
第三：既没实现PriorityOrdered接口，也没有实现Ordered接口的； 
第四：实现了MergedBeanDefinitionPostProcessor接口的（这些也按照PriorityOrdered、Ordered等逻辑拍过续）； 
第五：实例化一个ApplicationListenerDetector对象； 
3. 实例化bean的时候，对于每个bean，先用MergedBeanDefinitionPostProcessor实现类的postProcessMergedBeanDefinition方法处理每个bean的定义类； 
4. 再用BeanPostProcessor的postProcessBeforeInitialization方法处理每个bean实例； 
5. bean实例初始化； 
6. 用BeanPostProcessor的postProcessAfterInitialization方法处理每个bean实例；
原文链接：https://blog.csdn.net/boling_cavalry/article/details/82250986

BeanFactoryPostProcessor:
	spring容器初始化时，从资源中读取到bean的相关定义后，保存在beanFactory的成员变量中（参考DefaultListableBeanFactory类的成员变量beanDefinitionMap），在实例化bean的操作就是依据这些bean的定义来做的，而在实例化之前，spring允许我们通过自定义扩展来改变bean的定义，定义一旦变了，后面的实例也就变了，而beanFactory后置处理器，即BeanFactoryPostProcessor就是用来改变bean定义的

小结：
1. ApplicationContext扩展类可以调用AbstractApplicationContext.addBeanFactoryPostProcessor方法，将自定义的BeanFactoryPostProcessor实现类保存到ApplicationContext中； 
2. spring容器初始化时，上一步中被加入到ApplicationContext的bean会被优先调用其postProcessBeanFactory方法； 
3. 自定义的BeanFactoryPostProcessor接口实现类，也会被找出来，然后调用其postProcessBeanFactory方法； 
4. postProcessBeanFactory方法被调用时，beanFactory会被作为参数传入，自定义类中可以使用该参数来处理bean的定义，达到业务需求； 
5. 此时的spring容器还没有开始实例化bean，因此自定义的BeanFactoryPostProcessor实现类不要做与bean实例有关的操作，而是做一些与bean定义有关的操作，例如修改某些字段的值，这样后面实例化的bean的就会有相应的改变；

原文链接:https://blog.csdn.net/boling_cavalry/article/details/82083889

BeanDefinitionRegistryPostProcessor：

	我们开发的类，如果想注册到spring容器，让spring来完成实例化，常用方式如下： 
	1. xml中通过bean节点来配置； 
	2. 使用@Service、@Controller、@Conponent等注解； 
	3. 其实，除了以上方式，spring还支持我们通过代码来将指定的类注册到spring容器中，也就是今天我们要实践的主要内容，接下来就从spring源码开始，先学习源码再动手实战；

原文链接：https://blog.csdn.net/boling_cavalry/article/details/82193692

LifecycleProcessor:
关于容器启动时的Lifecycle的处理:
以上就是初始化阶段容器对SmartLifecycle实例的处理逻辑，简单的小结如下： 
1. Lifecycle的处理都是委托给LifecycleProcessor执行的，先准备好此实例； 
2. 将所有的Lifecycle实例按照phase分组； 
3. 从phase值最小的分组开始，依次执行其中每个Lifecycle对象的start方法；

以上就是关闭容器阶段对SmartLifecycle实例的处理逻辑，简单的小结如下： 
1. AbstractApplicationContext的doClose方法在容器关闭时会被执行，此处调用LifecycleProcessor的onClose方法，由LifecycleProcessor负责所有Lifecycle实例的关闭操作； 
2. 将所有的Lifecycle实例按照phase分组； 
3. 从phase值最大的分组开始，依次执行其中每个Lifecycle对象的stop方法； 
4. 对每个SmartLifecycle实例，若想并行执行以加快stop执行速度，可以在stop方法中用新的线程来执行stop业务逻辑，但是最后不要忘记调用Runnable入参的run方法，以完成主线程的计数和统计； 
5. 主线程使用了CountDownLatch，在调用了SmartLifecycle实例的stop方法后就会等待，等到计数达到SmartLifecycle总数或者等待超时，再继续向后执行；

fecycle和SmartLifecycle，自定义的时候用哪个？
看了上面的源码分析，我们对Lifecycle和SmartLifecycle有了更全面的认知，如果对执行顺序没有要求，在关闭的时候也没有性能或者时间要求，那么就用Lifecycle吧，因为更简单，如果在乎顺序，也期望关闭时多个Lifecycle实例能并行执行，快速结束，SmartLifecycle无疑更适合；


原文链接：https://blog.csdn.net/boling_cavalry/article/details/82051356












