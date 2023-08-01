package compact.smart.solutions.catalogue.services.brand;

import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.INVALID_BRAND;

import compact.smart.solutions.catalogue.dao.brand.BrandDao;
import compact.smart.solutions.catalogue.exceptions.CarCatalogueException;
import compact.smart.solutions.catalogue.models.Brand;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService
{

  private final BrandDao brandDao;

  @Override
  public Long createBrand(Brand brand)
  {
    return brandDao.createBrand(brand);
  }

  @Override
  public Optional<Brand> findBrandByID(Long id)
  {
    return Optional.ofNullable(brandDao
        .findBrandByID(id)
        .orElseThrow(() -> new CarCatalogueException(INVALID_BRAND)));
  }

  @Override
  public List<Brand> findAllBrands()
  {
    return brandDao.findAllBrands();
  }

  @Override
  public void updateBrand(Brand brand, Long id)
  {
    brandDao.updateBrand(brand, id);
  }

  @Override
  public void deleteBrand(Long id)
  {
    brandDao.deleteBrand(id);
  }
}
