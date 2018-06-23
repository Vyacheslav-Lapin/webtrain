package lab.model;

import lab.commons.Identifiable;

public interface User extends Identifiable<User> {

    String getFirstName();

    User setFirstName(String firstName);

    String getLastName();

    User setLastName(String lastName);

    default String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
