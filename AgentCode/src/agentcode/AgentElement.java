package agentcode;

import java.util.ArrayList;
import java.util.Random;

public class AgentElement extends WorldElement {

    protected int perceptionRadius = 2; //0 -> soi meme; 1 -> 1 autour; ...

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

        if (perceptionRadius <= 0)
            return;

        for (int i = -perceptionRadius; i <= perceptionRadius; i++) {
            for (int j = -perceptionRadius; j <= perceptionRadius; j++) {
                if((0 <= (currentX + i) && (currentX + i) < realWorld.worldDimensions)
                 &&(0 <= (currentY + j) && (currentY + j) < realWorld.worldDimensions))
                    currentWorldPerception.setCell(realWorld.getCell(currentX + i, currentY + j), currentX + i, currentY + j);
            }
        }
    }

    protected World next(World realWorld) { // choose the appropriate action and execute it

    	int oldX = this.currentX;
    	int oldY = this.currentY;

        System.out.println("What does the Agent see ?? \n");
        currentWorldPerception.display();
        //try{Thread.sleep(100);}catch(Exception e){}
        int index = 0;

        while (index < rules.size() && !rules.get(index).validate(currentWorldPerception, currentX, currentY)) {
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
        realWorld.UpdatePlayOrder(oldX, oldY, currentX,currentY);

        return realWorld;
    }

    protected World attack(World realWorld) {
        realWorld.removeElementToCell(new EnemyElement(currentX, currentY));
        return realWorld;
    }

    public void randomMove(World realWorld) {
        ArrayList<Integer> dir = new ArrayList<>();

        if (realWorld.hasEmptyCell(currentX, currentY -1)) { //North Free
            //System.out.println("Can move North");
            dir.add(0);
        }
        if (realWorld.hasEmptyCell(currentX, currentY +1)) { //South Free
            //System.out.println("Can move South");
            dir.add(1);
        }
        if (realWorld.hasEmptyCell(currentX -1, currentY)) { //West Free
            //System.out.println("Can move West");
            dir.add(2);
        }
        if (realWorld.hasEmptyCell(currentX+1 , currentY)) { //East Free
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
