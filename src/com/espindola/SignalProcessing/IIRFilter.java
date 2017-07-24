package com.espindola.SignalProcessing;

/**
 * Created by Ariel on 7/12/17.
 */
public class IIRFilter implements Convolution {
    private int M;
    private int L;
    private double[] b;
    private double[] a;


    public IIRFilter(double[] b, double[] a){
        this.b = b;
        this.a = a;
        this.M = b.length;
        this.L = a.length;
    }

    @Override
    public double[] conv(double[] inputSignal) {
        int N = inputSignal.length;
        double u [] = new double[M];
        double out[] = new double[N];
        double temp1,temp2, x;
        int n,k,l;
        double y = 0;

        for (n = 0; n < N; n ++) {
            if(L != M){
                System.out.println("a and b must be of same length"); break;}
            x = inputSignal[n];
            temp1 = 0;
            temp2 = 0;

            // Rutina IIR
            for (k = 1; k < M; k++) //recorre los coeficientes
            {
                temp1 = temp1 - a[k] * u[k];
                temp2 = temp2 + b[k] * u[k];
            }
            u[0] = x + temp1;
            y =  (u[0] * b[0] + temp2);

            for (l = M-1; l > 0; l--) {
                u[l] = u[l - 1];
            }
            System.out.println("y["+n+"]="+y);
            out[n] = y;
        }
        return out;
    }
}
