import { useEffect } from "react"

const TarjetaTarea = ({tarea, cambiarEstado}) => {
    useEffect(()=>{
        console.log(tarea)
    })
    return (
        <div className={`tarjeta-tarea ${tarea.estado ? "tarjeta-tarea-terminada" : "tarjeta-tarea-pendiente"}`}>
            <h3><strong>Nombre:</strong> {tarea.nombre}</h3>
            <p><strong>Descripción:</strong> {tarea.descripcion}</p>
            <button onClick={() => cambiarEstado(tarea)}>Cambiar</button>
        </div>
    )
}
export default TarjetaTarea