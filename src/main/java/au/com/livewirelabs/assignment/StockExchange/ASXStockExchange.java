package au.com.livewirelabs.assignment.StockExchange;

import com.google.inject.Inject;

import javax.annotation.PostConstruct;

/**
 * ASX exchange
 */
public class ASXStockExchange extends AbstractStockExchange{
    @Inject
    public ASXStockExchange(double surcharge) {
        this.surcharge = surcharge;
    }
}
