import ilog.concert.*;
import ilog.cplex.*;


public class Model  {

    private int vessels;
    private int timeSlots;
    private int drones;
    private int[][] demand;

    public Model(int vessels , int timeSlots , int drones , int[][] demand){

        this.vessels = vessels;
        this.timeSlots = timeSlots;
        this.drones = drones;
        this.demand = demand;

    }

    public void Solve(){

        try{

            IloCplex cplex = new IloCplex();

            IloNumVar[][] x = new IloNumVar[vessels][timeSlots];
            for (int i = 0 ; i < vessels ; i++){
                for (int j = 0 ; j < timeSlots ; j++){
                    x[i][j] = cplex.boolVar();
                    //x[i][j] = cplex.numVar(0,drones);
                }
            }

            /*for (int i = 0 ; i < vessels ; i++){
                for (int j = 0 ; j < timeSlots ; j++){
                    cplex.addLe(x[i][j],1);
                }
            }*/

            for (int j = 0 ; j < timeSlots ; j++){

                IloLinearNumExpr sum = cplex.linearNumExpr();
                for (int i = 0 ; i < vessels ; i++){
                    sum.addTerm(1,x[i][j]);
                }

                cplex.addLe(sum,drones);

            }

            IloLinearNumExpr obj = cplex.linearNumExpr();
            for (int i = 0 ; i < vessels ; i++){
                for (int j = 0 ; j < timeSlots ; j++){
                    obj.addTerm(demand[i][j] , x[i][j]);
                }
            }

            cplex.addMaximize(obj);

            if (cplex.solve()){
                System.out.println(cplex.getObjValue());
            }

        }
        catch (IloException exc) {
            exc.printStackTrace();
        }

    }

}
