package Titanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Proue extends Flottant {
	private Direction direction;
	private Case caseBateau;
	
	public Proue(char id, Plateau p, int i, int j, Direction d){
		super(id,p,i,j);
		direction = d;
	}
	
	public void setCaseBateau(Case caseBateau)
	{
		this.caseBateau = caseBateau;
	}
	
	public Case getCaseBateau()
	{
		return caseBateau;
	}
	
	public void AfficherProue(Graphics g, int w,int h){
		char[] id = {this.getId()};
		String str = new String(id);
		
		g.setColor(Color.ORANGE);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();
		
		// Triangle vers le haut 
		if(this.direction == Direction.HAUT){
			g2d.rotate(Math.toRadians(0));
			this.remplirTriangle(g2d, 0,0, w, h);
			g.setColor(Color.BLACK);
			g.drawString(str, w/2, h/2);
		}	
		// Triangle � l'envers
		if(this.direction == Direction.BAS){
			g2d.rotate(Math.toRadians(180));
		    this.remplirTriangle(g2d, -w, -h, w, h);
		    g.setColor(Color.BLACK);
		    g.drawString(str, -w/2,-h/2);
		}
		//Triangle vers la droite
		if(this.direction == Direction.DROITE){
			g2d.rotate(Math.toRadians(90));
		    this.remplirTriangle(g2d, 0, -h, w, h);
		    g.setColor(Color.BLACK);
		    g.drawString(str, w/2, -h/2);
		}
		//Triangle vers la gauche
		if(this.direction == Direction.GAUCHE){
			g2d.rotate(Math.toRadians(-90));
			this.remplirTriangle(g2d, -w, 0, w	, h);
			g.setColor(Color.BLACK);
			g2d.drawString(str, -w/2, h/2);
		}
						
		g2d.setTransform(old);
	}
	private void drawTriangle (Graphics2D g, int x, int y, int largeur, int hauteur){
        // trace un triangle isocèle inscrit dans un rectangle, la base
        // du triangle est celle du rectangle et le sommet est le centre
        // du haut du rectangle
        // détermine les trois sommets et on trace une ligne entre eux
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
	
	private void remplirTriangle (Graphics2D g, int x, int y, int largeur, int hauteur){
        if (largeur>0 && hauteur>0){
            drawTriangle(g,x,y,largeur,hauteur);
            remplirTriangle(g,x+1,y+1,largeur-2,hauteur-2);
        } else if (largeur>0) g.drawLine(x,y,x+largeur,y);
        else if (hauteur>0) g.drawLine(x,y,x,y+hauteur);
    }
	
}