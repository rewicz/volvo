package pl.softiq.volvo.tax.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import pl.softiq.volvo.tax.model.CongestionTaxEntity;

@Repository
public class CongestionTaxRepository {
  static List<CongestionTaxEntity> listOfTaxes = new ArrayList<>();

  public void saveCongestionTax(CongestionTaxEntity entity) {

    Optional<CongestionTaxEntity> taxDay = listOfTaxes.stream()
        .filter(tax -> tax.getPlate().equals(entity.getPlate()) && tax.getDate().equals(entity.getDate())).findFirst();

    if (taxDay.isPresent()) {
      //duplicat
      taxDay.get().setTax(entity.getTax());
    } else {
      addCongestionTax(entity);
    }
  }

  public void addCongestionTax(CongestionTaxEntity entity) {
    listOfTaxes.add(entity);
  }

  public List<CongestionTaxEntity> getAll() {
    return listOfTaxes;
  }
}
