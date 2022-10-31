package com.njha.inmemorydb.schema;

import com.njha.inmemorydb.schema.constraint.Constraint;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Column {
    private String name;
    private DataType dataType;
    private List<Constraint> constraints; // user defined constraints
    private boolean isMandatory;
}
