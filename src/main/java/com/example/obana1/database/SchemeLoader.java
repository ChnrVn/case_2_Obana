package com.example.obana1.database;

import com.example.obana1.data.Column;
import com.example.obana1.data.Table;
import com.example.obana1.data.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Class for loading scheme from database
 */
@Component
public class SchemeLoader {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public SchemeLoader(DataSource postgreSqlDataSource){
        this.jdbcTemplate = new JdbcTemplate(postgreSqlDataSource);
    }

    /**
            * Splits constraint on parts for recognizing
     * @param constraintName - full name of constraint (template: table_column_type)
     * @return name of linked table
     */
    public String parseLinkedTable(Tables tables, String constraintName) {

        int i = 0;

        String[] partsOfConstraint = constraintName.split("_");
        String nameOfTable = partsOfConstraint[i++];

        while (!tables.isContainsByName(nameOfTable) && i <= partsOfConstraint.length) {
            nameOfTable += "_" + partsOfConstraint[i++];
        }

        return nameOfTable;
    }

    /**
     * Operates queries to databases for defining tables and loads to map
     */
    public Tables readScheme() {
        Tables tables = new Tables();

        //First query for loading tables
        jdbcTemplate.query("SELECT TABLE_NAME as table_name " +
                        "FROM INFORMATION_SCHEMA.TABLES " +
                        "WHERE table_schema = 'bookings' AND Table_Type = 'BASE TABLE'" +
                        "ORDER BY TABLE_NAME;"
                ,
                (rs) -> {
                    Table table = new Table(rs.getString("table_name"));
                    tables.addTable(table);
                });

        //Second query for loading columns to tables
        for (Table table: tables.getTableMap().values()) {
            jdbcTemplate.query("SELECT COLUMN_NAME as column_name, " +
                            "DATA_TYPE as data_type " +
                            "FROM INFORMATION_SCHEMA.COLUMNS " +
                            "WHERE table_schema = 'bookings' AND table_name = '" + table.getName() + "'" +
                            "ORDER BY TABLE_NAME;"
                    ,
                    (rs) -> {
                        Column column = new Column(rs.getString("column_name"), rs.getString("data_type"));
                        table.addColumn(column);
                    });
        }

        //Third query for loading constraints for tables
        jdbcTemplate.query("SELECT * " +
                        "FROM  information_schema.constraint_column_usage " +
                        "WHERE table_schema = 'bookings' ;"
                ,
                (rs) -> {
                    String constraintName = rs.getString(7); //Тип связи в формате table_column_type

                    if (constraintName.contains("fkey")) {
                        String definedName = parseLinkedTable(tables, constraintName);

                        Table linkedTable = tables.getTableByName(definedName);
                        tables.getTableByName(rs.getString(3)).addLink(linkedTable);
                    }

                });

        //tables.printTableMap(); //Debugging
        return tables;
    }
}
