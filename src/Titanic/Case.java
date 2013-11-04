package Titanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Case extends JPanel implements MouseListener{
	
	final int RAYON = 15;
	private int x;
	private int y;
	private Flottant flot;
	private Plateau plateau;
	private boolean selectionner = false;
	private Color c = new Color(176,196,222,150);
	
	Case(Plateau p,int x,int y,Flottant f){
		plateau = p;
		flot = f;
		this.x = x;
		this.y = y;
		this.setBackground(Color.CYAN);
		addMouseListener(this);
	}
	
	Case(Plateau p, int x, int y){
		this.plateau = p;
		this.flot = null; 
		this.x = x;
		this.y = y;
		this.setBackground(Color.CYAN);
		addMouseListener(this);
	}
	
	public void setCoor(int i,int j){
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
        	Case c = plateau.TestSauver((Naufrage)(this.flot));
        	if( c != null && !((Naufrage)(this.flot)).getSafe()){	
        			plateau.SauverNaufrage((Naufrage)(this.flot),c);
        	}
        	((Naufrage)(this.flot)).AfficherNaufrage(g,getSize().width,getSize().height);
        }
        if(selectionner)
        {
        	g.setColor(c);
        	g.fillRect(0, 0, getSize().width, getSize().height);
        }
	}
	
	public void mouseClicked(MouseEvent e) 
	{
			this.plateau.DeSelectionCases();
			if (flot instanceof Bateau || flot instanceof Proue)
			{
				if (flot instanceof Bateau && ((Bateau)(this.flot)).getNaufrage() == null)
				{
					Selection();
					((Bateau) flot).getProue().Selection();
				}
				else
				{
					if (flot instanceof Proue && ((Bateau)(((Proue) flot).getCaseBateau().getFlottant())).getNaufrage() == null)
					{
						Selection();
						((Proue) flot).getCaseBateau().Selection();
					}
				}
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
