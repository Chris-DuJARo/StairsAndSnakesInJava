package domain;

public class StairsAndSnakesException extends Exception{

	public static final String NOT_ENOUGHT_SPACE = "No hay suficientes casillas libres para el numero indicado de" +
			"serpientes y escaleras";

	public static final String NOT_FOUND_SNAKE = "No existe una serpiente menor a la posicion de la ficha";

	public static final String NOT_FOUND_STAIR = "No existe una escalera mayor a la posicion de la ficha";

	public static final String NOT_PORC_ALLOW = "Demasiadas casillas especiales";

	public StairsAndSnakesException(String mensaje) {
		super(mensaje);
	}
}
