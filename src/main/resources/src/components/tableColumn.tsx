import {useContext, useState} from 'react';
import {IColumn, ITable} from "../models";
import {FormChooser} from "./forms/formChooser";

interface ITableColumnProps{
    col : IColumn
    table_name: string
}
export function TableColumn({col, table_name} : ITableColumnProps){

    return (
        <tr>
            <td>
                { col.name }
            </td>
            <td>
                { col.dataType }
            </td>
            <td>
                <FormChooser col_type={col.dataType}
                             col_name={col.name}
                             table_name={table_name}
                />
            </td>
        </tr>
    )
}