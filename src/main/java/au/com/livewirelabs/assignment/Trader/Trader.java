package au.com.livewirelabs.assignment.Trader;

import au.com.livewirelabs.assignment.CustomizedExceptions.InsufficientUnitsException;
import au.com.livewirelabs.assignment.CustomizedExceptions.InvalidCodeException;

public interface Trader {
    /**
     * Buy stock
     */
    void buy(String code, int amount) throws InsufficientUnitsException, InvalidCodeException;

    /**
     * Sell stock
     */
    void sell(String code, int amount) throws InvalidCodeException;
}
