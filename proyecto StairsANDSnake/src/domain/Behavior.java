package domain;

public interface Behavior extends Item{
    String normal = "normal";
    String debil = "debil";
    String sper = "Super";
    String dual = "dual";

    default void setBehavior(String comportamiento){

    }

}

