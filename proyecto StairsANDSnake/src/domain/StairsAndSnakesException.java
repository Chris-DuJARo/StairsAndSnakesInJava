package domain;

public class StairsAndSnakesException extends Exception{

	public static final String NOT_ENOUGHT_SPACE = "No hay suficientes casillas libres para el numero indicado de" +
			" serpientes y escaleras";

	public static final String SAME_NAMES = "Los jugadores NO pueden tener el mismo nombre";

	public static final String NOT_FOUND_SNAKE = "No existe una serpiente menor a la posicion de la ficha";

	public static final String NOT_FOUND_STAIR = "No existe una escalera mayor a la posicion de la ficha";

	public static final String NOT_PORC_ALLOW = "Demasiadas casillas especiales";

    public static final String NOT_ALLOW_MOVEMENT = "No puede realizar ese movimiento supera al tablero";
	public static final String NOT_ENOUGHT_PLAYERS = "Debe rellenar el nombre de los jugadores";
    public static final String NOT_ALLOW_MODIFIERS = "Demadiados modificadores";
    public static final String NOT_ALL_SPACES_FULL = "Debe rellenar todos los espacios";
	public static final String SAME_COLORS = "Los jugadores NO pueden tener el mismo color";

	public StairsAndSnakesException(String mensaje) {
		super(mensaje);
	}
}
