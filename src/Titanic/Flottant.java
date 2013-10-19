package Titanic;
//caractéristiques communes aux naufragés et au bateaux
public abstract class Flottant {
 private char id;         //identificateur
 private Plateau plateau; // lien sur le plateau
 private int i,j;         // position 
 private boolean selected; // s'il l'objet est actuellement sélectionné
 
 // flottant special retourné lorsqu'on demande une position en dehors du jeu
 public static final Flottant BORD = new Flottant('/',null,0,0){};

 Flottant(char id, Plateau p, int i, int j){
     this.id=id;
     this.plateau=p;
     this.i=i;
     this.j=j;
 }
 
 public String toString(){
     return this.getClass().getSimpleName()+" "+id+":"+i+"@"+j;
 }
 
 public char getId(){
     return id;
 }

 public int getI(){
     return i;
 }
 
 public int getJ(){
     return j;
 }
 
 public void setIJ(int i, int j){
     this.i=i;
     this.j=j;
 }
 
 public Plateau getPlateau(){
     return plateau;
 }
 
 public boolean isSelected(){
     return selected;
 }
 
 public void setSelected(boolean s){
     this.selected=s;
 }
}