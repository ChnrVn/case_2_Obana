import {useContext, useState} from 'react';
import {ITable} from "../models";
import {Table} from "../components/Table";
import {loadedTables} from "../data/tables";
import {ArcherContainer, ArcherElement} from "react-archer";
import {AnchorPositionType, RelationType} from "react-archer/lib/types";

interface MainPageProps{
    tables: ITable[]
}

interface coord{
    x:number
    y:number
}

export function MainPage(){
    const divStyle = {margin:'30px',display:'flex', justifyContent:'space-between '}
    const scrStyle = {height:'700px', margin:'10px'}

    const getStrColNums = () => {
        let els = loadedTables.length
        let cols = Math.ceil(
            Math.sqrt(els * 16 / 9)
        )

        let strs =  Math.ceil(
            els / cols
        )

        return {cols, strs}
    }
    const getRelation = (tab:ITable) => {
        let relat: RelationType[] = tab.linksToTables.map(
            t => {
                // @ts-ignore
                let ySub:number = tab.str - t.str
                let tarAnch:AnchorPositionType;
                let sourceAnch:AnchorPositionType;

                if(ySub === 0){
                    tarAnch = 'top'
                    sourceAnch = 'top'
                }
                else if(ySub > 0){
                    tarAnch = 'bottom'
                    sourceAnch = 'top'
                }
                else{
                    tarAnch = 'top'
                    sourceAnch = 'bottom'
                }

                // @ts-ignore
                let color1:number =  (tab.str * 8)  % 16
                // @ts-ignore
                let color2:number =  (tab.col * 8) % 16


                let color:string = '#'
                    + color1.toString(16)
                    + color2.toString(16)
                    + color2.toString(16)
                    + color1.toString(16)
                    + color2.toString(16)
                    + color2.toString(16)

                console.log(color)
                let r: RelationType = {
                    targetId: t.name,
                    targetAnchor: tarAnch,
                    sourceAnchor: sourceAnch,
                    style: {strokeColor:  color,
                        strokeDasharray : '2.5'},
                }

                //console.log(r)

                return r
            }
        )

        return relat
    }

    const splitTables = (cols:number, strs:number) => {
        console.log( " " + cols + " " +strs )
        let res:ITable[][] = []

        for(var i:number = 0; i < strs; i++){
            if(i * cols + cols < loadedTables.length)
                res.push(loadedTables.slice(i * cols, i + cols))
            else if(i * cols < loadedTables.length)
                res.push(loadedTables.slice(i * cols, loadedTables.length))
            else
                break
        }

        for(var i:number = 0; i < res.length; i++){
            let len:number = res[i].length
            for(var j:number = 0; j < len; j++){
                res[i][j].str = i
                res[i][j].col = j
            }
        }
        return res
    }

    const {cols, strs} = getStrColNums()
    var splittedTables = splitTables(cols, strs)

    return (
        <div style={scrStyle}>

                <ArcherContainer strokeColor={"green"} strokeWidth={1} offset={6} noCurves={true}>
                    {
                        splittedTables.map(strs =>
                            <div style={ divStyle }>

                                { strs.map(
                                    tab =>
                                        <ArcherElement
                                            id={tab.name}
                                            relations={getRelation(tab)}
                                            key={tab.name}
                                        >
                                            <div style = {divStyle}>
                                                <Table table={tab} key={tab.name}/>
                                            </div>

                                        </ArcherElement>
                                )
                                }
                            </div>
                        )

                    }
                </ArcherContainer>
        </div>
    )
}