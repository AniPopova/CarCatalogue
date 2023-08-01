package compact.smart.solutions.catalogue.dao.brand;

import compact.smart.solutions.catalogue.models.Brand;
import java.util.List;
import java.util.Optional;

public interface BrandDao
{

  Long createBrand(Brand brand);

  Optional<Brand> findBrandByID(Long id);

  List<Brand> findAllBrands();

  void updateBrand(Brand brand, Long id);

  void deleteBrand(Long id);
}
