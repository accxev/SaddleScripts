package edu.stanford.multiagent.gamer;

/**
 * Return a version of the two player Chicken game.
 */


public class Chicken extends MatrixGame
{
    // Parameters: Takes no parameters
    private static Parameters.ParamInfo[] chParam;

    static {
	chParam = new Parameters.ParamInfo[] {};
	Global.registerParams(Chicken.class, chParam);
    }


    // ----------------------------------------------


    public Chicken() 
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

	initMatrix();
    }


    protected void checkParameters() throws Exception 
    {
    }


    protected String getGameHelp()
    {
	return "Creates a 2x2 Chicken Game" + getRangeHelp();
    }


    public void randomizeParameters() 
    {
    }


    public void doGenerate()
    {
	double low = DEFAULT_LOW;
	double high = DEFAULT_HIGH;

	setDescription("Chicken\n" 
			 + getDescription());
	setName("Chicken");

	SortedAndRandomSet payoffValues = new SortedAndRandomSet();

	// Generate four payoff values in the range from
	// payoff_low to payoff_high.  Make sure the four values
	// are distinct.
	for (int i = 0; i < 4; i++) {
	    Double randomPayoffAsDouble;
	    do {
		double randomPayoff = Global.randomDouble(low, high);
		randomPayoffAsDouble = new Double(randomPayoff);
	    } while (!(payoffValues.add(randomPayoffAsDouble)));
	}

	Double a = (Double) payoffValues.removeLargest();
	Double b = (Double) payoffValues.removeLargest();
	Double c = (Double) payoffValues.removeLargest();
	Double d = (Double) payoffValues.removeLargest();

	Outcome outcome=new Outcome(getNumPlayers(), getNumActions());

	outcome.reset();
	setPayoff(outcome.getOutcome(), 0, b.doubleValue());
	setPayoff(outcome.getOutcome(), 1, b.doubleValue());

	outcome.nextOutcome();
	setPayoff(outcome.getOutcome(), 0, a.doubleValue());
	setPayoff(outcome.getOutcome(), 1, c.doubleValue());

	outcome.nextOutcome();
	setPayoff(outcome.getOutcome(), 0, c.doubleValue());
	setPayoff(outcome.getOutcome(), 1, a.doubleValue());

	outcome.nextOutcome();
	setPayoff(outcome.getOutcome(), 0, d.doubleValue());
	setPayoff(outcome.getOutcome(), 1, d.doubleValue());
    }

}
