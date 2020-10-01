package agentcode;

import java.util.ArrayList;
import java.util.Random;

public class AgentElement extends WorldElement{
    
    protected int perceptionRadius; //1 is single cell, 2 is 3x3, 3 is 5x5 etc...
    
    protected World currentWorldPerception;
    
    protected ArrayList<Rule> rules;

    public AgentElement(int currentX, int currentY, ArrayList<Rule> rules) {
        super(currentX, currentY);
        this.rules = rules;
        this.currentWorldPerception = new World(10);
    }
    
    public AgentElement(int currentX, int currentY) {
        super(currentX, currentY);
        this.currentWorldPerception = new World(10);
        this.rules = new ArrayList<>();
    }
    //######################################################################

    @Override
    public String toString() {
        return "A";
    }
    
    @Override
    public World playTurn(World realWorld){
        updatePerception(realWorld);
        return next(realWorld);
    }
    
    public void setRules(ArrayList<Rule> rules){
        this.rules = rules;
    }
    
    //######################################################################
    
    
    protected void updatePerception(World realWorld){ //Refresh currentWorldPerception with available world data
        if(perceptionRadius == 1){currentWorldPerception.setCell(realWorld.getCell(currentX, currentY), currentX, currentY); return;}
        for (int x = -perceptionRadius/2; x <= perceptionRadius/2; x++) {
            for (int y = -perceptionRadius/2; y <= perceptionRadius/2; y++) {
                currentWorldPerception.setCell(realWorld.getCell(currentX + x, currentY + y), currentX + x, currentY + y);
            }
        }
    }
    
    protected World next(World realWorld){ // choose the appropriate action and execute it
        
        for (Rule rule : rules) {
            if(rule.validate(realWorld, currentX, currentY)){
                switch(rule.name){
                    case "Attack":
                        attack(realWorld);
                        break;
                    case "Move Random":
                        Random rand = new Random();
                        int move = rand.nextInt(4);
                        switch(move){
                            case 0: 
                                moveUp(realWorld);
                                break;
                            case 1: 
                                moveDown(realWorld);
                                break;
                            case 2: 
                                moveLeft(realWorld);
                                break;
                            case 3: 
                                moveRight(realWorld);
                                break;
                        }
                        break;
                    case "Chase Up":
                        moveUp(realWorld);
                        break;
                    case "Chase Down":
                        moveDown(realWorld);
                        break;
                    case "Chase Left":
                        moveLeft(realWorld);
                        break;
                    case "Chase Right":
                        moveRight(realWorld);
                        break;
                }
                break;
            }
        }
        
            
        
        return realWorld;
    }
    
    protected World attack (World realWorld){
        realWorld.removeElementToCell(new EnemyElement(currentX, currentY));
        currentWorldPerception.removeElementToCell(new EnemyElement(currentX, currentY));
        return realWorld;
    }
    
    @Override
    protected World moveUp(World realWorld){
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentY--;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }
    
    @Override
    protected World moveDown(World realWorld){
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentY++;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }
    
    @Override
    protected World moveLeft(World realWorld){
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentX--;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }
    
    @Override
    protected World moveRight(World realWorld){
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentX++;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }
    
}
