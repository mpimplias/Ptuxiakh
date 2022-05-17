import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int vessels = 10;
        int timeSlots = 5;
        int drones = 2;

        int[][] demand = new int[vessels][timeSlots];

        Random random = new Random();
        for (int i = 0 ; i < vessels ; i++){
            int j = random.nextInt(timeSlots);
            demand[i][j] = 1;
        }

        for (int i = 0 ; i < vessels ; i++){
            for (int j = 0 ; j < timeSlots ; j++){
                System.out.print(demand[i][j] + " ");
            }
            System.out.println();
        }

        Model model = new Model(vessels , timeSlots , drones , demand);

        model.Solve();

    }

}
