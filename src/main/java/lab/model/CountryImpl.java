package lab.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Component("country")
@Accessors(chain = true)
@Table(name = "country")
@EqualsAndHashCode(exclude = "id")
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
