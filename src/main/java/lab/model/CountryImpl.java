package lab.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@Component("country")
@Accessors(chain = true)
@Table(name = "country")
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CountryImpl implements Country {

    @Id
    @GeneratedValue
    long id;
    String name;
    String codeName;

    public CountryImpl(String name, String codeName) {
        this(0L, name, codeName);
    }
}
