public class new_test {
    public static void main(String[] args){
        /*
         * x1 = Value(2.0, label='x1')
x2 = Value(0.0, label='x2')
# weights w1,w2
w1 = Value(-3.0, label='w1')
w2 = Value(1.0, label='w2')
# bias of the neuron
b = Value(6.8813735870195432, label='b')
# x1*w1 + x2*w2 + b
x1w1 = x1*w1; x1w1.label = 'x1*w1'
x2w2 = x2*w2; x2w2.label = 'x2*w2'
x1w1x2w2 = x1w1 + x2w2; x1w1x2w2.label = 'x1*w1 + x2*w2'
n = x1w1x2w2 + b; n.label = 'n'
# ----
e = (2*n).exp()
o = (e - 1) / (e + 1)
# ----
o.label = 'o'
o.backward()
         */
        Value x1 = new Value(2.0,"x");
        Value x2 = new Value(0.0);
        Value w1 = new Value(-3.0);
        Value w2 = new Value(1.0);
        Value b = new Value(6.8813735870195432);
        Value x1w1 = x1.mul(w1);
        Value x2w2 = x2.mul(w2);
        Value sum = x1w1.add(x2w2);
        Value n = sum.add(b);
        Value out = n.tanh();
        out.backward();
        System.out.println("x1w1: " + x1w1);
        System.out.println("x2w2: " + x2w2);
        System.out.println("n: " + n);
        System.out.println("out: " + out);

        // To read the content of out._backward, you would need to trigger it manually or inspect it
        System.out.println("out._backward:");

    }
    
}
