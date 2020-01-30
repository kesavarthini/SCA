package com.publicissapient.lloyds.orderservice.exception;

public class OrderServiceCustomException extends RuntimeException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param message
     *            - String
     */
    public OrderServiceCustomException(String message) {
        super(message);
    }

}
