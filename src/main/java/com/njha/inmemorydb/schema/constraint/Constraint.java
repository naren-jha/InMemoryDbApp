package com.njha.inmemorydb.schema.constraint;

public interface Constraint {

    boolean isValid(Object val);

}
