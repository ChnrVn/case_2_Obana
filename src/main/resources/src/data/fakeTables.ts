import {IColumn, ITable} from "../models";

let c1:IColumn = {
    name:"name",
    dataType:"text"
}

let c2:IColumn = {
    name:"surname",
    dataType:"text"
}

let t1:ITable = {
    name : "t1",
    linksToTables : [],
    columns : [c1, c2]
}

let c3:IColumn = {
    name:"age",
    dataType:"number"
}

let t2:ITable = {
    name : "t2",
    linksToTables : [t1],
    columns : [c3]
}

let t3:ITable = {
    name : "t3",
    linksToTables : [t1, t2],
    columns : [c3]
}

let t4:ITable = {
    name : "t4",
    linksToTables : [t1, t2],
    columns : [c3]
}

let t5:ITable = {
    name : "t5",
    linksToTables : [t4, t3],
    columns : [c3]
}

let t6:ITable = {
    name : "t6",
    linksToTables : [t3, t2],
    columns : [c3]
}

let t7:ITable = {
    name : "t7",
    linksToTables : [t3, t2, t6],
    columns : [c3]
}

export const testTable : ITable[] = [t1, t2, t3, t4, t5, t6, t7]