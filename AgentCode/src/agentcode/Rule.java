package agentcode;

import java.util.ArrayList;
import java.util.Random;

public class Rule {
    public enum Action {Attack, Chase_Up, Chase_Down, Chase_Left, Chase_Right,
                        Move_Random, Move_Up ,Move_Down, Move_Left, Move_Right,
                        Move_Restricted_Upward,Move_Restricted_Downward,Move_Restricted_Leftward,Move_Restricted_Rightward};
    
    protected ArrayList<Axiom> axioms;
    
    protected Action action;

    public Rule(Action act) {
        action = act;
        axioms = new ArrayList<>();
    }

    public void addAxiom(Axiom condition){
        axioms.add(condition);
    }
    
    public boolean validate(World world, int x, int y){
        for (Axiom condition : axioms) {
            if(!condition.evaluate(world, x, y)) return false;
        }
        return true;
    }
    
    public static Action getRandomMove(){
        Random rand = new Random();
        int randInt = rand.nextInt(4);
        switch(randInt){
            case 0:
                return Action.Move_Up;
            case 1:
                return Action.Move_Down;
            case 2:
                return Action.Move_Left;
            case 3:
                return Action.Move_Right;    
        }
        return Action.Move_Right;
    }
    
    public Action getCurrentAction(){
        return action;
    }
    
    public static Rule buildAttackRule(){
        Axiom c = new Axiom(Axiom.Direction.H, Axiom.Actor.E); //Means Is there an E-nemy  H-ere
        Rule r = new Rule(Rule.Action.Attack);
        r.addAxiom(c);
        return r;
    }
    
    public static Rule buildRandomMovementRule(){
        Axiom c1 = new Axiom(Axiom.Direction.N, Axiom.Actor.N); //Means Is there an N-othing  N-orth of me
        Axiom c2 = new Axiom(Axiom.Direction.S, Axiom.Actor.N); //Means Is there an N-othing  S-outh of me
        Axiom c3 = new Axiom(Axiom.Direction.W, Axiom.Actor.N); //Means Is there an N-othing  W-est of me
        Axiom c4 = new Axiom(Axiom.Direction.E, Axiom.Actor.N); //Means Is there an N-othing  E-ast of me
        
        
        Rule r = new Rule(Rule.Action.Move_Random);
        
        r.addAxiom(c1);
        r.addAxiom(c2);
        r.addAxiom(c3);
        r.addAxiom(c4);
        
        return r;
    }
    
    public static Rule buildUpwardRestrictedRule(){
        Axiom c1 = new Axiom(Axiom.Direction.S, Axiom.Actor.N); //Means Is there an N-othing  S-outh of me
        Axiom c2 = new Axiom(Axiom.Direction.W, Axiom.Actor.N); //Means Is there an N-othing  W-est of me
        Axiom c3 = new Axiom(Axiom.Direction.E, Axiom.Actor.N); //Means Is there an N-othing  E-ast of me
        
        Rule r = new Rule(Rule.Action.Move_Restricted_Upward);
        
        r.addAxiom(c1);
        r.addAxiom(c2);
        r.addAxiom(c3);
        
        return r;
    }
    
    public static Rule buildDownwardRestrictedRule(){
        Axiom c1 = new Axiom(Axiom.Direction.N, Axiom.Actor.N); //Means Is there an N-othing  N-orth of me
        Axiom c2 = new Axiom(Axiom.Direction.W, Axiom.Actor.N); //Means Is there an N-othing  W-est of me
        Axiom c3 = new Axiom(Axiom.Direction.E, Axiom.Actor.N); //Means Is there an N-othing  E-ast of me
        
        
        Rule r = new Rule(Rule.Action.Move_Restricted_Downward);
        
        r.addAxiom(c1);
        r.addAxiom(c2);
        r.addAxiom(c3);
        
        return r;
    }
    
    public static Rule buildLeftwardRestrictedRule(){
        Axiom c1 = new Axiom(Axiom.Direction.N, Axiom.Actor.N); //Means Is there an N-othing  N-orth of me
        Axiom c2 = new Axiom(Axiom.Direction.S, Axiom.Actor.N); //Means Is there an N-othing  S-outh of me
        Axiom c3 = new Axiom(Axiom.Direction.E, Axiom.Actor.N); //Means Is there an N-othing  E-ast of me
        
        
        Rule r = new Rule(Rule.Action.Move_Restricted_Leftward);
        
        r.addAxiom(c1);
        r.addAxiom(c2);
        r.addAxiom(c3);
        
        return r;
    }
    
    public static Rule buildRightwardRestrictedRule(){
        Axiom c1 = new Axiom(Axiom.Direction.N, Axiom.Actor.N); //Means Is there an N-othing  N-orth of me
        Axiom c2 = new Axiom(Axiom.Direction.S, Axiom.Actor.N); //Means Is there an N-othing  S-outh of me
        Axiom c3 = new Axiom(Axiom.Direction.W, Axiom.Actor.N); //Means Is there an N-othing  W-est of me
        
        Rule r = new Rule(Rule.Action.Move_Random);
        
        r.addAxiom(c1);
        r.addAxiom(c2);
        r.addAxiom(c3);
        
        return r;
    }
    
    public static Rule buildChaseUpRule(){
        Axiom c = new Axiom(Axiom.Direction.N, Axiom.Actor.E); //Means Is there an N-othing  N-orth of me
        Rule r = new Rule(Rule.Action.Chase_Up);
        
        r.addAxiom(c);
        
        return r;
    }
    
    public static Rule buildChaseDownRule(){
        Axiom c = new Axiom(Axiom.Direction.N, Axiom.Actor.E); //Means Is there an N-othing  N-orth of me
        Rule r = new Rule(Rule.Action.Chase_Down);
        
        r.addAxiom(c);
        
        return r;
    }
    
    public static Rule buildChaseLeftRule(){
        Axiom c = new Axiom(Axiom.Direction.N, Axiom.Actor.E); //Means Is there an N-othing  N-orth of me
        Rule r = new Rule(Rule.Action.Chase_Left);
        
        r.addAxiom(c);
        
        return r;
    }
    
    public static Rule buildChaseRightRule(){
        Axiom c = new Axiom(Axiom.Direction.N, Axiom.Actor.E); //Means Is there an N-othing  N-orth of me
        Rule r = new Rule(Rule.Action.Chase_Right);
        
        r.addAxiom(c);
        
        return r;
    }
}
