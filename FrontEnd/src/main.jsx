import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import UsuariosTareas from './Components/Tarea/UsuariosTareas.jsx'
import Login from "./Components/Login/Login.jsx"
import UserProvider from './context/UserContext.jsx'
import LoginAndRegister from './Components/Login/LoginAndRegister.jsx'
import Register from './Components/Login/Register'
//  <Route index element={<Usuarios/>}/>
createRoot(document.getElementById('root')).render(
  
  <>
    <BrowserRouter>
      <UserProvider>
        <Routes>
            <Route path='/' element={<App/>}>
              <Route index element={<UsuariosTareas></UsuariosTareas>}></Route>
            </Route>
            <Route path='/user' element={<LoginAndRegister></LoginAndRegister>}>
              <Route path='login' element={<Login/>}/>
              <Route path='register' element={<Register></Register>}/>
            </Route>
        </Routes>
      </UserProvider>
    </BrowserRouter>
  </>
  
)
