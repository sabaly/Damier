/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damier;

import java.util.ArrayList;

/**
 *
 * @author TMS
 */
public class Pawn {
    protected Position position;
    private boolean king = false;
    private int indice;
    protected Position NextPos;

    public Pawn(Position position) {
        this.position = position;
        this.king = isKing();
        this.indice = 0;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setKing(boolean king) {
        this.king = king;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    /*
        This function change moves a pawn by changing his hut
    */
    public boolean move(Hut hut){
        if(hut.isActivated()){
            System.out.println("Déplacer de " + this.position.getColumn() + "" + this.position.getLine() + ""
                    + " à " + hut.getPosition().getColumn() + "" + hut.getPosition().getLine());
            this.setPosition(hut.getPosition());
            return true;
        }
        return false;
    }
    
    //The following function check if a pawn can catch or eat an opponent opponent pawn
    public boolean canEat(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        if(this.getIndice()==0)
            this.setIndice(1);
        
        if(this.isKing()){
            //nothing yet
        }else{
            //Check if there is a possible catch moving towards right
            if(this.canEatTowardsRight(my_pawn, opponent_pawn, line_number) && this.getIndice() == 1)
                return true;
            else
                this.setIndice(2);
            
            //Check if there is a possible catch moving towards left
            if(this.canEatTowardsLeft(my_pawn, opponent_pawn, line_number) && this.getIndice() == 2)
                return true;
            else
                this.setIndice(3);
            
            //Check if there is a possible catch moving behind right
            if(this.canEatBehindRight(my_pawn, opponent_pawn, line_number) && this.getIndice() == 3)
                return true;
            else
                this.setIndice(4);
            
            //Check if there is a possible catch moving behind left
            if(this.canEatBehindLeft(my_pawn, opponent_pawn, line_number) && this.getIndice() == 4)
                return true;
            else
                this.setIndice(0);
            
            
        }
        return false;
    }
    
    public boolean canEatTowardsRight(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        Hut hut = new Hut(new Position(this.getPosition().nextLine(line_number), this.getPosition().nextColumn(line_number)));
        if(this.getIndice() != 1 && this.getIndice() != 0)
            return false;
        if(hut.busyByOpponentPawn(opponent_pawn)){
            hut.setPosition(new Position(hut.getPosition().nextLine(line_number), hut.getPosition().nextColumn(line_number)));
            if(hut.isAvailable(my_pawn, opponent_pawn)){
                return true;
            }
        }
        return false;
    }
    public boolean canEatTowardsLeft(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        Hut hut = new Hut(new Position(this.getPosition().nextLine(line_number), this.getPosition().previewColumn()));
        if(this.getIndice() != 2 && this.getIndice() != 0)
            return false;
        if(hut.busyByOpponentPawn(opponent_pawn)){
            hut.setPosition(new Position(hut.getPosition().nextLine(line_number), hut.getPosition().previewColumn()));
            if(hut.isAvailable(my_pawn, opponent_pawn)){
                return true;
            }
        }
        return false;
    }
    public boolean canEatBehindRight(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        Hut hut = new Hut(new Position(this.getPosition().previewLine(), this.getPosition().nextColumn(line_number)));
        if(this.getIndice() != 3 && this.getIndice() != 0)
            return false;
        if(hut.busyByOpponentPawn(opponent_pawn)){
            hut.setPosition(new Position(hut.getPosition().previewLine(), hut.getPosition().nextColumn(line_number)));
            if(hut.isAvailable(my_pawn, opponent_pawn)){
                return true;
            }
        }
        return false;
    }
    public boolean canEatBehindLeft(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        Hut hut = new Hut(new Position(this.getPosition().previewLine(), this.getPosition().previewColumn()));
        if(this.getIndice() != 4 && this.getIndice() != 0)
            return false;
        if(hut.busyByOpponentPawn(opponent_pawn)){
            hut.setPosition(new Position(hut.getPosition().previewLine(), hut.getPosition().previewColumn()));
            if(hut.isAvailable(my_pawn, opponent_pawn)){
                return true;
            }
        }
        return false;
    }
    
    /*
        return the next possible hut of the pawn if it can eat an opponent pawn
    */
    public Position jumpPawn(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        if(this.getIndice() == 0)
            this.setIndice(1);
        if(this.canEatTowardsRight(my_pawn, opponent_pawn, line_number) && this.getIndice()==1){
            Hut hut = new Hut(new Position(this.getPosition().nextLine(line_number), this.getPosition().nextColumn(line_number)));
            return new Position(hut.getPosition().nextLine(line_number), hut.getPosition().nextColumn(line_number));
        }
        
        if(this.canEatTowardsLeft(my_pawn, opponent_pawn, line_number) && this.getIndice()==2){
            Hut hut = new Hut(new Position(this.getPosition().nextLine(line_number), this.getPosition().previewColumn()));
            return new Position(hut.getPosition().nextLine(line_number), hut.getPosition().previewColumn());
        }
        
        if(this.canEatBehindRight(my_pawn, opponent_pawn, line_number) && this.getIndice()==3){
            Hut hut = new Hut(new Position(this.getPosition().previewLine(), this.getPosition().nextColumn(line_number)));
               return new Position(hut.getPosition().previewLine(), hut.getPosition().nextColumn(line_number));
        }
//        }else if(this.getIndice() != 4)
//            this.setIndice(4);
        
        if(this.canEatBehindLeft(my_pawn, opponent_pawn, line_number) && this.getIndice()==4){
            Hut hut = new Hut(new Position(this.getPosition().previewLine(), this.getPosition().previewColumn()));
            return new Position(hut.getPosition().previewLine(), hut.getPosition().previewColumn());
        }//else
//            this.setIndice(0);
//        
        {
            return this.getPosition();
        }
    }
    
    public static ArrayList<Integer> order(ArrayList<Integer> liste){
        for(int i=0; i<liste.size(); i++){
            if(liste.get(i) > liste.get(0)){
                int t=liste.get(i);
                liste.set(0, liste.get(i));
                liste.set(i,t);
            }
        }
        return liste;
    }
    
    /*
        It comes that a pawn has the possibility to eat more than one opponent pawn.
        The following function gives the number of opponent pawns it can eat by moving 
        towards-Right, towards-Left, behind-Right or behind-Left.
        
    */    
    public int eatNumberPawn(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number,
            ArrayList<Hut> previewHuts){
        previewHuts.add(new Hut(this.getPosition()));
        if(!this.canEat(my_pawn, opponent_pawn, line_number))
            return 0;
        int numberofcatches = 1;
        Pawn p=new Pawn(this.getPosition());
        ArrayList<Integer> possibilities = p.CatchPossibilities(my_pawn, opponent_pawn, line_number);
        //System.out.println(">>>" + possibilities.size());
        if(possibilities.size()==1){
            p.setIndice(possibilities.get(0));
            if(!containsHut(previewHuts, new Hut(p.jumpPawn(my_pawn, opponent_pawn, line_number)))){
                Pawn np = new Pawn(p.jumpPawn(my_pawn, opponent_pawn, line_number));
                //np.showPawn();
                numberofcatches += np.eatNumberPawn(my_pawn, opponent_pawn, line_number, previewHuts);
            }else{
                return 0;
            }
        }else{
            ArrayList<Integer> catches = new ArrayList<>();
            for(int i=0 ; i<possibilities.size(); i++){
                p.setIndice(possibilities.get(i));
                if(!containsHut(previewHuts, new Hut(p.jumpPawn(my_pawn, opponent_pawn, line_number)))){
                    Pawn np = new Pawn(p.jumpPawn(my_pawn, opponent_pawn, line_number));
                    catches.add(numberofcatches + np.eatNumberPawn(my_pawn, opponent_pawn, line_number, previewHuts));
                }else{
                    catches.add(0);
                }
            }
            catches = order(catches);
            if(!catches.isEmpty())
                numberofcatches = catches.get(0);
        }
        
        return numberofcatches;
    }
    
    
    public ArrayList<Integer> CatchPossibilities(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        ArrayList<Integer> indices = new ArrayList<>();
        if(this.canEatTowardsRight(my_pawn, opponent_pawn, line_number))
            indices.add(1);
        if(this.canEatTowardsLeft(my_pawn, opponent_pawn, line_number))
            indices.add(2);
        if(this.canEatBehindRight(my_pawn, opponent_pawn, line_number))
            indices.add(3);
        if(this.canEatBehindLeft(my_pawn, opponent_pawn, line_number))
                indices.add(4);
        return indices;
    }
    public boolean containsHut(ArrayList<Hut> huts, Hut hut){
        if(huts.isEmpty())
            return false;
        for(int i=0; i<huts.size(); i++){
            if(huts.get(i).getPosition().getLine() == hut.getPosition().getLine() &&
                    huts.get(i).getPosition().getColumn() == hut.getPosition().getColumn()){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean iseaten(){
        return false;
    }
    
    public boolean isKing(){
        /*if(!this.king && this.getPosition().getLine() == 6){
            this.setKing(true);
            return true;
        }*/
        return false;
    }
    
    public ArrayList<Hut> nextMove(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        ArrayList<Hut> huts = new ArrayList<Hut>();
        if(this.isKing()){
            //nothing yet
        }else{
            Position p1 = new Position(this.getPosition().nextLine(line_number), this.getPosition().nextColumn(line_number));
            Hut hut1 = new Hut(p1);
            if(hut1.isAvailable(my_pawn, opponent_pawn)){
                huts.add(hut1);
            }
            
            Position p2 = new Position(this.getPosition().nextLine(line_number), this.getPosition().previewColumn());
            Hut hut2 = new Hut(p2);
            if(hut2.isAvailable(my_pawn, opponent_pawn)){
                huts.add(hut2);
            }
        }
        return huts;
    }
    
    
    /*
        Gives informations about the pawn. That information change if the pawn moves. 
    */
    public void showPawn(){
        System.out.println(position.getColumn() + "" + position.getLine());
    }
}
