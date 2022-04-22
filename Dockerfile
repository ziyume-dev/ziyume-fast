# 该镜像需要依赖的基础镜像
FROM openjdk:8-jdk-alpine
# 设定时区
ENV TZ=Asia/Shanghai
RUN set -eux; \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime; \
    echo $TZ > /etc/timezone
# 创建文件夹
RUN mkdir -p /root/lfs
# 拷贝jar包，并重命名
COPY lfs-admin/target/lfs-admin-1.0.jar /lfs-admin.jar
# 设置环境变量
ENV JAVA_OPTS="-Xms512m -Xmx512m" SPRING_CONFIG="-Dspring.config.location=/root/lfs/application-docker.yml"
# 指定docker容器启动时运行jar包
CMD nohup java ${JAVA_OPTS} -jar ${SPRING_CONFIG} /lfs-admin.jar &
# 指定维护者的名字
MAINTAINER besscroft