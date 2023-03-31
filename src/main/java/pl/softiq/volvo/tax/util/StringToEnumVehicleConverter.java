package pl.softiq.volvo.tax.util;

import org.springframework.core.convert.converter.Converter;
import pl.softiq.volvo.tax.enums.Vehicle;

public class StringToEnumVehicleConverter implements Converter<String, Vehicle> {
    @Override
    public Vehicle convert(String source) {
      return Vehicle.valueOf(source.toUpperCase());
    }
  }
