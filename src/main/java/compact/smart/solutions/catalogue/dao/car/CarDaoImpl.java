package compact.smart.solutions.catalogue.dao.car;

import compact.smart.solutions.catalogue.models.Car;
import java.math.BigDecimal;
import java.sql.Date;
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
public class CarDaoImpl implements CarDao
{

  private final NamedParameterJdbcOperations jdbcOperations;

  @Override
  public Long createCar(Car car)
  {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("vinNumber", car.getVinNumber())
        .addValue("model", car.getModel_id())
        .addValue("price", car.getPrice())
        .addValue("regDate", car.getRegDate())
        .addValue("transmission", car.getTransmission_id())
        .addValue("fuel", car.getFuel_id())
        .addValue("remarks", car.getRemarks());

    String sql = ""
        + "INSERT INTO CAR(CAR_ID, "
        + "VIN_NUMBER, MODEL_ID, PRICE, REG_DATE,     "
        + "TRANSMISSION_ID, FUEL_ID, REMARKS)         "
        + "VALUES((DEFAULT), :vinNumber, :model,      "
        + ":price, :regDate, :transmission, :fuel,    "
        + ":remarks )                                 ";

    jdbcOperations.update(sql, params, keyHolder, new String[]{"TRANSMISSION_ID"});
    Long transmissionId = Objects
        .requireNonNull(keyHolder.getKey())
        .longValue();
    log.info("A new car has been added successfully in DB!");
    return transmissionId;
  }

  @Override
  public Optional<Car> findCarByID(Long id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("carId", id);
      return jdbcOperations.queryForObject(
          "SELECT                  "
              + "VIN_NUMBER,           "
              + "MODEL_ID,             "
              + "PRICE,                "
              + "REG_DATE,             "
              + "TRANSMISSION_ID,      "
              + "FUEL_ID,              "
              + "REMARKS               "
              + "FROM CAR              "
              + "WHERE CAR_ID = :carId "
              + "ORDER BY PRICE DESC   ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Car(
              rs.getString("VIN_NUMBER"),
              rs.getBigDecimal("PRICE"),
              rs.getLong("MODEL_ID"),
              rs.getDate("REG_DATE"),
              rs.getLong("TRANSMISSION_ID"),
              rs.getLong("FUEL_ID"),
              rs.getString("REMARKS"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Car> findCarByVin(String vinNum)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("vinNumber", vinNum);
      return jdbcOperations.queryForObject(
            "SELECT                     "
              + "VIN_NUMBER,                "
              + "MODEL_ID,                  "
              + "PRICE,                     "
              + "REG_DATE,                  "
              + "TRANSMISSION_ID,           "
              + "FUEL_ID,                   "
              + "REMARKS                    "
              + "FROM CAR                   "
              + "WHERE VIN_NUMBER = :vinNum "
              + "ORDER BY PRICE DESC        ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Car(
              rs.getString("VIN_NUMBER"),
              rs.getBigDecimal("PRICE"),
              rs.getLong("MODEL_ID"),
              rs.getDate("REG_DATE"),
              rs.getLong("TRANSMISSION_ID"),
              rs.getLong("FUEL_ID"),
              rs.getString("REMARKS"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Car> findCarByDate(Date date)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("regDate", date);
      return jdbcOperations.queryForObject(
          "SELECT                   "
              + "CAR_ID,                "
              + "VIN_NUMBER,            "
              + "MODEL_ID,              "
              + "PRICE,                 "
              + "TRANSMISSION_ID,       "
              + "FUEL_ID,               "
              + "REMARKS                "
              + "FROM CAR               "
              + "WHERE REG_DATE = :date "
              + "ORDER BY PRICE DESC    ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Car(
              rs.getString("VIN_NUMBER"),
              rs.getBigDecimal("PRICE"),
              rs.getLong("MODEL_ID"),
              rs.getDate("REG_DATE"),
              rs.getLong("TRANSMISSION_ID"),
              rs.getLong("FUEL_ID"),
              rs.getString("REMARKS"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Car> findCarByModel(Long modelId)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("modelId", modelId);
      return jdbcOperations.queryForObject(
          "SELECT                      "
              + "CAR_ID,                   "
              + "VIN_NUMBER,               "
              + "MODEL_ID,                 "
              + "PRICE,                    "
              + "TRANSMISSION_ID,          "
              + "FUEL_ID,                  "
              + "REMARKS                   "
              + "FROM CAR                  "
              + "WHERE MODEL_ID = :modelId "
              + "ORDER BY PRICE DESC       ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Car(
              rs.getString("VIN_NUMBER"),
              rs.getBigDecimal("PRICE"),
              rs.getLong("MODEL_ID"),
              rs.getDate("REG_DATE"),
              rs.getLong("TRANSMISSION_ID"),
              rs.getLong("FUEL_ID"),
              rs.getString("REMARKS"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Car> findCarByFuelType(Long fuelId)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("fuelId", fuelId);
      return jdbcOperations.queryForObject(
          "SELECT                    "
              + "CAR_ID,                 "
              + "VIN_NUMBER,             "
              + "MODEL_ID,               "
              + "PRICE,                  "
              + "TRANSMISSION_ID,        "
              + "FUEL_ID,                "
              + "REMARKS                 "
              + "FROM CAR                "
              + "WHERE FUEL_ID = :fuelId "
              + "ORDER BY PRICE DESC     ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Car(
              rs.getString("VIN_NUMBER"),
              rs.getBigDecimal("PRICE"),
              rs.getLong("MODEL_ID"),
              rs.getDate("REG_DATE"),
              rs.getLong("TRANSMISSION_ID"),
              rs.getLong("FUEL_ID"),
              rs.getString("REMARKS"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Car> findCarByTransmissionType(Long transmissionId)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("retransmission_id", transmissionId);
      return jdbcOperations.queryForObject(
          "SELECT CAR_ID, VIN_NUMBER, MODEL_ID,      "
              + "PRICE, TRANSMISSION_ID, FUEL_ID,        "
              + "REMARKS                                 "
              + "FROM CAR                                "
              + "WHERE TRANSMISSION_ID = :transmissionId "
              + "ORDER BY PRICE DESC                     ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Car(
              rs.getString("VIN_NUMBER"),
              rs.getBigDecimal("PRICE"),
              rs.getLong("MODEL_ID"),
              rs.getDate("REG_DATE"),
              rs.getLong("TRANSMISSION_ID"),
              rs.getLong("FUEL_ID"),
              rs.getString("REMARKS"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Car> findCarByPrice(BigDecimal price)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("price", price);
      return jdbcOperations.queryForObject(
          "SELECT                   "
              + "CAR_ID,                "
              + "VIN_NUMBER,            "
              + "MODEL_ID,              "
              + "PRICE,                 "
              + "TRANSMISSION_ID,       "
              + "FUEL_ID,               "
              + "REMARKS                "
              + "FROM CAR               "
              + "WHERE PRICE = :price   "
              + "ORDER BY PRICE DESC    ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Car(
              rs.getString("VIN_NUMBER"),
              rs.getBigDecimal("PRICE"),
              rs.getLong("MODEL_ID"),
              rs.getDate("REG_DATE"),
              rs.getLong("TRANSMISSION_ID"),
              rs.getLong("FUEL_ID"),
              rs.getString("REMARKS"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<Car> findAllCars()
  {
    String sql =
              "SELECT              "
            + "CAR_ID, VIN_NUMBER, "
            + "MODEL_ID, PRICE,    "
            + "REG_DATE,           "
            + "TRANSMISSION_ID,    "
            + "FUEL_ID, REMARKS    "
            + "FROM CAR            ";

    return jdbcOperations.query(sql,
        (rs, rowNum) -> new Car(
            rs.getString("VIN_NUMBER"),
            rs.getBigDecimal("PRICE"),
            rs.getLong("MODEL_ID"),
            rs.getDate("REG_DATE"),
            rs.getLong("TRANSMISSION_ID"),
            rs.getLong("FUEL_ID"),
            rs.getString("REMARKS")));
  }

  @Override
  public void updateCar(Car car, Long id)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("car_id", id)
        .addValue("vinNumber", car.getVinNumber())
        .addValue("model", car.getModel_id())
        .addValue("price", car.getPrice())
        .addValue("regDate", car.getRegDate())
        .addValue("transmission", car.getTransmission_id())
        .addValue("fuel", car.getFuel_id())
        .addValue("remarks", car.getRemarks());

    String sql =
              "UPDATE CAR             "
            + "SET PRICE    = :price  "
            + "WHERE MODEL_ID =:id    ";

    jdbcOperations.update(sql, params);
    log.info("Model has been updated!");
  }

  @Override
  public void deleteCar(String vin)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("vinNum", vin);
    String sql = ""
        + "DELETE FROM CAR        "
        + "WHERE VIN_NUMBER=:vin  ";

    jdbcOperations.update(sql, param);
    log.info("A car has been deleted successfully from the catalogue!");
  }
}
