package com.njha.inmemorydb.schema;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Database {
    private String name;
    private List<Table> tables;
}
