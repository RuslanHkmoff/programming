package expression;

public class Variable implements AbstractExpression {
    private final String var;

    public Variable(String var) {

        this.var = var;
    }


    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (obj == this) return true;
        Variable other = (Variable) obj;
        return other.var.equals(this.var);
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (var.equals("x")) {
            return x;
        }
        if (var.equals("y")) {
            return y;
        }

        if (var.equals("z")){
            return z;

        }

        if (var.length() >=4){
            if (var.substring(2, 3).equals("z")){
                return -z;
            }
            if (var.substring(2, 3).equals("y")){
                return -y;
            }
            if (var.substring(2, 3).equals("x")){
                return -x;
            }
        }
        return Integer.parseInt("-"+var.substring(2, var.length()-1));
    }
}
