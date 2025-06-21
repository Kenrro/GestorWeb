import "../stilos/usuariostareas.css"
import axios from "axios"
import { useContext, useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import PerfilUsuario from "../Usuario/PerfilUsuario"
import TarjetaTarea from "./TarjetaTarea"
import IconoMas from "../iconos/IconoMas"
import TarjetaNuevaTarea from "./TarjetaNuevaTarea"
import ContenedorAgregarTarea from "./ContenedorAgregarTarea"
import UserProvider, { UserContext } from "../../context/UserContext"

const Usuariosts = () =>{
    const {user, setUser} = useContext(UserContext)
    const [tareas, setTareas] = useState([])
    const [estadisticas, setestadisticas] = useState({
        total: 0,
        pendientes: 0,
        terminadas: 0

    })

    const url_usuarios = import.meta.env.VITE_API_USER
    const url_tarea = import.meta.env.VITE_API_TAREA 

    const obtenerTareas = async () => {
        try{
            const response = await axios.get(`${url_usuarios}/${user.id}/tareas`)
            setTareas(response.data)
        } catch(e) {
            console.log("Error al cargar las tareas", e)
        }
    }
    useEffect(()=> {
        const total = tareas.length
        const pendientes = tareas.filter((tarea) => tarea.estado === false).length
        const terminadas = total-pendientes
        setestadisticas({total, pendientes, terminadas})
    },[tareas])

    useEffect(()=> {
        obtenerTareas()
    }, [])

    

    const cambiarEstado = async (tareaModificada) => {
        try{
            await axios.put(`${url_tarea}`, tareaModificada) 
            setTareas(prev =>
                prev.map(t => tareaModificada.id === t.id ? tareaModificada : t))
        } catch(e) {
            console.error("Error al cambiar estado:", e)
            
        }
    }
    const eliminarTarea = async (tareaEliminar) => {
            try{
                await axios.delete(`${url_tarea}/${tareaEliminar.id}`)
                obtenerTareas()
            } catch(e){
                console.error("Error al eliminar tareas", e)
            }
        }
    return (
        <div id="wrap-content">
            <div id="wrap-informacion">
                <PerfilUsuario></PerfilUsuario>
            </div>
            <div id="wrap-tareas">
                <div id="wrap-encabezado-tareas">
                    <h2>Lista de tareas</h2>
                <div className="wrap-contador-tareas">
                    <span>{estadisticas.count} elementos</span>
                <span>Pendientes: {estadisticas.pendientes} - Terminadas: {estadisticas.terminadas}</span>
                </div>
                </div>
                <ContenedorAgregarTarea
                recargarTareas={obtenerTareas}/>
                {tareas.map((t) => (
                    <TarjetaTarea
                    tarea={t}
                    cambiarEstado = {cambiarEstado}
                    eliminarTarea = {eliminarTarea}
                    ></TarjetaTarea>
                ))}
            </div>
        </div>
    )
}

export default Usuariosts