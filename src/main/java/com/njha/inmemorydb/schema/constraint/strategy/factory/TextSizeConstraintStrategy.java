package com.njha.inmemorydb.schema.constraint.strategy.factory;

import com.njha.inmemorydb.schema.constraint.Constraint;
import com.njha.inmemorydb.schema.constraint.TextSizeConstraint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TextSizeConstraintStrategy implements ConstraintStrategy {
    @Override
    public Constraint getConstraint(List<Object> args) {
        return TextSizeConstraint.builder()
                .maxSize((int) args.get(0))
                .build();
    }

    @Override
    public ConstraintType constraintStrategyType() {
        return ConstraintType.TEXT_SIZE;
    }
}
