package au.com.livewirelabs.assignment.Trader;

import au.com.livewirelabs.assignment.CustomizedExceptions.InsufficientUnitsException;
import au.com.livewirelabs.assignment.CustomizedExceptions.InvalidCodeException;
import au.com.livewirelabs.assignment.StockExchange.AbstractStockExchange;
import au.com.livewirelabs.assignment.Utils.DataHandler;
import com.google.inject.Inject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * RandomTrader Class for simulating trading operations
 */
public class RandomTrader implements Trader{
    // Storing data files
    public static final String BOUGHT_STOCK_CSV = "src\\main\\java\\au\\com\\livewirelabs\\assignment\\data\\boughtStock.csv";
    public static final String OPERATION_HISTORY_CSV = "src\\main\\java\\au\\com\\livewirelabs\\assignment\\data\\operationHistory.csv";
    // The number of operation is between 0 and 100;
    public static final int MAX_ROUND_NUM = 100;
    // List of target stock codes
    public static final String[] CODE_LIST = new String[]{"NAB", "CBA", "QAN"};
    // max buying unit per buying operation
    public static final int MAX_UNIT_NUM = Integer.MAX_VALUE;

    // Purchased stock amount for each stock
    private Map<String, BigInteger> boughtStock;
    // Operation history, format [<stock_code>, <amount>].
    // Positive for buying and negative for selling
    protected ArrayList<String[]> operations;
    // random number generator
    protected Random rGen;

    private final AbstractStockExchange stockExchange;

    @Inject
    public RandomTrader(AbstractStockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    public void initialize(){
        this.boughtStock = new HashMap<>();
        this.operations = new ArrayList<>();
        this.rGen = new Random();
        // Read previous purchased stock amount
        DataHandler.loadData(BOUGHT_STOCK_CSV, this.boughtStock);
        this.stockExchange.initialize();
    }

    /**
     * Simulate trading for random number [0, 100) of times
     */
    public void trade() {
        int roundNum = rGen.nextInt(MAX_ROUND_NUM);
        for (int i=0;i<roundNum;i++) {
            boolean isBuy = rGen.nextBoolean();
            String code =  CODE_LIST[rGen.nextInt(CODE_LIST.length)];
            try {
                if (isBuy) {
                    // buying operation
                    buy(code);
                } else {
                    // selling operation
                    sell(code);
                }
            } catch (InsufficientUnitsException e) {
                System.out.println("ERROR - No Sufficient Units - " + e.getMessage());
            } catch (InvalidCodeException e) {
                System.out.println("ERROR - Invalid Stock Code - " + e.getMessage());
            }
        }

        storeData();
    }

    /**
     * Simulate buy operation
     * @param code target stock code
     * @throws InsufficientUnitsException
     * @throws InvalidCodeException
     */
    public void buy(String code) throws InsufficientUnitsException, InvalidCodeException {
        int unitNum = rGen.nextInt(MAX_UNIT_NUM);

        buy(code, unitNum);

        System.out.println("BUY - " + code + " - " + unitNum + " Unit(s)");
    }

    @Override
    public void buy(String code, int amount) throws InsufficientUnitsException, InvalidCodeException {
        this.stockExchange.buy(code, amount);
        this.boughtStock.put(code, this.boughtStock.get(code).add(BigInteger.valueOf(amount)));
        this.operations.add(new String[]{code, String.valueOf(amount)});
    }

    /**
     * Simulate selling operation
     * @param code target stock code
     * @throws InvalidCodeException
     */
    public void sell(String code) throws InvalidCodeException {
        int unitNum;
        if (this.boughtStock.get(code).compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) < 0){
            unitNum = rGen.nextInt(Integer.parseInt(this.boughtStock.get(code).toString())+1);
        } else {
            unitNum = rGen.nextInt(Integer.MAX_VALUE);
        }

        sell(code, unitNum);

        System.out.println("SELL - " + code + " - " + unitNum + " Unit(s)");
    }

    @Override
    public void sell(String code, int amount) throws InvalidCodeException {
        this.stockExchange.sell(code, amount);
        this.boughtStock.put(code, this.boughtStock.get(code).add(BigInteger.valueOf(amount).negate()));
        this.operations.add(new String[]{code, String.valueOf(-amount)});
    }

    /**
     * Save data to corresponding files
     */
    public void storeData() {
        this.stockExchange.updateData();
        DataHandler.updateData(BOUGHT_STOCK_CSV, this.boughtStock);
        DataHandler.appendOperations(OPERATION_HISTORY_CSV, this.operations);
    }

    /**
     * Display income and available stock number for target exchange
     * @param exchange The name of the target exchange
     */
    public void displayResult(String exchange) {
        System.out.println("Available volume after operations: " + this.stockExchange.getOrderBookTotalVolume().toString());
        System.out.println("The income of " + exchange + " exchange: " + this.stockExchange.getTradingCosts().toString() + " Dollar(s).");
    }


}
