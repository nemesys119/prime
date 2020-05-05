From openjdk:11
copy ./target/prime-0.0.1-SNAPSHOT.jar prime-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","prime-0.0.1-SNAPSHOT.jar"]