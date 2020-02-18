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
public class Opponent {
    protected ArrayList pawns;

    public Opponent(ArrayList pawns) {
        this.pawns = pawns;
    }

    public ArrayList getPawns() {
        return pawns;
    }
    
    
}
