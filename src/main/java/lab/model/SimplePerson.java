package lab.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
@Component("person")
public class SimplePerson implements Person {
    long id;
    String firstName;
    String lastName;
    Country country;
    int age;
    float height;
    boolean isProgrammer;
    boolean broke;
    @Singular
    List<Contact> contacts;
}
