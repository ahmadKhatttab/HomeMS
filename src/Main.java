import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DB.initialized();


        while (true){
            System.out.println("\n===============Home Management System===============");
            System.out.println("1. add a new room ");
            System.out.println("2.add device to room");
            System.out.println("3.Exit");
            System.out.println("4.Report");
            System.out.println("5.Insert Car ");
            System.out.print("choice number of operation ");

            int choice = in.nextInt();
            in.nextLine();

            if (choice ==1 ) {
                System.out.print("Enter name of room : ");
                String roomName = in.nextLine();
                DB.insertRoom(roomName);
            } else if (choice == 2) {
                System.out.println("Enter (ID) of room : ");
                int roomID = in.nextInt();
                in.nextLine();
                System.out.println("choice typy of Device: ( 1.AC | 2.Fridge | 3.TV | 4.Heater ) ");
                int type = in.nextInt();
                in.nextLine();
                System.out.print("Enter Brand of Device : ");
                String Brand = in.nextLine();
                System.out.print("Enter watts for this Device : ");
                double watts = in.nextDouble();

                Device device= null;
                if(type==1) device = new AC (Brand,watts,roomID);
                else if (type==2) device = new fridge (Brand,watts,roomID);
                else if (type==3) device = new TV (Brand,watts,roomID);
                else if (type==4) device = new Heater (Brand,watts,roomID);

                if (device != null)
                    DB.insertDevice(device);
                else
                    System.out.println("Device type UnKnown ");
                }
            else if (choice == 3) {
                System.out.println("Thank you for using our System ");
                break;
                }
            else if (choice == 4) {
                System.out.println("what daily operating hours for the devices?");
                int hours = in.nextInt();
                DB.showTotalReport(hours);
            }
            else if (choice == 5) {
                System.out.println("Enter Model of car : ");
                String Model = in.nextLine();
                System.out.println("Enter the battery capacity  ");
                double battery = in.nextDouble();
                in.nextLine();
                ElictricCar car = new ElictricCar(Model , battery);
                DB.insertCar(car);
            }
            else
                System.out.println("this choice is not valid ");

        }

        }

    }
