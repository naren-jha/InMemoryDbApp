package com.njha.inmemorydb.schema.constraint.strategy.factory;

import com.njha.inmemorydb.schema.constraint.Constraint;
import com.njha.inmemorydb.schema.constraint.IntRangeConstraint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IntRangeConstraintStrategy implements ConstraintStrategy {

    @Override
    public Constraint getConstraint(List<Object> args) {
        return IntRangeConstraint.builder()
                .lowerRange((int) args.get(0))
                .upperRange((int) args.get(1))
                .build();
    }

    @Override
    public ConstraintType constraintStrategyType() {
        return ConstraintType.INT_RANGE;
    }
}
