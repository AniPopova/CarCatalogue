package compact.smart.solutions.catalogue.dao.brand;

import compact.smart.solutions.catalogue.models.Brand;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
@RequiredArgsConstructor
public class BrandDaoImpl implements BrandDao
{

  private final NamedParameterJdbcOperations jdbcOperations;

  @Override
  public Long createBrand(Brand brand)
  {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("name", brand.getName());

    String sql = ""
        + "INSERT INTO BRAND(BRAND_ID, NAME) "
        + "VALUES((DEFAULT), :name)";

    jdbcOperations.update(sql, params, keyHolder, new String[]{"BRAND_ID"});
    Long brandId = Objects
        .requireNonNull(keyHolder.getKey())
        .longValue();
    log.info("A new brand has been added successfully in DB!");
    return brandId;
  }

  @Override
  public Optional<Brand> findBrandByID(Long id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("brandId", id);
      return jdbcOperations.queryForObject(
            "SELECT               "
              + "BRAND_ID, NAME       "
              + "FROM BRAND           "
              + "WHERE BRAND_ID = :id ",

          sqlParam,
          (rs, rowNum) -> Optional.of(new Brand(
              rs.getString("NAME"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<Brand> findAllBrands()
  {
    String sql = "SELECT BRAND_ID, "
               + "NAME             "
               + "FROM BRAND       ";

    return jdbcOperations.query(sql,
        (rs, rowNum) -> new Brand(
            rs.getString("name")));
  }

  @Override
  public void updateBrand(Brand brand, Long id)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("brand_id", id)
        .addValue("name", brand.getName());

    String sql =
              "UPDATE BRAND          "
            + "SET NAME    = :name   "
            + "WHERE BRAND_ID=:id    ";

    jdbcOperations.update(sql, params);
    log.info("Brand info has been updated!");
  }

  @Override
  public void deleteBrand(Long id)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("brand_id", id);
    String sql = ""
        + "DELETE FROM BRAND     "
        + "WHERE BRAND_ID =:id   ";

    jdbcOperations.update(sql, param);
    log.info("A brand has been deleted successfully from DB!");
  }
}
