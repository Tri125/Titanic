package Titanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Bateau extends Flottant {

	private Direction direction;
	private Naufrage Sauver;
	private Case proue;
	
	public Bateau(char id, Plateau p, int i, int j,int iProue, int jProue, Direction d)
	{
		super(id, p, i, j);
		this.proue = new Case(p, iProue, jProue, new Proue(id,p,iProue,jProue,d));
		this.direction = d;
	}
	
	public void setNaufrage(Naufrage n){
		Sauver = n;
	}
	public Case getProue(){
		return proue;
	}
	
	public Naufrage getNaufrage(){
		return Sauver;
	}
	public Direction getDirection(){
		return direction;
	}
	
	public void AfficherBateau(Graphics g, int w,int h){
		g.setColor(Color.ORANGE);	
		g.fillRect(0, 0, w+1, h+1);
	}
	
}
