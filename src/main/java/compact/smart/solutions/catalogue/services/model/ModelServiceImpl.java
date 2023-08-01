package compact.smart.solutions.catalogue.services.model;

import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_MODEL_SEARCH;

import compact.smart.solutions.catalogue.dao.model.ModelDao;
import compact.smart.solutions.catalogue.exceptions.CarCatalogueException;
import compact.smart.solutions.catalogue.models.Model;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService
{

  private final ModelDao modelDao;

  @Override
  public Long createModel(Model model)
  {
    return modelDao.createModel(model);
  }

  @Override
  public Optional<Model> findModelByID(Long id)
  {
    return Optional.ofNullable(modelDao
        .findModelByID(id)
        .orElseThrow(() -> new CarCatalogueException(INVALID_MODEL_SEARCH)));
  }

  @Override
  public List<Model> findAllModels()
  {
    return modelDao.findAllModels();
  }

  @Override
  public void updateModel(Model model, Long id)
  {
    modelDao.updateModel(model, id);
  }

  @Override
  public void deleteModel(Long id)
  {
    modelDao.deleteModel(id);
  }
}
