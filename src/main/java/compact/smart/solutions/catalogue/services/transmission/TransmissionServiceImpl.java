package compact.smart.solutions.catalogue.services.transmission;

import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_TRANSMISSION_SEARCH;

import compact.smart.solutions.catalogue.dao.transmission.TransmissionDao;
import compact.smart.solutions.catalogue.exceptions.CarCatalogueException;
import compact.smart.solutions.catalogue.models.Transmission;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransmissionServiceImpl implements TransmissionService
{

  private final TransmissionDao transmissionDao;

  @Override
  public Long createTransmission(Transmission transmission)
  {
    return transmissionDao.createTransmission(transmission);
  }

  @Override
  public Optional<Transmission> findTransmissionByID(Long id)
  {
    return Optional.ofNullable(transmissionDao
        .findTransmissionByID(id)
        .orElseThrow(() -> new CarCatalogueException(INVALID_TRANSMISSION_SEARCH)));
  }

  @Override
  public List<Transmission> findAllTransmissionTypes()
  {
    return transmissionDao.findAllTransmissionTypes();
  }

  @Override
  public void updateTransmission(Transmission transmission, Long id)
  {
    transmissionDao.updateTransmission(transmission, id);
  }

  @Override
  public void deleteTransmission(Long id)
  {
    transmissionDao.deleteTransmission(id);
  }
}
