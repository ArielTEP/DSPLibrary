package com.espindola.SignalProcessing;

/**
 * Created by Ariel on 7/12/17.
 */
public class FIRFilter implements Convolution{
    private int M;
    private double[] b;

    public FIRFilter(double[] b){
        this.b = b;
        this.M = b.length;
    }

    @Override
    public double[] conv(double[] inputSignal) {
        int N = inputSignal.length;
        double x[] = new double[N+M-1];
        double out[] = new double[N+M-1];
        double y=0;
        for(int n = 0; n < N+M-1; n++ ){
            if (n<N)
                x[0] = inputSignal[n];
            else
                x[0] = 0;
            y=0;
            for(int k = 0; k < M; k++){
                y = y + x[k]*b[k];
            }
            for(int k = M-1; k>0; k--){
                x[k] = x[k-1];
            }
            System.out.println("y["+n+"]="+y);
            out[n] = y;
        }
        return out;
    }

}
