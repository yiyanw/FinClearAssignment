# FinClear Interview Assignment
* Project Structure 
  * Guice + Maven + Java 8
* Some Assumptions
  1. The definition of "Income"
     * According to the assignment description:
       * _"After the trading activity is complete, report the remaining volume and the income of the exchange."_ 
     * However, the assignment does not provide a specific definition of what exactly constitutes income. Therefore, for the purpose of this implementation, I have considered income as the sum of income for each command line execution.
  2. I decided to use BigInteger to store stock amount, because I found that the issued amount of NAB is over the MAX_VAL of Integer. (https://www2.asx.com.au/markets/company/nab)
  3. ERROR Report format
     * According to the assignment description:
       * _"Attempts to buy stock when insufficient volume is available should result in an error, reported but not fatal to continuted processing."_
     * It does not mention the format of the error messages. Therefore, for this implementation, I have decided to print all errors using the format: 
       * "ERROR - \<Error Info> - \<What Incorrect Data is>".
  4. Testing - Although the assignment does not mention testing, I have performed some unit tests on the StockExchange component to ensure its integrity.
  5. Configuration File
     * According to the assignment description:
        * _"The CXA exchange charges 5 cents per trade. The ASX exchange charges 7 cents per trade. These rates are subject to change."_
     * Therefore, I have created a config.json file to store the surcharges for different exchanges, which makes it easy to modify.
  6. Activities Records
     * According to the assignment description:
       * _"All trading activity (buys & sells) should be recorded."_
     * However, since the assignment does not specify how the trading activity records should be used, I have decided to print activities to the terminal and store all the activities in a file named "operationHistory.csv" for potential future use.