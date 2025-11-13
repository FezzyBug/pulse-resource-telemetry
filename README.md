# Pulse Resource Telemetry

Pulse Resource Telemetry balances compute capacity across Hookline's simulated edge clusters. The service exposes an HTTP endpoint that mirrors the `io.hookline.pulse.supply.ResourceTelemetryService` log statements used throughout the LogTracer demo data.

## What You Get
- `/telemetry/balance` endpoint that reports variance and balancing actions
- Identical log lines for "Telemetry balance check started" and "Telemetry balance reconciled"
- Spring Boot + Maven stack targeting Java 17

## Usage
```bash
sdk use java 17-tem
mvn spring-boot:run
```
Service URL: `http://localhost:8084`

```bash
curl -X POST http://localhost:8084/telemetry/balance \
     -H 'Content-Type: application/json' \
     -d '{"clusterId":"delta","varianceTarget":0.5,"currentLoad":0.82}'
```

## Repository Structure
```
src/main/java/io/hookline/pulse/supply/ResourceTelemetryApplication.java
src/main/java/io/hookline/pulse/supply/ResourceTelemetryService.java
src/main/java/io/hookline/pulse/supply/model/TelemetryBalanceRequest.java
```

Clone this directory on its own when publishing the telemetry microservice to GitHub.
