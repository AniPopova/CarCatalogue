package compact.smart.solutions.catalogue.controllers;

import compact.smart.solutions.catalogue.models.Model;
import compact.smart.solutions.catalogue.services.model.ModelService;
import compact.smart.solutions.catalogue.validations.ValidationImpl;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api")
public class ModelController
{
  private final ModelService   modelService;
  private final ValidationImpl validation;

  @PostMapping("/models")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createModel(@Valid
  @RequestBody Model model)
  {
    validation.checkValidModel(model.getName());
    log.info("Request for adding new model in DB.");
    return modelService.createModel(model);
  }

  @PatchMapping("/models/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateModel(@Valid
  @RequestBody Model model,
      @PathVariable Long id)
  {
    validation.checkIfModelExist(id);
    log.info("Update of model info requested.");
    modelService.updateModel(model, id);
  }

  @GetMapping(value = "/models/db")
  @ResponseStatus(HttpStatus.OK)
  public List<Model> getAllModels()
  {
    log.info("List of all models in the DB requested");
    return modelService.findAllModels();
  }

  @DeleteMapping(value = "/models/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteModelById(@Valid
  @PathVariable Long id)
  {
    validation.checkIfModelExist(id);
    log.info("Request for deleting model was sent.");
    modelService.deleteModel(id);
  }
}
