package pl.softiq.volvo.tax.controller;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.softiq.volvo.tax.dto.CongestionTaxDto;
import pl.softiq.volvo.tax.enums.Vehicle;
import pl.softiq.volvo.tax.service.CongestionTaxService;

@RestController
@RequestMapping(value = {"register"})
@RequiredArgsConstructor
@Slf4j
public class CongestionTaxController {

  private final CongestionTaxService congestionTaxService;

  @PostMapping()
  public ResponseEntity<?> registerEntry(@RequestParam(name = "vehicle") Vehicle vehicleType,
                                         @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
                                         @RequestParam(name = "plate") String plate) {
    log.info("Entry registration vahicle: " + vehicleType.name() + " at: " + date.toString());

    congestionTaxService.registerEntry(CongestionTaxDto.builder().plate(plate).date(date).vehicle(vehicleType).build());

    return ResponseEntity.ok("Registered entry for " + vehicleType.name());
  }
}
