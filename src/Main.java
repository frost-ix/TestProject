import java.lang.String;

public class Main
{
    public static void main(String[] argc)
    {
        int size = 0;
        Battery_time.setData_size(size);
        System.out.println("Now battery cycle : " + Battery_time.getBattery_cycle());
        if (Battery_time.battery_cycle == 1)
        {
            Battery_time.setData();
            System.out.println("Battery Cycle is just 1.");
            System.out.println("Now Battery : " + Battery_time.getNow());
            if (Battery_time.battery_now == Battery_time.getNow())
            {
                System.out.println(Battery_time.isStable_battery(Battery_time.getNow()));
                Battery_time.test_int.close();
            }
            else
            {
                // size miss macthed end
                System.out.println("Unmacthed cylce <--> backup size");
                System.exit(1);
                Battery_time.test_int.close();
            }
        }
        else if(Battery_time.battery_cycle > 1)
        {
            Battery_time.runRoutine();
            System.out.println("Battery Average : " + Battery_time.Avg_cycle.getResult());
            System.out.println(Battery_time.Avg_cycle.isStable_avg(Battery_time.Avg_cycle.result));
            Battery_time.test_int.close();
        }
        else if (Battery_time.battery_cycle <= 0)
        {
            System.out.println("Device Offline");
            System.exit(0);
            Battery_time.test_int.close();
        }
    }
}