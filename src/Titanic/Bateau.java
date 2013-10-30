package Titanic;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Bateau extends Flottant {

	private Direction direction;
	
	public void afficher(Graphics g, String mess)
	{
		Graphics2D g2d = (Graphics2D)g;
		remplirTriangle(g,100,200,40,40);
		
	}
	
	public Bateau(char id, Plateau p, int i, int j, Direction d)
	{
		super(id, p, i, j);
		this.direction = d;
	}
	
	
	
	public enum Direction{
		HAUT('↑'), GAUCHE('←'), DROITE('→'), BAS('↓');
		
		private final char dir;
		
		Direction(char dir){
			this.dir = dir;
		}
		
		public char getChar(){
			return dir;
		}
	}
	
	
	  private static void drawTriangle (Graphics g, int x, int y, int largeur, int hauteur){
	        // trace un triangle isocÃ¨le inscrit dans un rectangle, la base
	        // du triangle est celle du rectangle et le sommet est le centre
	        // du haut du rectangle
	        // dÃ©termine les trois sommets et on trace une ligne entre eux
	        if (hauteur>=0 && largeur>=0){
	            int x1 = x+largeur/2,
	                y1 = y,
	                x2 = x,
	                y2 = y+hauteur,
	                x3 = x+largeur,
	                y3 = y+hauteur;
	            g.drawLine(x1,y1,x2,y2);
	            g.drawLine(x2,y2,x3,y3);
	            g.drawLine(x3,y3,x1,y1);
	        }
	    }
		
	    private static void remplirTriangle (Graphics g, int x, int y, int largeur, int hauteur){
	        if (largeur>0 && hauteur>0){
	            drawTriangle(g,x,y,largeur,hauteur);
	            remplirTriangle(g,x+1,y+1,largeur-2,hauteur-2);
	        } else if (largeur>0) g.drawLine(x,y,x+largeur,y);
	        else if (hauteur>0) g.drawLine(x,y,x,y+hauteur);
	    }
}
