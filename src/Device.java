public abstract class Device implements Elictrifide {
    private String name;
    private double watts;
    private int roomId;

    public Device(String name, double watts, int roomId){
        this.name = name;
        this.watts = watts;
        this.roomId = roomId;
    }
    public double getWatts() {return watts;}
    public String getName() {return name;}
    public int getRoomId() {return roomId;}

    public abstract double calculateDailyConsumption(int hours);



    @Override
    public String toString() {
        return "Device: " + name + " | power: " + watts ;
    }
}
