FROM openjdk:17
COPY target/*.jar pictsmanagerapp.jar

EXPOSE 8080

HEALTHCHECK --interval=10s --timeout=3s \
    CMD curl -v --fail http://localhost:8080/version || exit 1

ENTRYPOINT [ "java","-jar","pictsmanagerapp.jar" ]