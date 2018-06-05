package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.stereotype.Component;

@Value
@Builder
@AllArgsConstructor
@Component("country")
public class SimpleCountry implements Country {
    long id;
    String name;
    String codeName;
}
