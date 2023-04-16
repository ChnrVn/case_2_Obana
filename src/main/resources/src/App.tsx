import {createElement as crEl, useContext, useEffect, useState} from 'react';
import {Route, Routes} from 'react-router-dom'
import {RegPage} from "./pages/RegPage";
import {MainPage} from "./pages/mainPage";



function App() {

    return (
        <>
            <Routes>
                <Route path={"/"} element={ <RegPage /> } />
                <Route path={"/mainPage"} element={ <MainPage /> } />
            </Routes>
        </>
    )
}
//функция Product вставляется как тег

export default App;














// Так можно писать с помощью синтаксиса js
//const [count, setCount] = useState(0) //cостояние
// return crEl('div', {className: 'container'}, [
//     crEl('h1', {className: 'font-bold', key:1},
//         `Test jsx ${count}`), // обратные кавычки для шаблонизации
//
//     crEl('button', {className: 'py-2 px-4 boarder',
//         key:2, //нужно для таилвайнд для детей
//         onClick: () => setCount(count + 1)
//     }, 'Click me !')
//
// ])