package pl.softiq.volvo.tax.model;

import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AmountDictionaryEntity {
  Integer cityId;
  LocalTime from;
  LocalTime to;
  int value;
}
