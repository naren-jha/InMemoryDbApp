package com.njha.inmemorydb;

import com.njha.inmemorydb.schema.DataType;
import com.njha.inmemorydb.schema.Table;
import com.njha.inmemorydb.schema.constraint.Constraint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppDatabase {
    Map<String, Table> tables = new HashMap<>();
    private Map<DataType, List<Constraint>> defaultConstraints = new HashMap<>();
    private Map<String, List<Map<String, Object>> > tableData = new HashMap<>();
    // Map<String, Object> = one record in a table

    public void addTable(Table table) {
        tables.put(table.getName(), table);
    }

    public List<Table> getTables() {
        return tables.values().stream().toList();
    }

    public Table getTableByName(String name) {
        return tables.get(name);
    }

    public void addDefaultConstraint(DataType dataType, Constraint constraint) {
        List<Constraint> constraints = defaultConstraints.getOrDefault(dataType, new ArrayList<>());
        constraints.add(constraint);
        defaultConstraints.put(dataType, constraints);
    }

    public List<Constraint> getDefaultConstraintsByType(DataType dataType) {
        return defaultConstraints.get(dataType);
    }

    public void addRecordToTable(String tableName, Map<String, Object> record) {
        List<Map<String, Object>> records = tableData.getOrDefault(tableName, new ArrayList<>());
        records.add(record);
        tableData.put(tableName, records);
    }

    public List<Map<String, Object>> getAllRecords(String tableName) {
        return tableData.getOrDefault(tableName, new ArrayList<>());
    }
}
