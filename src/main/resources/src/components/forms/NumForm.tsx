import {useContext, useState} from 'react';
import {ErrorMsg} from "../ErrorMsg";

interface IFormChooserProps{
    col_type : string
    col_name : string
    table_name : string
}

export function NumForm(props : IFormChooserProps){
    const [error, setError] = useState(' ')
    const[value, setValue] = useState('')

    return(
        <>
            <p>
                <select
                    name= {props.table_name + "-" +props.col_name + "_mode" }>
                    <option value={"Random"}>Rand</option>
                    <option value={"Pattern"}>Pattern</option>
                </select>
            </p>
                <input type={"number"}
                       className={"boarder py-2 px-4 mb-2 w-full"}
                       placeholder={"Enter number"}
                       value = {value}
                       name = {props.table_name + "-" + props.col_name}
                       form={props.table_name}
                       onChange = { event => setValue(event.target.value) }
                />

            {error && <ErrorMsg error={error} />}
        </>
    )
}