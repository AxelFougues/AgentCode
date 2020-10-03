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
        return next(currentWorldPerception);
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
        int index = 0;

        while (!rules.get(index).validate(realWorld, currentX, currentY)) {
            index++;
        }

        switch (rules.get(index).action) {
            case Attack:
                System.out.println("Agent attacking.");
                attack(realWorld);
                break;
            case Chase_Up:
                System.out.println("Agent chasing up.");
                moveUp(realWorld);
                break;
            case Chase_Down:
                System.out.println("Agent chasing down.");
                moveDown(realWorld);
                break;
            case Chase_Left:
                System.out.println("Agent chasing left.");
                moveLeft(realWorld);
                break;
            case Chase_Right:
                System.out.println("Agent chasing right.");
                moveRight(realWorld);
                break;
            default:
                System.out.println("Agent moving randomly.");
                randomMove(realWorld);
                break;
        }

        return realWorld;
    }

    protected World attack(World realWorld) {
        realWorld.removeElementToCell(new EnemyElement(currentX, currentY));
        currentWorldPerception.removeElementToCell(new EnemyElement(currentX, currentY));
        return realWorld;
    }

    public void randomMove(World realWorld) {
        ArrayList<Integer> dir = new ArrayList<>();
        
        if(new Axiom(Axiom.Direction.N, Axiom.Actor.A).checkN(realWorld, currentX, currentY)){
            dir.add(0);
        }
        if(new Axiom(Axiom.Direction.S, Axiom.Actor.A).checkS(realWorld, currentX, currentY)){
            dir.add(1);
        }
        if(new Axiom(Axiom.Direction.E, Axiom.Actor.A).checkE(realWorld, currentX, currentY)){
            dir.add(2);
        }
        if(new Axiom(Axiom.Direction.W, Axiom.Actor.A).checkW(realWorld, currentX, currentY)){
            dir.add(3);
        }
        
        Random rand = new Random();
        int randInt = rand.nextInt(dir.size());
        
        switch(dir.get(randInt)){
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

    @Override
    protected World moveUp(World realWorld) {
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentX--;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }

    @Override
    protected World moveDown(World realWorld) {
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentX++;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }

    @Override
    protected World moveLeft(World realWorld) {
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentY--;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }

    @Override
    protected World moveRight(World realWorld) {
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentY++;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }

}
