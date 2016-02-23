package com.company;

/**
 * Created by Will on 2/22/16.
 */
public class BinaryConstraint {

    BinaryConstraint(char item1, char item2, TypeOfBinaryConstraint type){
        this.item1 = item1;
        this.item2 = item2;
        this.constraint = type;
    }

    BinaryConstraint(char item1, char item2, char bag1, char bag2){
        this.item1 = item1;
        this.item2 = item2;
        this.bag1 = bag1;
        this.bag2 = bag2;
        this.constraint = TypeOfBinaryConstraint.MUTUAL;
    }


    TypeOfBinaryConstraint constraint;

    char item1;
    char item2;

    char bag1 = ' ';
    char bag2 = ' ';

    enum TypeOfBinaryConstraint{
         EQUAL,
         NOTEQUAL,
         MUTUAL;
    }


}
