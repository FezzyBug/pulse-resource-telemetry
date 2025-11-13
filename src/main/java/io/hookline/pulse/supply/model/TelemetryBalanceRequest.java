package io.hookline.pulse.supply.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TelemetryBalanceRequest(
        @NotBlank String clusterId,
        @Min(0) @Max(1) double varianceTarget,
        @Min(0) @Max(1) double currentLoad) {
}
