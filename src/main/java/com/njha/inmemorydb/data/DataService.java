package com.njha.inmemorydb.data;

import com.njha.inmemorydb.AppDatabase;
import com.njha.inmemorydb.schema.Column;
import com.njha.inmemorydb.schema.Table;
import com.njha.inmemorydb.schema.constraint.Constraint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataService {

    @Autowired
    private AppDatabase db;

    public void addRecordToTable(String tableName, Map<String, Object> record) {

        Table table = db.getTableByName(tableName);

        // verify generic constraints by data types
        for (Column column : table.getColumns()) {
            List<Constraint> defaultConstraintsByType = db.getDefaultConstraintsByType(column.getDataType());
            for (Constraint constraint : defaultConstraintsByType) {
                if (!constraint.isValid(record.get(column.getName()))) {
                    log.error("Generic Constraint violation: {}", constraint);
                    return;
                }
            }
        }

        // verify user specific constraints
        for (Column column : table.getColumns()) {
            for (Constraint constraint : column.getConstraints()) {
                if (!constraint.isValid(record.get(column.getName()))) {
                    log.error("User Constraint violation: {}", constraint);
                    return;
                }
            }
        }

        db.addRecordToTable(tableName, record);
        log.info("{} record inserted successfully", tableName);
    }

    public List<Map<String, Object>> getAllRecords(String tableName) {
        return db.getAllRecords(tableName);
    }
}
