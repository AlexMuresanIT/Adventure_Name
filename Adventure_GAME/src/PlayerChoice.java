

import java.util.Map;
import java.util.Scanner;

public class PlayerChoice {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocationData.getData();
        System.out.println("-----------------------");
        System.out.println("At the start of the game you are at the road location!!!");
        System.out.println("Press Q if you want to exit!");
        LocationData.play("road");
        LocationData.setLastPlace("road");

        while(true){

            System.out.println("Choose one direction!");
            String direction = scanner.nextLine().trim().toUpperCase();
            if(direction.equals("Q")){
                break;
            }
            else
                LocationData.move(direction);
        }



    }
}
