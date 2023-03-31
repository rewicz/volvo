package pl.softiq.volvo.tax.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.softiq.volvo.tax.enums.Vehicle;

@Getter
@Builder
@Setter
public class CongestionTaxEntity {
  private LocalDate date;
  private Vehicle vehicle;
  private String plate;
  private Integer tax;
}
