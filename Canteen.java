import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Canteen{
    //Initializing the foods for menu
    private static class foodCostAmount{
        private final HashMap<String, Double> foodCost;
        
        public foodCostAmount(){
            foodCost = new HashMap<>();
            foodCost.put("Burger", 80.00);
            foodCost.put("Pizza", 120.00);
            foodCost.put("Pasta", 100.00);
            foodCost.put("Sandwich", 70.00);
            foodCost.put("Milktea", 90.00);
        }
    }

    //Running the whole code via methods and user inputs
    public static void main(String[] args){

        Integer itemNumber, itemQuantity, totalItemNumber = 0;
        Boolean studentConfirmation, continueOrder;
        Character confirmationAnswer, anotherOrder;
        Double calculatedSubtotal, totalBeforeDiscount = 0.00, 
        totalDiscount, totalAfterDiscount = 0.00, itemValueRecord, totalDiscountAmount = 0.00;
        
        printMenu();
            try (Scanner userInput = new Scanner(System.in)){
            do{
            System.out.print("Enter item number: ");
            itemNumber = userInput.nextInt();
            itemValueRecord = recordOrder(itemNumber);
                
            System.out.print("Enter item quantity: ");
            itemQuantity = userInput.nextInt();

            if(itemNumber <= 5){
                calculatedSubtotal = calculateSubtotal(itemValueRecord, itemQuantity);
                do{
                System.out.print("Are you a student? (Y/N): ");
                confirmationAnswer = userInput.next().charAt(0);
                if (Character.toUpperCase(confirmationAnswer) != 'Y' && Character.toUpperCase(confirmationAnswer) != 'N') {
                    System.out.println("Invalid answer.\n");
                    }
                }while((Character.toUpperCase(confirmationAnswer) != 'Y') && (Character.toUpperCase(confirmationAnswer) != 'N'));
                studentConfirmation = (Character.toUpperCase(confirmationAnswer) == 'Y');

                totalDiscount = studentDiscount(studentConfirmation, calculatedSubtotal);
                System.out.printf("%nSubtotal: %.2f%n", calculatedSubtotal);
                System.out.printf("Discount: %.2f%n", totalDiscount);
                System.out.printf("Order total: %.2f%n", (calculatedSubtotal - totalDiscount));

                totalItemNumber += itemQuantity;
                totalBeforeDiscount += calculatedSubtotal;
                totalDiscountAmount += totalDiscount;
                totalAfterDiscount = totalBeforeDiscount - totalDiscountAmount;
            }else{
                System.out.println("");
                System.out.println("Invalid order! Please enter a valid item and quantity.");
            }

            do{
            System.out.print("\nDo you want to order again? (Y/N): ");
            anotherOrder = userInput.next().charAt(0);
            if((Character.toUpperCase(anotherOrder)) != 'Y' && (Character.toUpperCase(anotherOrder) != 'N')){
                System.out.println("Invalid answer.");
            }
            }while((Character.toUpperCase(anotherOrder) != 'Y') && (Character.toUpperCase(anotherOrder) != 'N'));
            continueOrder = (Character.toUpperCase(anotherOrder) == 'Y');
            System.out.println(" ");
            }while(continueOrder); 
        orderSummary(totalItemNumber, totalBeforeDiscount, totalDiscountAmount, totalAfterDiscount);
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

            default -> 0.00;            
        };
        return getItemValue;
    }

    //Method to calculate the subtotal of the order
    public static Double calculateSubtotal(Double itemValueRecord, Integer itemQuantity){
        Double Subtotal = itemValueRecord * itemQuantity;

        return Subtotal;
    }

    //Method to calculate the applied discount
    public static Double studentDiscount(Boolean studentConfirmation, Double calculatedSubtotal){
        Double subtotalDiscount;
        if (studentConfirmation){
            if (calculatedSubtotal > 500.00){
                subtotalDiscount = calculatedSubtotal * 0.15;
            }else{
                subtotalDiscount = calculatedSubtotal * 0.10;
            }
        }else{
            subtotalDiscount = 0.00;
        }
        return subtotalDiscount;
    }

    //Method to print the order summary
    public static void orderSummary(Integer totalItemNumber, Double totalBeforeDiscount, Double totalDiscountAmount, Double totalAfterDiscount){
        System.out.printf("==== %-5s ====%n", "ORDER SUMMARY");
        System.out.printf("Total items: %n", totalItemNumber);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscountAmount);
        System.out.printf("Final amount: $%.2f%n", totalAfterDiscount);
        System.out.println("Thank you for ordering!");
    }
}  

