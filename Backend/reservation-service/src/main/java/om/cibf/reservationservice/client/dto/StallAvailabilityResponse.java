package om.cibf.reservationservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StallAvailabilityResponse {
    private Boolean available;
    private String message;
    private String stallId;
}
