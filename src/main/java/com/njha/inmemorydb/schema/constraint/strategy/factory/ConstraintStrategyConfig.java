package com.njha.inmemorydb.schema.constraint.strategy.factory;

import com.njha.inmemorydb.schema.DataType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class ConstraintStrategyConfig {

    private final List<ConstraintStrategy> constraintStrategies;

    @Bean
    public Map<ConstraintType, ConstraintStrategy> constraintStrategyMap() {
        Map<ConstraintType, ConstraintStrategy> constraintStrategyMap = new EnumMap<>(ConstraintType.class);
        constraintStrategies.forEach(constraintStrategy -> constraintStrategyMap.put(constraintStrategy.constraintStrategyType(), constraintStrategy));
        return constraintStrategyMap;
    }
}
