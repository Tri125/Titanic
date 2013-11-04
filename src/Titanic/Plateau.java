package Titanic;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
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
		for (int y = 0; y < cases.length; y++)
		{
			for (int x = 0; x <cases[y].length; x++)
			{
				if(cases[y][x].IsSelectionner()== true)
				{
						posBat[i][0]=x; 	
						posBat[i][1]=y;
						i++;
				}
			}
		}
		System.out.println("Select : "+posBat[0][0]+":"+posBat[0][1]+" - "+ posBat[1][0]+" - "+posBat[1][1]);
		if(VerifierCase(dir,posBat[0][0],posBat[0][1]) && VerifierCase(dir,posBat[1][0],posBat[1][1]))
		{
			if((cases[posBat[0][1]][posBat[0][0]].getFlottant() instanceof Bateau || cases[posBat[0][1]][posBat[0][0]].getFlottant() instanceof Proue)
					&& (cases[posBat[1][1]][posBat[1][0]].getFlottant() instanceof Bateau || cases[posBat[1][1]][posBat[1][0]].getFlottant() instanceof Proue)){
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
		}
		this.Reafficher();
	}
	
	private void Bouger(Direction dir,int x,int y){
		if(dir == Direction.HAUT){
			cases[y-1][x] = cases[y][x];
			cases[y-1][x].setCoor(x, y-1);
		}
		if(dir == Direction.BAS){
			cases[y+1][x] = cases[y][x];
			cases[y+1][x].setCoor(x, y+1);
		}
		if(dir == Direction.GAUCHE){
			cases[y][x-1] = cases[y][x];
			cases[y][x-1].setCoor(x-1, y);
		}
		if(dir == Direction.DROITE){
			cases[y][x+1] = cases[y][x];
			cases[y][x+1].setCoor(x+1, y);
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
	
	private void Reafficher(){
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
	
	public Case getCase(int i,int j){
		return cases[j][i];
	}
	
	public Case TestSauver(Naufrage n){
		Case c = null;
		int xCour = n.getI();
		int yCour = n.getJ();
		if(xCour-1 >=0 
				&& cases[yCour][xCour-1].getFlottant() instanceof Bateau 
				&& ((Bateau)(cases[yCour][xCour-1].getFlottant())).getNaufrage() == null){
			c=cases[yCour][xCour-1];
		}
		else
		{
			if(xCour+1 < dimX
				&&cases[yCour][xCour+1].getFlottant() instanceof Bateau 
				&& ((Bateau)(cases[yCour][xCour+1].getFlottant())).getNaufrage() == null){
				c=cases[yCour][xCour+1];
			}
			else
			{
				if(yCour-1 >= 0
					&& cases[yCour-1][xCour].getFlottant() instanceof Bateau 
					&& ((Bateau)(cases[yCour-1][xCour].getFlottant())).getNaufrage() == null){
					c=cases[yCour-1][xCour];
				}
				else
				{
					if(yCour+1 <= dimY
						&& cases[yCour+1][xCour].getFlottant() instanceof Bateau 
						&& ((Bateau)(cases[yCour+1][xCour].getFlottant())).getNaufrage() == null){
						c=cases[yCour+1][xCour];
					}
				}
			}
		}
		
		return c;
	}
	public void SauverNaufrage(Naufrage n,Case secoureur){
		int x = n.getI();
		int y = n.getJ();
		((Bateau)(secoureur.getFlottant())).setNaufrage(n);
		n.setSafe();
		cases[y][x] = new Case(this,x,y);
		this.DeSelectionCases();
		this.Reafficher();
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
	
