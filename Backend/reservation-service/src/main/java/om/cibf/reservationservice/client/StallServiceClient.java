package om.cibf.reservationservice.client;

import om.cibf.reservationservice.client.dto.StallDTO;
import om.cibf.reservationservice.client.dto.StallAvailabilityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "stall-service", url = "${stall.service.url}")
public interface StallServiceClient {
    
    @GetMapping("/api/v1/stalls/{stallId}")
    StallDTO getStallById(@PathVariable("stallId") String stallId);
    
    @GetMapping("/api/v1/stalls/{stallId}/availability")
    StallAvailabilityResponse checkAvailability(
            @PathVariable("stallId") String stallId,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime
    );
    
    @PutMapping("/api/v1/stalls/{stallId}/reserve")
    void reserveStall(@PathVariable("stallId") String stallId);
    
    @PutMapping("/api/v1/stalls/{stallId}/release")
    void releaseStall(@PathVariable("stallId") String stallId);
}
