import { useContext, useEffect, useState } from "react"
import axios from "axios"
import { UserContext } from "../../context/UserContext"
import IconoBasurero from "../iconos/IconoBasurero"

const PerfilUsuario = () => {
    const {user, setUser} = useContext(UserContext)
    const url_usuarios = import.meta.env.VITE_API_USER
    //const [usuario, setUsuario] = useState([])
    /*useEffect(()=> {
        axios.get(`${url_usuarios}/${id}`)
            .then((data) =>{
                setUsuario(data.data)
            })
    }, [id])*/
    const eliminarUsuario = () => {
        axios.delete(`${url_usuarios}/${user.id}`)
            .then((result) => {
                setUser({
                    id: "",
                    userName: "",
                    nombre: "",
                    apellido: "",
                    fechCreacion: ""
                })
            }).catch((err) => {
                
            });
    }
    if (!user || !user.userName) {
        return <div>Cargando datos del usuario...</div>
    }
    return (
        <div className="Wrap-info-usuario">
            <h2>{user.userName}</h2>
            <span><strong>Nombre:</strong> {user.nombre}</span>
            <span><strong>Apellido:</strong> {user.apellido}</span>
            <span><strong>Fecha creacion:</strong> {user.fechCreacion}</span>
            <p><strong>id:</strong>{user.id}</p>
            <button onClick={eliminarUsuario}><IconoBasurero></IconoBasurero></button>
        </div>
    )
}
export default PerfilUsuario