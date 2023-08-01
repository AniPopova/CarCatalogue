package compact.smart.solutions.catalogue.services.model;

import compact.smart.solutions.catalogue.models.Model;
import java.util.List;
import java.util.Optional;

public interface ModelService
{
  Long createModel(Model model);

  Optional<Model> findModelByID(Long id);

  List<Model> findAllModels();

  void updateModel(Model model, Long id);

  void deleteModel(Long id);

}
