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
		try 
		{
			String ligne;
			int nbrLigne = 0;
			in = new BufferedReader(new FileReader(chemin));
			while ( (ligne = in.readLine()) != null)
			{
				for (int i = 0; i < ligne.length(); i++)
				{
					if (ligne.charAt(i) == '-')
						continue;
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
