package metagrid.core;

public class MetaGridException extends RuntimeException {
    
    public MetaGridException(String message) {
        super(message);
    }

    public MetaGridException(String format, Object... args) {
        super(String.format(format, args));
    }
    
    public MetaGridException(Throwable cause) {
        super(cause);
    }
}
