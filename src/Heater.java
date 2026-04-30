public class Heater extends Device{
    public Heater(String name, double watts, int roomId){
        super(name,watts,roomId);
    }

    public double calculateDailyConsumption(int hours){
        return (getWatts() * hours) / 1000.0;

    }
}
