package pl.softiq.volvo.tax.exception;

public class DictionaryException extends RuntimeException {
  public DictionaryException(int cityId) {
    super("Cannot find date in dictionary for city " + cityId);
  }

}
