package compact.smart.solutions.catalogue.dao.car;

import compact.smart.solutions.catalogue.models.Car;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CarDao
{

  Long createCar(Car car);

  Optional<Car> findCarByID(Long id);

  Optional<Car> findCarByVin(String vinNum);

  Optional<Car> findCarByDate(Date date);

  Optional<Car> findCarByModel(Long id);

  Optional<Car> findCarByFuelType(Long id);

  Optional<Car> findCarByTransmissionType(Long id);

  Optional<Car> findCarByPrice(BigDecimal price);

  List<Car> findAllCars();

  void updateCar(Car car, Long id);

  void deleteCar(String vin);
}
