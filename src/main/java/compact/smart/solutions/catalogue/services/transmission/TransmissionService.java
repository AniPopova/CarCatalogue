package compact.smart.solutions.catalogue.services.transmission;

import compact.smart.solutions.catalogue.models.Transmission;
import java.util.List;
import java.util.Optional;

public interface TransmissionService
{
  Long createTransmission(Transmission transmission);

  Optional<Transmission> findTransmissionByID(Long id);

  List<Transmission> findAllTransmissionTypes();

  void updateTransmission(Transmission transmission, Long id);

  void deleteTransmission(Long id);
}
