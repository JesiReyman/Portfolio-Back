# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.8.5-eclipse-temurin-17-alpine as builder

# Copy local code to the container image.
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME


# Build a release artifact.
RUN mvn package -DskipTests

# Use AdoptOpenJDK for base image.
# It's important to use OpenJDK 8u191 or above that has container support enabled.
# https://hub.docker.com/r/adoptopenjdk/openjdk8
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM openjdk:17-alpine3.14

# Copy the jar to the production image from the builder stage.
COPY --from=builder /usr/app/target/portfolio-*.jar portfolio.jar

# Run the web service on container startup.
CMD java $JAVA_OPTS -Dserver.port=$PORT -jar portfolio.jar



#COPY "./target/ejemplo-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8000
#ENTRYPOINT ["java","-jar","app.jar"]
