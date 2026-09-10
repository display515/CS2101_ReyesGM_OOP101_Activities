//import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Canteen{
    private static class foodCostAmount{
        private final HashMap<String, Double> foodCost;
        
        public foodCostAmount(){
            foodCost = new HashMap<>();
            foodCost.put("Burger", 8.99);
            foodCost.put("Pizza", 120.00);
            foodCost.put("Pasta", 100.00);
            foodCost.put("Sandwich", 70.00);
            foodCost.put("Milktea", 90.00);
        }
    }
    public static void main(String[] args){
        printMenu();

        }
    
    public static void printMenu(){
        System.out.printf("==== %-12s ====%n", "   M E N U");
        
        foodCostAmount getMenuClass = new foodCostAmount();

        for(Map.Entry<String, Double> entry : getMenuClass.foodCost.entrySet()) {
            String getFood = entry.getKey();
            Double getPrice = entry.getValue();
            Integer totalAmount = 1;
            System.out.printf("%d. %-10s - $%.2f%n", totalAmount, getFood, getPrice);
            totalAmount++;
        }
        
        System.out.println();
    }

    
}
