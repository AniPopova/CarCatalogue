package compact.smart.solutions.catalogue.dao.model;

import compact.smart.solutions.catalogue.models.Model;
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
public class ModelDaoImpl implements ModelDao
{
  private final NamedParameterJdbcOperations jdbcOperations;

  @Override
  public Long createModel(Model model)
  {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("name", model.getName())
        .addValue("brand_id", model.getBrandId());

    String sql = ""
        + "INSERT INTO MODEL(MODEL_ID, BRAND_ID, NAME) "
        + "VALUES((DEFAULT), :brandId, :name)          ";

    jdbcOperations.update(sql, params, keyHolder, new String[]{"MODEL_ID"});
    Long modelId = Objects
        .requireNonNull(keyHolder.getKey())
        .longValue();
    log.info("A new car model has been added successfully in DB!");
    return modelId;
  }

  @Override
  public Optional<Model> findModelByID(Long id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("modelId", id);
      return jdbcOperations.queryForObject(
            "SELECT               "
              + "MODEL_ID, NAME,      "
              + "BRAND_ID             "
              + "FROM MODEL           "
              + "WHERE MODEL_ID = :id ",

          sqlParam,
          (rs, rowNum) -> Optional.of(new Model(
              rs.getLong("BRAND_ID"),
              rs.getString("NAME"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<Model> findAllModels()
  {
    String sql = "SELECT MODEL_ID, "
               + "NAME,            "
               + "BRAND_ID         "
               + "FROM MODEL       ";

    return jdbcOperations.query(sql,
        (rs, rowNum) -> new Model(
            rs.getLong("BRAND_ID"),
            rs.getString("NAME")));
  }

  @Override
  public void updateModel(Model model, Long id)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("model_id", id)
        .addValue("name", model.getName());

    String sql =
              "UPDATE MODEL         "
            + "SET NAME    = :name  "
            + "WHERE MODEL_ID =:id  ";

    jdbcOperations.update(sql, params);
    log.info("Model info has been updated!");
  }

  @Override
  public void deleteModel(Long id)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("model_id", id);
    String sql = ""
        + "DELETE FROM MODEL    "
        + "WHERE MODEL_ID =:id  ";

    jdbcOperations.update(sql, param);
    log.info("A model has been deleted successfully!");

  }
}
