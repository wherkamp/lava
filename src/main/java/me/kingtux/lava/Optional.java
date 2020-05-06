package me.kingtux.lava;


import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * My new idea for an Optional
 *
 * @param <T> the type the value
 */
public class Optional<T> {
    @Nullable
    private T value;
    @Nullable
    private Exception exception;

    private Optional() {

    }

    private Optional(T value) {
        this.value = value;
    }

    public Optional(@Nullable Exception exception) {
        this.exception = exception;
    }

    public Optional<T> ifPresent(Consumer<T> consumer) {
        consumer.accept(value);
        return this;
    }

    public Optional<T> ifEmpty(Consumer<T> consumer) {
        consumer.accept(value);
        return this;
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public T or(T t) {
        return isPresent() ? value : t;
    }

    public T or(Supplier<T> t) {
        return isPresent() ? value : t.get();
    }

    public <X extends Throwable> T orThrow(X t) throws X {
        if (isPresent()) return value;
        throw t;
    }

    public <X extends Throwable> T orThrow(Supplier<X> t) throws X {
        if (isPresent()) return value;
        throw t.get();
    }/


    public T get() {
        if (isEmpty()) throw new NullPointerException("Value is null");
        return value;
    }

    /**
     * Did an error occur
     *
     * @return the status of the error
     */
    public boolean errorOccured() {
        return exception != null;
    }

    /**
     * Runs the consumer if any error occurred
     *
     * @param consumer the exception handler
     * @return this
     */
    public Optional<T> ifErrorOccurred(Consumer<Exception> consumer) {
        consumer.accept(exception);
        return this;
    }
    //STATIC CREATORS

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    public static <T> Optional<T> ofException(Exception value) {
        return new Optional<>(value);
    }

    public static <T> Optional<T> empty() {
        return new Optional<>();
    }
}
