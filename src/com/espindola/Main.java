package com.espindola;

import com.espindola.SignalProcessing.Convolution;
import com.espindola.SignalProcessing.FIRFilter;
import com.espindola.SignalProcessing.IIRFilter;

public class Main {

    public static void main(String[] args) {
	    double []inputSamples = {1,2,3,4,5,6,7,8,9,10};
        double a [] = {1,2,3,4}; //denominador
        double b [] = {3,5,4,-4}; //numerador

        FIRFilter []y = new FIRFilter[2];
        y[0] = new FIRFilter();
        y[0].rcosdesign(0.25f, 4 , 4); // Root-Rised Cosine Filter for QPSK
        //y[1] = new IIRFilter(b,a);

        y[0].conv(inputSamples);
        //y[1].conv(inputSamples);
    }
}
