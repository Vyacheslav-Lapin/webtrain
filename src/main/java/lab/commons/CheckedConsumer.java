package lab.commons;

import java.util.function.Consumer;

import static lab.commons.Exceptions.throwChecked;

public interface CheckedConsumer<R> extends io.vavr.CheckedConsumer<R> {

    /**
     * Returns an unchecked consumer that will <em>sneaky throw</em> if an exceptions occurs when applying the function.
     * @return a new consumer that throws a {@code Throwable}.
     */
    default Consumer<R> unchecked() {
        return r -> {
            try {
                accept(r);
            } catch(Throwable t) {
                throwChecked(t);
            }
        };
    }
}
