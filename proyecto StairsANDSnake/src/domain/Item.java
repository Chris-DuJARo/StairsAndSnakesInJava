package domain;

import java.awt.Color;

public interface Item {

	default void DoAction(Ficha ficha){

	}

	default void DoAction(Color color) {

	}

	default void SetObject(){

	}

}
