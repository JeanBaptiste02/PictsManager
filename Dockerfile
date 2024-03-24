FROM openjdk:17-oracle
COPY target/*.jar pictsmanagerapp.jar
COPY src/main/resources/db/changelog/sql/001_create_model_pictsmanager.sql /scripts/001_create_model_pictsmanager.sql
EXPOSE 8089
ENTRYPOINT [ "java","-jar","pictsmanagerapp.jar" ]