package pl.softiq.volvo.tax.mapper;

import java.time.LocalDate;
import pl.softiq.volvo.tax.dto.CongestionTaxDto;
import pl.softiq.volvo.tax.model.CongestionTaxEntity;

public class CongestionTaxMapper {

  public static CongestionTaxEntity map(CongestionTaxDto dto, int tax){
    return CongestionTaxEntity.builder()
        .date(LocalDate.from(dto.getDate()))
        .vehicle(dto.getVehicle())
        .plate(dto.getPlate())
        .tax(tax)
        .build();
  }

}
