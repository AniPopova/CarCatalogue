package compact.smart.solutions.catalogue.services.fuel;

import compact.smart.solutions.catalogue.models.Fuel;
import java.util.List;
import java.util.Optional;

public interface FuelService
{

  Long createFuel(Fuel fuel);
  List<Fuel> findAllFuelTypes();
  Optional<Fuel> findFuelByID(Long id);
  void updateFuel(Fuel fuel, Long id);
  void deleteFuel(Long id);
}
