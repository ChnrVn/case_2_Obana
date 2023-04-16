import {ITable} from "../models";
import {TableColumn} from "./tableColumn";
import {Navigate} from "react-router-dom";
import axios from "axios";
import {useState} from "react";

interface ITableProps{
    table: ITable
}

export function Table({table} : ITableProps){
    const[value, setValue] = useState('')

    const formSubmitHandler = ( event:React.FormEvent<HTMLFormElement>) => {

    } //TODO

    const tblStyle = {margin:'10px', padding:'5px', border: '1px solid black', fontSize:'8px'}


    return (
        <form action={"http://localhost:8080"}
              method={"post"}
              style = {tblStyle}

              onSubmit={async (event) => {
                  event.preventDefault()
                  const form = document.getElementById(table.name)
                  // @ts-ignore
                  let formData = new FormData(form)

                  let args:string = ""
                  formData.forEach( (val, key) => {
                      let a:string = key + "=" + val.toString() + "&"
                      args += a
                  })

                  await axios.post('http://localhost:8080', args )
              }}

              id={table.name}>
            <table>
                <caption >{table.name}</caption>
                <thead>
                    <tr>
                        <th>Column name</th>
                        <th>Column type</th>
                        <th>Generate Settings</th>
                    </tr>
                </thead>
                <tbody>
                {
                    table.columns.map
                    (
                        column => <TableColumn col={column}
                                               table_name={table.name}
                                               key={column.name}/>
                    )
                }
                </tbody>
            </table>

            <input type={"number"}
                   className={"boarder py-2 px-4 mb-2 w-full"}
                   placeholder={"Enter number"}
                   value = {value}
                   name = {table.name + "-" + "ElementNumber"}
                   form={table.name}
                   onChange = { event => setValue(event.target.value) }
            />

            <button type={"submit"} className={"py-2 origin-center px-4 border bg-blue-400 hover:text-white"}>Submit</button>
        </form>
    )
}