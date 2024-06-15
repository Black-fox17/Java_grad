import java.util.List;
import java.util.LinkedList;
public class MLP {
    int nin;
    int[] nouts;
    int[] sz;
    List<layer> layers;
    Value val_x;
    double[] double_x;
    List<Value> params;
    protected MLP(int nin,int[] nouts){
        this.layers = new LinkedList<>();
        int last_nout = nin;
        for (int nout: nouts){
            layer lay = new layer(last_nout,nout);
            this.layers.add(lay);
            last_nout = nout;
        }
    }
    public List<layer> valu(){
        return this.layers;
    }
    protected double[] call(double[] x){
        double[] input = x;
        for (layer lay: this.layers){
            input = lay.call(x);

        }
        return input;
    }
    protected int len(List<Value> x){
        int j = 1;
        for(Value v: x){
            j += 1;
        }
        return j;
    }
    protected List<Value> parameters(){
        params = new LinkedList<>();
        for (layer l: this.layers){
            for (Value val: l.parameters()){
                params.add(val);
            }
        }
        return params;
    }
}
