package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<Bag> bags = new ArrayList<Bag>();
    ArrayList<BagItem> items;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.print("Wrong number of command line arguments!");
            System.exit(-1);
        }

        ArrayList<BinaryConstraint> constraints = new ArrayList<BinaryConstraint>();

        ArrayList<BagItem> variables = new ArrayList<BagItem>();

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
                            String[] vars = line.split(" ");
                            BagItem item = new BagItem();
                            item.letter = vars[0].charAt(0);
                            item.weight = Integer.parseInt(vars[1]);

                            variables.add(item);
                            break;
                        case 2:
                            String[] x = line.split(" ");
                            Bag bag = new Bag();
                            bag.letter = x[0].charAt(0);
                            bag.capacity = Integer.parseInt(x[1]);

                            bags.add(bag);
                            break;
                        case 3:
                            String[] limits = line.split(" ");

                            for(Bag y: bags){
                                y.lowerLimit = Integer.parseInt(limits[0]);
                                y.upperLimit = Integer.parseInt(limits[1]);
                            }
                            break;
                        case 4:
                            String[] unIn = line.split(" ");

                            for(BagItem var: variables){
                                if(var.letter == unIn[0].charAt(0)){
                                    for(int i = 1; i < unIn.length; i++){
                                        var.allowedBags.add(unIn[i].charAt(0));
                                    }
                                }
                            }
                            break;
                        case 5:
                            String[] unEx = line.split(" ");

                            for(BagItem var: variables){
                                if(var.letter == unEx[0].charAt(0)){
                                    for(int i = 1; i < unEx.length; i++){
                                        var.disallowedBags.add(unEx[i].charAt(0));
                                    }
                                }
                            }
                            break;
                        case 6:
                            String[] eq = line.split(" ");
                            constraints.add(new BinaryConstraint(eq[0].charAt(0), eq[1].charAt(0), BinaryConstraint.TypeOfBinaryConstraint.EQUAL));
                            break;
                        case 7:
                            String[] notEQ = line.split(" ");
                            constraints.add(new BinaryConstraint(notEQ[0].charAt(0), notEQ[1].charAt(0), BinaryConstraint.TypeOfBinaryConstraint.NOTEQUAL));
                            break;
                        case 8:
                            String[] mutIn = line.split(" ");
                            constraints.add(new BinaryConstraint(mutIn[0].charAt(0), mutIn[1].charAt(0),mutIn[2].charAt(0), mutIn[3].charAt(0)));
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

    void backTrackingSearch(){
        backTrack(bags);
    }

    ArrayList<BagItem> backTrack(ArrayList<BagItem> items){
        if(complete(items)){
            return items;
        }

        Bag unassigned = getUnassignedVariable();
        for(Bag bag: orderDomainValues(unassigned, items)){
            if(satifysConstraints(bag, items)){
                //Add item to assignment
                //inferences ←INFERENCE(csp,var,value)
                if(satifysConstraints(inference, items){
                    //add inference to assignment
                    ArrayList<BagItem> result = backTrack(items);
                    if(result.size() != 0){
                        //Not a failure
                        return result;
                    }
                }
            }
            //Remove item and inference from assignment
        }
        return new ArrayList<BagItem>();
    }

    Bag[] orderDomainValues(Bag unassigned, ArrayList<BagItem> items) {
        //Implement heuristic here.
    }

    boolean satifysConstraints(Bag bag, ArrayList<BagItem> items) {
        //Is it possible to add item to bags without violating any constraints?
    }

    boolean complete(ArrayList<BagItem> items){
        for(BagItem item: items){
            if(item.bagValue == ' '){
                return false;
            }
        }
        return true;
    }

    Bag getUnassignedVariable(){
        //Implement heuristic here.
    }

}
