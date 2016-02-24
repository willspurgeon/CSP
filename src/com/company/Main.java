package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<Bag> bags = new ArrayList<Bag>();
    static ArrayList<BagItem> items;

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

        backTrackingSearch();

    }

    static void backTrackingSearch(){
        ArrayList<BagItem> result = backTrack(items);

        if(result.size() == 0){
            System.out.println("There is no solution.");
        }else{
            for(BagItem item: result){
                System.out.println("Item " + item.letter + " is in bag " + item.bagValue);
            }
        }

    }

    static ArrayList<BagItem> backTrack(ArrayList<BagItem> items){
        if(complete(items)){
            return items;
        }

        BagItem unassigned = getUnassignedVariable();
        for(Bag bag: orderDomainValues(unassigned, items)){
            if(satifiesConstraints(bag, unassigned, items)){
                //Add item to assignment
                //inferences ←INFERENCE(csp,var,value)
                if(satifiesConstraints(inference, items){
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

    static Bag[] orderDomainValues(BagItem unassigned, ArrayList<BagItem> items) {
        //Implement heuristic here.
        return (Bag[]) bags.toArray();
    }

    static boolean satifiesConstraints(Bag bag, BagItem singleItem, ArrayList<BagItem> items) {
        //Is it possible to add bag value to items without violating any constraints?
        //Do we need to include a binary constraints classifier to the intances?



        if (bag.lowerLimit > bag.numItems || bag.numItems > bag.upperLimit) {
            return false;
        }
        else if (BinaryConstraint.getConstraint().equals(MUTUAL))// should equal mutual
        {
            if (BinaryConstraint.getItem1()==BinaryConstraint.getBag1()||BinaryConstraint.getItem1()==BinaryConstraint.getBag2())
            {
                if(BinaryConstraint.getItem2() != BinaryConstraint.getBag1()||BinaryConstraint.getItem2() != BinaryConstraint.getBag2()){
                    return false;
                }
            }
        }
        else if(BinaryConstraint.getConstraint().equals("EQUAL")&&BinaryConstraint.getItem1().getBag1i()!= BinaryConstraint.getItem2().getBag2i()){ // need it to equal EQUAL and get the bag from item 1& item 2

                return false;

        }
        else if(BinaryConstraint.getConstraint().equals("NOTEQUAL")&& BinaryConstraint.getItem1().getBag1i()== BinaryConstraint.getItem2().getBag2i()){ // need to get it to equal NOT EQUAL and get the bag from item 1& item 2

                return false;

        }
        else {
            for (int i = 0; i < singleItem.getallowedbags().size(); i++) { // need to get size of the array
                if (! singleItem.getallowedbags(i).equals(bag)) {
                    return false;
                }
            }
            for (int j = 0; j < singleItem.getDisallowedbags().size(); j++) {
                if (! singleItem.getDisallowedbags(j).equals(bag))// need to access the ith bag in that array
                    return false;
            }
        }

        return true;


    }

    static int min(Integer[] input){
        //Returns the smallest integer.
        int result = Integer.MAX_VALUE;

        for(Integer num: input){
            if(num < result){
                result = num;
            }
        }
        return result;
    }

    static boolean complete(ArrayList<BagItem> items){
        //Check to see if all items have been assigned to bags.
        for(BagItem item: items){
            if(item.bagValue == ' '){
                return false;
            }
        }
        return true;
    }

    static BagItem getUnassignedVariable(){
        //Implement heuristic here.
        //Min-remaining values.
        Integer[] resultArray = new Integer[items.size()];
        int i = 0;
        for(BagItem item: items){
            for(Bag bag: bags){
                if(satifiesConstraints(bag, item, items)){
                    resultArray[i]++;
                }
            }
            i++;
        }

        return items.get(min(resultArray));
    }

}
