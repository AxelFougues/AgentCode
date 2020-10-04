package agentcode;

import java.util.ArrayList;

import javafx.util.Pair;

public class World {
 
    protected int worldDimensions;
    protected WorldCell [][] worldGrid;
    
    protected ArrayList<Pair<Integer, Integer>> playingOrder;

    public World(int worldDimensions) {
        this.worldDimensions = worldDimensions;
        worldGrid = new WorldCell[worldDimensions][worldDimensions];
        
        playingOrder = new ArrayList<Pair<Integer, Integer>>();
        
        for (int y = 0; y < worldDimensions; y++) {
            for (int x = 0; x < worldDimensions; x++) {
                worldGrid[y][x] = new WorldCell();
                if(y == 0 && x == 0){
                    worldGrid[y][x].addElement(new ObstacleElement(x,y));
                }
                else if(y == worldDimensions -1 && x == worldDimensions -1){
                    worldGrid[y][x].addElement(new ObstacleElement(x,y));
                }
                else if(y == worldDimensions -1 || x == worldDimensions -1){
                    worldGrid[y][x].addElement(new ObstacleElement(x,y));
                }
                else if(y == 0 || x == 0){
                    worldGrid[y][x].addElement(new ObstacleElement(x,y));
                }
            }
        }
    }
    
    public boolean addElementToCell(WorldElement element){
        if( element.getX() > 0 && element.getX() < worldDimensions && element.getY() > 0 && element.getY() < worldDimensions){
            worldGrid[element.getY()][element.getX()].addElement(element);
            return true;
        }
        return false;
    }
    
    public boolean removeElementToCell(WorldElement element){
        if( element.getX() > 0 && element.getX() < worldDimensions && element.getY() > 0 && element.getY() < worldDimensions){
            return worldGrid[element.getY()][element.getX()].removeElement(element);
        }
        return false;
    }
    
    public WorldCell getCell(int x, int y){
        return worldGrid[y][x];
    }
    
    public void setCell(WorldCell cell, int x, int y){
        worldGrid[y][x] = cell;
    }
    
    public boolean elementExists(WorldElement element){
        return worldGrid[element.getY()][element.getX()].containsElement(element);
    }
    
    public boolean hasEmptyCell(int x, int y){
        return worldGrid[y][x].isEmpty();
    }

    @Override
    public String toString() {
        String result = "\n";
        
        result += "   ";
        for (int x = 0; x < worldDimensions; x++)
            result += x + " ";
        result += "\n";
        result += "   ";
        for (int x = 0; x < worldDimensions; x++)
            result += "- ";
        result += "\n";
        
        for (int y = 0; y < worldDimensions; y++) {
            result += y + "| ";
            for (int x = 0; x < worldDimensions; x++) {
                result += worldGrid[y][x].toString() + " ";
            }
            result += "\n";
        }
        return result += "\n";
    }
    
    public void display(){
        System.out.println(this.toString());
    }
    
    public void AddElementToPlayOrder(int x, int y) {
    	playingOrder.add(new Pair<Integer,Integer>(x,y));
    }
    
    public void UpdatePlayOrder(int oldX, int oldY, int newX, int newY) {
    	int i=0;
    	for(Pair<Integer,Integer> elem : playingOrder) {
    		if(elem.getKey()== oldX && elem.getValue()== oldY) {
    			playingOrder.set(i,new Pair<Integer,Integer>(newX,newY));
    		}
    		i++;
    	}
    	
    }
    
    public World playTurn(World realWorld){
        /*for (int x = 0; x < worldDimensions; x++) {
            for (int y = 0; y < worldDimensions; y++) {
                realWorld = worldGrid[x][y].playTurn(realWorld);
            }
        }*/
    	for(Pair<Integer,Integer> elem : playingOrder) {
    		realWorld = worldGrid[elem.getValue()][elem.getKey()].playTurn(realWorld);
    	}
    	
    	
        
        for (int x = 0; x < worldDimensions; x++)
            for (int y = 0; y < worldDimensions; y++)
                worldGrid[x][y].resetTurnPlayedToken();
                
        return realWorld;
    }
    
}
