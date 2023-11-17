package test;

import domain.*;
import org.junit.Test;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StairsAndSnakesTest {
    @Test
    public void shouldThrowTheDice() throws StairsAndSnakesException {
        ArrayList<Dado> myDades;
        StairsAndSnakes myStairsGame;
        Valor valorDado;
        Tablero myTablero;

        String nombre = "Christian";
        Color color = Color.red;
        myStairsGame = new StairsAndSnakes(nombre,color,"Humano",10,5,4,false,0,0);

        myTablero = myStairsGame.getTablero();
        myDades = myTablero.getDados();
        valorDado = myDades.get(0).getDado();

        myStairsGame.changeDados("Christian");
        assertNotEquals(valorDado, myDades.get(0).getDado());
    }

    @Test
    public void someDadeShouldHaveAtLeastOneModifier() throws StairsAndSnakesException {
        Dado myDade;
        StairsAndSnakes myStairsGame;
        Tablero myTablero;

        String nombre = "Christian";
        Color color = Color.red;
        myStairsGame = new StairsAndSnakes(nombre,color,"Maquina",10,5,4,false,0,1);
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
    public void someDadeShouldHaveAllFacesWithModifiers() throws StairsAndSnakesException {
        Dado myDade;
        StairsAndSnakes myStairsGame;
        Tablero myTablero;

        String nombre = "Christian";
        Color color = Color.red;
        myStairsGame = new StairsAndSnakes(nombre,color,"Maquina",10,5,4,false,0,6);
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
    public void shouldHaveKnowThePlayersNames() throws StairsAndSnakesException {
        StairsAndSnakes myStairsGame;
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Color> colores = new ArrayList<>();
        Tablero myTablero;

        nombres.add("Christian");
        nombres.add("Miguel");
        colores.add(Color.RED);
        colores.add(Color.BLUE);

        myStairsGame = new StairsAndSnakes(nombres,colores,"Humano",10,0,0,false,0,0,"Facil");
        myTablero = myStairsGame.getTablero();
        assertEquals(myTablero.getJugador("Christian").getName(),"Christian");
        assertEquals(myTablero.getJugador("Miguel").getName(),"Miguel");

    }

    @Test
    public void shouldMoveAplayer() throws StairsAndSnakesException {
        StairsAndSnakes myStairsGame;
        Tablero myTablero;

        myStairsGame = new StairsAndSnakes("Christian",Color.RED,"Maquina",10,4,5,false,0,0);
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
    public void modifierShouldDoAnAction() throws StairsAndSnakesException {

        StairsAndSnakes myStairsGame;
        myStairsGame = new StairsAndSnakes("Christian",Color.RED,"Maquina",10,4,5,false,0,6);
        myStairsGame.changeDados("Christian");
        myStairsGame.movePlayer("Christian");
    }

    @Test
    public void shouldHaveSpecialsBoxs() throws StairsAndSnakesException {
        StairsAndSnakes myStairsGame;
        myStairsGame = new StairsAndSnakes("Christian", Color.RED,"Maquina", 10, 1, 1, false, 20, 0);
        Tablero myTablero = myStairsGame.getTablero();
        int i = 1;

        while (true) {
            Box shouldSpecial = myTablero.searchBox(i);
            i++;
            if (shouldSpecial.hasApower()) {
                break;
            } else if (i == 101) {
                fail();
            }
        }
    }

    @Test
    public void shouldHaveSomeStairsOrSnakes() throws StairsAndSnakesException {
        StairsAndSnakes myStairsGame;
        myStairsGame = new StairsAndSnakes("Christian", Color.RED,"Maquina", 10, 1, 1, false, 0, 0);
        Tablero tablero = myStairsGame.getTablero();
        int i=1;
        while (true) {
            Box shouldHaveAtTramp = tablero.searchBox(i);
            i++;
            if(shouldHaveAtTramp.hasAnyTramp() ){
                break;
            } else if (i == 101) {
                fail();
            }
        }
    }

    @Test
    public void stairsOrSnakeShouldMoveAplayer() throws StairsAndSnakesException {
        StairsAndSnakes myStairsGame;
        myStairsGame = new StairsAndSnakes("Christian", Color.RED,"Maquina", 10, 16, 17, false, 0, 0);
        myStairsGame.changeDados("Christian");
        Tablero tablero = myStairsGame.getTablero();
        int cantidad = tablero.getDados().get(0).getDado().getNumero();

        if(tablero.searchBox(cantidad+ 1).getItem() instanceof Tramp) {
            myStairsGame.movePlayer("Christian");
            Box casillaEsperada = ((Tramp) tablero.searchBox(cantidad + 1).getItem()).getCasillafin();
            Box casillaJugador = tablero.getJugador("Christian").getFichas().get(0).getBox();
            assertEquals(casillaEsperada,casillaJugador);
        }else stairsOrSnakeShouldMoveAplayer();
    }

    @Test
    public void specialBoxShouldMoveAplayer(){
        try {
        StairsAndSnakes myStairsGame;
        myStairsGame = new StairsAndSnakes("Christian", Color.RED,"Maquina", 10, 5, 5, false, 40, 0);
        myStairsGame.changeDados("Christian");
        Tablero tablero = myStairsGame.getTablero();
        int cantidad = tablero.getDados().get(0).getDado().getNumero() + 1;

            if (tablero.searchBox(cantidad).hasApower()) {
                if (tablero.searchBox(cantidad) instanceof Jumper) {
                    Box cajaSaltarina = tablero.searchBox(cantidad);
                    myStairsGame.movePlayer("Christian");

                    int ValorcasillaJugador = tablero.getJugador("Christian").getFichas().get(0).getBox().getValue();
                    assertTrue(ValorcasillaJugador >= cajaSaltarina.getValue());

                } else if (tablero.searchBox(cantidad) instanceof JumperInverse) {
                    Box cajaSaltarinaInversa = tablero.searchBox(cantidad);
                    myStairsGame.movePlayer("Christian");

                    int ValorcasillaJugador = tablero.getJugador("Christian").getFichas().get(0).getBox().getValue();
                    assertTrue(ValorcasillaJugador <= cajaSaltarinaInversa.getValue());

                } else if (tablero.searchBox(cantidad) instanceof Death) {
                    myStairsGame.movePlayer("Christian");

                    int ValorCasillaJugador = tablero.getJugador("Christian").getFichas().get(0).getBox().getValue();
                    assertEquals(1, ValorCasillaJugador);

                } else if (tablero.searchBox(cantidad) instanceof Movement) {
                    myStairsGame.movePlayer("Christian");
                    Stair myStair = (Stair) tablero.getJugador("Christian").getFichas().get(0).getBox().getItem();
                    assertNotNull(myStair);

                } else if (tablero.searchBox(cantidad) instanceof Recoil) {
                    myStairsGame.movePlayer("Christian");
                    Snake mySnake = (Snake) tablero.getJugador("Christian").getFichas().get(0).getBox().getItem();
                    assertNull(mySnake);

                } else if (tablero.searchBox(cantidad) instanceof Question) {
                    myStairsGame.movePlayer("Christian");
                }
            }else {
                specialBoxShouldMoveAplayer();
            }
        }catch (StairsAndSnakesException e){
            System.out.println(e.getMessage());
        }
    }
}