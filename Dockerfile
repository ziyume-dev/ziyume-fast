# 该镜像需要依赖的基础镜像
FROM openjdk:8-jdk-alpine
# 创建文件夹
RUN mkdir -p /root/lfs
# 拷贝jar包，并重命名
COPY lfs-admin/target/lfs-admin-1.0.jar /root/lfs/lfs-admin.jar
# 切换工作目录
ORKDIR /root/lfs/
# 指定docker容器启动时运行jar包
ENTRYPOINT ["nohup", "java", "-jar", "-Dspring.config.location=/root/lfs/application-docker.yml", "/lfs-admin.jar", "&"]
# 指定维护者的名字
MAINTAINER besscroft