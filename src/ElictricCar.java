public class ElictricCar implements Elictrifide{

    private String modle;
    private double BatteryCapacity;

    public ElictricCar(String modle , double BatteryCapacity){
        this.modle = modle;
        this.BatteryCapacity = BatteryCapacity;
    }
    public double calculateDailyConsumption(int hours) {
        return BatteryCapacity; // السيارة تستهلك سعة بطاريتها عند الشحن
    }

    public String getModel() { return modle; }
}
