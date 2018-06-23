package lab.model;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
//@Component("person")
public class PersonImpl implements Person {
    long id;
    String firstName;
    String lastName;
    Country country;
    int age;
    float height;
    boolean isProgrammer;

    @Wither
    boolean broke;

    @Singular
    List<Contact> contacts;
}
