package compact.smart.solutions.catalogue.dao.transmission;

import compact.smart.solutions.catalogue.models.Transmission;
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
public class TransmissionDaoImpl implements TransmissionDao
{

  private final NamedParameterJdbcOperations jdbcOperations;


  @Override
  public Long createTransmission(Transmission transmission)
  {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("name", transmission.getName());

    String sql = ""
        + "INSERT INTO TRANSMISSION(TRANSMISSION_ID, NAME) "
        + "VALUES((DEFAULT), :name)                        ";

    jdbcOperations.update(sql, params, keyHolder, new String[]{"TRANSMISSION_ID"});
    Long transmissionId = Objects
        .requireNonNull(keyHolder.getKey())
        .longValue();
    log.info("A new transmission type has been added successfully in DB!");
    return transmissionId;
  }

  @Override
  public Optional<Transmission> findTransmissionByID(Long id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("transmissionId", id);
      return jdbcOperations.queryForObject(
            "SELECT                      "
              + "TRANSMISSION_ID, NAME       "
              + "FROM TRANSMISSION           "
              + "WHERE TRANSMISSION_ID = :id ",

          sqlParam,
          (rs, rowNum) -> Optional.of(new Transmission(
              rs.getString("NAME"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<Transmission> findAllTransmissionTypes()
  {
    String sql = "SELECT TRANSMISSION_ID, "
               + "NAME                    "
               + "FROM TRANSMISSION       ";

    return jdbcOperations.query(sql,
        (rs, rowNum) -> new Transmission(
            rs.getString("name")));
  }

  @Override
  public void updateTransmission(Transmission transmission, Long id)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("transmission_id", id)
        .addValue("name", transmission.getName());

    String sql =
        "UPDATE TRANSMISSION        "
            + "SET NAME    = :name        "
            + "WHERE TRANSMISSION_ID =:id ";

    jdbcOperations.update(sql, params);
    log.info("Transmission info has been updated!");
  }

  @Override
  public void deleteTransmission(Long id)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("transmission_id", id);
    String sql = ""
        + "DELETE FROM INFORMATION_SCHEMA.TRANSMISSION   "
        + "WHERE TRANSMISSION_ID =:id  ";

    jdbcOperations.update(sql, param);
    log.info("Transmission has been deleted successfully!");

  }
}
