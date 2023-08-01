package compact.smart.solutions.catalogue.dao.fuel;

import compact.smart.solutions.catalogue.models.Fuel;
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
public class FuelDaoImpl implements FuelDao
{

  private final NamedParameterJdbcOperations jdbcOperations;

  @Override
  public Long createFuel(Fuel fuel)
  {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("name", fuel.getName());

    String sql = ""
        + "INSERT INTO FUEL(FUEL_ID, NAME) "
        + "VALUES((DEFAULT), :name)        ";

    jdbcOperations.update(sql, params, keyHolder, new String[]{"FUEL_ID"});
    Long fuelId = Objects
        .requireNonNull(keyHolder.getKey())
        .longValue();
    log.info("A new fuel type has been added successfully in DB!");
    return fuelId;
  }

  @Override
  public Optional<Fuel> findFuelByID(Long id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("fuelId", id);
      return jdbcOperations.queryForObject(
            "SELECT               "
              + "FUEL_ID, NAME        "
              + "FROM FUEL            "
              + "WHERE FUEL_ID = :id  ",

          sqlParam,
          (rs, rowNum) -> Optional.of(new Fuel(
              rs.getString("NAME"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<Fuel> findAllFuelTypes()
  {
    String sql = "SELECT FUEL_ID, "
               + "NAME            "
               + "FROM FUEL       ";

    return jdbcOperations.query(sql,
        (rs, rowNum) -> new Fuel(
            rs.getString("name")));
  }

  @Override
  public void updateFuel(Fuel fuel, Long id)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("fuel_id", id)
        .addValue("name", fuel.getName());

    String sql =
        "UPDATE FUEL          "
            + "SET NAME    = :name  "
            + "WHERE FUEL_ID=:id    ";

    jdbcOperations.update(sql, params);
    log.info("Fuel info has been updated!");
  }

  @Override
  public void deleteFuel(Long id)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("fuel_id", id);
    String sql = ""
        + "DELETE FROM INFORMATION_SCHEMA.FUEL "
        + "WHERE FUEL_ID =:id                  ";

    jdbcOperations.update(sql, param);
    log.info("A fuel type has been deleted successfully from DB!");
  }

}
