package lab.commons;

public interface Identifiable<T> {
    long getId();
    T setId(long id);
}
