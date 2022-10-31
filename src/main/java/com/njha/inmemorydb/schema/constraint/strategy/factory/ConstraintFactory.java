package com.njha.inmemorydb.schema.constraint.strategy.factory;

import com.njha.inmemorydb.schema.constraint.Constraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ConstraintFactory {

    @Autowired
    private Map<ConstraintType, ConstraintStrategy> constraintStrategyMap;

    public Constraint getConstraint(ConstraintType type, List<Object> args) {
        return constraintStrategyMap.get(type).getConstraint(args);
    }
}
