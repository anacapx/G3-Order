FROM debrum/ubuntu-jdk-mvn
RUN mkdir g3-order
COPY . g3-order
RUN cd g3-order && mvn clean package -Dmaven.test.skip -e
RUN cp g3-order/target/*jar /app-order.jar
RUN rm -rf g3-order
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app-order.jar" ]