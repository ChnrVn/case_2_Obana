import {useContext, useState} from 'react';
import {ErrorMsg} from "../components/ErrorMsg";
import axios from "axios/index";
import {IDBurl, ITable} from "../models";
import { Navigate } from 'react-router-dom';
import {testTable} from "../data/fakeTables";
import {loadedTables} from "../data/tables";
import {loadTables} from "../network/tableLoader";

interface RegPageProps{
    tables: ITable[]
}
export function RegPage(){
    const[dbAddr, setdbAddr] = useState('')
    const[error, setError] = useState('')
    const[loading, setLoading] = useState('')
    const[loaded, setLoaded] = useState(false)


    // @ts-ignore
    const submitHandler = async (event: React.FormEvent) => {
        event.preventDefault()
        setError('')
        setLoading('Loading')

        //const resp = await axios.post<IDBurl, ITable[]>('http://localhost:8080', dbAddr)

        /*const resp = testTable
        resp.map(tbl => loadedTables.push(tbl))*/
        /*if (value.trim().length == 0) {
            setError('No such DB')
            return
        }*/
        await loadTables()


        setLoaded(true)
        setLoading('')

    }

    return (


    <>
        <div className={"fixed bg-black/50 top-0 right-0 left-0 bottom-0"}></div>

        <div className={"w-[500px] p-5 rounded bg-white absolute top-10 left-1/2 -translate-x-1/2"}>
            <h1 className={"text-2xl text-center mb-2"}> Введите url базы данных </h1>

            {!loading &&
                <form onSubmit={submitHandler}>
                    <input type={"text"}
                           className={"boarder py-2 px-4 mb-2 w-full"}
                           placeholder={"Enter DB URL"}
                           value = {dbAddr}
                           onChange = { event => setdbAddr(event.target.value) }
                    />

                    {error && <ErrorMsg error={error} />}

                    <button type={"submit"} className={"py-2 px-4 border bg-yellow-400 hover:text-white"}>Log in</button>
                </form>
            }

            {loading && <h1> Загрузка... </h1>}
            {loaded && <Navigate to={"/mainPage"}/>}
        </div>
    </>

    )
}
