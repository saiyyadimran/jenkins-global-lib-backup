FROM java
COPY target/*.jar /tmp
EXPOSE 18999
ENTRYPOINT ["sh", "-c", "java -jar /tmp/*.jar"]

