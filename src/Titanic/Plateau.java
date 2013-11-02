package Titanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Plateau extends JPanel{
	
	private int dimY;
	private int dimX;
	protected List<Bateau> bateaux;
	protected List<Naufrage> naufrages;
	
	Plateau(int dimX, int dimY, char[][] tabJeu){
		this.dimX = dimX;
		this.dimY = dimY;
		this.setLayout(new GridLayout(dimX,dimY));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		this.setBackground(Color.CYAN);
		Case[][] cases = new Case[dimY][dimX];	
		/*Assignation des cases et des éléments liés*/
		for(int y = 0; y<dimY ;y++)
		{
			for(int x =0; x< dimX; x++)
			{
				if(Character.isLetter(tabJeu[y][x]))
					cases[y][x] = new Case(this,x,y,new Naufrage('a',this,x,y));
				else
				{
					if(tabJeu[y][x] == '-')
					{
						cases[y][x] = new Case(x,y);
					}
					else 
					{
						switch (tabJeu[y][x])
						{
							case '↑':
							{
								System.out.println(x + ":" + (y+1));
								System.out.println(x + ":" + (y));
								cases[y+1][x] = new Case(this,x,y+1,new Bateau('a',this,x,y+1,Direction.HAUT));
								cases[y][x] = new Case(x,y);
								break;
							}
							case '←':
							{
								System.out.println((x+1) + ":" + (y));
								cases[y][x+1] = new Case(this,x+1,y,new Bateau('a',this,x+1,y,Direction.GAUCHE));
								cases[y][x] = new Case(x,y);
								break;
							}
							case '↓':
							{
								System.out.println(x + ":" + (y-1));
								cases[y-1][x] = new Case(this,x,y-1,new Bateau('a',this,x,y-1,Direction.BAS));
								cases[y][x] = new Case(x,y);
								break;
							}
							case '→':
							{
								System.out.println((x-1) + ":" + (y));
								cases[y][x-1] = new Case(this,x-1,y,new Bateau('a',this,x-1,y,Direction.DROITE));
								cases[y][x] = new Case(x,y);
								break;
							}
						
							default:
							{
								break;
							}
						}
						
					}
				}				
			}
		}
		System.out.println("dd");
		for (int y = 0; y< cases.length; y++)
		{
			for (int x = 0; x < cases[y].length; x++)
			{
				this.add(cases[y][x]);
			}
		}
	}
	
	public void PositionProue(Graphics g, int i,int j, Direction d){
		System.out.println(d.toString());
		if(d == Direction.HAUT){
			//((Case)(this.getComponentAt(i, j-1))).AfficherProue(g,d);
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