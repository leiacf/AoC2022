import java.util.ArrayList;

public class Valve {
    
    private ArrayList<Valve> tunnel = new ArrayList<>();
    private String name;
    private long flowrate;
    private String tunnels;
    private boolean open = false;

    Valve(String name, long flowrate, String tunnels){

        this.name = name;
        this.flowrate = flowrate;
        this.tunnels = tunnels;

    }

    protected String getName(){
        return name;
    }

    protected long getFlowRate(){
        return flowrate;
    }

    protected void addTunnel(Valve valve){
        
        if (tunnel.contains(valve) == false){
            tunnel.add(valve);
        }

    }

    protected ArrayList<Valve> getTunnelList(){
        return tunnel;
    }

    protected String getTunnelString(){
        return tunnels;
    }

    protected void open(){
        open = true;
    }

    protected void close(){
        open = false;
    }

    protected boolean isOpen(){
        return open;
    }


}    