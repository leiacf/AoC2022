import java.util.ArrayList;

public class Monkey {
    
    int number;
    ArrayList<Long> items = new ArrayList<>();
    Long inspections = 0L;
    String[] operation;
    String[] test;
    int magicNumber = 1;

    Monkey (int number, String[] items, String[] operation, String[] test){
        
        this.number = number;
        this.operation = operation;
        this.test = test;

        addItems(items);

    }

    protected void addItem(long item){
        items.add(item);
    }

    private void addItems(String[] items){

        for (int i = 0; i < items.length; i++){
            addItem(Long.parseLong(items[i]));
        }

    }

    protected void addMagicNumber(int magicNumber){
        this.magicNumber = magicNumber;
    }

    protected long throwItem(){
        return items.remove(0);
    }

    protected boolean hasItems(){
        return (items.size() >= 1);
    }
    
    protected void inspect(boolean part1){

        for (int i = 0; i < items.size(); i++) {

            long item = items.get(i);

            item = updateWorryLevel(item, part1);

            items.set(i, item);

            inspections++;
            
        }

    }

    protected int test(long item){

        int tester = Integer.parseInt(test[0]);
        int ifTrue = Integer.parseInt(test[1]);
        int ifFalse = Integer.parseInt(test[2]);

        if (item % tester == 0){
            return ifTrue;
        }

        return ifFalse;
    }

    protected long getInspections(){
        return inspections;
    }

    private long updateWorryLevel(long item, boolean part1){

        long times = 0;
        String test = operation[1];

        switch (test) {

            case "old":

                times = item;
                break;

            default:

                times = Long.parseLong(test);
                break;
        }
        
        String modifier = operation[0];

        switch (modifier){

            case "+":

                item = item + times;
                break;

            case "-":
    
                item = item - times;
                break;

            case "*":
        
                item = item * times;
                break;

            case "/":
            
                item = item / times;
                break;

            default:
            
                break;

        }

        if (part1){
            item = item / 3;
        } else {
            item = item % magicNumber;
        }
        
        return item;

    }

}
