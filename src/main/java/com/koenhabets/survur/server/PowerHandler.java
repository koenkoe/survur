package com.koenhabets.survur.server;

import spark.Request;
import spark.Response;

import java.util.Scanner;

public class PowerHandler {
    static double currentEnergyUsage = 0.0;
    static double currentEnergyProduction = 0.0;
    static double energyUsage1 = 0.0;
    static double energyUsage2 = 0.0;
    static double energyProduction1 = 0.0;
    static double energyProduction2 = 0.0;
    static double gasUsage = 0.0;

    public String setEnergy(Request request, Response response){
        String data = request.queryParams("data");
        parseData(data);
        return "tada";
    }

    private void parseData(String data){
        Scanner sc = new Scanner(data);
        while(sc.hasNextLine()){
            String netxLine = sc.nextLine();
            if(netxLine.contains("1-0:1.7.0")){
                String[] d = netxLine.split("\\(");
                String[] e = d[1].split("\\*");
                currentEnergyUsage = Double.parseDouble(e[0]);
            }
            if(netxLine.contains("1-0:2.7.0")){
                String[] d = netxLine.split("\\(");
                String[] e = d[1].split("\\*");
                currentEnergyProduction = Double.parseDouble(e[0]);
            }
            if(netxLine.contains("1-0:1.8.1")){
                String[] d = netxLine.split("\\(");
                String[] e = d[1].split("\\*");
                energyUsage1 = Double.parseDouble(e[0]);
            }
            if(netxLine.contains("1-0:1.8.2")){
                String[] d = netxLine.split("\\(");
                String[] e = d[1].split("\\*");
                energyUsage2 = Double.parseDouble(e[0]);
            }
            if(netxLine.contains("1-0:2.8.1")){
                String[] d = netxLine.split("\\(");
                String[] e = d[1].split("\\*");
                energyProduction1 = Double.parseDouble(e[0]);
            }
            if(netxLine.contains("1-0:2.8.2")){
                String[] d = netxLine.split("\\(");
                String[] e = d[1].split("\\*");
                energyProduction2 = Double.parseDouble(e[0]);
            }
            if(netxLine.contains("0-1:24.2.1")){
                String[] d = netxLine.split("\\(");
                String[] e = d[2].split("\\*");
                gasUsage = Double.parseDouble(e[0]);
            }
        }
        WebSocketHandler.updateAll();
    }
}