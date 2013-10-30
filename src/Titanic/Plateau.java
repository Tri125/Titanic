package Titanic;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import Titanic.Bateau.Direction;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

public class Plateau  extends JPanel{

	private GridLayout grille;
	Bateau pd= new Bateau('c',this,0,0,Direction.BAS);
	public Plateau()
	{
		JPanel panneau = new JPanel(new GridLayout(6,6));
		panneau.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		this.setBorder(BorderFactory.createLineBorder(Color.orange, 4));

		this.add(new JLabel("idk"));
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.cyan);
        g.drawString("Bonjour les amis", 40, 80);
        // si on utilise les possibilitÃ©s du Graphique 2D
        Graphics2D g2d = (Graphics2D)g;
        //g2d.rotate(Math.PI/4,40,80); // tourner de 45 deg autour de 40,80
        g2d.drawString("Bonjour les amis",40,80);
        pd.afficher(g,"");
        
    }
}
