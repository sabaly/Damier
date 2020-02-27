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
        for(int i=0; i<checkboarder.getHut().size(); i++){
            checkboarder.getHut().get(i).setCase_number(huts);
        }
        
        //Now we create the camputer pawns
        ArrayList<Pawn> my_pawns = new ArrayList<Pawn>();
        for(int i=0; i<huts; i++){
            if(i == huts/2 - 1){
                break;
            }else{
                for(int j=i*huts; j<huts*(i+1); j++){
                    if (checkboarder.getHut().get(j).isActivated()){
                        my_pawns.add(new Pawn(checkboarder.getHut().get(j).getPosition()));
                    }
                }
            }
            
        }
        
        //Here were the opponent pawns
        ArrayList<Pawn> opponent_pawns = new ArrayList<Pawn>();
        //int count = 0;
        for(int i=huts-1; i>0; i--){
            if(i == huts/2)
                break;
            for(int j=(i+1)*huts-1; j>=i*huts; j--){
                if (checkboarder.getHut().get(j).isActivated()){
                    opponent_pawns.add(new Pawn(checkboarder.getHut().get(j).getPosition()));
                }
            }
        }
        
        Position p1=new Position(3,'D');
//        Position p2=new Position(4,'G');
//        Position p3=new Position(6,'A');
//        Position p4=new Position(5,'D');
//        Position p5=new Position(5,'H');
//        Position p6=new Position(5,'J');
        for(int i=0; i<opponent_pawns.size(); i++){
            if(opponent_pawns.get(i).getPosition().getLine()==6 && opponent_pawns.get(i).getPosition().getColumn()=='C')
                opponent_pawns.get(i).move(new Hut(p1));
//            if(opponent_pawns.get(i).getPosition().getLine()==7 && opponent_pawns.get(i).getPosition().getColumn()=='H')
//                opponent_pawns.get(i).move(new Hut(p2));
//            if(opponent_pawns.get(i).getPosition().getLine()==8 && opponent_pawns.get(i).getPosition().getColumn()=='A')
//                opponent_pawns.get(i).move(new Hut(p3));
//            if(opponent_pawns.get(i).getPosition().getLine()==8 && opponent_pawns.get(i).getPosition().getColumn()=='C')
//                opponent_pawns.get(i).move(new Hut(p4));
//            if(opponent_pawns.get(i).getPosition().getLine()==8 && opponent_pawns.get(i).getPosition().getColumn()=='I')
//                opponent_pawns.get(i).move(new Hut(p5));
//            if(opponent_pawns.get(i).getPosition().getLine()==10 && opponent_pawns.get(i).getPosition().getColumn()=='G')
//                opponent_pawns.get(i).move(new Hut(p6));
            
            //opponent_pawns.get(i).showPawn();
        }
        System.out.println("--------------------\n");
        for(int i=0; i<my_pawns.size(); i++){
            ArrayList<Hut> hutprev =new ArrayList<Hut>();
            System.out.println(my_pawns.get(i).eatNumberPawn(my_pawns, opponent_pawns, huts,hutprev));
//            System.out.println(my_pawns.get(i).jumpPawn(my_pawns, opponent_pawns, huts).getColumn() + ""
//                    + "" + my_pawns.get(i).jumpPawn(my_pawns, opponent_pawns, huts).getLine());
        }
        
    }
}
