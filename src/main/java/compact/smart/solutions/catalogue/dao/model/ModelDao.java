package compact.smart.solutions.catalogue.dao.model;

import compact.smart.solutions.catalogue.models.Model;
import java.util.List;
import java.util.Optional;

public interface ModelDao
{

  Long createModel(Model model);

  Optional<Model> findModelByID(Long id);

  List<Model> findAllModels();

  void updateModel(Model model, Long id);

  void deleteModel(Long id);
}
