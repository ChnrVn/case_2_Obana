import axios from "axios";
import {IColumn, ITable} from "../models";
import {loadedTables} from "../data/tables";


interface loadedTable{
    name:string
    columns: IColumn[]
    linkedTables: string[]
}
// @ts-ignore
export async function loadTables() {



    let tbls = (await axios.get<loadedTable[]>('http://localhost:8080',
        {
            headers:
                {
                    'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
                }
        }
        )).data


    tbls.map(tbl => {
        let t:ITable = {
            name : tbl.name,
            columns : tbl.columns,
            linksToTables:[]
        }
        loadedTables.push(t)
    })

    tbls.map(tbl => {
        let curTabl = loadedTables.filter( t => t.name === tbl.name)[0]
            tbl.linkedTables.map(
                connectTableName => {
                    let links:ITable[] = loadedTables.filter( ltbls => ltbls.name === connectTableName)

                    links.forEach(l => curTabl.linksToTables.push(l))
                }
            )
        }
    )
    //tbls.tableMap.map( (t: string) => console.log(t))

}