package pl.softiq.volvo.tax.dto;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class ApiBasicResponse {
  private Boolean success;
  private String message;
}
