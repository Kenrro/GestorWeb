import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Usuarios from './Components/Usuarios.jsx'
import UsuariosTareas from './Components/UsuariosTareas.jsx'

createRoot(document.getElementById('root')).render(
  
  <>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<App/>}>
          <Route index element={<Usuarios/>}/>
          <Route path=':id' element={<UsuariosTareas></UsuariosTareas>}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  </>
  
)
