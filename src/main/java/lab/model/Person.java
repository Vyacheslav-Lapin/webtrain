package lab.model;

import java.util.List;

public interface Person extends User {

    Country getCountry();

    int getAge();

    float getHeight();

    boolean isProgrammer();

    boolean isBroke();

    Person withBroke(boolean broke);

//    List<Contact> getContacts();
}
