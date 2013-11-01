package Titanic;

public enum Direction{
	HAUT('↑'), GAUCHE('←'), DROITE('→'), BAS('↓');
	
	private final char dir;
	
	Direction(char dir){
		this.dir = dir;
	}
	
	public char getChar(){
		return dir;
	}
}