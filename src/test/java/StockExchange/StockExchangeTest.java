package StockExchange;

import au.com.livewirelabs.assignment.CustomizedExceptions.InsufficientUnitsException;
import au.com.livewirelabs.assignment.CustomizedExceptions.InvalidCodeException;
import au.com.livewirelabs.assignment.StockExchange.AbstractStockExchange;
import au.com.livewirelabs.assignment.StockExchange.CXAStockExchange;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StockExchangeTest {
    AbstractStockExchange stockExchange;

    @Before
    public void setUp() {
        stockExchange = new CXAStockExchange(1.0);
        stockExchange.setIncome(BigDecimal.ZERO);
        Map<String, BigInteger> volume = new HashMap<>();
        volume.put("NAB", BigInteger.valueOf(1000));
        stockExchange.setVolume(volume);
    }

    @Test
    public void buySuccessTest() throws InsufficientUnitsException, InvalidCodeException {
        stockExchange.buy("NAB", 100);

        assertEquals(BigDecimal.valueOf(1.0), stockExchange.getTradingCosts());
    }

    @Test
    public void buyFailureInsufficientUnitsExceptionTest() throws InsufficientUnitsException, InvalidCodeException {
        assertThrows(InsufficientUnitsException.class, () -> {stockExchange.buy("NAB", 2000);});
    }

    @Test
    public void buyFailureInvalidCodeExceptionTest() throws InsufficientUnitsException, InvalidCodeException {
        assertThrows(InvalidCodeException.class, () -> stockExchange.buy("TEST", 2000));
    }

    @Test
    public void sellSuccessTest() throws InsufficientUnitsException, InvalidCodeException {
        stockExchange.buy("NAB", 100);
        stockExchange.sell("NAB", 100);

        assertEquals(BigDecimal.valueOf(2.0), stockExchange.getTradingCosts());
    }

    @Test
    public void sellFailureInvalidCodeExceptionTest() throws InsufficientUnitsException, InvalidCodeException {
        stockExchange.buy("NAB", 100);
        assertThrows(InvalidCodeException.class, () -> stockExchange.sell("TEST", 100));
    }
}
