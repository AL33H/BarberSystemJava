
package model.Exception;

public class CamposInvalidosExceptionException extends Exception  /* RuntimeException */{
    /**
     * importante caso a exceção seja serializada
     */
    private static final long serialVersionUID = 1149241039409861914L;

    // constrói um objeto NumeroNegativoException com a mensagem passada por parâmetro
    public CamposInvalidosExceptionException(String msg){
        super(msg);
    }

    // contrói um objeto NumeroNegativoException com mensagem e a causa dessa exceção, utilizado para encadear exceptions
    public CamposInvalidosExceptionException(String msg, Throwable cause){
        super(msg, cause);
    }
}
