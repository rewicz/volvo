package pl.softiq.volvo.tax.dao;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import pl.softiq.volvo.tax.model.ActionEntity;

@Repository
public class ActionRepository {

  static List<ActionEntity> listOfActions = new ArrayList<>();

  public void saveAction(ActionEntity actionEntity) {
    listOfActions.add(actionEntity);
  }

  public List<ActionEntity> getLastDayActionsByPlate(String plate, LocalDateTime entryDate) {
    LocalDateTime localDateTime = entryDate.truncatedTo(ChronoUnit.DAYS);
    return listOfActions.stream().filter(
        tax -> tax.getPlate().equals(plate) && tax.getEntryDate().isAfter(localDateTime) &&
            tax.getEntryDate().isBefore(localDateTime.plusDays(1))).toList();
  }
}
