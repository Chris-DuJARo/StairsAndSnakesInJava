package domain;

import java.awt.*;
import java.util.ArrayList;

public class Machine extends Player{

    private String dificultad;

    private int decision = 1;

    public Machine(String name, Color color,String dificultad, ArrayList<Dado> dados) {
        super(name, color, dados);
        this.dificultad = dificultad;
    }

    public int getDecision(){
        return decision;
    }

    public int DecideBox(ArrayList<Integer> opciones){
        int decision = 0;
        if (dificultad.equals("Aprendiz")){
           decision = DecideExpertBox(opciones);
        }
        else{
            for (int i = 0; i < opciones.size(); i++) {
                if (opciones.get(i) > decision) decision = opciones.get(i);
            }

        }
        return decision;
    }

    private int DecideExpertBox(ArrayList<Integer> opciones){
        int decision = opciones.get(opciones.size()-1);
        Tablero tablero = getFichas().get(0).getBox().getTablero();
        for (Integer op: opciones){
            Box casilla = tablero.searchBox(op);
            if (casilla.getItem() instanceof Movement){
                decision = op;
            }else if (casilla instanceof Jumper){
                decision = op;
            }else{
                decision = opciones.get(opciones.size()-1);
            }
        }
        return decision;
    }

    @Override
    public void moveToken() throws StairsAndSnakesException {
        if (dificultad.equals("Principiante")){
            super.moveToken();
        }else {
            decideMovement();
        }
    }

    private void decideMovement() throws StairsAndSnakesException {
        Ficha ficha = getFichas().get(0);
        Box casilla = ficha.getBox();
        Tablero tablero = casilla.getTablero();
        Valor cara = dados.get(0).getDado();

        if (cara.getModifier() != null){
            decideModifier(tablero,cara);
            super.moveToken();
        } else {
            super.moveToken();
        }
    }

    private void decideModifier(Tablero tablero, Valor cara){
        Modifier mod = cara.getModifier();
        if (mod instanceof MovementBuff){
            decision = 0;
        } else if (mod instanceof MovementNerf) {
            decision = 1;
        } else {
            Ficha myToken = getFichas().get(0);
            Ficha jugador2 = tablero.searchOpponent(getColor());
            if (jugador2.getBox().getValue() > myToken.getBox().getValue()){
                decision = 0;
            }else {
                decision = 1;
            }
        }
    }

}
