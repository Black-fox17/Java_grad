import java.util.function.Consumer;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
public class Value{
    protected double data;
    protected double grad;
    protected Runnable _backward;
    protected Set<Value>prev;
    protected Set<Value>visited;
    protected String label;

    public Value(double data){
        this(data,new HashSet<>());
    }
    public Value(double data,Set<Value> children){
        this.data = data;
        this.grad = 0.0;
        this.prev = new HashSet<>(children);
        this._backward = () -> {};
    }
    public Value(double data,String label){
        this.data = data;
        this.grad = 0.0;
        this.label = label;
    }
    @Override
    public String toString(){
        return "Value( data: " + data + " grad: " + grad + " label: " + label +")";
    }

    public Value add(Value other){
        Value out = new Value(this.data + other.data,Set.of(this,other));
        
        out._backward = () -> {
            this.grad += 1.0 * out.grad;
            other.grad += 1.0 * out.grad;
        };
        return out;
    }
    public Value mul(Value other){
        Set<Value> children = new HashSet<>();
        children.add(this);
        children.add(other);
        Value out = new Value(this.data * other.data,children);
        out._backward = () -> {
            this.grad += other.data * out.grad;
            other.grad += this.data * out.grad;
        };
        return out;
    }
    public void new_backward() {
        _backward.run();
    }
    public Value exp(){
        Value out = new Value(Math.exp(this.data),Set.of(this));
        return out;
    }
    public Value sub(Value other){
        Value out = new Value(this.data - other.data,Set.of(this,other));
        return out;
    }
    public Value div(Value other){
        Value out = new Value(this.data / other.data,Set.of(this,other));
        return out;
    }
    public Value tanh(){
        double x = (Math.exp(2 * this.data) - 1) / (Math.exp(2 * this.data) + 1);
        Value out = new Value(x,Set.of(this));
        out._backward = () -> {
            this.grad += (1 - Math.pow(x, 2)) * out.grad;
        };
        return out;

    }
    public void setGrad(double grad){
        this.grad = grad;
    }
    public void setdata(double data){
        this.data = data;
    }
    public void backward() {
        List<Value> topo = new LinkedList<>();
        Set<Value> visited = new HashSet<>();

        // Helper method to build the topological order
        buildTopo(this, visited, topo);

        this.grad = 1.0;
        for (int i = topo.size() - 1; i >= 0; i--) {
            topo.get(i)._backward.run();
        }
    }

    // Helper method to build the topological order
    private void buildTopo(Value v, Set<Value> visited, List<Value> topo) {
        if (!visited.contains(v)) {
            visited.add(v);
            for (Value child : v.prev) {
                buildTopo(child, visited, topo);
            }
            topo.add(v);
        }
    }

    public double getGrad(){
        return this.grad;
    }
    public Runnable getBackward() {
        return _backward;
    }
    protected double getdouble(){
        return data;
    }
    public double[] todouble(){
        double[] output = new double[1];
        output[0] = data;
        return output;
    }
}