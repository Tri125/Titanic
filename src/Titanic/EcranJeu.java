package Titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class EcranJeu extends JFrame {

	private JMenuBar topMenu;
	private Plateau plateauJeu;
	private ImporteurGrille importeurGrille;
	private final String CHEMIN_FICHIER_GRILLE = "ConfigsPetits.txt";
	private char[][] tabJeu;
	
	public EcranJeu()
	{
		super();
		importeurGrille = new ImporteurGrille(6);
		tabJeu = importeurGrille.Chargement(CHEMIN_FICHIER_GRILLE);
		plateauJeu = new Plateau(6,6, tabJeu);
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
		plateauJeu.setVisible(true);
		plateauJeu.setSize(400,400);
		this.add(plateauJeu);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
}
