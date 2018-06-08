package lab.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@Component("country")
public class SimpleCountry implements Country {
    long id;
    String name;
    String codeName;

    public SimpleCountry(String name, String codeName) {
        this(0L, name, codeName);
    }

    public SimpleCountry setId(long id) {
        this.id = id;
        return this;
    }

    public SimpleCountry setName(String name) {
        this.name = name;
        return this;
    }

    public SimpleCountry setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }
}
