import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Canteen{
    //Initializing the foods for menu
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

    //Running the whole code via methods and user inputs
    public static void main(String[] args){

        Integer itemNumber, itemQuantity;
        Boolean studentConfirmation;
        
        printMenu();

        try (Scanner userInput = new Scanner(System.in)){

            System.out.print("Enter item number: ");
            itemNumber = userInput.nextInt();
            Double itemValueRecord = recordOrder(itemNumber);
            
            System.out.print("Enter item quantity: ");
            itemQuantity = userInput.nextInt();
            Double calculatedSubtotal = calculateSubtotal(itemValueRecord, itemQuantity);

            System.out.print("Are you a student? (Y/N): ");
            studentConfirmation = userInput.nextBoolean();
            studentDiscount(studentConfirmation, calculatedSubtotal);
            }
        }

    //Method for printing the menu
    public static void printMenu(){
        Integer totalAmount = 1;

        System.out.printf("==== %-12s ====%n", "   M E N U");
        
        foodCostAmount getMenuClass = new foodCostAmount();

        for(Map.Entry<String, Double> entry : getMenuClass.foodCost.entrySet()) {
            String getFood = entry.getKey();
            Double getPrice = entry.getValue();
            System.out.printf("%d. %-10s - $%.2f%n", totalAmount, getFood, getPrice);
            totalAmount++;
        }
        
        System.out.println();
    }

    //Method for getting the order using switch case
    public static Double recordOrder(Integer itemNumber){
        foodCostAmount getMenuClass = new foodCostAmount();
        Double getItemValue = switch(itemNumber){

            case 1 -> getMenuClass.foodCost.get("Burger");
            case 2 -> getMenuClass.foodCost.get("Pizza");
            case 3 -> getMenuClass.foodCost.get("Pasta");
            case 4 -> getMenuClass.foodCost.get("Sandwich");
            case 5 -> getMenuClass.foodCost.get("Milktea");

            default -> null;            
        };
        return getItemValue;
    }

    //Calculate the subtotal of the order
    public static Double calculateSubtotal(Double itemValueRecord, Integer itemQuantity){
        Double Subtotal = itemValueRecord * itemQuantity;

        return Subtotal;
    }

    //Calculate the applied discount
    public static Double studentDiscount(Boolean studentConfirmation, Double calculatedSubtotal){
        Double subtotalDiscount;
        if (studentConfirmation){
            if (calculatedSubtotal > 500.00){
                subtotalDiscount = calculatedSubtotal * 0.15;
            }else{
                subtotalDiscount = calculatedSubtotal * 0.10;
            }
        }else{
            subtotalDiscount = calculatedSubtotal;
        }
        return subtotalDiscount;
    }
}  

