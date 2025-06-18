import "./stilos/usuariostareas.css"
import axios from "axios"
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import PerfilUsuario from "./PerfilUsuario"
import TarjetaTarea from "./TarjetaTarea"

const Usuariosts = () =>{
    
    const url_usuarios = import.meta.env.VITE_API_USER
    const url_tarea = import.meta.env.VITE_API_TAREA 
    
    const [tarea, setTarea] = useState([])
    const [usuario, setUsuario] = useState()
    
    const params = useParams()

    useEffect(()=> {
        axios.get(`${url_usuarios}/${params.id}/tareas`)
            .then((data)=>{
                setTarea(data.data)
                
            })
    },[params.id])

    async function cambiarEstadoConsulta(tarea) {
        console.log(tarea)
        try{
            await axios.put(`${url_tarea}`, tarea) 
            return true
        } catch(e) {
            console.error("Error al cambiar estado:", e)
            return false
        }
    }
    async function cambiarEstado(tareaModificada){
        const exito = await cambiarEstadoConsulta(tareaModificada)
        if(!exito) return
        setTarea(prevTareas =>
        prevTareas.map(t =>
            t.id === tareaModificada.id ? { ...t, ...tareaModificada } : t
        )
    );
    }

    return (
        <div id="wrap-content">
            <div id="wrap-informacion">
                <PerfilUsuario
                id={params.id}></PerfilUsuario>
            </div>
            <div id="wrap-tareas">
                <div id="wrap-encabezado-tareas">
                    <h2>Lista de tareas</h2>
                <span>{Object.keys(tarea).length} elementos</span>
                </div>
                
                {tarea.map((t) => (
                    <TarjetaTarea
                    tarea={t}
                    cambiarEstado = {cambiarEstado}
                    ></TarjetaTarea>
                ))}
            </div>
        </div>
    )
}

export default Usuariosts