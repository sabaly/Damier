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

    public Pawn(Position position) {
        this.position = position;
        this.king = isKing();
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
    
   
    
    
    public boolean move(Hut hut){
        if(hut.isActivated()){
            System.out.println("Déplacer de " + this.position.getColumn() + "" + this.position.getLine() + ""
                    + " à " + hut.getPosition().getColumn() + "" + hut.getPosition().getLine());
            this.setPosition(hut.getPosition());
            return true;
        }
        return false;
    }
    
    public boolean eat(Pawn pawn){
        
        return true;
    }
    
    public boolean canEat(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number, int indice){
        if(indice==0)
            indice++;
        
        if(this.isKing()){
            //nothing yet
        }else{
            //Check if there is a possible catch moving towards right
            if(this.canEatTowardsRight(my_pawn, opponent_pawn, line_number) && indice == 1)
                return true;
            else
                indice++;
            
            //Check if there is a possible catch moving towards left
            if(this.canEatTowardsLeft(my_pawn, opponent_pawn, line_number) && indice == 2)
                return true;
            else
                indice++;
            
            
            //Check if there is a possible catch moving behind right
            if(this.canEatBehindRight(my_pawn, opponent_pawn, line_number) && indice == 3)
                return true;
            else
                indice++;
            
            //Check if there is a possible catch moving behind left
            if(this.canEatBehindLeft(my_pawn, opponent_pawn, line_number) && indice == 4)
                return true;
            
            
        }
        return false;
    }
    
    public boolean canEatTowardsRight(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        Hut hut = new Hut(new Position(this.getPosition().nextLine(line_number), this.getPosition().nextColumn(line_number)));
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
        if(hut.busyByOpponentPawn(opponent_pawn)){
            hut.setPosition(new Position(hut.getPosition().previewLine(), hut.getPosition().previewColumn()));
            if(hut.isAvailable(my_pawn, opponent_pawn)){
                return true;
            }
        }
        return false;
    }
    
    public Position jumpPawn(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number, int indice){
        if(indice == 0)
            indice++;
        if(this.canEatTowardsRight(my_pawn, opponent_pawn, line_number) && indice==1){
            Hut hut = new Hut(new Position(this.getPosition().nextLine(line_number), this.getPosition().nextColumn(line_number)));
            return new Position(hut.getPosition().nextLine(line_number), hut.getPosition().nextColumn(line_number));
        }else
            indice++;
        
        if(this.canEatTowardsLeft(my_pawn, opponent_pawn, line_number) && indice==2){
            Hut hut = new Hut(new Position(this.getPosition().nextLine(line_number), this.getPosition().previewColumn()));
            return new Position(hut.getPosition().nextLine(line_number), hut.getPosition().previewColumn());
        }else
            indice++;
        
        if(this.canEatBehindRight(my_pawn, opponent_pawn, line_number) && indice==3){
            Hut hut = new Hut(new Position(this.getPosition().previewLine(), this.getPosition().nextColumn(line_number)));
               return new Position(hut.getPosition().previewLine(), hut.getPosition().nextColumn(line_number));
        }else
            indice++;
        
        if(this.canEatBehindLeft(my_pawn, opponent_pawn, line_number) && indice==4){
            Hut hut = new Hut(new Position(this.getPosition().previewLine(), this.getPosition().previewColumn()));
            return new Position(hut.getPosition().previewLine(), hut.getPosition().previewColumn());
        }
        
        {
            return this.getPosition();
        }
    }
    
    
    public int eatNumberPawn(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn, int line_number){
        ArrayList<Hut> previewHuts = new ArrayList<Hut>();
        int numberofcatches = 0;
        Pawn p=new Pawn(this.getPosition());
        while(p.canEat(my_pawn, opponent_pawn, line_number, 0)){
            boolean caneatotherway = false;
            int indice = 0;
            if(containsHut(previewHuts, new Hut(p.jumpPawn(my_pawn, opponent_pawn, line_number, 0))))
            {
                break;
            }
            
            previewHuts.add(new Hut(p.getPosition()));
            numberofcatches++;
            p.setPosition(p.jumpPawn(my_pawn, opponent_pawn, line_number, indice));
        }
        
        return numberofcatches;
    }
    
    public boolean containsHut(ArrayList<Hut> huts, Hut hut){
        if(huts.size()==0)
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
    
    public boolean canMove(ArrayList<Hut> huts){
//        char[] Alphabet = {'A', 'B', 'C', 'D', 'E', 'F'};
        return false;
    }
    
    
    
    public void showPawn(){
        System.out.println(position.getColumn() + "" + position.getLine());
    }
}
