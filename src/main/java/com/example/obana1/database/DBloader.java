package com.example.obana1.database;

import com.example.obana1.data.Column;
import com.example.obana1.data.Table;
import com.example.obana1.data.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Component
public class DBloader {
    JdbcTemplate jdbcTemplate;
    SchemeLoader schemeLoader;
    @Autowired
    public DBloader(DataSource postgreSqlDataSource){
        this.jdbcTemplate = new JdbcTemplate(postgreSqlDataSource);
        this.schemeLoader = new SchemeLoader(postgreSqlDataSource);
    }

    public void fillWithSinthetic(Map<String, List<Integer>> dataMap,
                                  String table_name){

       /* Table tab = schemeLoader.readScheme()
                .getTableMap().get(table_name);

        List<Column> columns = tab.getColumns();

        Map<String, String> columnDatatype = new HashMap<>();


        dataMap.entrySet().forEach(
                (entry) -> {
                    Optional<String> dataOpt = columns.stream()
                            .filter( (c) -> c.getName() == entry.getKey())
                            .map(Column::getDataType)
                            .findAny();

                    String datatype = dataOpt.get();

                    columnDatatype.put(entry.getKey(), datatype);
                }
        );
        */

        int len = dataMap.values().stream()
                .findAny().get().size();


        for(int i = 0; i < len; i++){
            Map<String, Integer> colValue = new HashMap<>();

            int finalI = i;
            dataMap.entrySet().forEach(
                    entry ->
                    colValue.put(entry.getKey(), entry.getValue().get(finalI))
            );

            StringBuilder statementBuild = new StringBuilder();
            statementBuild.append("INSERT INTO " + table_name + " (");

            StringBuilder coloms = new StringBuilder();
            List<Integer> args = new ArrayList<>();

            colValue.entrySet().forEach( e -> {
                        coloms.append(e.getKey() +  ",");
                        args.add(e.getValue());
                    }
            );

            statementBuild.append(coloms);
            statementBuild.deleteCharAt(statementBuild.length() - 1);
            statementBuild.append(") values (");
            for(int a = 0; a <  dataMap.size(); a++ )      {
                statementBuild.append("?,");
            }
            statementBuild.deleteCharAt(statementBuild.length() - 1);
            statementBuild.append(")");

            System.out.println(statementBuild.toString());

            Integer[] resargs = new Integer[dataMap.size()];
            resargs = args.toArray(resargs);

            jdbcTemplate.update(statementBuild.toString(), resargs);
        }
    }
}
