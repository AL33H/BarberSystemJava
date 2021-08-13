
package model.Exception;

/**
 *
 * @author ALEFF
 */

public class BDException extends RuntimeException {

    public BDException(String message) {
        super(message);
    }

    public BDException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
