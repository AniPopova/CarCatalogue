package compact.smart.solutions.catalogue.models;

import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.DATE_OF_PRODUCTION;
import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.VIN_NUMBER;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car
{

   @NotBlank(message = VIN_NUMBER)
   @Min(17)
   @Max(17)
   private String vinNumber;
   @NotNull
   @DecimalMin(value="0.00")
   @DecimalMax(value="99999.99")
   @Positive
   private BigDecimal price;
   @NotNull
   private Long      model_id;
   @NotNull
   @Past(message = DATE_OF_PRODUCTION)
   private Date regDate;
   @NotNull
   private Long      transmission_id;
   @NotNull
   private Long  fuel_id;
   private String remarks;

}
