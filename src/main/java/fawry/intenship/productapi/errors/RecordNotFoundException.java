package fawry.intenship.productapi.errors;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(){
        super();
    }
    public RecordNotFoundException(String message) {
        super(message);

    }



}
