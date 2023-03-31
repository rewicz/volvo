package pl.softiq.volvo.tax.mapper;

import java.time.LocalDateTime;
import pl.softiq.volvo.tax.dto.CongestionTaxDto;
import pl.softiq.volvo.tax.model.ActionEntity;

public class ActionMapper {
  public static ActionEntity map(CongestionTaxDto dto){
    return ActionEntity.builder()
        .registerDate(LocalDateTime.now())
        .entryDate(dto.getDate())
        .vehicle(dto.getVehicle())
        .plate(dto.getPlate())
        .build();
  }
}
