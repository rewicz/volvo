package pl.softiq.volvo.tax.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import pl.softiq.volvo.tax.enums.Vehicle;

@Builder
@Getter
public class ActionEntity {
  private LocalDateTime registerDate;
  private LocalDateTime entryDate;
  private Vehicle vehicle;
  private String plate;

}
