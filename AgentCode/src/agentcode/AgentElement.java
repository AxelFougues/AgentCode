package agentcode;

import java.util.ArrayList;
import java.util.Random;

public class AgentElement extends WorldElement {

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
    public World playTurn(World realWorld) {
        updatePerception(realWorld);
        return next(realWorld);
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    //######################################################################
    protected void updatePerception(World realWorld) { //Refresh currentWorldPerception with available world data
        if (perceptionRadius == 1) {
            currentWorldPerception.setCell(realWorld.getCell(currentX, currentY), currentX, currentY);
            return;
        }
        for (int x = -perceptionRadius / 2; x <= perceptionRadius / 2; x++) {
            for (int y = -perceptionRadius / 2; y <= perceptionRadius / 2; y++) {
                currentWorldPerception.setCell(realWorld.getCell(currentX + x, currentY + y), currentX + x, currentY + y);
            }
        }
    }

    protected World next(World realWorld) { // choose the appropriate action and execute it

        for (Rule rule : rules) {
            switch (rule.action) {
                case Attack:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        attack(realWorld);
                    }
                    break;
                case Chase_Up:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        moveUp(realWorld);
                    }
                    break;
                case Chase_Down:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        moveDown(realWorld);
                    }
                    break;
                case Chase_Left:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        moveLeft(realWorld);
                    }
                    break;
                case Chase_Right:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        moveRight(realWorld);
                    }
                    break;
                
                case Move_Random:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        randomMove(realWorld);
                    }
                    break;
                case Move_Restricted_Upward:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        randomMoveUpRestricted(realWorld);
                    }
                    break;
                case Move_Restricted_Downward:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        randomMoveDownRestricted(realWorld);
                    }
                    break;
                case Move_Restricted_Leftward:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        randomMoveLeftRestricted(realWorld);
                    }
                    break;
                case Move_Restricted_Rightward:
                    if (rule.validate(realWorld, currentX, currentY)) {
                        randomMoveRightRestricted(realWorld);
                    }
                    break;
            }
        }

        return realWorld;
    }

    protected World attack(World realWorld) {
        realWorld.removeElementToCell(new EnemyElement(currentX, currentY));
        currentWorldPerception.removeElementToCell(new EnemyElement(currentX, currentY));
        return realWorld;
    }
    
    public void randomMove(World realWorld){
        Random rand = new Random();
        int randInt = rand.nextInt(4);
        switch(randInt){
            case 0:
                moveUp(realWorld);
                break;
            case 1:
                moveDown(realWorld);
                break;
            case 2:
                moveLeft(realWorld);
                break;
            default:
                moveRight(realWorld);
                break;
        }
    }
    
    public void randomMoveUpRestricted(World realWorld){
        Random rand = new Random();
        int randInt = rand.nextInt(3);
        switch(randInt){
            case 0:
                moveDown(realWorld);
                break;
            case 1:
                moveLeft(realWorld);
                break;
            default:
                moveRight(realWorld);
                break;
        }
    }
    
    public void randomMoveDownRestricted(World realWorld){
        Random rand = new Random();
        int randInt = rand.nextInt(3);
        switch(randInt){
            case 0:
                moveUp(realWorld);
                break;
            case 1:
                moveLeft(realWorld);
                break;
            default:
                moveRight(realWorld);
                break;
        }
    }
    
    public void randomMoveLeftRestricted(World realWorld){
        Random rand = new Random();
        int randInt = rand.nextInt(3);
        switch(randInt){
            case 0:
                moveUp(realWorld);
                break;
            case 1:
                moveDown(realWorld);
                break;
            default:
                moveRight(realWorld);
                break;
        }
    }
    
    public void randomMoveRightRestricted(World realWorld){
        Random rand = new Random();
        int randInt = rand.nextInt(3);
        switch(randInt){
            case 0:
                moveUp(realWorld);
                break;
            case 1:
                moveDown(realWorld);
                break;
            default:
                moveLeft(realWorld);
                break;
        }
    }

    @Override
    protected World moveUp(World realWorld) {
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentY--;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }

    @Override
    protected World moveDown(World realWorld) {
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentY++;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }

    @Override
    protected World moveLeft(World realWorld) {
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentX--;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }

    @Override
    protected World moveRight(World realWorld) {
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentX++;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }

}
