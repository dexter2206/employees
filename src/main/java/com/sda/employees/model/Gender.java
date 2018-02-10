package com.sda.employees.model;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromInitial(String initial) {
        if(initial.equalsIgnoreCase("M")) {
            return Gender.MALE;
        }
        if(initial.equalsIgnoreCase("F")) {
            return Gender.FEMALE;
        }
        throw new RuntimeException("Cannot construct member of Gender enum from " + initial);
    }
}
