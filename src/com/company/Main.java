package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.print("Wrong number of command line arguments!");
            System.exit(-1);
        }

        ArrayList<String> variables = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> fittingLimits = new ArrayList<String>();
        ArrayList<String> unaryInclusive = new ArrayList<String>();
        ArrayList<String> unaryExclusive = new ArrayList<String>();
        ArrayList<String> binaryEquals = new ArrayList<String>();
        ArrayList<String> binaryNotEquals = new ArrayList<String>();
        ArrayList<String> mutualInclusive = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(args[0]);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input = "";
            String line = "";



            int inputCount = 0;
            while((line = bufferedReader.readLine()) != null){
                if(line.contains("#####")){
                    inputCount++;
                }else{
                    switch (inputCount){
                        case 1:
                            variables.add(line);
                            break;
                        case 2:
                            values.add(line);
                            break;
                        case 3:
                            fittingLimits.add(line);
                            break;
                        case 4:
                            unaryInclusive.add(line);
                            break;
                        case 5:
                            unaryExclusive.add(line);
                            break;
                        case 6:
                            binaryEquals.add(line);
                            break;
                        case 7:
                            binaryNotEquals.add(line);
                            break;
                        case 8:
                            mutualInclusive.add(line);
                            break;
                    }
                }
                input = input + "\n" + line;
            }
            System.out.print(input);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
