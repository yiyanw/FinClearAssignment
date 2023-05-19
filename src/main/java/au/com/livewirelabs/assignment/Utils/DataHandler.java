package au.com.livewirelabs.assignment.Utils;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;

public class DataHandler {
    public static void loadData(String dataPath, Map<String, BigInteger> volume){
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(dataPath));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                volume.put(data[0], new BigInteger(data[1].replace(" ", "")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateData(String dataPath, Map<String, BigInteger> volume){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath));
            for (String code: volume.keySet()) {
                bw.write(code + ", " + volume.get(code).toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void appendOperations(String dataPath, ArrayList<String[]> operations) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath, true));

            for (String[] operation: operations) {
                bw.write(operation[0] + ", " + operation[1]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFeatureByExchange(String exchange, String featureName) {
        try {
            String fileName = "src\\main\\java\\au\\com\\livewirelabs\\assignment\\config.json";
            JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(fileName));;
            return String.valueOf(((Map)jo.get(exchange)).get(featureName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
