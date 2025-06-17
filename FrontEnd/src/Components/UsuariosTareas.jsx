import "./stilos/usuariostareas.css"
import axios from "axios"
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import PerfilUsuario from "./PerfilUsuario"
import TarjetaTarea from "./tarjetatarea"

const UsuariosTareas = () =>{
    
    const url_usuarios = import.meta.env.VITE_API_USER
    const [tarea, setTarea] = useState([])
    const [usuario, setUsuario] = useState()
    
    const params = useParams()

    useEffect(()=> {
        axios.get(`${url_usuarios}/${params.id}/tareas`)
            .then((data)=>{
                setTarea(data.data)
                
            })
    },[params.id])
    

    return (
        <div id="wrap-content">
            <div id="wrap-informacion">
                <PerfilUsuario
                id={params.id}></PerfilUsuario>
            </div>
            <div id="wrap-tareas">
                <h2>Lista de tareas</h2>
                <span>{Object.keys(tarea).length} elementos</span>
                {tarea.map((tarea) => (
                    <TarjetaTarea 
                    tarea={tarea}
                    ></TarjetaTarea>
                ))}
            </div>
        </div>
    )
}

export default UsuariosTareas