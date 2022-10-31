package com.njha.inmemorydb.schema.constraint;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextSizeConstraint implements Constraint {
    private int maxSize;

    @Override
    public boolean isValid(Object val) {
        String strVal = (String) val;
        return strVal.length() <= maxSize;
    }
}
