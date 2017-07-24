package com.espindola.SignalProcessing;

import static java.lang.Math.*;

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

    public FIRFilter(){

    }

    public double[] rcosdesign(float beta, int symbols, int sps){
        int order = symbols*sps;
        int Ts = 1; // sample rate
        double[] B = new double[order+1];
        float pi = 3.1416f;
        int m = 0;
        System.out.println("Beta = "+beta);
        for(int n=-B.length/2; n< (B.length+1)/2; n++){
            if(n==0){
                 B[m] = (1/Ts)*(1+ beta*((4/pi) - 1));
                //B[n] = 1 - beta + (4*beta/pi);
            }else if(n == Math.abs(Ts/(4*beta))){
                B[m] = (beta/(Ts*sqrt(2))) * ( (1+2/PI)*sin(PI/(4*beta))+(1-2/PI)*cos(PI/(4*beta)) );
            }else{
                B[m] = (1/Ts)*( sin( (PI*n*(1-beta))/Ts ) + 4*beta*(n/Ts)*cos(PI*(n/Ts)*(1+beta)) ) / (PI*(n/Ts)*(1-pow(4*beta*(n/Ts),2)));
            }
            //System.out.println("B["+n+"] = " + B[n]);
            System.out.println(B[m] + ",");
            m++;
        }
        this.b = B;
        return B;
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
