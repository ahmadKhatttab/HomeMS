public class AC extends Device {

    public AC(String name, double watts, int roomId){
        super(name,watts,roomId);
    }

    public double calculateDailyConsumption(int hours){
        return (getWatts() * hours) / 1000.0;

    }
}
