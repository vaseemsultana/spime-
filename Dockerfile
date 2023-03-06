FROM gradle:7.3.3-jdk11

RUN apt-get update && apt-get install -y zip curl

WORKDIR /app/mnt/
ADD src /app/mnt/src
ADD config /app/mnt/config
ADD gradle /app/mnt/gradle
ADD libs /app/mnt/libs
ADD entrypoint.sh /app/entrypoint.sh
ADD gradle.properties /app/mnt/gradle.properties
ADD settings.gradle /app/mnt/settings.gradle
ADD build.gradle /app/mnt/build.gradle

RUN chmod +x /app/entrypoint.sh
RUN tr -d '\r' < /app/entrypoint.sh > /app/mnt/entrypoint.sh
RUN chmod +x /app/mnt/entrypoint.sh

ENTRYPOINT [ "/app/mnt/entrypoint.sh" ]