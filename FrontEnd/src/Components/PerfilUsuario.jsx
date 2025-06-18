import { useEffect, useState } from "react"
import axios from "axios"

const PerfilUsuario = ({id}) => {
    
    const url_usuarios = import.meta.env.VITE_API_USER
    const [usuario, setUsuario] = useState([])
    useEffect(()=> {
        axios.get(`${url_usuarios}/${id}`)
            .then((data) =>{
                setUsuario(data.data)
            })
    }, [id])
    return (
        <div className="Wrap-info-usuario">
            <h2>{usuario.userName}</h2>
            <span><strong>Nombre:</strong> {usuario.nombre}</span>
            <span><strong>Apellido:</strong> {usuario.apellido}</span>
            <span><strong>Fecha creacion:</strong> {usuario.fechCreacion}</span>
            <p><strong>id:</strong>{usuario.id}</p>
        </div>
    )
}
export default PerfilUsuario