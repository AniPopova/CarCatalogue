package compact.smart.solutions.catalogue.dao.fuel;

import compact.smart.solutions.catalogue.models.Fuel;
import java.util.List;
import java.util.Optional;

public interface FuelDao
{

  Long createFuel(Fuel fuel);

  Optional<Fuel> findFuelByID(Long id);

  List<Fuel> findAllFuelTypes();

  void updateFuel(Fuel fuel, Long id);

  void deleteFuel(Long id);
}
