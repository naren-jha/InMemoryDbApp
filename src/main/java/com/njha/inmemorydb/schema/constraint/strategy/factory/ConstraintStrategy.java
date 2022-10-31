package com.njha.inmemorydb.schema.constraint.strategy.factory;

import com.njha.inmemorydb.schema.constraint.Constraint;

import java.util.List;

public interface ConstraintStrategy {
 Constraint getConstraint(List<Object> args);
 ConstraintType constraintStrategyType();
}
