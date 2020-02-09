/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damier;

/**
 *
 * @author user
 */
public class Position {
    protected int line;
    protected char column;

    public Position(int line, char column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }
    
    public int nextLine(int maxLine){
        int newLine;
        if(this.getLine() != maxLine)
           newLine = this.getLine() + 1;
        else
            newLine = this.getLine();
        return newLine;
    }
    
    public char nextColumn(int maxColumn){
        char nextColumn;
        if((int)this.getColumn()%65 != maxColumn - 1)
            nextColumn = (char)((int)(this.getColumn()+1)%65 + 65);
        else
            nextColumn  = this.getColumn();
        return nextColumn;
    }
    
    public int previewLine(){
        int newLine;
        if(this.getLine() != 1){
            newLine = this.getLine() - 1;
        }else{
            newLine = this.getLine();
        }
        return newLine;
    }
    
    public char previewColumn(){
        char previewColumn;
        if((int)this.getColumn()%65 != 0){
            previewColumn = (char)(((int)this.getColumn() - 1)%65 + 65);
        }else{
            previewColumn =  this.getColumn();
        }
        return previewColumn;
    }
    
    public Position nextRightPosition(int maxLine){
        return new Position(this.nextLine(maxLine), this.nextColumn(maxLine));
    }
    public Position nextLeftPosition(int maxLine){
        return new Position(this.nextLine(maxLine), this.previewColumn());
    }
    public Position previewRightPosition(int maxLine){
        return new Position(this.previewLine(), this.nextColumn(maxLine));
    }
    public Position previewLeftPosition(){
        return new Position(this.previewLine(), this.previewColumn());
    }
}
