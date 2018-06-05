package lab.model;

public interface Person {
    String getFirstName();

    String getLastName();

    Country getCountry();

    int getAge();

    float getHeight();

    boolean isProgrammer();

    boolean isBroke();

    java.util.List<Contact> getContacts();

    default String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
