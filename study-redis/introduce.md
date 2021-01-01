Redis

**1：为什么使用redis而不用map、guave做缓存？**
```text
缓存分为本地缓存和分布式缓存，java自带的map或者guava实现的是本地缓存，最主要的特点是轻量以及快速，生命周期随着jvm的销毁二结束，并且在多实例的情况下，每个实例都需要
各自保存一份缓存，缓存不具有一致性；
使用redis或者memcached之类的称之为分布式缓存，在多实例的情况下，各实例公用一份缓存数据，缓存具有一致性，缺点是需要保持redis或memcached服务的高可用，整个架构上较为复杂。
```
**2：redis和memcached的区别**
```text

1.1 redis支持更丰富的数据类型，：Redis不仅仅支持简单的key、value类型的数据，同时还提供lis、set、zset、hash等数据结构的存储，memcached支持简单的数据类型 string
1.2 redis支持数据的持久化，可以将内存中的数据保持在磁盘中，重启的时候可以再次加载镜像使用，而mamcache吧数据全部存在内存之中
1.3 集群模式：memcached没有原生的的集群模式，需要依靠客户端来实现集群中的分片写入数据，但是redis目前是原生支持cluster模式的
1.4 memcached是多线程，非阻塞IO复用的网络模型，Redis使用单线程的多路IO复用模型

```
3:redis常见数据结构以及使用场景分析
```text
1.1 String
  常用命令：set/get/deci/incr/mget等
  String数据结构是简单的key-value类型，value其实不仅可以是String，也可以是数字。 常规key-value缓存应用； 常规计数：微博数，粉丝数等。
1.2.Hash
　　常用命令： hget,hset,hgetall 等。
　　hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象，后续操作的时候，你可以直接仅仅修改这个对象中的某个字段的值。 比如我们可以 hash 数据结构来存储用户信息，商品信息等等。.
比如下面我就用 hash 类型存放了我本人的一些信息：
key=JavaUser293847 
value={  
       “id”: 1,  
       “name”: “SnailClimb”,  
       “age”: 22,  
        “location”: “Wuhan, Hubei”
         }
1.3.List  
　　常用命令: lpush,rpush,lpop,rpop,lrange等  
　　list 就是链表，Redis list 的应用场景非常多，也是Redis最重要的数据结构之一，比如微博的关注列表，粉丝列表，消息列表等功能都可以用Redis的 list 结构来实现。  
　　Redis list 的实现为一个双向链表，即可以支持反向查找和遍历，更方便操作，不过带来了部分额外的内存开销。  
　　另外可以通过 lrange 命令，就是从某个元素开始读取多少个元素，可以基于 list 实现分页查询，这个很棒的一个功能，
基于 redis 实现简单的高性能分页，可以做类似微博那种下拉不断分页的东西（一页一页的往下走），性能高。
1.4.Set  
　　常用命令： sadd,spop,smembers,sunion 等  
　　set 对外提供的功能与list类似是一个列表的功能，特殊之处在于 set 是可以自动排重的。  
　　当你需要存储一个列表数据，又不希望出现重复数据时，set是一个很好的选择，并且set提供了判断某个成员是否在一个set集合内的重要接口，这个也是list所不能提供的。可以基于 set 轻易实现交集、并集、差集的操作。  
　　比如：在微博应用中，可以将一个用户所有的关注人存在一个集合中，将其所有粉丝存在一个集合。Redis可以非常方便的实现如共同关注、共同粉丝、共同喜好等功能。这个过程也就是求交集的过程，具体命令如下：
   sinterstore key1 key2 key3     将交集存在key1内
1.5.Sorted Set  
  　　常用命令： zadd,zrange,zrem,zcard等  
  　　和set相比，sorted set增加了一个权重参数score，使得集合中的元素能够按score进行有序排列。 

```
4：redis设置过期时间
作为一个缓存数据库，这个是非常使用的