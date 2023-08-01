package compact.smart.solutions.catalogue.controllers;

import compact.smart.solutions.catalogue.models.Transmission;
import compact.smart.solutions.catalogue.services.transmission.TransmissionService;
import compact.smart.solutions.catalogue.validations.ValidationImpl;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api")
public class TransmissionController
{
  private final TransmissionService transmissionService;
  private final ValidationImpl      validation;

  @PostMapping("/transmissions")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createTransmission(@Valid
  @RequestBody Transmission transmission)
  {
    validation.checkValidTransmissionType(transmission.getName());
    log.info("Request for adding new transmission type in DB.");
    return transmissionService.createTransmission(transmission);
  }

  @PatchMapping("/transmissions/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateTransmission(@Valid
  @RequestBody Transmission transmission,
      @PathVariable Long id)
  {
    validation.checkIfTransmissionTypeExist(id);
    log.info("Update of transmission type info requested.");
    transmissionService.updateTransmission(transmission, id);
  }

  @GetMapping(value = "/transmissions/db")
  @ResponseStatus(HttpStatus.OK)
  public List<Transmission> getAllTransmissionTypes()
  {
    log.info("List of all transmission types in the DB requested");
    return transmissionService.findAllTransmissionTypes();
  }

  @DeleteMapping(value = "/transmissions/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteTransmissionById(@Valid
  @PathVariable Long id)
  {
    validation.checkIfTransmissionTypeExist(id);
    log.info("Request for deleting transmission was sent.");
    transmissionService.deleteTransmission(id);
  }
}
