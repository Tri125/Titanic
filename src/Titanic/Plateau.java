package Titanic;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Titanic.Bateau.Direction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

public class Plateau  extends JPanel{

	private GridLayout grille;
	
	public Plateau()
	{
		grille = new GridLayout(6,6);
		JPanel jdJPanel = new JPanel(grille);
		this.setBorder(BorderFactory.createLineBorder(Color.orange, 4));
		//jdJPanel.add(new Bateau('c',this,0,0,Direction.BAS));
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
    }
}
