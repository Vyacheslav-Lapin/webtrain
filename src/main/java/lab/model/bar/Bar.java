package lab.model.bar;

import lab.model.Person;

@FunctionalInterface
public interface Bar {
    Drink sellSquishee(Person person);
}