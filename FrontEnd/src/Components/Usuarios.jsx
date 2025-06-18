import "./stilos/usuarios.css"
import { useEffect, useState } from "react"
import axios from 'axios';
import TarjetaUsuario from "./TarjetaUsuario";

const Usuarios = () => {
const url = import.meta.env.VITE_API_USER
const [state, setState] = useState()

useEffect(()=>{
    console.log(url)
    axios.get(url)
        .then((data)=>{
            setState(data.data)
            console.log(state)
        })
        .catch((e) => {
            console.log(e)
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