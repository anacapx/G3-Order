FROM debrum/ubuntu-jdk-mvn
RUN mkdir g3-order
COPY . g3-order
RUN cd g3-order && mvn clean package -Dmaven.test.skip
RUN cp g3-order/target/*jar /app-order.jar
RUN cp g3-order/newrelic/*jar /newrelic-order.jar
RUN cp g3-order/newrelic/*yml /newrelic.yml
ENV NEW_RELIC_APP_NAME=${NEW_RELIC_APP_NAME}
ENV NEW_RELIC_LICENSE_KEY=${NEW_RELIC_LICENSE_KEY}
ENV NEW_RELIC_LOG_FILE_NAME=${NEW_RELIC_LOG_FILE_NAME}
EXPOSE 8080
ENTRYPOINT ["java","-javaagent:/newrelic-order.jar","-jar","/app-order.jar"]
