package au.com.livewirelabs.assignment.StockExchange;

import au.com.livewirelabs.assignment.CustomizedExceptions.InsufficientUnitsException;
import au.com.livewirelabs.assignment.CustomizedExceptions.InvalidCodeException;
import au.com.livewirelabs.assignment.Utils.DataHandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractStockExchange class
 * Parent Class for all exchange class
 */
public abstract class AbstractStockExchange implements StockExchange{
    protected static final String DATA_FILE = "src\\main\\java\\au\\com\\livewirelabs\\assignment\\data\\stockVolume.csv";

    protected double surcharge;

    protected Map<String, BigInteger> volume;


    protected BigDecimal income;

    public void initialize() {
        this.income = BigDecimal.ZERO;
        this.volume = new HashMap<>();
        loadData();
    }

    /**
     * Buy Stock
     * @param code target stock code
     * @param units stock amount
     * @throws InsufficientUnitsException
     * @throws InvalidCodeException
     */
    @Override
    public void buy(String code, Integer units) throws InsufficientUnitsException,
            InvalidCodeException {
        if (!getVolume().containsKey(code)) {
            throw new InvalidCodeException("Current Stock Code: " + code);
        }
        if (getVolume().get(code).compareTo(BigInteger.valueOf(units)) < 0) {
            throw new InsufficientUnitsException("Current available unit number: " + volume.get(code).toString());
        }
        this.income = this.income.add(BigDecimal.valueOf(getSurcharge()));
        getVolume().put(code, getVolume().get(code).add(BigInteger.valueOf(units).negate()));
    }

    /**
     * Sell Stock
     * @param code target stock code
     * @param units stock amount
     * @throws InvalidCodeException
     */
    @Override
    public void sell(String code, Integer units) throws InvalidCodeException {
        if (!getVolume().containsKey(code)) {
            throw new InvalidCodeException(code);
        }
        this.income = this.income.add(BigDecimal.valueOf(getSurcharge()));
        getVolume().put(code, getVolume().get(code).add(BigInteger.valueOf(units)));
    }

    /**
     * Report aggregate volume available for each code
     */
    @Override
    public Map<String, BigInteger> getOrderBookTotalVolume() {
        return volume;
    }

    /**
     * Returns dollar value of trading activity
     */
    @Override
    public BigDecimal getTradingCosts() {
        return this.income;
    }

    /**
     * Read total available stock amount for target stocks
     */
    public void loadData() {
        DataHandler.loadData(DATA_FILE, getVolume());
    }

    /**
     * Update total available stock amount for target stocks
     */
    public void updateData() {
        DataHandler.updateData(DATA_FILE, getVolume());
    }

    public double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }

    public Map<String, BigInteger> getVolume() {
        return volume;
    }

    public void setVolume(Map<String, BigInteger> volume) {
        this.volume = volume;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
