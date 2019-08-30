事件驱动模型：发布订阅模式	

1. 广播的核心接口是ApplicationEventPublisher； 
2. 我们见过了如何发起一条广播，监听器如何响应广播；
提到广播与监听，我们常常会想到RabbitMQ、Kafka等消息中间件，这些常用于分布式系统中多个应用之间，有时候应用自身内部也有广播和监听的需求（例如某个核心数据发生变化后，有些业务模块希望立即被感知），
这时候spring提供的基于ApplicationContext的广播与监听就派上用场了

原文链接：https://blog.csdn.net/boling_cavalry/article/details/81697314