package compact.smart.solutions.catalogue.exceptions;

import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler
{

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Map<String, Object>> handleConstraintExceptions(ConstraintViolationException exception)
  {
    Map<String, Object> exceptedBody = new HashMap<>();
    for (ConstraintViolation<?> e : exception.getConstraintViolations()) {
      exceptedBody.put("message: ", e.getMessage());
    }
    return new ResponseEntity<>(exceptedBody, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler({CarCatalogueException.class})
  public ResponseEntity<String> handlerCarCatalogueException(CarCatalogueException cce)
  {
    return new ResponseEntity<>(cce.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
