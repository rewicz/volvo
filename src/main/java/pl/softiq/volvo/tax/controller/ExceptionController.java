package pl.softiq.volvo.tax.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.softiq.volvo.tax.dto.ApiBasicResponse;
import pl.softiq.volvo.tax.exception.DictionaryException;

@ControllerAdvice
public class ExceptionController {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
      MethodArgumentTypeMismatchException.class
  } )
  @ResponseBody
  ApiBasicResponse handleMethodArgumentTypeMismatchException() {
    return ApiBasicResponse.builder().success(false).message("Cannot find type vehicle").build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
      DictionaryException.class
  } )
  @ResponseBody
  ApiBasicResponse handleDictionaryException(DictionaryException e) {
    return ApiBasicResponse.builder().success(false).message(e.getMessage()).build();
  }


}
