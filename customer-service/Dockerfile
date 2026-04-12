# ── Stage 1: Build ────────────────────────────────────────────────────────────
FROM gradle:8.5-jdk17 AS builder
WORKDIR /workspace

# Copy root Gradle files
COPY build.gradle settings.gradle gradle.properties gradlew gradlew.bat ./
COPY gradle ./gradle

# Copy only the modules we need
COPY common-lib ./common-lib
COPY customer-service ./customer-service

# Build customer-service fat JAR (skip tests for Docker build speed)
RUN gradle :customer-service:bootJar -x test --no-daemon

# ── Stage 2: Runtime ──────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=builder /workspace/customer-service/build/libs/customer-service-1.0.0.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
