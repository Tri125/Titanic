package Titanic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImporteurGrille 
{
	private String cheminFichier = null;
	private BufferedReader in = null;
	private final int HAUTEUR_GRILLE = 6;
	
	public ImporteurGrille()
	{
	}
	
	private Flottant CreateurFlottant()
	{
		return null;
	}
	
	
	public void Chargement(String chemin) throws IOException
	{
		char sup[][] = new char [HAUTEUR_GRILLE][HAUTEUR_GRILLE];
		try 
		{
			String ligne;
			int nbrLigne = 0;
			in = new BufferedReader(new FileReader(chemin));
			while ( (ligne = in.readLine()) != null)
			{
				for (int i = 0; i < ligne.length(); i++)
				{
					sup[nbrLigne][i] = ligne.charAt(i);
				}
				nbrLigne++;
			}
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			in.close();
		}
	}

}
