FROM java:8
EXPOSE 8080:8080
ADD /target/springDocker-demo.jar springDocker-demo.jar
ENTRYPOINT ["java", "-jar", "springDocker-demo.jar"]