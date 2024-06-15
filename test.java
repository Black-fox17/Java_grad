import java.util.List;
import java.util.LinkedList;
public class test {
    public static void main(String[] args){
        double[][] xs = {
            {2.0,3.0,-1.0},
            {3.0,-1.0,0.5},
            {0.5,1.0,1.0},
            {1.0,1.0,-1.0}
        };
        double [] ys ={1.0,-1.0,-1.0,1.0};
        int nin = 3;
        int[] nouts = {4,4,1};
        MLP n = new MLP(nin,nouts);
        Value y_pred;
        double[] pred;
        Value loss;
        List<Value> all_pred;
        int j = 0;
        for(Value t: n.parameters()){
            j += 1;
        }
        System.out.println(j);
        for (int i = 0; i < 4;i++){
            loss = new Value(0.0);
            all_pred = new LinkedList<>();
            for (int k = 0; k < ys.length; k++ ){
                pred = n.call(xs[k]);
                y_pred = new Value(pred[0]);
                all_pred.add(y_pred);
                Value diff = y_pred.sub(new Value(ys[k]));
                Value squared = diff.mul(diff);
                loss = loss.add(squared);
            for (Value p: n.parameters()){
                p.grad = 0.0;
                p.setGrad(p.grad);

            }
        }
            loss.backward();
            double lr = 0.1;
            for (Value p: n.parameters()){
                p.data += -lr * p.grad;
                p.setdata(p.data);
                
            }
            //System.out.println(all_pred);
        }
    }
}
