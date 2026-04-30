import java.sql.*;

public class DB {
    private static final String DATABASE_NAME = "HomeSystem.db";
    private static final String CONNECTION_URL  = "jdbc:sqlite:" + DATABASE_NAME ;


    public static void initialized(){
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            if (conn != null) {
                System.out.println("connecting Successful");

                Statement statement = conn.createStatement();
                String sqlRoom = "CREATE TABLE IF NOT EXISTS rooms (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL" +
                        ");" ;
                statement.execute(sqlRoom);

                String sqlDevice = "CREATE TABLE IF NOT EXISTS devices (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "watts REAL NOT NULL," +
                        "room_id INTEGER," +
                        "FOREIGN KEY (room_id) REFERENCES rooms(id));";
                statement.execute(sqlDevice);

                String sqlCar = "CREATE TABLE IF NOT EXISTS cars " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, model TEXT, battery REAL)";
                statement.execute(sqlCar);

            }

        }
        catch (SQLException e) {
            System.out.println("error101" + e.getMessage());
        }

    }



    public static void insertDevice(Device device) {
        String sql = "INSERT INTO devices(name, watts, room_id) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, device.getName());
            pstmt.setDouble(2, device.getWatts());
            pstmt.setInt(3, device.getRoomId());

            pstmt.executeUpdate();
            System.out.println("Successfully saved: " + device.getName());

        } catch (SQLException e) {
            System.out.println("Error saving device: " + e.getMessage());
        }
    }



    public static void insertRoom(String name) {
        String sql = "INSERT INTO rooms(name) VALUES(?)";
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Added " + name + " is successful ");

        } catch (SQLException e) {
            System.out.println("error in adding room" + e.getMessage());
        }
    }


    public static void insertCar(ElictricCar car) {
        String sql = "INSERT INTO cars(model, battery) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, car.getModel());
            pstmt.setDouble(2, car.calculateDailyConsumption(0)); // نمرر 0 لأن السيارة تستهلك سعة ثابتة
            pstmt.executeUpdate();
            System.out.println("car saved is successful ");
        } catch (SQLException e) {
            System.out.println("Error in Save car " + e.getMessage());
        }
    }



    public static void showTotalReport(int hours) {
        String sql = "SELECT * FROM devices";
        double totalHomeConsumption = 0;

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n----Home power Report----");
            System.out.println(" Device | watt | power ");
            System.out.println("---------------------------");

            while (rs.next()) {
                String name = rs.getString("name");
                double watts = rs.getDouble("watts");

                double dailyUsage = (watts * hours) / 1000.0;
                totalHomeConsumption += dailyUsage;

                System.out.println(name + " | " + watts + "W | " + dailyUsage + " kWh");
            }

            System.out.println("-----------------------------------");
            System.out.println(" Total household consumption in  " + hours + " hours " + totalHomeConsumption + " kWh");

            System.out.println(" Estimated cost: " + (totalHomeConsumption * 0.05) + " jd");

        } catch (SQLException e) {
            System.out.println("Error in report extraction: " + e.getMessage());
        }
    }




    }




