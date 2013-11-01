package Titanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Plateau extends JPanel{
	
	private int dimY;
	private int dimX;
	protected List<Bateau> bateaux;
	protected List<Naufrage> naufrages;
	
	Plateau(int x, int y, char[][] tabJeu){
		this.dimX = x;
		this.dimY = y;
		this.setLayout(new GridLayout(dimX,dimY));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		this.setBackground(Color.CYAN);
			
		/*Assignation des cases et des éléments liés*/
		for(int j = 0; j<dimX ;j++)
		{
			for(int i =0; i< dimY; i++)
			{
				if(Character.isLetter(tabJeu[j][i]))
					this.add(new Case(this,j,i,new Naufrage('a',this,j,i)));
				else 
				{
					if(Character.isDigit(tabJeu[j][i]))
					{
						this.add(new Case(this,j,i,new Bateau('a',this,j,i,Direction.BAS)));
					}
					else
						this.add(new Case(j,i));
				}				
			}
		}
	}
	
	public void PositionProue(Graphics g, int i,int j, Direction d){
		
		if(d == Direction.HAUT){
			((Case)(this.getComponentAt(i, j-1))).AfficherProue(g,d);
		}
		if(d == Direction.BAS){
			((Case)(this.getComponentAt(i, j+1))).AfficherProue(g,d);
		}
		if(d == Direction.DROITE){
			((Case)(this.getComponentAt(i+1, j))).AfficherProue(g,d);
		}
		if(d == Direction.GAUCHE){
			((Case)(this.getComponentAt(i-1, j))).AfficherProue(g,d);
		}
		
	}
	
	/*public void paintComponent(Graphics g) {		
		super.paintComponent(g);
	}*/
}