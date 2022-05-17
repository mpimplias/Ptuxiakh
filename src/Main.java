import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int vessels = 10;
        int timeSlots = 10;

        int[][] demand = new int[vessels][timeSlots];

        Random random = new Random();
        for (int i = 0 ; i < vessels ; i++){
            int j = random.nextInt(10);
            demand[i][j] = 1;
        }

        for (int i = 0 ; i < vessels ; i++){
            for (int j = 0 ; j < timeSlots ; j++){
                System.out.print(demand[i][j] + " ");
            }
            System.out.println();
        }

        Model model = new Model(vessels , timeSlots , demand);

        model.Solve();

    }

}
