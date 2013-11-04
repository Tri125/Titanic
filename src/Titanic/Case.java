package Titanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Case extends JPanel implements MouseListener{
	
	final int RAYON = 15;
	private int x,y;
	private Flottant flot;
	private Plateau plateau;
	private boolean selectionner = false;
	private Color c = new Color(176,196,222,150);
	
	Case()
	{
		x=-1;
		y=-1;
		flot=null;
		plateau =null;
	}

	Case(Plateau p,int x,int y,Flottant f){
		plateau = p;
		this.x = x;
		this.y = y;
		flot = f;
		this.setBackground(Color.CYAN);
		addMouseListener(this);
	}
	
	Case(Plateau p, int x, int y){
		this.x = x;
		this.y = y;
		this.plateau = p;
		this.flot = null; 
		this.setBackground(Color.CYAN);
		addMouseListener(this);
	}
	public void setCoor(int i,int j){
		this.x=i;
		this.y=j;
		this.flot.setIJ(i, j);
	}
	
	public Flottant getFlottant(){
		return flot;
	}
	
	public boolean IsSelectionner(){return selectionner;}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		//Contour Case
		g.setColor(Color.black);
        g.drawRect(0, 0, getSize().width, getSize().height);
        if(this.flot instanceof Bateau){
        	((Bateau)(this.flot)).AfficherBateau(g,getSize().width,getSize().height);
        }
        if(this.flot instanceof Proue){
        	((Proue)(this.flot)).AfficherProue(g,getSize().width,getSize().height);
        }
        if(this.flot instanceof Naufrage){
        	AfficherNaufrage(g,getSize().width,getSize().height);
        }
        if(selectionner)
        {
        	g.setColor(c);
        	g.fillRect(0, 0, getSize().width, getSize().height);
        }     	
	}
	
	private void AfficherNaufrage(Graphics g, int w, int h){
		char[] id = {this.flot.getId()};
		String str = new String(id);
		
		g.setColor(Color.RED);
		g.fillOval(w/2-this.RAYON, h/2-this.RAYON, this.RAYON*2, this.RAYON*2);
		g.setColor(Color.black);
		g.drawString(str, w/2, h/2);
	}
	
	public void mouseClicked(MouseEvent e) 
	{
			this.plateau.DeSelectionCases();
			//System.out.println(this.x + ":" + this.y);
			if (flot instanceof Bateau || flot instanceof Proue)
			{
				if (flot instanceof Bateau)
				{
					((Bateau) flot).getProue().Selection();;
				}
				else
				{
					if (flot instanceof Proue)
					{
						((Proue) flot).getCaseBateau().Selection();
					}
				}
				Selection();
				this.repaint();
			}
	}
	
	public void DeSelection()
	{
		if(this.flot != null){
			this.flot.setSelected(false);
		}
		selectionner = false;
	}
	
	public void Selection()
	{
		if(this.flot != null){
			this.flot.setSelected(true);
		}
		selectionner = true;
	}
	
	
	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	
	public String toString(){
        return "Case : "+ selectionner + "flot: "+ flot;
    }
}
