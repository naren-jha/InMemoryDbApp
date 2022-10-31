package com.njha.inmemorydb;

import com.njha.inmemorydb.data.DataService;
import com.njha.inmemorydb.schema.Column;
import com.njha.inmemorydb.schema.DataType;
import com.njha.inmemorydb.schema.SchemaService;
import com.njha.inmemorydb.schema.Table;
import com.njha.inmemorydb.schema.constraint.Constraint;
import com.njha.inmemorydb.schema.constraint.strategy.factory.ConstraintFactory;
import com.njha.inmemorydb.schema.constraint.strategy.factory.ConstraintType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
@Slf4j
public class InMemoryDbApplication {

	@Autowired
	private SchemaService schemaService;

	@Autowired
	private DataService dataService;

	@Autowired
	private ConstraintFactory constraintFactory;

	@Value("${constraint.default.value.integer.range.lower}")
	private Integer intLrVal;

	@Value("${constraint.default.value.integer.range.upper}")
	private Integer intUrVal;

	@Value("${constraint.default.value.string.maxsize}")
	private Integer strSizeVal;

	public static void main(String[] args) {
		SpringApplication.run(InMemoryDbApplication.class, args);
	}

	@PostConstruct
	public void start() {
		schemaService.addDefaultConstraint(DataType.INTEGER, constraintFactory.getConstraint(ConstraintType.INT_RANGE, Arrays.asList(intLrVal, intUrVal)));
		schemaService.addDefaultConstraint(DataType.STRING, constraintFactory.getConstraint(ConstraintType.TEXT_SIZE, Arrays.asList(strSizeVal)));

		List<Column> columns = new ArrayList<>();
		columns.add(Column.builder().name("id").dataType(DataType.INTEGER).constraints(new ArrayList<>()).isMandatory(true).build());
		columns.add(Column.builder().name("name").dataType(DataType.STRING).constraints(new ArrayList<>()).isMandatory(true).build());

		Constraint studentAgeValueConstraint = constraintFactory.getConstraint(ConstraintType.INT_RANGE, Arrays.asList(0, 100));
		List<Constraint> studentAgeValueConstraints = new ArrayList<>();
		studentAgeValueConstraints.add(studentAgeValueConstraint);
		columns.add(Column.builder().name("age").dataType(DataType.INTEGER).constraints(studentAgeValueConstraints).isMandatory(false).build());

		Table table = Table.builder()
				.name("student")
				.columns(columns)
				.build();

		schemaService.createTable(table);

		log.info(schemaService.getTableByName("student").toString());

		Map<String, Object> record = new LinkedHashMap<>();
		record.put("id", 0);
		record.put("name", "rakesh");
		record.put("age", 23);
		dataService.addRecordToTable("student", record);

		record = new LinkedHashMap<>();
		record.put("id", 1);
		record.put("name", "mohan");
		record.put("age", 33);
		dataService.addRecordToTable("student", record);

		record = new LinkedHashMap<>();
		record.put("id", 2);
		record.put("name", "sunil");
		record.put("age", 1500);
		dataService.addRecordToTable("student", record);

		record = new LinkedHashMap<>();
		record.put("id", 3);
		record.put("name", "ajay");
		record.put("age", 150);
		dataService.addRecordToTable("student", record);

		record = new LinkedHashMap<>();
		record.put("id", 3);
		record.put("name", "ajay1234567890123456");
		record.put("age", 100);
		dataService.addRecordToTable("student", record);

		record = new LinkedHashMap<>();
		record.put("id", 3);
		record.put("name", "ajay12345678901234567");
		record.put("age", 100);
		dataService.addRecordToTable("student", record);

		List<Map<String, Object>> students = dataService.getAllRecords("student");
		for (Map<String, Object> sRecord : students) {
			log.info(sRecord.toString());
		}

	}
}
