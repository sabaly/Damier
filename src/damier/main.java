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
public class main {
    public static void main(String[] args) {
        int huts = 6;//défine sqrt of the number of hut to create, this case 6 x 6
        // then we generate a checkboader that depends to the huts
        Checkboarder checkboarder = new Checkboarder(huts);
        
        //Now we create the camputer pawns
        ArrayList<Pawn> my_pawns = new ArrayList<Pawn>();
        for(int i=0; i<(huts*huts/2 - huts); i++){
            if (checkboarder.getHut().get(i).isActivated()){
                my_pawns.add(new Pawn(checkboarder.getHut().get(i).getPosition()));
            }
        }
        
        //Here were the opponent pawns
        ArrayList<Pawn> opponent_pawns = new ArrayList<Pawn>();
        int count = 0;
        for(int i=checkboarder.getHut().size() - 1; i>(huts*huts/2 - huts); i--){
            if (checkboarder.getHut().get(i).isActivated()){
                opponent_pawns.add(new Pawn(checkboarder.getHut().get(i).getPosition()));
                count++;
            }
            if(count==huts)
                break;
        }
        Position p1=new Position(3,'D');
        Position p2=new Position(3,'F');
        Position p3=new Position(6,'A');
        Position p4=new Position(3,'B');
        for(int i=0; i<opponent_pawns.size(); i++){
            if(opponent_pawns.get(i).getPosition().getLine()==5 && opponent_pawns.get(i).getPosition().getColumn()=='D')
                opponent_pawns.get(i).move(new Hut(p1));
            if(opponent_pawns.get(i).getPosition().getLine()==6 && opponent_pawns.get(i).getPosition().getColumn()=='C')
                opponent_pawns.get(i).move(new Hut(p2));
            if(opponent_pawns.get(i).getPosition().getLine()==6 && opponent_pawns.get(i).getPosition().getColumn()=='A')
                opponent_pawns.get(i).move(new Hut(p4));
            
            opponent_pawns.get(i).showPawn();
        }
        System.out.println("--------------------\n");
        ArrayList<Hut> hutprev =new ArrayList<Hut>();
        for(int i=0; i<my_pawns.size(); i++){
            System.out.println(my_pawns.get(i).eatNumberPawn(my_pawns, opponent_pawns, huts,hutprev));
//            System.out.println(my_pawns.get(i).jumpPawn(my_pawns, opponent_pawns, huts).getColumn() + ""
//                    + "" + my_pawns.get(i).jumpPawn(my_pawns, opponent_pawns, huts).getLine());
        }
        
    }
}
