import java.util.Random;
import java.util.LinkedList;
import java.util.List;
public class neuron {
    protected List<Value> w;
    protected int nin;
    protected Value b;
    protected double data_b;
    protected int[] x;
    protected Random rand = new Random();
    protected Value sum;
    protected Value out;
    protected List<Value> params;

    protected neuron(int nin){
        w  = new LinkedList<>();
        for (int i = 0;i < nin; i ++){
            double data_a = 2 * rand.nextDouble() - 1;
            Value rand_a = new Value(data_a,"Weight");
            this.w.add(rand_a);
        }
        data_b = 2 * rand.nextDouble() - 1;
        b = new Value(data_b,"Bias");
    }
    protected Value call(double[] x){
        Value sum = new Value(0.0);
        //System.out.println(x);
        for (int i = 0;i < x.length;i ++){
            Value result = new Value(x[i]).mul(w.get(i));
            sum = sum.add(result);
        }
        sum = sum.add(b);
        out = sum.tanh();
        return out;
    }
    protected List<Value> parameters(){
        List<Value> params = new LinkedList<>(w);
        params.add(b);
        return params;
    }
}
