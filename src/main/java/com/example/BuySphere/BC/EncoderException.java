package com.example.BuySphere.BC;

/**
 * Exception thrown if an attempt is made to encode invalid data, or some other
 * failure occurs.
 */
public class EncoderException
        extends IllegalStateException {

    private final Throwable cause;

    public EncoderException(String msg, Throwable cause) {
        super(msg);

        this.cause = cause;
    }

    /**
     *
     * @return
     */
    @Override
    public Throwable getCause() {
        return cause;
    }
}
