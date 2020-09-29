TO DO:
Add an action attribute to Rule and a way to interpret it (maybe with an enum like the conditions)

Complete next() function in AgentElement

Write down aaaall ze rules!

Debug

Enjoy


Possible bugs:
I saw an enemy disapear once, this might be related to the random in playTurn() of EnemyElement

Notes:
When you call world.playTurn() it calls playTurn() on each of its cell which in turn calls playTurn() on all its WorldElements. This way all actors in the world have the oportunity do act. The current world is being passed down and modified with each action and then returned so DO NOT froget to do world = world.playTurn(world) or nothing will happen.

A rule has x conditions. Conditions have an actor and a direction, it means "is Actor at Direction relative to me". N stands for nothing and H for here (see enums in Condition).
