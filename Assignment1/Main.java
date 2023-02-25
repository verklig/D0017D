/**
* The program calculates how long it takes in hours to charge a car battery depending on the charging power, printing the info to the console
*
* 1. Create a constant and variables
* 2. Print battery capacity and charging table to the console
* 3. Calculate wattage and charging time
* 4. Print out strings to the console with max 2 decimals
*
* @author Eric Blohm, boleri2
*/
class Main {
  public static void main(String[] args) {
  
    final double CAPACITY = 35.8;
    double wattage;
    double roundWattage;
    double time;
    double roundTime;
    int voltage;
    int current;
    
    System.out.println("\nBattery: " + CAPACITY + " (kWh)\n");
    System.out.printf("%-18s%-18s%-26s%-18s\n", "Current(A)", "Voltage(V)", "Charging Power(kW)", "Charging Time(h)");
    
    voltage = 230;
    current = 10;
    roundWattage = Math.round(current * voltage);
    wattage = roundWattage / 1000;
    roundTime = Math.round(CAPACITY / wattage * 100);
    time = roundTime / 100;
    
    System.out.printf("%-18s%-18s%-26.2f%-18.2f\n", current, voltage, wattage, time);
    
    voltage = 230;
    current = 16;
    roundWattage = Math.round(current * voltage);
    wattage = roundWattage / 1000;
    roundTime = Math.round(CAPACITY / wattage * 100);
    time = roundTime / 100;
    
    System.out.printf("%-18s%-18s%-26.2f%-18.2f\n", current, voltage, wattage, time);
        
    voltage = 400;
    current = 10;
    roundWattage = Math.round(current * voltage * Math.sqrt(3));
    wattage = roundWattage / 1000;
    roundTime = Math.round(CAPACITY / wattage * 100);
    time = roundTime / 100;
    
    System.out.printf("%-18s%-18s%-26.2f%-18.2f\n", current, voltage, wattage, time);
    
    voltage = 400;
    current = 16;
    roundWattage = Math.round(current * voltage * Math.sqrt(3));
    wattage = roundWattage / 1000;
    roundTime = Math.round(CAPACITY / wattage * 100);
    time = roundTime / 100;
    
    System.out.printf("%-18s%-18s%-26.2f%-18.2f\n", current, voltage, wattage, time);
    
    voltage = 400;
    current = 32;
    roundWattage = Math.round(current * voltage * Math.sqrt(3));
    wattage = roundWattage / 1000;
    roundTime = Math.round(CAPACITY / wattage * 100);
    time = roundTime / 100;
    
    System.out.printf("%-18s%-18s%-26.2f%-18.2f\n", current, voltage, wattage, time);
  }
}
