package agentcode;

public class EnemyElement extends WorldElement{

    public EnemyElement(int currentX, int currentY) {
        super(currentX, currentY);
    }

    @Override
    public String toString() {
        return "E";
    }

    @Override
    public World playTurn(World realWorld) {
        switch((int)(Math.random() * 5)){
            case 1: moveUp(realWorld);
                break;
            case 2: moveDown(realWorld);
                break;
            case 3: moveLeft(realWorld);
                break;
            case 4: moveRight(realWorld);
                break;
        }
        return realWorld;
    }
    
}
