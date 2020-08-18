# 👀 simultaneous-viewing-limit-system
Limit simultaneous viewing with Apache Kafka & Spring Boot Microservices, Redis


## Overview


### 📲 About OTT Service..

</br>

Most people use media services. Recently, users (especially because of the covid-19 😷 ) have been staying indoors, and usage has been increasing. In particular, it uses OTT services, which have subscription-based pricing services.

Therefore, there is a trend that users **share their accounts** and use them. Are there any **policy or technical issues** that could arise from this?

If it is an account sharing service, there is an issue about **`simultaneous viewing restrictions`**. Then, let's implement this simultaneous viewing limit asynchronously and expandable from the basics through various micro-services! 👩🏻‍💻👨‍💻

</br>

### 📮 Why Redis?

In release server, there will be a lot of connections, and watch logs.

When we select log from database, `THERE WILL BE OVERHEAD FOR SELECTS` 

</br>
</br>

### 🧤Why Kafka?

Micro services need to response each other async.

So, use Kafka as a `Message Queue`.

Produce topic, and Consume each topics that micro service need to subscribe.

</br>
</br>
</br>

## 📝 Before we start..

 1. Push service ( Spring Boot Micro service )

2. Check service ( Spring Boot Micro service)

3. Redis Cluster
   - 3 master nodes ↔  3 slave nodes

4. Kafka Cluster
   - 3 kafka cluster ↔ 1 zookeeper server  (Actually, 3 zookeeper server is proper)

All of these servers are executed by `localhost:portnumber`

</br>
</br>

### System Requirements
---
+ Java 8
+ Apache Kafka 2.3.0
+ Redis 6.0.x

</br>
</br>

### You can start Redis & Kafka cluster easily with these documents!
#### Redis Cluster tutorial
[😎  Start Redis cluster easily](https://github.com/kimyuuum/redis-cluster-tutorial)
</br>

#### Kafka tutorial
[🙄 Start Kafka easily](https://github.com/kimyuuum/kafka-cluster-tutorial)
</br>
</br>
