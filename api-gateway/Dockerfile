# ── Stage 1: Build ────────────────────────────────────────────────────────────
FROM gradle:8.5-jdk17 AS builder
WORKDIR /workspace

# Copy root Gradle files
COPY build.gradle settings.gradle gradle.properties gradlew gradlew.bat ./
COPY gradle ./gradle

# Copy only the modules we need (gateway has no common-lib dep)
COPY api-gateway ./api-gateway

# Build api-gateway fat JAR (skip tests for Docker build speed)
RUN gradle :api-gateway:bootJar -x test --no-daemon

# ── Stage 2: Runtime ──────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=builder /workspace/api-gateway/build/libs/api-gateway-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
