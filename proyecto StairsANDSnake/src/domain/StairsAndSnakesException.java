package domain;

public class StairsAndSnakesException extends Exception{
    public static final String MODIFIER = "El dado posee un modificador";

    public StairsAndSnakesException(String mensaje) {
		super(mensaje);
	}
}
