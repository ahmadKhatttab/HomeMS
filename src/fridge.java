public class fridge extends Device{
    public fridge(String name, double watts, int roomId){
        super(name,watts,roomId);
    }


    public double calculateDailyConsumption(int hours){
        return (getWatts() * hours*0.7) / 1000.0;
    }


}
