package au.com.livewirelabs.assignment.StockExchange;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.annotation.PostConstruct;

/**
 * CXA exchange
 */
public class CXAStockExchange extends AbstractStockExchange{
    @Inject
    public CXAStockExchange(double surcharge) {
        this.surcharge = surcharge;
    }
}
