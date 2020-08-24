# 👀 simultaneous-viewing-limit-system
Limit simultaneous viewing with Apache Kafka & Spring Boot Microservices, Redis

I ❤️ `issue` & `pull requests` & ... 


## Overview


### 📲 About OTT Service..

</br>

Most people use media services. Recently, users (especially because of the covid-19 😷 ) have been staying indoors, and usage has been increasing. In particular, it uses OTT services, which have subscription-based pricing services.

Therefore, there is a trend that users **share their accounts** and use them together. Are there any **policy or technical issues** that could arise from this?

If it is an account sharing service, there is an issue about **`simultaneous viewing restrictions`**. Then, let's implement this simultaneous viewing limit asynchronously and expandable from the basics through various micro-services! 👩🏻‍💻👨‍💻

</br>

### 📮 Why Redis?

In release server, there will be a lot of connections, and watch logs.

This system checks log with `key = set-top-box_id` & `hashkey = unique vod episode_id` for searching faster

</br>
</br>

### 🧤 Why Kafka?

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

### System Architecture

![image](https://user-images.githubusercontent.com/46887352/91052403-5ffcbc80-e65c-11ea-9f86-510c228c4e37.png)


</br>
</br>

## System Requirements
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

### Server ports

Push Service : `9002`
</br>
Check Service : `9000`
</br>
Redis Cluster : `7001 ~ 7005`
</br>
Kafka Cluster : `9093 ~ 9095`
</br>
Zookeeper : `2182`
</br>
</br>

### Required Parameter

**Request Params for Start VOD**

```
{
  pcid = "username",
  episode_id = "episode_id",
  stb_id = "stb_id",
  play_start = "YYYY-mm-dd'T'HH:mm:ss",
  mac_address = "mac_address",
  running = true
  
}
```

**Request Params for Stop VOD**

```
{
  pcid = "username",
  episode_id = "episode_id",
  stb_id = "stb_id",
  play_start = "YYYY-mm-dd'T'HH:mm:ss",
  play_end = "YYYY-mm-dd'T'HH:mm:ss",
  mac_address = "mac_address",
  running = false
}

```

</br>
</br>

## API Server


| METHOD | PATH   | DESCRIPTION                                                  |
| ------ | ------ | ------------------------------------------------------------ |
| POST   | /send  | Send request data for start watching VOD when new user connects |
| POST   | /force | Send request data for start watching VOD when another user exists |
| PATCH  | /stop  | Send request data for stop watching VOD                      |

</br>
</br>

| PATH              | DESCRIPTION                                                |
| ----------------- | ---------------------------------------------------------- |
| /queue/notify     | send notify to user who subscribed each channel            |
| /queue/disconnect | send disconnect notify to user who subscribed each channel |
| /queue/connect    | send success response to user who subscribed each channel  |


</br>
</br>

## Execute Spring boot application

1. Execute with mvnw

```bash
$ ./mvnw spring-boot:run
```
</br>

2. Maven Packaging

```bash
$ mvn package
```
</br>

3. Execute with jar file

```bash
$ java -jar target/push-service-0.0.1-SNAPSHOT.jar
$ java -jar target/check-service-0.0.1-SNAPSHOT.jar
```

</br>
</br>

## Conclusion

Now you can use this Viewing-Limit-System.

Contact with my github profiles / dbals4818@gmail.com

