package au.com.livewirelabs.assignment;

import au.com.livewirelabs.assignment.Modules.AssignmentModule;
import au.com.livewirelabs.assignment.Trader.RandomTrader;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Assignment Class
 * Main class to handle key logical operations
 */
public class Assignment {
    private final RandomTrader trader;
    public static final String ASX = "ASX";
    public static final String CXA = "CXA";

    @Inject
    public Assignment(RandomTrader trader) {
        this.trader = trader;
    }

    private void trade() {
        trader.initialize();
        trader.trade();
    }

    private void displayResult(String exchange) {
        trader.displayResult(exchange);
    }

    /**
     * Static function for get the name of the target exchange
     *
     * @param args command line arguments
     * @return the name of the target exchange
     */
    public static String getExchange(String[] args) {
        String exchange = ASX;
        if (args.length >= 2 && args[0].equals("-exchange")) {
            if (ASX.equals(args[1]) || CXA.equals(args[1])) {
                exchange = args[1];
            }
        }
        return exchange;
    }

    /**
     * Entry function
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String exchange = getExchange(args);

        Injector injector = Guice.createInjector(new AssignmentModule(exchange));
        Assignment assignment = injector.getInstance(Assignment.class);
        assignment.trade();

        assignment.displayResult(exchange);
    }


}



