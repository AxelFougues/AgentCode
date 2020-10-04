package agentcode;

public class Axiom {

    public enum Direction {
        N, S, E, W, H
    };// H = here

    public enum Actor {
        A, E, O, N
    } // N = nothing // O = Obstacle

    protected Direction direction;
    protected Actor actor;

    public Axiom(Direction directions, Actor actor) {
        this.direction = directions;
        this.actor = actor;
    }

    public boolean evaluate(World world, int x, int y) {
        switch (direction) {
            case N:
                return checkN(world, x, y);
            case S:
                return checkS(world, x, y);
            case E:
                return checkE(world, x, y);
            case W:
                return checkW(world, x, y);
            case H:
                return checkLocaly(world, x, y);
        }
        return false;
    }

    protected boolean checkLocaly(World world, int x, int y) {
        switch (actor) {
            case A:
                return world.elementExists(new AgentElement(x, y));
            case E:
                return world.elementExists(new EnemyElement(x, y));
            case O:
                return world.elementExists(new ObstacleElement(x, y));
            case N:
                return world.hasEmptyCell(x, y);
        }
        return false;
    }

    public boolean checkN(World world, int X, int Y) {
        System.out.println("is there an " + actor.toString() + " north of (" + Y + ", " + X +")");
        
        if ((X >= 0 && X < world.worldDimensions) && (Y >= 0 && Y < world.worldDimensions - 1)) {
            for (int x = 0; x < world.worldDimensions; x++) {
                for (int y = Y - 1; y > 0; y--) {
                    switch (actor) {
                        case A:
                            if (world.elementExists(new AgentElement(x, y))) {
                                return true;
                            }
                            break;
                        case E:
                            if (world.elementExists(new EnemyElement(x, y))) {
                                return true;
                            }
                            break;
                        case O:
                            if (world.elementExists(new ObstacleElement(x, y))) {
                                return true;
                            }
                            break;
                        case N:
                            if (world.hasEmptyCell(x, y)) {
                                return true;
                            }
                            break;
                    }
                }
            }
        }
        System.out.println("No\n");
        return false;
    }

    public boolean checkS(World world, int X, int Y) {
        System.out.println("is there an " + actor.toString() + " south of (" + Y + ", " + X +")");

        if ((X >= 0 && X < world.worldDimensions) && (Y > 0 && Y < world.worldDimensions)) {
            for (int x = 0; x < world.worldDimensions; x++) {
                for (int y = Y + 1; y < world.worldDimensions; y++) {
                    switch (actor) {
                        case A:
                            if (world.elementExists(new AgentElement(x, y))) {
                                return true;
                            }
                            break;
                        case E:
                            if (world.elementExists(new EnemyElement(x, y))) {
                                return true;
                            }
                            break;
                        case O:
                            if (world.elementExists(new ObstacleElement(x, y))) {
                                return true;
                            }
                            break;
                        case N:
                            if (world.hasEmptyCell(x, y)) {
                                return true;
                            }
                            break;
                    }
                }
            }
        }
        System.out.println("No\n");
        return false;
    }

    public boolean checkW(World world, int X, int Y) {
        
        System.out.println("is there an " + actor.toString() + " west of (" + Y + ", " + X +")");

        if ((X > 0 && X < world.worldDimensions) && (Y >= 0 && Y < world.worldDimensions)) {
            for (int x = X - 1; x > 0; x--) {
                for (int y = 0; y < world.worldDimensions; y++) {
                    switch (actor) {
                        case A:
                            if (world.elementExists(new AgentElement(x, y))) {
                                return true;
                            }
                            break;
                        case E:
                            if (world.elementExists(new EnemyElement(x, y))) {
                                return true;
                            }
                            break;
                        case O:
                            if (world.elementExists(new ObstacleElement(x, y))) {
                                return true;
                            }
                            break;
                        case N:
                            if (world.hasEmptyCell(x, y)) {
                                return true;
                            }
                            break;
                    }
                }
            }
        }
        System.out.println("No\n");
        return false;
    }

    public boolean checkE(World world, int X, int Y) {
        System.out.println("is there an " + actor.toString() + " east of (" + Y + ", " + X +")");

        if ((X >= 0 && X < world.worldDimensions - 1) && (Y >= 0 && Y < world.worldDimensions)) {
            for (int x = X + 1; x < world.worldDimensions; x++) {
                for (int y = 0; y < world.worldDimensions; y++) {
                    switch (actor) {
                        case A:
                            if (world.elementExists(new AgentElement(x, y))) {
                                return true;
                            }
                            break;
                        case E:
                            if (world.elementExists(new EnemyElement(x, y))) {
                                return true;
                            }
                            break;
                        case O:
                            if (world.elementExists(new ObstacleElement(x, y))) {
                                return true;
                            }
                            break;
                        case N:
                            if (world.hasEmptyCell(x, y)) {
                                return true;
                            }
                            break;
                    }
                }
            }
        }
        System.out.println("No\n");
        return false;
    }
}
