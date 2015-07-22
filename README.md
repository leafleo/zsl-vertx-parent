# zsl-vertx-parent
zsl-vertx-parent（要求jdk8支持）基于Vert.x3.0是一个轻量级的高性能JVM应用平台，基于它可开发各种移动，Web和企业应用程序。
一个主要特点是可使用多种语言编写应用，如Java, JavaScript, CoffeeScript, Ruby, Python 或 Groovy等等，
它的简单actor-like机制能帮助脱离直接基于多线程编程。它是基于Netty和Java 7的NIO2的编写的。
当前业界遭遇C10K问题，当并发连接超过10,000+以上时使用传统技术会引发暂停，移动设备或视频 声音如类似微信这样的实时聊天，
都是属于长任务连接Long-lived。
通常Tomcat会在100个并发长请求(这个请求要求做很多事长任务)下堵塞，
而Vertx将长任务委托给另外一个线程来执行，从而不会堵塞当前线程，与NodeJS的原理非常类似。
