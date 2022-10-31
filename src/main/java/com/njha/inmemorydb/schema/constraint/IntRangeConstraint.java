package com.njha.inmemorydb.schema.constraint;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntRangeConstraint implements Constraint {
    private int lowerRange;
    private int upperRange;

    @Override
    public boolean isValid(Object val) {
        int intVal = (int) val;
        return intVal >= lowerRange && intVal <= upperRange;
    }
}
