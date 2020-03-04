/*
    This is the main classe
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
            //Set in it's hut the number of line which also the number of column
            checkboarder.getHut().get(i).setCase_number(huts);
        }
        
        //Now we create the camputer pawns
        ArrayList<Pawn> my_pawns = new ArrayList<Pawn>();
        /*
            This is a new list which contains some huts in which we put pawns.
            Notice that the line number huts/2 - 1 et huts/2 in default situation.
            In our case, the line before huts/2 - 1 contains the camputer pawns with there default positions
        */
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
        //Here, this list contain the opponent pawns with there default positions
        for(int i=huts-1; i>0; i--){
            if(i == huts/2)
                break;
            for(int j=(i+1)*huts-1; j>=i*huts; j--){
                if (checkboarder.getHut().get(j).isActivated()){
                    opponent_pawns.add(new Pawn(checkboarder.getHut().get(j).getPosition()));
                }
            }
        }
        
        /*--------------------------TEST---------------------------------------------------------*/
        
        Position p1=new Position(3,'D');
        for(int i=0; i<opponent_pawns.size(); i++){
            if(opponent_pawns.get(i).getPosition().getLine()==6 && opponent_pawns.get(i).getPosition().getColumn()=='C')
                opponent_pawns.get(i).move(new Hut(p1));
        }
        System.out.println("--------------------\n");
        for(int i=0; i<my_pawns.size(); i++){
            ArrayList<Hut> hutprev =new ArrayList<Hut>();
            System.out.println(my_pawns.get(i).eatNumberPawn(my_pawns, opponent_pawns, huts,hutprev));
        }
        
    }
}
