package compact.smart.solutions.catalogue.controllers;

import compact.smart.solutions.catalogue.models.Fuel;
import compact.smart.solutions.catalogue.services.fuel.FuelService;
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
public class FuelController
{
  private final FuelService    fuelService;
  private final ValidationImpl validation;

  @PostMapping("/fuels")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createFuel(@Valid
  @RequestBody Fuel fuel)
  {
    validation.checkValidFuelType(fuel.getName());
    log.info("Request for adding new fuel type in DB.");
    return fuelService.createFuel(fuel);
  }

  @PatchMapping("/fuels/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateFuel(@Valid
  @RequestBody Fuel fuel,
      @PathVariable Long id)
  {
    validation.checkIfFuelTypeExist(id);
    log.info("Update of fuel info requested.");
    fuelService.updateFuel(fuel, id);
  }

  @GetMapping(value = "/fuels/db")
  @ResponseStatus(HttpStatus.OK)
  public List<Fuel> getAllFuelTypes()
  {
    log.info("List of all fuel types in the DB requested");
    return fuelService.findAllFuelTypes();
  }

  @DeleteMapping(value = "/fuels/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteFuelById(@Valid
  @PathVariable Long id)
  {
    validation.checkIfFuelTypeExist(id);
    log.info("Request for deleting fuel type was sent.");
    fuelService.deleteFuel(id);
  }

}
