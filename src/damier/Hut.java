package damier;

/**
 *
 * @author TMS
 */
import java.util.ArrayList;

public class Hut {
    protected Position position;

    public Hut(Position position) {
        this.position = position;
    }
    
    public boolean isActivated(){
        char[] Alphabet = {'A', 'B', 'C', 'D', 'E', 'F'};
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
