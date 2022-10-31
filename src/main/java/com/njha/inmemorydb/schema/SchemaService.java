package com.njha.inmemorydb.schema;

import com.njha.inmemorydb.Database;
import com.njha.inmemorydb.schema.constraint.Constraint;
import com.njha.inmemorydb.schema.constraint.strategy.factory.ConstraintFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SchemaService {

    @Autowired
    private Database db;

    @Autowired
    private ConstraintFactory constraintFactory;

    public boolean createTable(Table table) {
        /* add user specific constraint
        for (Column col : table.getColumns()) {
            if (col.getDataType() instanceof Integer) {
                col.getConstraints().add(constraintFactory.getConstraint(ConstraintType.INT_RANGE,
                        ConstraintCreationDto.builder().lRange(Integer.valueOf(intLrVal)).uRange(Integer.valueOf(intUrVal)).build()));
            }
            else if (col.getDataType() instanceof String) {
                col.getConstraints().add(constraintFactory.getConstraint(ConstraintType.TEXT_SIZE,
                        ConstraintCreationDto.builder().size(Integer.valueOf(strSizeVal)).build()));
            }
        }*/
        db.addTable(table);

        log.info("{} table created successfully", table.getName());
        return true;
    }

    public List<Table> getTables() {
        return db.getTables();
    }

    public Table getTableByName(String name) {
        return db.getTableByName(name);
    }

    public void addDefaultConstraint(DataType dataType, Constraint constraint) {
        db.addDefaultConstraint(dataType, constraint);
    }

    public List<Constraint> getConstraintByType(DataType dataType) {
        return db.getDefaultConstraintsByType(dataType);
    }

}
