package edu.stanford.multiagent.gamer;

/**
 * Return a version of the standard Matching Pennies game.
 */ 


public class MatchingPennies extends ZeroSumGame
{

    // Parameters: Matching Pennies takes no parameters

    private static Parameters.ParamInfo[] mpParam;

    static {
	mpParam = new Parameters.ParamInfo[] {};
	Global.registerParams(MatchingPennies.class, mpParam);
    }


    // ----------------------------------------------


    public MatchingPennies() 
	throws Exception
    {
	super();
    }


    public void initialize()
	throws Exception
    {
	super.initialize();

	setNumPlayers(2);
	
	int numActions[];
	numActions = new int[2];
	numActions[0] = numActions[1] = 2;
	setNumActions(numActions);

	initPayoffs(2, 2);
    }


    //
    // No params
    //
    protected void checkParameters() throws Exception 
    {
    }


    public void randomizeParameters() 
    {
    }


    protected String getGameHelp()
    {
	return "Creates an instance of the Matching Pennies Game" +
	    getRangeHelp();
    }


    public void doGenerate()
    {
	double a = DEFAULT_HIGH;

	setDescription("Matching Pennies\n" + getDescription());
	setName("Matching Pennies");

	Outcome outcome = new Outcome(getNumPlayers(), getNumActions());

	outcome.reset();
	setPayoff(outcome.getOutcome(), a);

	outcome.nextOutcome();
	setPayoff(outcome.getOutcome(), -a);

	outcome.nextOutcome();
	setPayoff(outcome.getOutcome(), -a);

	outcome.nextOutcome();
	setPayoff(outcome.getOutcome(), a);
    }

}
