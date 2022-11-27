package fawry.intenship.productapi.errors;

public class QuantityException extends RuntimeException {
    public QuantityException(){
        super();
    }
    public QuantityException(String message) {
        super(message);

    }
}
