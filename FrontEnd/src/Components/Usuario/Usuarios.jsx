import "../stilos/usuarios.css"
import { useContext, useEffect, useState } from "react"
import axios from 'axios';
import TarjetaUsuario from "./TarjetaUsuario";
import {UserContext} from "../../context/UserContext"

const Usuarios = () => {
//const {user, setUser} = useContext(UserContext)
const url = import.meta.env.VITE_API_USER
const [state, setState] = useState()

useEffect(()=>{
    console.log(url)
    axios.get(url)
        .then((data)=>{
            setState(data.data)
        })
        .catch((e) => {
            console.error(e)
        })
},[])
    if(!state) return <h1>Cargando...</h1>
    
    return (
        <div className="wrap-usuarios">
            <h2>lista de usuarios</h2>
            <div id="wrap-tarjetas">
                {state.map((usuario)=>(
                    <TarjetaUsuario
                    id={usuario.id}
                    name={usuario.userName}
                    contraseña={usuario.contrasena}
                    />
                ))}
            </div>
        </div>
    )
}

export default Usuarios