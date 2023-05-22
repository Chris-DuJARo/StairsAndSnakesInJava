package domain;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Question extends Box{

    private HashMap<String,String[]> MyQuest;

    private ArrayList<Integer> respuestas;

    public Question(Tablero tbl) {
        super(tbl);
        MyQuest = new HashMap<>();
        respuestas = new ArrayList<>();
        setMyQuest();
    }
    private void setMyQuest(){
        String question1 = "Cual es la capital de Australia?";
        String[] posRespuestas1 = {"a) Sydney ", "b) Melbourne ","c) Brisbane","d) Canberra ","e) Perth "};

        MyQuest.put(question1,posRespuestas1);
        respuestas.add(3);

        String question2 = "Cual es el oceano mas grande de el mundo?";
        String[] posRespuestas2 = {"a) Atlantico ", "b) Indico ","c) Pacifico","d) Artico ","e) Antartico "};

        MyQuest.put(question2,posRespuestas2);
        respuestas.add(2);

        String question3 = "Quien es el autor de la novela 'Cien años de soledad'";
        String[] posRespuestas3 = {"a) Gabriel Garcia Marquez ", "b) Mario Vargas Llosa ","c) Pablo Neruda",
                "d) Octavio Paz ","e) Julio Cortazar "};

        MyQuest.put(question3,posRespuestas3);
        respuestas.add(0);

        String question4 = "En que pais se encuentra la torre Eiffel?";
        String[] posRespuestas4 = {"a) España ", "b) Francia ","c) Italia","d) Alemania ","e) Inglaterra "};

        MyQuest.put(question4,posRespuestas4);
        respuestas.add(1);

        String question5 = "Cual es el elemento quimico mas abundante en la corteza terrestre";
        String[] posRespuestas5 = {"a) Hidrogeno ", "b) Oxigeno ","c) Hierro","d) Aluminio ","e) Calcio "};

        MyQuest.put(question5,posRespuestas5);
        respuestas.add(3);
    }

    @Override
    public boolean hasApower(){
        return true;
    }
    @Override
    public void moveTokenWithPower(Ficha ficha) throws StairsAndSnakesException {
        super.moveTokenWithPower(ficha);
        JOptionPane.showMessageDialog(null,"Preparese para responder una pregunta!!");
        ficha.canMove = false;
        ficha.setMaxCas(getValue());
    }

    public boolean doQuestion(Ficha ficha){
        ficha.canMove = true;
        return true;
    }

}
