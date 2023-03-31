package pl.softiq.volvo.tax.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.softiq.volvo.tax.dao.AmountDictionaryRepository;
import pl.softiq.volvo.tax.enums.Vehicle;

@Service
@AllArgsConstructor
public class CongestionTaxCalculator {

  private final AmountDictionaryRepository amountDictionaryRepository;
  private final EnumSet<Vehicle> tollFreeVehicles =
      EnumSet.of(Vehicle.MOTORCYCLES, Vehicle.BUS, Vehicle.EMERGENCY, Vehicle.DIPLOMA, Vehicle.MILITARY,
          Vehicle.FOREIGN);

  public int getTax(Vehicle vehicle, List<LocalDateTime> dates) {
    LocalDateTime intervalStart = dates.get(0);
    int totalFee = 0;

    for (int i = 0; i < dates.size(); i++) {
      LocalDateTime date = dates.get(i);
      int nextFee = getTollFee(date, vehicle);
      int tempFee = getTollFee(intervalStart, vehicle);

      long diffInMillies = date.until(intervalStart, ChronoUnit.MILLIS);

      long minutes = diffInMillies / 1000 / 60;

      if (minutes <= 60) {
        if (totalFee > 0) {
          totalFee -= tempFee;
        }
        if (nextFee >= tempFee) {
          tempFee = nextFee;
        }
        totalFee += tempFee;
      } else {
        intervalStart = date;
        totalFee += nextFee;
      }
    }

    if (totalFee > 60) {
      totalFee = 60;
    }
    return totalFee;
  }

  private boolean IsTollFreeVehicle(Vehicle vehicle) {
    if (vehicle == null) {
      return false;
    }
    return tollFreeVehicles.contains(vehicle);
  }

  public int getTollFee(LocalDateTime date, Vehicle vehicle) {
    if (IsTollFreeDate(date) || IsTollFreeVehicle(vehicle)) {
      return 0;
    }

    //data from controller
    int cityId = 1;
    int hour = date.getHour();
    int minute = date.getMinute();
    return amountDictionaryRepository.getFeeByDateAndCity(cityId, LocalTime.of(hour, minute));

  }


  private Boolean IsTollFreeDate(LocalDateTime date) {
    int year = date.getYear();
    int month = date.getMonth().getValue() + 1;
    DayOfWeek day = date.getDayOfWeek();
    int dayOfMonth = date.getDayOfMonth();

    if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
      return true;
    }

    if (year == 2013) {
      return (month == 1 && dayOfMonth == 1) || (month == 3 && (dayOfMonth == 28 || dayOfMonth == 29)) ||
          (month == 4 && (dayOfMonth == 1 || dayOfMonth == 30)) ||
          (month == 5 && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9)) ||
          (month == 6 && (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 21)) || (month == 7) ||
          (month == 11 && dayOfMonth == 1) ||
          (month == 12 && (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31));
    }
    return false;
  }
}
