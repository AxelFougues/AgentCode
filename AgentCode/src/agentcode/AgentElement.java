package agentcode;

import java.util.ArrayList;
import java.util.Random;

public class AgentElement extends WorldElement {

    protected int perceptionRadius = 2; //1 is single cell, 2 is 3x3, 3 is 5x5 etc...

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
        currentWorldPerception = new World(realWorld.worldDimensions);
        
        if (perceptionRadius == 1)
            return;
        
        for (int x = -perceptionRadius / 2; x <= perceptionRadius / 2; x++) {
            for (int y = -perceptionRadius / 2; y <= perceptionRadius / 2; y++) {
                currentWorldPerception.setCell(realWorld.getCell(currentY + x, currentX + y), currentY + x, currentX + y);
            }
        }
    }

    protected World next(World realWorld) { // choose the appropriate action and execute it
        System.out.println("============================ \n");
        System.out.println("What does the Agent see ?? \n");
        currentWorldPerception.display();
        System.out.println("============================ \n");
        //try{Thread.sleep(100);}catch(Exception e){}
        int index = 0;

        while (index < rules.size() && !rules.get(index).validate(currentWorldPerception, currentY, currentX)) {
            index++;
        }
        
        if (index >= rules.size()) {
            System.out.println("Agent moving randomly.");
            randomMove(realWorld);
        } else {
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
        }
        
        System.out.println("Agent's position : (" + currentX + "," + currentY +")");

        return realWorld;
    }

    protected World attack(World realWorld) {
        realWorld.removeElementToCell(new EnemyElement(currentY, currentX));
        return realWorld;
    }

    public void randomMove(World realWorld) {
        ArrayList<Integer> dir = new ArrayList<>();

        if (realWorld.hasEmptyCell(currentY -1, currentX )) { //North Free
            //System.out.println("Can move North");
            dir.add(0); 
        }
        if (realWorld.hasEmptyCell(currentY +1, currentX )) { //South Free
            //System.out.println("Can move South");
            dir.add(1);
        }
        if (realWorld.hasEmptyCell(currentY, currentX -1)) { //West Free
            //System.out.println("Can move West");
            dir.add(2);
        }
        if (realWorld.hasEmptyCell(currentY , currentX+1)) { //East Free
            //System.out.println("Can move East");
            dir.add(3);
        }
        //System.out.println();

        Random rand = new Random();
        int randInt = rand.nextInt(dir.size());

        switch (dir.get(randInt)) {
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
}
