package compact.smart.solutions.catalogue.controllers;

import compact.smart.solutions.catalogue.models.Brand;
import compact.smart.solutions.catalogue.services.brand.BrandService;
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
public class BrandController
{
private final BrandService   brandService;
private final ValidationImpl validation;

  @PostMapping("/brands")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createBrand(@Valid
  @RequestBody Brand brand)
  {
    validation.checkValidBrand(brand.getName());
    log.info("Request for adding new brand in DB.");
    return brandService.createBrand(brand);
  }

  @PatchMapping("/brands/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateBrand(@Valid
  @RequestBody Brand brand,
      @PathVariable Long id)
  {
    validation.checkIfBrandExist(id);
    log.info("Update of brand info requested.");
    brandService.updateBrand(brand, id);
  }

  @GetMapping(value = "/brands/db")
  @ResponseStatus(HttpStatus.OK)
  public List<Brand> getAllBrands()
  {
    log.info("List of all brands in the DB requested");
    return brandService.findAllBrands();
  }

  @DeleteMapping(value = "/brands/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteBrandById(@Valid
  @PathVariable Long id)
  {
    validation.checkIfBrandExist(id);
    log.info("Request for deleting brand was sent.");
    brandService.deleteBrand(id);
  }

}
