package src;
import java.util.Scanner;

class Battery_time
{
    public static class DBsave
    {
        // statusBatteryDB = Charge before battery
        // save_statusBatteryDB = backup statusBatteryDB
        // chargeDB = device need charge database
        // save_chargeDB = backup chargeDB

        static int [] statusBatteryDB = new int [Battery_time.battery_cycle];
        static int [] save_statusBatteryDB = new int [Battery_time.battery_cycle];

        static int [] chargeDB =  new int[Battery_time.battery_cycle];
        static int [] save_chargeDB = new int[Battery_time.battery_cycle];

        public static void setDBsave(int i)
        {
            DBsave.save_statusBatteryDB[i] = DBsave.statusBatteryDB[i];
            DBsave.save_chargeDB[i] = DBsave.chargeDB[i];
        }
        public static int getSave_chargeDB(int i)
        {
            return save_chargeDB[i];
        }
        public static int getSave_statusBatteryDB(int i)
        {
            return save_statusBatteryDB[i];
        }
    }

    public static class Avg_cycle 
    {
        static float result = 0;
        static int playback = 1;
    
        public static void calDB_Avg(int DB_data)
        {
            Avg_cycle.result += DB_data;
            System.out.println(playback + " Result : "+Battery_time.Avg_cycle.result);
            playback++;
        }
        public static float getResult()
        {
            return result /= (float)Battery_time.battery_cycle;
        }
        public static String isStable_avg(float data)
        {
            if (data >= 80)
            {
                return "Battery cycle is Stable.";
            }
            else
            {
                return "Warning !! NEED CHANGE BATTERY";
            }
        }
    }

    // define cycle : charge routine
    // batteryMAX = Define MAX status : 100
    // battery_now = Now battery Remaining amount
    // need_charge = Now Need charge battery
    // battery_cycle = device get charge 1st time * i
    
    final static int batteryMAX = 100;
    static int battery_now;
    static int need_charge = 0;
    static int battery_cycle;

    static Scanner test_int = new Scanner(System.in);

    public static void setData()
    {
        int data = 0;
        data = test_int.nextInt();
        battery_now = data;

        Battery_time.need_charge = Battery_time.batteryMAX - battery_now;
    }

    public static void setData_size(int input_size)
    {
        input_size = test_int.nextInt();
        battery_cycle = input_size;
    }

    // public static void inNeedCharge(int input_avg_data)
    // {
    //     for (int i = 0; i < Battery_time.battery_cycle; i++)
    //     {
    //         Battery_time.DBsave.getDBsave(i);
    //     }
    // }

    public static int getBattery_cycle()
    {
        return battery_cycle;
    }

    public static int getNow()
    {
        return battery_now;
    }

    public static int getNeed_charge()
    {
        return need_charge;
    }

    public static String isStable_battery(int data)
    {
        // Battery boolean check if that status under 25
        boolean isPaas = data < 25 ;
        System.out.println("Less battery : " + Battery_time.getNeed_charge());
        System.out.println("Boolean status : " + isPaas);

        System.out.print("Now status : ");

        if(isPaas == true) 
        {
            return "Warning. Need Charge";
        }
        else
        {
            return "Stable. After charge";
        }
    }

    public static void runRoutine()
    {
        // Return Value at DBsave.chargeDB array
        for (int i = 0; i < Battery_time.battery_cycle; i++)
        {
            Battery_time.setData();
            Battery_time.DBsave.statusBatteryDB[i] = Battery_time.battery_now;
            Battery_time.DBsave.chargeDB[i] = Battery_time.need_charge;
            Battery_time.DBsave.setDBsave(i);
        }

        for (int i = 0; i < Battery_time.battery_cycle; i++)
        {
            Battery_time.Avg_cycle.calDB_Avg(Battery_time.DBsave.save_statusBatteryDB[i]);
            
        }
    }
}