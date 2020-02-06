FROM openjdk:8-jdk-alpine AS compilation
WORKDIR /app
COPY . .
RUN ./mvnw clean install -U -Dsurefire.rerunFailingTestsCount=5 -Dfailsafe.rerunFailingTestsCount=5

FROM openjdk:8-jdk-alpine AS builder
WORKDIR target/dependency
ARG APPJAR=/app/target/*.jar
COPY --from=compilation ${APPJAR} app.jar
RUN jar -xf ./app.jar

FROM alpine AS git
WORKDIR git
COPY .git .git

FROM alpine AS scope
WORKDIR scope
COPY .scope/*.jar scope-agent.jar
COPY scope.yaml scope.yaml

FROM openjdk:8-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
ARG GIT=git
ARG SCOPE=scope
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
COPY --from=git ${GIT}/.git /app/.git
COPY --from=scope ${SCOPE}/scope-agent.jar /app/agent/scope-agent.jar
COPY --from=scope ${SCOPE}/scope.yaml /app/scope.yaml
WORKDIR app
ENTRYPOINT ["java", "-javaagent:/app/agent/scope-agent.jar","-cp","/app:/app/lib/*","com.undefinedlabs.scope.Application"]