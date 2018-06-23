package lab.model.bar;

import lab.aop.Loggable;
import lab.model.Person;

//@Component("bar")
public class ApuBar implements Bar {

    @Loggable
    @Override
	public Drink sellSquishee(Person person)  {
        if (person.isBroke())
            throw new CustomerBrokenException();

        System.out.println("Here is your Squishee");
        return () -> "Usual Squishee";
    }
}