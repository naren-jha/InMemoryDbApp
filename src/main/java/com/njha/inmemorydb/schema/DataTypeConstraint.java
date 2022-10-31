package com.njha.inmemorydb.schema;

import com.njha.inmemorydb.schema.constraint.Constraint;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataTypeConstraint<T> {
    private T dataType;
    private List<Constraint> constraints;
}
