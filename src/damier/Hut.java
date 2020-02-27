package damier;

/**
 *
 * @author TMS
 */
import java.util.ArrayList;

public class Hut {
    protected Position position;
    protected int case_number=10;

    public Hut(Position position) {
        this.position = position;
    }

    public void setCase_number(int case_number) {
        this.case_number = case_number;
    }
    
    
    public boolean isActivated(){
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
        for(int i=0; i<case_number; i++){
            
        }
        for(int j = 0; j<Alphabet.length; j++){
            if(Alphabet[j] == this.getPosition().getColumn()){
                if((this.getPosition().getLine()+j+1)%2==0){
                    return false;
                }          
            }
        }
        return true;
    }

    public Position getPosition() {
        return position;
    }
    
    public boolean isAvailable(ArrayList<Pawn> my_pawn, ArrayList<Pawn> opponent_pawn){
        for(int i=0; i < my_pawn.size(); i++){
            if((my_pawn.get(i).getPosition().getLine() == this.getPosition().getLine() &&
                    my_pawn.get(i).getPosition().getColumn() == this.getPosition().getColumn() )|| 
                    this.isActivated() == false ||
                    (opponent_pawn.get(i).getPosition().getColumn() == this.getPosition().getColumn() &&
                    opponent_pawn.get(i).getPosition().getLine() == this.getPosition().getLine())){
                return false;
            }
        }
        return true;
    }
    
    public void hut(){
        System.out.println(this.getPosition().getColumn() + "" + this.getPosition().getLine());
    }
    
    public boolean busyByOpponentPawn(ArrayList<Pawn> opponent_pawn){
        if(!this.isActivated())
            return false;
        for(int i=0; i<opponent_pawn.size(); i++){
            if(opponent_pawn.get(i).getPosition().getLine()==this.getPosition().getLine() &&
                    opponent_pawn.get(i).getPosition().getColumn()==this.getPosition().getColumn()){
                return true;
            }
        }
        return false;
    }
    
    

    public void setPosition(Position position) {
        this.position = position;
    }
    
    public Hut nextRightHut(int maxLine){
        return new Hut(this.getPosition().nextRightPosition(maxLine)); 
    }
    public Hut nextLeftHut(int maxLine){
        return new Hut(this.getPosition().nextLeftPosition(maxLine)); 
    }
    public Hut previewRightHut(int maxLine){
        return new Hut(this.getPosition().previewRightPosition(maxLine)); 
    }
    public Hut previewLeftHut(int maxLine){
        return new Hut(this.getPosition().previewLeftPosition());
    }
}
