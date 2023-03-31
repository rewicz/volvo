package pl.softiq.volvo.tax.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.softiq.volvo.tax.dao.ActionRepository;
import pl.softiq.volvo.tax.dao.CongestionTaxRepository;
import pl.softiq.volvo.tax.dto.CongestionTaxDto;
import pl.softiq.volvo.tax.mapper.ActionMapper;
import pl.softiq.volvo.tax.mapper.CongestionTaxMapper;
import pl.softiq.volvo.tax.model.ActionEntity;
import pl.softiq.volvo.tax.util.CongestionTaxCalculator;

@Service
@Slf4j
@AllArgsConstructor
public class CongestionTaxService {

  private final CongestionTaxRepository congestionTaxRepository;
  private final ActionRepository actionRepository;
  private final CongestionTaxCalculator congestionTaxCalculator;

  public void registerEntry(CongestionTaxDto entry) {

    // save all actions
    actionRepository.saveAction(ActionMapper.map(entry));

    List<LocalDateTime> listThatDayEntry =
        actionRepository.getLastDayActionsByPlate(entry.getPlate(), entry.getDate()).stream()
            .map(ActionEntity::getEntryDate).toList();

    int taxDay = congestionTaxCalculator.getTax(entry.getVehicle(), listThatDayEntry);

    log.info("Tax for " + entry.getPlate() + " :" + taxDay + " at " + entry.getDate().truncatedTo(ChronoUnit.DAYS));

    // save congestion tax
    congestionTaxRepository.saveCongestionTax(CongestionTaxMapper.map(entry, taxDay));

    log.info(congestionTaxRepository.getAll().stream()
        .map(tax -> "\n" + tax.getPlate() + " " + tax.getTax() + " " + tax.getDate().toString()).toList().toString());

  }
}
