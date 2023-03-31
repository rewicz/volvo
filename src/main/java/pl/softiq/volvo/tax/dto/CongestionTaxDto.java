package pl.softiq.volvo.tax.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import pl.softiq.volvo.tax.enums.Vehicle;

@Data
@Getter
@Builder
public class CongestionTaxDto {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
  private Vehicle vehicle;
  private String plate;

}
