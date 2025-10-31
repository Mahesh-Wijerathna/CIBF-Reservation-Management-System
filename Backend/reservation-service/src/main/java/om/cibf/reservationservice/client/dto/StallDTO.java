package om.cibf.reservationservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StallDTO {
    private String stallId;
    private String stallNumber;
    private String area;
    private String size;
    private Double price;
    private Boolean available;
}
