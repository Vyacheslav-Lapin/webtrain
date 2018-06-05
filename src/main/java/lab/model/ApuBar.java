package lab.model;

import org.springframework.stereotype.Component;

@Component("bar")
public class ApuBar implements Bar {

    @Override
	public Drink sellSquishee(Person customer)  {
        if (customer.isBroke())
            throw new CustomerBrokenException();

        System.out.println("Here is your Squishee");
        return () -> "Usual Squishee";
    }
}