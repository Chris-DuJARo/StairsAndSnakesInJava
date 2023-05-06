package test;

import domain.*;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StairsAndSnakesTest {
    @Test
    public void shouldThrowTheDice(){
        ArrayList<Dado> myDades;
        StairsAndSnakes myStairsGame;
        Valor valorDado;
        Tablero myTablero;

        String nombre = "Christian";
        Color color = Color.red;
        myStairsGame = new StairsAndSnakes(nombre,color,10,5,4,false,0,0);

        myTablero = myStairsGame.getTablero();
        myDades = myTablero.getDados();
        valorDado = myDades.get(0).getDado();

        myStairsGame.changeDados("Christian");
        assertNotEquals(valorDado, myDades.get(0).getDado());
    }

    @Test
    public void someDadeShouldHaveAtLeastOneModifier() {
        Dado myDade;
        StairsAndSnakes myStairsGame;
        Tablero myTablero;

        String nombre = "Christian";
        Color color = Color.red;
        myStairsGame = new StairsAndSnakes(nombre,color,10,5,4,false,0,1);
        myTablero = myStairsGame.getTablero();
        myDade = myTablero.getDados().get(0);
        myStairsGame.changeDados("Christian");
        for (Valor v: myDade.getValues()) {
            if(v.getModifier() != null) {
                assertNotNull(v.getModifier());
            }
        }
    }
    @Test
    public void someDadeShouldHaveAllFacesWithModifiers() {
        Dado myDade;
        StairsAndSnakes myStairsGame;
        Tablero myTablero;

        String nombre = "Christian";
        Color color = Color.red;
        myStairsGame = new StairsAndSnakes(nombre,color,10,5,4,false,0,6);
        myTablero = myStairsGame.getTablero();
        myDade = myTablero.getDados().get(0);
        myStairsGame.changeDados("Christian");
        int number = 0;
        for (Valor v: myDade.getValues()) {
            if(v.getModifier() != null) {
                number ++;

            }
        }
        assertEquals(6,number);
    }
    @Test
    public void shouldHaveKnowThePlayersNames() {
        StairsAndSnakes myStairsGame;
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Color> colores = new ArrayList<>();
        Tablero myTablero;

        nombres.add("Christian");
        nombres.add("Miguel");
        colores.add(Color.RED);
        colores.add(Color.BLUE);

        myStairsGame = new StairsAndSnakes(nombres,colores,10,5,4,false,0,0);
        myTablero = myStairsGame.getTablero();
        assertEquals(myTablero.getJugador("Christian").getName(),"Christian");
        assertEquals(myTablero.getJugador("Miguel").getName(),"Miguel");

    }

    @Test
    public void shouldMoveAplayer() {
        StairsAndSnakes myStairsGame;
        Tablero myTablero;

        myStairsGame = new StairsAndSnakes("Christian",Color.RED,10,4,5,false,0,0);
        myTablero = myStairsGame.getTablero();
        Player player = myTablero.getJugador("Christian");
        Ficha ficha = player.getFichas().get(0);
        Box BoxInicial = ficha.getBox();
        myStairsGame.changeDados("Christian");
        myStairsGame.movePlayer("Christian");
        Box BoxFinal = ficha.getBox();
        assertNotSame(BoxInicial, BoxFinal);
    }

    @Test
    public void aModifierShouldDoAnAction() {

        StairsAndSnakes myStairsGame;
        myStairsGame = new StairsAndSnakes("Christian",Color.RED,10,4,5,false,0,6);
        myStairsGame.changeDados("Christian");
        myStairsGame.movePlayer("Christian");
    }
}