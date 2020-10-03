package agentcode;

import java.util.ArrayList;
import java.util.Random;

public class Rule {
    public enum Action {Attack, Chase_Up, Chase_Down, Chase_Left, Chase_Right,
                        Move_Random};
    
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
    
    public Action getCurrentAction(){
        return action;
    }
    
    public static Rule buildAttackRule(){
        Axiom c = new Axiom(Axiom.Direction.H, Axiom.Actor.E); //Means Is there an E-nemy  H-ere
        Rule r = new Rule(Rule.Action.Attack);
        r.addAxiom(c);
        return r;
    }
    
    public static Rule buildChaseUpRule(){
        Axiom c = new Axiom(Axiom.Direction.N, Axiom.Actor.E); // is there an Enemy North of me ?
        Rule r = new Rule(Rule.Action.Chase_Up);
        
        r.addAxiom(c);
        
        return r;
    }
    
    public static Rule buildChaseDownRule(){
        Axiom c = new Axiom(Axiom.Direction.S, Axiom.Actor.E); 
        Rule r = new Rule(Rule.Action.Chase_Down);
        
        r.addAxiom(c);
        
        return r;
    }
    
    public static Rule buildChaseLeftRule(){
        Axiom c = new Axiom(Axiom.Direction.W, Axiom.Actor.E);
        Rule r = new Rule(Rule.Action.Chase_Left);
        
        r.addAxiom(c);
        
        return r;
    }
    
    public static Rule buildChaseRightRule(){
        Axiom c = new Axiom(Axiom.Direction.E, Axiom.Actor.E);
        Rule r = new Rule(Rule.Action.Chase_Right);
        
        r.addAxiom(c);
        
        return r;
    }
}
