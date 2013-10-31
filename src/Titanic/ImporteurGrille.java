package Titanic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImporteurGrille 
{
	private String cheminFichier = null;
	private BufferedReader in = null;
	
	
	public ImporteurGrille()
	{
	}
	
	private Flottant CreateurFlottant()
	{
		return null;
	}
	
	
	public char[][] Chargement(String chemin) throws IOException
	{
		char sup[][] = new char [5][5];
		try 
		{
			String ligne;
			int nbrLigne = 0;
			in = new BufferedReader(new FileReader(chemin));
			while ((ligne = in.readLine()) != null)
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
		return sup;
	}

}
