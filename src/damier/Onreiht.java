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
public class Onreiht {
    protected ArrayList<Pawn> my_pawns;

    public Onreiht(ArrayList<Pawn> pawns) {
        this.my_pawns = pawns;
    }

    public ArrayList<Pawn> getPawns() {
        return my_pawns;
    }
    
    
}
