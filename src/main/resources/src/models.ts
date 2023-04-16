export interface IDBurl{
    url:string
}

export interface IColumn{
    name : string
    dataType: string
}

export interface ITable{
    name : string
    linksToTables : ITable[]
    columns : IColumn[]
    str?:number
    col?:number
}

