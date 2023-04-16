package com.example.obana1.data;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for operating all tables in dataset
 */
@Component
public class Tables {

    private Map<String, Table> tableMap = new HashMap<>();

    /**
     * @return current map
     */
    public Map<String, Table> getTableMap() {
        return this.tableMap;
    }

    /**
     * Adds element in map
     * @param table to add
     */
    public void addTable(Table table) {
        //TODO: Validation
        this.tableMap.put(table.getName(), table);
    }

    /**
     * @param name of tabel
     * @return table by name
     */
    public Table getTableByName(String name) {
        return tableMap.get(name);
    }

    /**
     * Checking if an element exists by name
     * @param name of element
     * @return true if element already exists
     */
    public boolean isContainsByName(String name) {
        return (tableMap.containsKey(name));
    }


}
