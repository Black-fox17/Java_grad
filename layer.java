import java.util.List;
import java.util.LinkedList;
public class layer {
    protected int nin;
    protected int nout;
    protected neuron neur;
    protected double[] x;
    protected List<neuron> neurons;
    protected List<Value> outs;
    protected Value out;
    protected List<Value> params;
    protected layer(int nin,int nout){
        this.neurons = new LinkedList<>();
        for (int i = 0; i < nout;i++){
            neur = new neuron(nin);
            this.neurons.add(neur);
        }
    }
    public double[] call(double[] x) {
        double[] outs = new double[neurons.size()];
        for (int i = 0; i < neurons.size(); i++) {
            outs[i] = neurons.get(i).call(x).getdouble();
        }
        return outs;
    }
    protected List<Value> parameters(){
        params = new LinkedList<>();
        for (neuron n: this.neurons){
            for(Value p: n.parameters()){
                params.add(p);
            }
        }
        return params;
    }

    protected int length(List<Value> test){
        int j = 1;
        for (Value _: test){
            j += 1;
        }
        return j;
    }
}
