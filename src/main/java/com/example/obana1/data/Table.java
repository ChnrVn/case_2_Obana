package com.example.obana1.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Element of Database
 */
public class Table {
    private String name;
    private List<Column> columns;
    private List<Table> linkedTables;

    public Table(String name) {
        this.name = name;
        columns = new ArrayList<>();
        linkedTables = new ArrayList<>();
    }

    /**
     * Adds columns at table
     * @param column to add
     */
    public void addColumn(Column column){
        columns.add(column);
    }

    /**
     * Adds constrain for current table
     * @param table to add as a linked table to current
     */
    public void addLink(Table table){
        linkedTables.add(table);
    }

    /**
     * @return name of current table
     */
    public String getName() {
        return this.name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<String> getLinkedTables() {
        List<String> linkedTablesNames = new ArrayList<>();
        for (Table table: linkedTables) {
            linkedTablesNames.add(table.getName());
        }
        return linkedTablesNames;
    }



    @Override
    public String toString() {
        String result = "Table: " + this.name + "\nColumns: [ ";
        for (Column column: columns) {
            result += column.toString();
        }
        result += "]\n";

        if (linkedTables.size() > 0) {
            result += "Linked Tables: [ ";
            for (Table table: linkedTables) {
                result += table.getName() + " ";
            }
        } else return result + "No linked tables";

        return result + " ]";
    }

}
