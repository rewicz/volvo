package pl.softiq.volvo.tax.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import pl.softiq.volvo.tax.exception.DictionaryException;
import pl.softiq.volvo.tax.model.AmountDictionaryEntity;

@Repository
public class AmountDictionaryRepository {
   final static List<AmountDictionaryEntity> listDictionary = new ArrayList<>(Arrays.asList(
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(0,0,0)).to(LocalTime.of(5,59,59)).value(0).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(6,0,0)).to(LocalTime.of(6,29,59)).value(8).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(6,30,0)).to(LocalTime.of(6,59,59)).value(13).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(7,0,0)).to(LocalTime.of(7,59,59)).value(18).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(8,0,0)).to(LocalTime.of(8,29,59)).value(13).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(8,30,0)).to(LocalTime.of(14,59,59)).value(8).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(15,0,0)).to(LocalTime.of(15,29,59)).value(13).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(15,30,0)).to(LocalTime.of(16,59,59)).value(18).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(17,0,0)).to(LocalTime.of(17,59,59)).value(13).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(18,0,0)).to(LocalTime.of(18,29,59)).value(8).build(),
       AmountDictionaryEntity.builder().cityId(1).from(LocalTime.of(18,30,0)).to(LocalTime.of(23,59,59)).value(0).build()
   ));

   public int getFeeByDateAndCity(int cityId, LocalTime time){
     Optional<AmountDictionaryEntity>
         period = listDictionary.stream().filter(dict -> dict.getCityId().equals(cityId) && time.isAfter(dict.getFrom()) && time.isBefore(dict.getTo())).findFirst();

     if(period.isPresent()) {
       return period.get().getValue();
     } else {
       throw new DictionaryException(cityId);
     }
   }

}
