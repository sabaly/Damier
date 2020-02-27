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
public class Checkboarder {

    private int case_number;
    protected ArrayList<Hut> hut;

    public Checkboarder(int case_number) {
        this.case_number = case_number;
        this.hut = generate_checkboader();
    }
    
    private ArrayList<Hut> generate_checkboader(){
        ArrayList<Hut> liste = new ArrayList<>();
        char[] Alphabet=new char[case_number];
        switch(case_number){
            case 6:
                char[] Alph6 = {'A', 'B', 'C', 'D', 'E', 'F'};
                Alphabet=Alph6;
                break;
            case 8:
                char[] Alph8 = {'A', 'B', 'C', 'D', 'E', 'F','G', 'H'};
                Alphabet=Alph8;
                break;
            case 10:
                char[] Alph10 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
                Alphabet=Alph10;
                break;
            default:
                break;
        }
        
        for(int i=0; i < case_number; i++){
            for(int j=0; j<Alphabet.length; j++){
                Position position=new Position(i+1, Alphabet[j]);
                liste.add(new Hut(position));
            }
        }
        return liste;
    }

    public ArrayList<Hut> getHut() {
        return hut;
    }
    
    
}
