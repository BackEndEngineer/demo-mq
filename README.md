### 基于 spring boot 的 RabbitMQ demo

#### 本地安装 RabbitMQ (demo版本3.6.5)

[官网下载](http://www.rabbitmq.com/install-standalone-mac.html)

####  对主机名做ip域名绑定

- 查看主机名
```
uname -a
```

- 修改hosts
```
vim ／etc/hosts
#写入
17.0.0.1 主机名
```

#### 启动／关闭／重启
> 以下命令在安装目录下的sbin文件夹内执行

```
./rabbitmq-server start
```

```
./rabbitmqctl stop
```

```
./rabbitmq-server restart
```

#### 查看端口被监听 netstat -nlp | grep beam

#### 开启插件功能

```
sudo ./rabbitmq-plugins enable rabbitmq_management
```

#### 使用web管理RabbitMQ

```
http://127.0.0.1:15672/#/
#默认用户: guest
#默认密码: guest
```

#### 添加 exchanges: exchange0 exchange1

#### 添加 queues: queue0 queue1

#### 创建web管理用户

```
./rabbitmqctl add_user web_admin 123.com #添加web监听账户  
```

#### 设置web管理用户的角色

```
./rabbitmqctl set_user_tags web_admin monitoring #设置用户角色
./rabbitmqctl list_users #查看监听用户
```

### demo测试

- 启动 mq-receiver
- 启动 mq-sender
- 访问 http://127.0.0.1:8080/sender?message=hello123
- 查看后端控制台
