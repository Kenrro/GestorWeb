import { Link } from "react-router-dom"
import "../stilos/tarjetausuario.css"
const TarjetaUsuario = ({id, name, contraseña}) => {
    return (
       <div className="TarjetaUsuario">
            <h2>{id}</h2>
            <span>{name}</span>
            <span>{contraseña}</span>
            <button><Link to={`/${id}`}>Tareas</Link></button>
       </div> 
    )
}

export default TarjetaUsuario
