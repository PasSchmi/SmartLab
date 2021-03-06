package com.example.fabiosprotte.einkaufslistengenerator;

import java.util.ArrayList;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * Created by Fabio Sprotte on 15.05.2018.
 */
public class Service {

    //Konstruktor
    public Service() {

    }

    //Berechnet die StandardAbweichung
    public double computeStandardAbw(ArrayList<Integer> produktDaten){
        double mittelWert = 0;
        double varianz = 0;
        double standardAbw = 0;
        int tageSumme = 0;
        double zwischenBerechnung = 0;

        //Mittelwert
        for(int i=0; i<produktDaten.size(); i++) {
            tageSumme += produktDaten.get(i);
        }
        mittelWert = tageSumme/produktDaten.size();

        System.out.println("Mittelwert:         :" + mittelWert);

        //Varianz
        for(int i=0; i<produktDaten.size(); i++) {
            zwischenBerechnung += Math.pow((produktDaten.get(i)-mittelWert), 2);
        }
        varianz = zwischenBerechnung/produktDaten.size();

        System.out.println("Varianz:        " + varianz);

        //Standardabweichung
        standardAbw = Math.sqrt(varianz);

        return standardAbw;
    }

    //Berechnet den Erwartungswert P.S. unser Erwartungswert ist hier den Durchschnitt
    public double computeErwartungswert(ArrayList<Integer> produktDaten) {
        double erwartungsWert = 0;
        int tageSumme = 0;
        for(int i=0; i<produktDaten.size(); i++) {
            tageSumme += produktDaten.get(i);
        }
        erwartungsWert = tageSumme/produktDaten.size();

        return erwartungsWert;
    }

    //Erzeugt die Verteilungsfunktion und berechnet die Verbrauchtswahrscheinlichkeit.
    public double computeKumulativeWahrscheinlichkeit(ArrayList<Integer> produktDaten,  int abgelaufeneTage)
    {
        double erwartungsWert = this.computeErwartungswert(produktDaten);

        double standardAbweichung = this.computeStandardAbw(produktDaten);

        //Verteiluntsfunktion wird erzeugt
        NormalDistribution normDist = new NormalDistribution(erwartungsWert, standardAbweichung);

        //Wahrscheinlichkeit, dass ein Produkt nach höchstens n-1 Tagen verbraucht wird: P(x<n)
        double cumulWahrscheinlichkeit = normDist.cumulativeProbability(abgelaufeneTage);

        return cumulWahrscheinlichkeit;
    }
}
