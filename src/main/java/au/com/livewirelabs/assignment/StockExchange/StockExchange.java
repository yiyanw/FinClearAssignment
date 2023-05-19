package au.com.livewirelabs.assignment.StockExchange;

import au.com.livewirelabs.assignment.CustomizedExceptions.InsufficientUnitsException;
import au.com.livewirelabs.assignment.CustomizedExceptions.InvalidCodeException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;


public interface StockExchange {
    /**
     * Buy stock
     */
    void buy(String code, Integer units) throws InsufficientUnitsException,
            InvalidCodeException;
    /**
     * Sell stock
     */
    void sell(String code, Integer units) throws InvalidCodeException;
    /**
     * Report aggregate volume available for each code
     */
    Map<String, BigInteger> getOrderBookTotalVolume();
    /**
     * Returns dollar value of trading activity
     */
    BigDecimal getTradingCosts();
}
