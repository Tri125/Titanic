package Titanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Case extends JPanel{
	
	final int RAYON = 15;
	private int i,j;
	private Flottant flot;
	private Plateau plateau;

	Case(Plateau p,int x,int y,Flottant f){
		plateau = p;
		i = x;
		j = y;
		flot = f;
		this.setBackground(Color.CYAN);
	}
	
	Case(int x, int y){
		i = x;
		j = y;
		this.setBackground(Color.CYAN);
	}
	
	public Flottant getFlottant(){
		return flot;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		//Contour Case
		g.setColor(Color.black);
        g.drawRect(0, 0, getSize().width, getSize().height);
        
        if(this.flot instanceof Bateau)
        	AfficherBateau(g,getSize().width,getSize().height);
        else{
        	if(this.flot instanceof Naufrage)
        		AfficherNaufrage(g,getSize().width,getSize().height);
        }
        	
	}
	
	//***Manque les chiffres/lettres**//
	private void AfficherNaufrage(Graphics g, int w, int h){
		g.setColor(Color.RED);
		g.fillOval(w/2-this.RAYON, h/2-this.RAYON, this.RAYON*2, this.RAYON*2);
	}
	
	//**Manque exeption bateau et chiffres/lettres**//
	//Problème d'affichage de la proue du bateau
	private void AfficherBateau(Graphics g, int w,int h){
		g.setColor(Color.WHITE);
		
		g.fillRect(5, 5, w-10, h-10);
		
		//Problème d'affichage de la proue du bateau
		//this.plateau.PositionProue(g, i, j, ((Bateau)(this.flot)).getDirection());		
	}
	
	public void AfficherProue(Graphics g, Direction d){
		int h = getSize().height;
		int w = getSize().width;
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();
		
		// Triangle vers le haut 
		if(d == Direction.HAUT){
			g2d.rotate(Math.toRadians(0));
			Case.remplirTriangle(g2d, 0,0, w, h);
		}	
		// Triangle à l'envers
		if(d == Direction.BAS){
			g2d.rotate(Math.toRadians(180));
		    Case.remplirTriangle(g2d, -w, -h, w, h);
		}
		//Triangle vers la droite
		if(d == Direction.DROITE){
			g2d.rotate(Math.toRadians(90));
		    Case.remplirTriangle(g2d, 0, -h, w, h);
		}
		//Triangle vers la droite
		if(d == Direction.GAUCHE){
			g2d.rotate(Math.toRadians(-90));
			Case.remplirTriangle(g2d, -w, 0, w	, h);
		}
						
		g2d.setTransform(old);
	}
	
	private static void drawTriangle (Graphics2D g, int x, int y, int largeur, int hauteur){
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
	
	private static void remplirTriangle (Graphics2D g, int x, int y, int largeur, int hauteur){
        if (largeur>0 && hauteur>0){
            drawTriangle(g,x,y,largeur,hauteur);
            remplirTriangle(g,x+1,y+1,largeur-2,hauteur-2);
        } else if (largeur>0) g.drawLine(x,y,x+largeur,y);
        else if (hauteur>0) g.drawLine(x,y,x,y+hauteur);
    }
	
	public String toString(){
		String str = "Case : "+i+" x "+j;
        return str;
    }
	
}
