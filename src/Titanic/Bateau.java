package Titanic;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Bateau extends Flottant {

	private Direction direction;
	private Naufrage Sauver;
	
	public Bateau(char id, Plateau p, int i, int j, Direction d)
	{
		super(id, p, i, j);
		this.direction = d;
	}
	
	public void setNaufrage(Naufrage n){
		Sauver = n;
	}
	
	public Naufrage getNaufrage(){
		return Sauver;
	}
	public Direction getDirection(){
		return direction;
	}
	
}
