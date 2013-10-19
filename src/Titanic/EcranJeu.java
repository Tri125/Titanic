package Titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class EcranJeu extends JFrame {

	private JMenuBar topMenu;
	
	public EcranJeu()
	{
		super();
		
		topMenu = new JMenuBar();
		
		JMenu fichier = new JMenu("Fichier");
		JMenu actions = new JMenu("Actions");
		
		JMenuItem ouvrir = new JMenuItem("Ouvrir");
		ouvrir.setToolTipText("Charge un fichier de niveau");
		JMenuItem quitter = new JMenuItem("Quitter");
		//quitter.setMnemonic(KeyEvent.VK_Q); Dois avoir le menu fichier d'ouvert pour fonctionner
		quitter.setToolTipText("Quitte l'application");
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		JMenuItem haut = new JMenuItem("Haut");
		JMenuItem bas = new JMenuItem("Bas");
		JMenuItem gauche = new JMenuItem("Gauche");
		JMenuItem droite = new JMenuItem("Droite");
		JMenuItem recommencer = new JMenuItem("Recommencer");
		JMenuItem annuler = new JMenuItem("Annuler");
		
		fichier.add(ouvrir);
		fichier.add(quitter);
		
		actions.add(haut);
		actions.add(bas);
		actions.add(gauche);
		actions.add(droite);
		actions.add(recommencer);
		actions.add(annuler);
		
		topMenu.add(fichier);
		topMenu.add(actions);
		
		this.setJMenuBar(topMenu);
		this.setTitle("Titanic");
	}
	
}
