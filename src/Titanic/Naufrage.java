package Titanic;

import java.awt.Color;
import java.awt.Graphics;

public class Naufrage extends Flottant {
	final int RAYON = 15;
	private boolean isSafe = false;
	
	Naufrage(char id, Plateau p, int i, int j){
		super(id,p,i,j);
	}
	
	public void AfficherNaufrage(Graphics g, int w, int h){
		char[] id = {this.getId()};
		String str = new String(id);
		
		g.setColor(Color.RED);
		g.fillOval(w/2-this.RAYON, h/2-this.RAYON, this.RAYON*2, this.RAYON*2);
		g.setColor(Color.black);
		g.drawString(str, w/2, h/2);
	}
	
	public void setSafe(){
			isSafe=true;
	}
	public boolean getSafe(){
		return isSafe;
	}
}