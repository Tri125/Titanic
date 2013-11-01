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
	
	/*Tableau temporaire avant lecture fichier*/
	private char[] col0 = {'a','-','-','-','-','-'};
	private char[] col1 = {'-','b','-','-','-','-'};
	private char[] col2 = {'-','-','5','-','-','-'};
	private char[] col3 = {'-','-','-','-','-','-'};
	private char[] col4 = {'-','-','-','-','-','-'};
	private char[] col5 = {'-','-','-','-','-','1'};
	private char[][] tab = {col0,col1,col2,col3,col4,col5};
	
	Plateau(int x, int y){
		this.dimX = x;
		this.dimY = y;
		this.setLayout(new GridLayout(dimX,dimY));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		this.setBackground(Color.CYAN);
			
		/*Assignation des cases et des �l�ments li�s*/
		for(int j = 0; j<dimX ;j++){
			for(int i =0; i< dimY; i++){
				if(Character.isLetter(tab[i][j]))
					this.add(new Case(this,i,j,new Naufrage('a',this,i,j)));
				else {
					if(Character.isDigit(tab[i][j])){
						this.add(new Case(this,i,j,new Bateau('a',this,i,j,Direction.BAS)));
					}else
						this.add(new Case(i,j));
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