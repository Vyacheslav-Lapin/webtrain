package lab.aop;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@UtilityClass
public class TestUtils {

    public final String LINE_SEPARATOR = System.getProperty("line.separator");

    @SneakyThrows
    public String fromSystemOut(Runnable runnable) {

        PrintStream realOut = System.out;

        try (val out = new ByteArrayOutputStream();
             val printStream = new PrintStream(out)) {

            System.setOut(printStream);
            runnable.run();

            return new String(out.toByteArray());

        } finally {
            System.setOut(realOut);
        }
    }
}
