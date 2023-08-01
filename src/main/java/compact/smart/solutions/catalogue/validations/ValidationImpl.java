package compact.smart.solutions.catalogue.validations;

import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_BRAND;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_CAR_SEARCH;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_FUEL;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_FUEL_SEARCH;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_MODEL;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_MODEL_SEARCH;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_TRANSMISSION;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_TRANSMISSION_SEARCH;

import compact.smart.solutions.catalogue.dao.brand.BrandDao;
import compact.smart.solutions.catalogue.dao.car.CarDao;
import compact.smart.solutions.catalogue.dao.fuel.FuelDao;
import compact.smart.solutions.catalogue.dao.model.ModelDao;
import compact.smart.solutions.catalogue.dao.transmission.TransmissionDao;
import compact.smart.solutions.catalogue.exceptions.CarCatalogueException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ValidationImpl.class")
@RequiredArgsConstructor
public class ValidationImpl implements Validation
{

  private final CarDao          carDao;
  private final BrandDao        brandDao;
  private final ModelDao        modelDao;
  private final FuelDao         fuelDao;
  private final TransmissionDao transmissionDao;
  private final List<String>    availableBrands;
  private final List<String>    availableModels;
  private final List<String>    availableFuels;
  private final List<String>    availableTransmissions;

  @Override
  public void checkIfCarExist(String vinNum)
  {
    boolean doesCarExist = carDao
        .findCarByVin(vinNum)
        .isPresent();
    if (!doesCarExist) {
      throw new CarCatalogueException(INVALID_CAR_SEARCH);
    }
  }

  @Override
  public void checkValidBrand(String brandName)
  {
    boolean isValidBrandName = availableBrands
        .stream()
        .anyMatch(brand -> availableBrands
            .contains(brandName));
    if (!isValidBrandName) {
      throw new CarCatalogueException(INVALID_BRAND);
    }
  }

  @Override
  public void checkIfBrandExist(Long id)
  {
    if (!brandDao
        .findBrandByID(id)
        .isPresent()) {
      throw new CarCatalogueException(INVALID_BRAND);
    }
  }

  @Override
  public void checkValidModel(String modelName)
  {
    boolean isValidBrandName = availableModels
        .stream()
        .anyMatch(brand -> availableBrands
            .contains(modelName));
    if (!isValidBrandName) {
      throw new CarCatalogueException(INVALID_MODEL);
    }
  }

  @Override
  public void checkIfModelExist(Long id)
  {
    if (!modelDao
        .findModelByID(id)
        .isPresent()) {
      throw new CarCatalogueException(INVALID_MODEL_SEARCH);
    }
  }

  @Override
  public void checkValidFuelType(String fuelName)
  {
    boolean isValidFuelName = availableFuels
        .stream()
        .anyMatch(fuel -> availableFuels
            .contains(fuelName));
    if (!isValidFuelName) {
      throw new CarCatalogueException(INVALID_FUEL);
    }
  }

  @Override
  public void checkIfFuelTypeExist(Long id)
  {
    if (fuelDao
        .findFuelByID(id)
        .isEmpty()) {
      throw new CarCatalogueException(INVALID_FUEL_SEARCH);
    }
  }

  @Override
  public void checkValidTransmissionType(String transmissionName)
  {
    boolean isValidTransmissionName = availableTransmissions
        .stream()
        .anyMatch(transmission -> availableTransmissions
            .contains(transmissionName));
    if (!isValidTransmissionName) {
      throw new CarCatalogueException(INVALID_TRANSMISSION);
    }
  }

  @Override
  public void checkIfTransmissionTypeExist(Long id)
  {
    if (transmissionDao
        .findTransmissionByID(id)
        .isEmpty()) {
      throw new CarCatalogueException(INVALID_TRANSMISSION_SEARCH);
    }
  }
}
