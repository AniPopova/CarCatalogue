package compact.smart.solutions.catalogue.services.brand;

import compact.smart.solutions.catalogue.models.Brand;
import java.util.List;
import java.util.Optional;

public interface BrandService
{
  Long createBrand(Brand brand);

  Optional<Brand> findBrandByID(Long id);

  List<Brand> findAllBrands();

  void updateBrand(Brand brand, Long id);

  void deleteBrand(Long id);
}
