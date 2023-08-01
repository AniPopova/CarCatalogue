package compact.smart.solutions.catalogue.models;

import static compact.smart.solutions.catalogue.utils.MessagesAndConstants.EMPTY_FUEL;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fuel
{
  @NotEmpty(message = EMPTY_FUEL)
  private String name;
}
