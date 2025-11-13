package io.hookline.pulse.supply;

import io.hookline.pulse.supply.model.TelemetryBalanceRequest;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telemetry")
@Validated
public class ResourceTelemetryService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceTelemetryService.class);

    @PostMapping("/balance")
    public ResponseEntity<Map<String, Object>> balance(@RequestBody @Valid TelemetryBalanceRequest request) {
        logger.info("######### Telemetry balance check started cluster={} varianceTarget={}",
                request.clusterId(), request.varianceTarget());

        double variance = Math.abs(request.currentLoad() - request.varianceTarget());
        String action = variance > 0.1 ? "rebalanced" : "stable";

        logger.info("######### Telemetry balance reconciled cluster={} variance={}",
                request.clusterId(), String.format("%.3f", variance));

        Map<String, Object> body = Map.of(
                "clusterId", request.clusterId(),
                "completedAt", Instant.now().toString(),
                "variance", variance,
                "action", action);

        return ResponseEntity.ok(body);
    }
}
