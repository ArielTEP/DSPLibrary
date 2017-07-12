package com.espindola;

import com.espindola.SignalProcessing.Convolution;
import com.espindola.SignalProcessing.FIRFilter;
import com.espindola.SignalProcessing.IIRFilter;

public class Main {

    public static void main(String[] args) {
	    double []inputSamples = {1,2,3,4,5,6,7,8,9,10};
        double a [] = {1,2,3,4}; //denominador
        double b [] = {4,3,2,1}; //numerador

        Convolution []y = new Convolution[2];
        y[0] = new FIRFilter(b);
        y[1] = new IIRFilter(b,a);

        y[0].conv(inputSamples);
        y[1].conv(inputSamples);
    }
}
