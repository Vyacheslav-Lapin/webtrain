package lab.model;

import java.util.List;

public interface Person {
    String getFirstName();

    String getLastName();

    Country getCountry();

    int getAge();

    float getHeight();

    boolean isProgrammer();

    boolean isBroke();

    Person withBroke(boolean broke);

    List<Contact> getContacts();

    default String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
