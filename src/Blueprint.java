public class Blueprint {
    
    private int oreBotCost = 0;
    private int clayBotCost = 0;
    private int[] obsidianBotCost = new int[2];
    private int[] geodeBotCost = new int[2];
    private int quality = 0;
    private int id = 0;


    Blueprint (String[] numbers){

        this.id = Integer.parseInt(numbers[0]);

        setOreBotCost(Integer.parseInt(numbers[1]));
        setClayBotCost(Integer.parseInt(numbers[2]));
        setObsidianBotCost(Integer.parseInt(numbers[3]), Integer.parseInt(numbers[4]));
        setGeodeBotCost(Integer.parseInt(numbers[5]), Integer.parseInt(numbers[6]));

    }

    protected int getId(){
        return id;
    }

    protected void setOreBotCost(int oreBotCost){
        this.oreBotCost = oreBotCost;
    }

    protected int getOreBotCost(){
        return oreBotCost;
    }

    protected void setClayBotCost(int clayBotCost){
        this.clayBotCost = clayBotCost;
    }

    protected int getClayBotCost(){
        return clayBotCost;
    }

    protected void setObsidianBotCost(int obsidianBotCostOre, int obsidianBotCostClay){
        this.obsidianBotCost[0] = obsidianBotCostOre;
        this.obsidianBotCost[1] = obsidianBotCostClay;
    }

    protected int[] getObsidianBotCost(){
        return obsidianBotCost;
    }

    protected void setGeodeBotCost(int geodeBotCostOre, int geodeBotCostObsidian){
        this.geodeBotCost[0] = geodeBotCostOre;
        this.geodeBotCost[1] = geodeBotCostObsidian;
    }

    protected int[] getGeodeBotCost(){
        return geodeBotCost;
    }

    protected int getQuality(){
        return quality;
    }

    protected void setQuality(int quality){
        this.quality = quality;
    }

}
