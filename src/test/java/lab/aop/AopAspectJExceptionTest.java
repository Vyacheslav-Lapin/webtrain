package lab.aop;

import lab.JavaConfig;
import lab.model.Bar;
import lab.model.CustomerBrokenException;
import lab.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ContextConfiguration(classes = JavaConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AopAspectJExceptionTest {

    Bar bar;

    Person person;

    @BeforeEach
    void setUp() {
//        person.setBroke(true);
    }

    @Test
    void testAfterThrowingAdvice() {

        String fromSystemOut = TestUtils.fromSystemOut(() ->
                assertThrows(CustomerBrokenException.class, () ->
                        bar.sellSquishee(person)));

        assertTrue(fromSystemOut.contains("Hmmm..."), "Customer is not broken ");
    }
}