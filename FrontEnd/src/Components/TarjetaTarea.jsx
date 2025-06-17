import { useEffect } from "react"

const TarjetaTarea = ({tarea}) => {
    useEffect(()=>{
        console.log(tarea)
    })
    return (
        <div className={`tarjeta-tarea ${tarea.estado ? "tarjeta-tarea-terminada" : "tarjeta-tarea-pendiente"}`}>
            <h3>{tarea.id}</h3>
            <p>{tarea.descripcion}</p>
            <span>{tarea.estado}</span>
            <button>Cambiar</button>
        </div>
    )
}
export default TarjetaTarea