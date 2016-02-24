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

    static char getItem1()
    {
    return item1;
    }
    static char getItem2()
    {
        return item2;
    }

    static char getBag1()
    {
        return bag1;
    }

    static char getBag2()
    {
        return bag2;
    }
    static TypeOfBinaryConstraint getConstraint(){
        return constraint;
    }

    static char getBag1i(){
        return item1.getBagI();
    }
    static char getBag2i(){
        return item2.getBagI();
    }


    static TypeOfBinaryConstraint constraint;

    static char item1;
    static char item2;

    static char bag1 = ' ';
    static char bag2 = ' ';

    enum TypeOfBinaryConstraint{
         EQUAL,
         NOTEQUAL,
         MUTUAL;
    }


}
