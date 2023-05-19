package au.com.livewirelabs.assignment.Modules;

import au.com.livewirelabs.assignment.Assignment;
import au.com.livewirelabs.assignment.Trader.RandomTrader;
import au.com.livewirelabs.assignment.StockExchange.ASXStockExchange;
import au.com.livewirelabs.assignment.StockExchange.AbstractStockExchange;
import au.com.livewirelabs.assignment.StockExchange.CXAStockExchange;
import au.com.livewirelabs.assignment.Trader.Trader;
import au.com.livewirelabs.assignment.Utils.DataHandler;
import com.google.inject.AbstractModule;

/**
 * AssignmentModule class
 * Injection and mapping staff
 */
public class AssignmentModule extends AbstractModule {
    // Target exchange name
    private final String exchange;

    public AssignmentModule(String exchange) {
        this.exchange = exchange;
    }
    @Override
    protected void configure() {
        bind(Trader.class).to(RandomTrader.class);
        // Get surcharge for the target exchange
        Double surcharge = Double.valueOf(DataHandler.getFeatureByExchange(exchange, "surcharge"));

        if (Assignment.ASX.equals(exchange)) {
            bind(AbstractStockExchange.class).to(ASXStockExchange.class);
            bind(Double.class).toInstance(surcharge);
        }
        else if (Assignment.CXA.equals(exchange)) {
            bind(AbstractStockExchange.class).to(CXAStockExchange.class);
            bind(Double.class).toInstance(surcharge);
        }
    }
}
