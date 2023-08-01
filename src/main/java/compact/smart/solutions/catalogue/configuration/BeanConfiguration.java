package compact.smart.solutions.catalogue.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration
{
  @Bean
  public List<String> availableBrands()
  {
    List<String> brands = new ArrayList<>();
    brands.add("AUDI");
    brands.add("BMW");
    brands.add("VW");
    return brands;
  }

  @Bean
  public List<String> availableModels()
  {
    List<String> models = new ArrayList<>();
    models.add("Q7");
    models.add("X5");
    models.add("Q5");
    return models;
  }

  @Bean
  public List<String> availableFuels()
  {
    List<String> fuels = new ArrayList<>();
    fuels.add("GASOLINE");
    fuels.add("DIESEL");
    fuels.add("GAS");
    fuels.add("ELECTRIC");
    return fuels;
  }

  @Bean
  public List<String> availableTransmissions()
  {
    List<String> transmissions = new ArrayList<>();
    transmissions.add("AUTOMATIC");
    transmissions.add("MANUAL");
    transmissions.add("HYBRID");
    return transmissions;
  }

  @Bean
  public ObjectMapper objectMapper()
  {
    return new ObjectMapper();
  }
}
