package lab.aop;

import lab.JavaConfig;
import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ContextConfiguration(classes = JavaConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AopAspectJTest {

    Bar bar;

    Person person;

    @NonFinal
    String fromSystemOut;

    @BeforeEach
    void setUp() {
        fromSystemOut = TestUtils.fromSystemOut(() -> bar.sellSquishee(person));
    }

    @Test
    void testBeforeAdvice() {
        assertTrue(fromSystemOut.contains("Hello"), "Before advice is not good enough...");
        assertTrue(fromSystemOut.contains("How are you doing?"), "Before advice is not good enough...");
    }

    @Test
    void testAfterAdvice() {
        assertTrue(fromSystemOut.contains("Good Bye!"), "After advice is not good enough...");
    }

    @Test
    void testAfterReturningAdvice() {
        assertTrue(fromSystemOut.contains("Good Enough?"), "Customer is broken");
    }

    @Test
    void testAroundAdvice() {
        assertTrue(fromSystemOut.contains("Hi!"), "Around advice is not good enough...");
        assertTrue(fromSystemOut.contains("See you!"), "Around advice is not good enough...");
    }

    @Test
    void testAllAdvices() {
        assertFalse(bar instanceof ApuBar, "barObject instanceof ApuBar");
    }
}