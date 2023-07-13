package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = (long) 10.0;
        AbstractExpression a = new Const(10.9);
        System.out.println(a.toString());
    }
}
