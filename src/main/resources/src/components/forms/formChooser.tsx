import {useContext, useState} from 'react';
import {IColumn} from "../../models";
import {NumForm} from "./NumForm";
import {StrForm} from "./StrForm";

interface IFormChooserProps{
    col_type : string
    col_name : string
    table_name : string
}

export function FormChooser(props : IFormChooserProps){
    //switch для типов данных, возвращает jsx нужной формы
    let form : JSX.Element = (<NumForm col_name={props.col_name}
                                       col_type={props.col_type}
                                       table_name={props.table_name}
    />);

    switch (props.col_type){
        case "number":
            form = (<NumForm col_name={props.col_name}
                             col_type={props.col_type}
                             table_name={props.table_name}
            />)
            //console.log(props.col_type+props.table_name)
            break
        default:
            /*form = (<StrForm col_name={props.col_name}
                             col_type={props.col_type}
                             table_name={props.table_name}
            />)*/
            //console.log(props.col_type+props.table_name)
            break
    }
    return form
}