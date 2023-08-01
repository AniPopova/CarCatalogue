package compact.smart.solutions.catalogue.services.fuel;

import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_FUEL;

import compact.smart.solutions.catalogue.dao.fuel.FuelDao;
import compact.smart.solutions.catalogue.exceptions.CarCatalogueException;
import compact.smart.solutions.catalogue.models.Fuel;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FuelServiceImpl implements FuelService
{

  private final FuelDao fuelDao;

  @Override
  public Long createFuel(Fuel fuel)
  {
    return fuelDao.createFuel(fuel);
  }

  @Override
  public List<Fuel> findAllFuelTypes()
  {
    return fuelDao.findAllFuelTypes();
  }

  @Override
  public Optional<Fuel> findFuelByID(Long id)
  {
    return Optional.ofNullable(fuelDao
        .findFuelByID(id)
        .orElseThrow(() -> new CarCatalogueException(INVALID_FUEL)));
  }

  @Override
  public void updateFuel(Fuel fuel, Long id)
  {
    fuelDao.updateFuel(fuel, id);
  }

  @Override
  public void deleteFuel(Long id)
  {
    fuelDao.deleteFuel(id);
  }
}
