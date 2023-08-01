package compact.smart.solutions.catalogue.services.car;

import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_CAR_SEARCH;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_FUEL_SEARCH;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_MODEL_SEARCH;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_PRICE_SEARCH;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_PRODUCTION_DATE;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_TRANSMISSION_SEARCH;

import compact.smart.solutions.catalogue.dao.car.CarDao;
import compact.smart.solutions.catalogue.exceptions.CarCatalogueException;
import compact.smart.solutions.catalogue.models.Car;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService
{

  private final CarDao carDao;

  @Override
  public Long createCar(Car car)
  {
    return carDao.createCar(car);
  }

  /**
   * In methods findCarByID and findCarByVin
   * we use the same message, because cars do not change their vin numbers.
   */
  @Override
  public Optional<Car> findCarByID(Long id)
  {
    return Optional.ofNullable(carDao
        .findCarByID(id)
        .orElseThrow(() -> new CarCatalogueException(INVALID_CAR_SEARCH)));
  }

  @Override
  public Optional<Car> findCarByVin(String vinNum)
  {
    return Optional.ofNullable(carDao
        .findCarByVin(vinNum)
        .orElseThrow(() -> new CarCatalogueException(INVALID_CAR_SEARCH)));
  }


  @Override
  public Optional<Car> findCarByDate(Date date)
  {
    return Optional.ofNullable(carDao
        .findCarByDate(date)
        .orElseThrow(() -> new CarCatalogueException(INVALID_PRODUCTION_DATE)));
  }

  @Override
  public Optional<Car> findCarByModel(Long modelId)
  {
    return Optional.ofNullable(carDao
        .findCarByModel(modelId)
        .orElseThrow(() -> new CarCatalogueException(INVALID_MODEL_SEARCH)));
  }

  @Override
  public Optional<Car> findCarByFuelType(Long id)
  {
    return Optional.ofNullable(carDao
        .findCarByFuelType(id)
        .orElseThrow(() -> new CarCatalogueException(INVALID_FUEL_SEARCH)));
  }

  @Override
  public Optional<Car> findCarByTransmissionType(Long id)
  {
    return Optional.ofNullable(carDao
        .findCarByTransmissionType(id)
        .orElseThrow(() -> new CarCatalogueException(INVALID_TRANSMISSION_SEARCH)));
  }

  @Override
  public Optional<Car> findCarByPrice(BigDecimal price)
  {
    return Optional.ofNullable(carDao
        .findCarByPrice(price)
        .orElseThrow(() -> new CarCatalogueException(INVALID_PRICE_SEARCH)));
  }

  @Override
  public List<Car> findAllCars()
  {
    return carDao.findAllCars();
  }

  @Override
  public void updateCar(Car car, Long id)
  {
    carDao.updateCar(car, id);
  }

  @Override
  public void deleteCar(String vin)
  {
    carDao.deleteCar(vin);
  }
}
