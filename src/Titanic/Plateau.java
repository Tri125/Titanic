package Titanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Plateau extends JPanel {
	
	private int dimY;
	private int dimX;
	private Case[][] cases;
	
	Plateau(int dimX, int dimY, char[][] tabJeu){
		this.dimX = dimX;
		this.dimY = dimY;
		this.setLayout(new GridLayout(dimX,dimY));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		this.setBackground(Color.CYAN);
		cases = new Case[dimY][dimX];
		
		//Assignation des cases et des éléments liés
		GenerateGrid(tabJeu);
		SetKeybind();

	}
	
	private void GenerateGrid(char[][] tabJeu)
	{
		for(int y = 0; y<dimY ;y++)
		{
			for(int x =0; x< dimX; x++)
			{
				if(Character.isLetter(tabJeu[y][x]))
					cases[y][x] = new Case(this,x,y,new Naufrage(tabJeu[y][x],this,x,y));
				else
				{
					if(tabJeu[y][x] == '-')
					{
						cases[y][x] = new Case(this,x,y);
					}
					else 
					{
						switch (tabJeu[y][x])
						{
							case '↑':
							{
								cases[y+1][x] = new Case(this,x,y+1,new Bateau(tabJeu[y+1][x],this,x,y+1,x,y,Direction.HAUT));
								cases[y][x] = ((Bateau) (cases[y+1][x].getFlottant())).getProue();
								Proue p = (Proue)((Bateau) (cases[y+1][x].getFlottant())).getProue().getFlottant();
								p.setCaseBateau(cases[y+1][x]);
								break;
							}
							case '←':
							{
								cases[y][x+1] = new Case(this,x+1,y,new Bateau(tabJeu[y][x+1],this,x+1,y,x,y,Direction.GAUCHE));
								cases[y][x] = ((Bateau) (cases[y][x+1].getFlottant())).getProue();
								Proue p = (Proue)((Bateau) (cases[y][x+1].getFlottant())).getProue().getFlottant();
								p.setCaseBateau(cases[y][x+1]);
								break;
							}
							case '↓':
							{
								cases[y-1][x] = new Case(this,x,y-1,new Bateau(tabJeu[y-1][x],this,x,y-1,x,y,Direction.BAS));
								cases[y][x] = ((Bateau) (cases[y-1][x].getFlottant())).getProue();
								Proue p = (Proue)((Bateau) (cases[y-1][x].getFlottant())).getProue().getFlottant();
								p.setCaseBateau(cases[y-1][x]);
								break;
								
							}
							case '→':
							{
								cases[y][x-1] = new Case(this,x-1,y,new Bateau(tabJeu[y][x-1],this,x-1,y,x,y,Direction.DROITE));
								cases[y][x] = ((Bateau) (cases[y][x-1].getFlottant())).getProue();
								Proue p = (Proue)((Bateau) (cases[y][x-1].getFlottant())).getProue().getFlottant();
								p.setCaseBateau(cases[y][x-1]);
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
		for (int y = 0; y< cases.length; y++)
		{
			for (int x = 0; x < cases[y].length; x++)
			{
				this.add(cases[y][x]);
			}
		}
	}
	
	private void SetKeybind()
	{
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"pressedLeft");
		this.getActionMap().put("pressedLeft", new MoveShipAction(Direction.GAUCHE));
		
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"pressedRight");
		this.getActionMap().put("pressedRight", new MoveShipAction(Direction.DROITE));
		
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"pressedUp");
		this.getActionMap().put("pressedUp", new MoveShipAction(Direction.HAUT));
		
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"pressedDown");
		this.getActionMap().put("pressedDown", new MoveShipAction(Direction.BAS));
	}
	
	
	private class MoveShipAction extends AbstractAction {
		private Direction dir;
		
		public MoveShipAction(Direction dir) {
			this.dir = dir;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			MoveShip(dir);
		}
	}
		
	private void MoveShip(Direction dir)
	{
		//Méthode pour ordonner le déplacement
		int[][] posBat = new int[2][2];
		int i=0;
		Case tmp1 = new Case();
		Case tmp2 = new Case();
		for (int y = 0; y < cases.length; y++)
		{
			for (int x = 0; x <cases[y].length; x++)
			{
				if(cases[y][x].IsSelectionner()== true)
				{
						if(i==0)tmp1 = cases[y][x];
						else{tmp2 = cases[y][x];}
						posBat[i][0]=x; 	
						posBat[i][1]=y;
						i++;
				}
			}
		}
		if(VerifierCase(dir,posBat[0][0],posBat[0][1]) && VerifierCase(dir,posBat[1][0],posBat[1][1]))
		{
			
			if(dir == Direction.BAS || dir == Direction.DROITE)
			{
				Bouger(dir,posBat[1][0],posBat[1][1]);
				Bouger(dir,posBat[0][0],posBat[0][1]);
			}
			else
			{
				Bouger(dir,posBat[0][0],posBat[0][1]);
				Bouger(dir,posBat[1][0],posBat[1][1]);
			}
		}
		this.removeAll();
		this.revalidate();
		for (int y = 0; y < cases.length; y++)
		{
			for (int x = 0; x <cases[y].length; x++)
			{
				this.add(cases[y][x]);
				cases[y][x].repaint();			
			}
		}
	}
	
	
	private void Bouger(Direction dir,int x,int y){
		System.out.println("Bouge "+x+":"+y+"->");
		if(dir == Direction.HAUT){
			cases[y-1][x] = cases[y][x];
			cases[y-1][x].setCoor(x, y-1);
			cases[y-1][x].Selection();
			System.out.println(cases[y-1][x]);
		}
		if(dir == Direction.BAS){
			cases[y+1][x] = cases[y][x];
			cases[y+1][x].setCoor(x, y+1);
			System.out.println(cases[y+1][x]);
		}
		if(dir == Direction.GAUCHE){
			cases[y][x-1] = cases[y][x];
			cases[y][x-1].setCoor(x-1, y);
			System.out.println(cases[y][x-1]);
		}
		if(dir == Direction.DROITE){
			cases[y][x+1] = cases[y][x];
			cases[y][x+1].setCoor(x+1, y);
			System.out.println(cases[y][x+1]);
		}
		cases[y][x] = new Case(this,x,y);
	}
	
	private boolean VerifierCase(Direction dir,int x,int y){
		boolean b= false;
		if(dir == Direction.HAUT){
			if((y-1) >= 0
				&& (cases[y-1][x].getFlottant() == null 
				|| cases[y-1][x].IsSelectionner())){	
				b= true;
			}
		}
		if(dir == Direction.BAS){
			if((y+1) < dimY 
					&& (cases[y+1][x].getFlottant() == null 
					|| cases[y+1][x].IsSelectionner())){
				b= true;
			}
		}
		if(dir == Direction.GAUCHE){
			if((x-1) >= 0 
					&& (cases[y][x-1].getFlottant() == null 
					|| cases[y][x-1].IsSelectionner())){
				b= true;
			}
		}
		if(dir == Direction.DROITE){
			if((x+1) < dimX
					&& (cases[y][x+1].getFlottant() == null 
					|| cases[y][x+1].IsSelectionner())){
				b= true;
			}
		}
		return b;
	}
	
	public void DeSelectionCases()
	{
		for (int y = 0; y < cases.length; y++)
		{
			for (int x = 0; x <cases[y].length; x++)
			{
				cases[y][x].DeSelection();
				cases[y][x].repaint();
			}
		}
		
	}
	
}
	
