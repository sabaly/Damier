/*
A chechboader is constituted of huts that are disposed as a table.
Each column is named using letters. The first from the left is A the next B so on.
Each line is numerated. The first from the top is 1, the next 2 so on.
Number of line equals to number of columns, that number depends to the dimension of the checkboader 
Thus, a hut is identified by it's column and line.

In ours case, we are taking a checkboader as a List of huts displayed like : {A1, B1, C1,...,A2, B2,..., A3,B3...}
 */
package damier;

import java.util.ArrayList;

/**
 *
 * @author TMS
 */
public class Checkboarder {

    private int case_number;//Helps us to get the dimension of the checkboader which is case_number*case_number
    protected ArrayList<Hut> hut;//reprensente the checkborder it's self as a list

    public Checkboarder(int case_number) {
        this.case_number = case_number;
        this.hut = generate_checkboader();
    }
    
    
    private ArrayList<Hut> generate_checkboader(){
        ArrayList<Hut> liste = new ArrayList<>();
        char[] Alphabet=new char[case_number];//Défine the columns, it's lenght is given by case_number
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
            //The line number i+1
            for(int j=0; j<Alphabet.length; j++){
                //The colonne Alphabet[j]
                Position position=new Position(i+1, Alphabet[j]); // Define Position column = Alphabet[i] and line = i+1
                liste.add(new Hut(position)); // Define the hut Alphabet[j] + "i+1"
            }
        }
        return liste;
    }

    public ArrayList<Hut> getHut() {
        return hut;
    }
    
    
}
