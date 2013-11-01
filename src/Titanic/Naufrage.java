package Titanic;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class Naufrage extends Flottant {
	boolean IsSafe = false;
	
	Naufrage(char id, Plateau p, int i, int j){
		super(id,p,i,j);
	}
}