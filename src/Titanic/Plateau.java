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
	//protected List<Bateau> bateaux;
	//protected List<Naufrage> naufrages;
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
		System.out.println(dir);
	}
	
	private void LeftKey()
	{
		
	}
	
	public void DeSelectionCases()
	{
		for (int y = 0; y < cases.length; y++)
		{
			for (int x = 0; x <cases[y].length; x++)
			{
				cases[y][x].DeSellection();
				cases[y][x].repaint();
			}
		}
		
	}
	
}
	
