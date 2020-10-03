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
        if ((X >= 0 && X < world.worldDimensions) && (Y >= 0 && Y < world.worldDimensions - 1)) {
            for (int x = 0; x < world.worldDimensions; x++) {
                for (int y = Y; y > 0; y--) {
                    switch (actor) {
                        case A:
                            if (world.elementExists(new AgentElement(x, y))) {
                                return true;
                            }
                        case E:
                            if (world.elementExists(new EnemyElement(x, y))) {
                                return true;
                            }
                        case O:
                            if(world.elementExists(new ObstacleElement(x, y)))
                                return true;
                        case N:
                            if (world.hasEmptyCell(x, y)) {
                                return true;
                            }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkS(World world, int X, int Y) {

        if ((X >= 0 && X < world.worldDimensions) && (Y > 0 && Y < world.worldDimensions)) {
            for (int x = 0; x < world.worldDimensions; x++) {
                for (int y = Y; y < world.worldDimensions; y++) {
                    switch (actor) {
                        case A:
                            if (world.elementExists(new AgentElement(x, y))) {
                                return true;
                            }
                        case E:
                            if (world.elementExists(new EnemyElement(x, y))) {
                                return true;
                            }
                        case O:
                            if(world.elementExists(new ObstacleElement(x, y)))
                                return true;
                        case N:
                            if (world.hasEmptyCell(x, y)) {
                                return true;
                            }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkW(World world, int X, int Y) {

        if ((X > 0 && X < world.worldDimensions) && (Y >= 0 && Y < world.worldDimensions)) {
            for (int x = X; x < world.worldDimensions; x++) {
                for (int y = Y; y < world.worldDimensions; y++) {
                    switch (actor) {
                        case A:
                            if (world.elementExists(new AgentElement(x, y))) {
                                return true;
                            }
                        case E:
                            if (world.elementExists(new EnemyElement(x, y))) {
                                return true;
                            }
                        case O:
                            if(world.elementExists(new ObstacleElement(x, y)))
                                return true;
                        case N:
                            if (world.hasEmptyCell(x, y)) {
                                return true;
                            }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkE(World world, int X, int Y) {

        if ((X >= 0 && X < world.worldDimensions - 1) && (Y >= 0 && Y < world.worldDimensions)) {
            for (int x = X; x > 0; x--) {
                for (int y = Y; y < world.worldDimensions; y++) {
                    switch (actor) {
                        case A:
                            if (world.elementExists(new AgentElement(x, y))) {
                                return true;
                            }
                        case E:
                            if (world.elementExists(new EnemyElement(x, y))) {
                                return true;
                            }
                        case O:
                            if(world.elementExists(new ObstacleElement(x, y)))
                                return true;
                        case N:
                            if (world.hasEmptyCell(x, y)) {
                                return true;
                            }
                    }
                }
            }
        }
        return false;
    }
}
