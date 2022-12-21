package fawry.intenship.productapi.errors;

public class ConflictException extends RuntimeException {
    public ConflictException(){
        super();
    }
    public ConflictException(String message) {
        super(message);

    }
}
