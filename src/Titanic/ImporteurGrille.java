package Titanic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ImporteurGrille 
{
	private String cheminFichier = null;
	private BufferedReader in = null;
	private final int HAUTEUR_GRILLE = 6;
	
	public ImporteurGrille()
	{
	}
	
	private List<Flottant> CreateurFlottant(char[][] tab)
	{
		List<Flottant> flottans = new ArrayList<Flottant>();
		
		for (int i = 0; i < tab.length; i++)
		{
			
		}
		
		return null;
	}
	
	
	public void InitialisationJeu(String chemin)
	{
		try {
			CreateurFlottant(Chargement(chemin));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private char[][] Chargement(String chemin) throws IOException
	{
		char sup[][] = new char [HAUTEUR_GRILLE][HAUTEUR_GRILLE];
		try 
		{
			String ligne;
			int nbrLigne = 0;
			in = new BufferedReader(new InputStreamReader(new FileInputStream(chemin), "UTF-8"));
			
			while ( (ligne = in.readLine()) != null)
			{
				String ligneTraite = ligne.trim();
				
				if(ligneTraite.isEmpty() || ligneTraite.charAt(0) == '#' || ligneTraite.length() == 1)
				{
					//Continue
				}
				else
				{
					for (int i = 0; i < ligneTraite.length(); i++)
					{
						sup[nbrLigne][i] = ligneTraite.charAt(i);
					}
					if(++nbrLigne >= HAUTEUR_GRILLE)
					{
						break;
					}
				}
				
			}
			System.out.println("t");
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			in.close();
		}
		return sup;
	}

}
