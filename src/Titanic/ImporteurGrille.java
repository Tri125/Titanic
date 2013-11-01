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
	private final int DIMENSION_GRILLE;
	
	public ImporteurGrille(int dimension)
	{
		DIMENSION_GRILLE = dimension;
	}
	
	
	
	public char[][] Chargement(String chemin)
	{
		char sup[][] = new char [DIMENSION_GRILLE][DIMENSION_GRILLE];
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
					if(++nbrLigne >= DIMENSION_GRILLE)
					{
						break;
					}
				}
				
			}
			in.close();
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
			sup = null;
		}
		return sup;
	}

}
