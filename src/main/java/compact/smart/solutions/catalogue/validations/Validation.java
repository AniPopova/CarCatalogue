package compact.smart.solutions.catalogue.validations;


public interface Validation
{
  void checkIfCarExist(String vinMun);
  void checkValidBrand(String brandName);

  void checkIfBrandExist(Long id);

  void checkValidModel(String modelName);

  void checkIfModelExist(Long id);

  void checkValidFuelType(String fuelName);

  void checkIfFuelTypeExist(Long id);

  void checkValidTransmissionType(String transmissionName);

  void checkIfTransmissionTypeExist(Long id);

}
