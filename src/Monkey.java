import java.util.ArrayList;
import java.math.BigInteger;

public class Monkey {
    
    int number;
    ArrayList<BigInteger> items = new ArrayList<>();
    BigInteger inspections = BigInteger.valueOf(0);
    String[] operation;
    String[] test;

    Monkey (int number, String[] items, String[] operation, String[] test){
        
        this.number = number;
        this.operation = operation;
        this.test = test;

        addItems(items);

    }

    protected void addItem(BigInteger item){
        items.add(item);
    }

    private void addItems(String[] items){

        for (int i = 0; i < items.length; i++){
            addItem(new BigInteger(items[i]));
        }

    }

    protected BigInteger throwItem(){
        return items.remove(0);
    }

    protected boolean hasItems(){
        return (items.size() >= 1);
    }
    
    protected void inspect(boolean part1){

        for (int i = 0; i < items.size(); i++) {

            BigInteger item = items.get(i);

            item = updateWorryLevel(item, part1);

            items.set(i, item);

            inspections = inspections.add(BigInteger.valueOf(1));
            
        }

    }

    protected int test(BigInteger item){

        int tester = Integer.parseInt(test[0]);
        int ifTrue = Integer.parseInt(test[1]);
        int ifFalse = Integer.parseInt(test[2]);

        if (item.mod(BigInteger.valueOf(tester)).equals(BigInteger.valueOf(0))){
            return ifTrue;
        }

        return ifFalse;
    }

    protected BigInteger getInspections(){
        return inspections;
    }

    private BigInteger updateWorryLevel(BigInteger item, boolean part1){

        BigInteger times = BigInteger.valueOf(0);
        String test = operation[1];

        switch (test) {

            case "old":

                times = item;
                break;

            default:

                times = new BigInteger(test);
                break;
        }
        
        String modifier = operation[0];

        switch (modifier){

            case "+":
                    item = item.add(times);
                break;

            case "-":
                    item = item.subtract(times);
                break;

            case "*":
                    item = item.multiply(times);
                break;

            case "/":
                    item = item.divide(times);
                break;

            default:
            
                break;

        }

        if (part1){
            item = item.divide(BigInteger.valueOf(3));
        }

        return item;

    }


}
