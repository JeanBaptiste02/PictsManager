FROM openjdk:17-oracle
COPY target/*.jar pictsmanagerapp.jar
EXPOSE 8089
ENTRYPOINT [ "java","-jar","pictsmanagerapp.jar" ]