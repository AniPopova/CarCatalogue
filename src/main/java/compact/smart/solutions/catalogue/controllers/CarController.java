package compact.smart.solutions.catalogue.controllers;

import compact.smart.solutions.catalogue.models.Car;
import compact.smart.solutions.catalogue.services.car.CarService;
import compact.smart.solutions.catalogue.validations.ValidationImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
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
public class CarController
{
  private final CarService     carService;
  private final ValidationImpl validation;

  @PostMapping("/car-catalogue")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createCar(@Valid
  @RequestBody Car car)
  {
    validation.checkIfCarExist(car
        .getVinNumber());
    log.info("Request for adding new car in DB.");
    return carService.createCar(car);
  }

  @PatchMapping("/car-catalogue/{vin}{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateCar(@Valid
      @RequestBody Car car,
      @PathVariable String vin,
      @PathVariable Long id)
  {
    validation.checkIfCarExist(vin);
    log.info("Update of car info requested.");
    carService.updateCar(car, id);
  }

  @GetMapping(value = "/car-catalogue/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Car> getCarById(@Valid
  @PathVariable Long id)
  {
    return carService.findCarByID(id);
  }

  @GetMapping(value = "/car-catalogue/{vin}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Car> getCarByVinNum(@Valid
  @PathVariable String vin)
  {
    validation.checkIfCarExist(vin);
    return carService.findCarByVin(vin);
  }

  @GetMapping(value = "/car-catalogue/{model}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Car> getCarByModel(@Valid
  @PathVariable Long modelId)
  {
    return carService.findCarByModel(modelId);
  }

  @GetMapping(value = "/car-catalogue/{fuel}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Car> getCarByFuel(@Valid
  @PathVariable Long fuelId)
  {
    return carService.findCarByFuelType(fuelId);
  }

  @GetMapping(value = "/car-catalogue/{transmission}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Car> getCarByTransmission(@Valid
  @PathVariable Long transmissionId)
  {
    return carService.findCarByTransmissionType(transmissionId);
  }

  @GetMapping(value = "/car-catalogue/{price}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Car> getCarByPrice(@Valid
  @PathVariable BigDecimal price)
  {
    return carService.findCarByPrice(price);
  }

  @GetMapping(value = "/car-catalogue/{date}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Car> getCarByDate(@Valid
  @PathVariable Date date)
  {
    return carService.findCarByDate(date);
  }

  @GetMapping(value = "/car-catalogue/db")
  @ResponseStatus(HttpStatus.OK)
  public List<Car> getAllCars()
  {
    log.info("List of all cars in the catalogue requested");
    return carService.findAllCars();
  }

  @DeleteMapping(value = "/car-catalogue/{vin}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteCarByVin(@Valid
  @PathVariable String vin)
  {
    validation.checkIfCarExist(vin);
    log.info("Request for deleting car was sent.");
    carService.deleteCar(vin);
  }
}
