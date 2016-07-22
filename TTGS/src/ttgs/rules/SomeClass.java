/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.rules;

/**
 *
 * @author Vincent
 */
public class SomeClass {

    public static int getSomestatic() {
        return somestatic;
    }

    public static void setSomestatic(int aSomestatic) {
        somestatic = aSomestatic;
    }
    private int someint;
    private String somestring;
    private double somedouble;
    private float somefloat;
    private static int somestatic;

    public int getSomeint() {
        return someint;
    }

    public void setSomeint(int someint) {
        this.someint = someint;
    }

    public String getSomestring() {
        return somestring;
    }

    public void setSomestring(String somestring) {
        this.somestring = somestring;
    }

    public double getSomedouble() {
        return somedouble;
    }

    public void setSomedouble(double somedouble) {
        this.somedouble = somedouble;
    }

    public float getSomefloat() {
        return somefloat;
    }

    public void setSomefloat(float somefloat) {
        this.somefloat = somefloat;
    }
    
}
