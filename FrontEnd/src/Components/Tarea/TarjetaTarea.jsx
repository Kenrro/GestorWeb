import { useEffect, useState } from "react"

const TarjetaTarea = ({tarea, cambiarEstado, eliminarTarea}) => {
    const [tareaForm, setTareaForm] = useState({...tarea});

    useEffect(()=>{
        setTareaForm({...tarea})
    }, [tarea])

    const handleChange = (e)=>{
        const {name, value, type, checked} = e.target;
        setTareaForm(prev => ({
            ...prev,
            [name]: type ==="checkbox"? checked : value
        }))
    }

    const handleSubmit = (e) => {
        e.preventDefault();
         const accion = e.nativeEvent.submitter?.value;
        if(accion ==="Cambiar") cambiarEstado(tareaForm)
        else if (accion === "Eliminar") eliminarTarea(tareaForm)
        
    }

    return (
        <form
        onSubmit={handleSubmit}
         className={`tarjeta-tarea ${tarea.estado ? "tarjeta-tarea-terminada" : "tarjeta-tarea-pendiente"}`}>
            <ComponenteTexto
            id={`nombre-${tarea.id}`}
            label="Nombre"
            name="nombre"
            value={tareaForm.nombre} 
            onChange={handleChange}
            />
            <ComponenteTextArea
            id={`descripcion-${tarea.id}`}
            label="Descripcion"
            name="descripcion"
            value={tareaForm.descripcion}
            onchange={handleChange}/>

            <div className="form-field">
                <label htmlFor={`estado-${tarea.id}`}><strong>Terminada:</strong></label>
                <input 
                name="estado"
                id={`estado-${tarea.id}`}
                type="checkbox"
                checked={tareaForm.estado}
                onChange={handleChange}/>
            </div>
            <div className="form-field-enviar">
                <input className="from-submit" type="submit" value="Cambiar"/>
                <div></div>
                <input className="from-submit" type="submit" value="Eliminar"/>
            </div>
        </form>
    )
}

// Componentes
const ComponenteTexto = ({id, label, name, value, onChange}) => {
    return (
        <div className="form-field">
            <label htmlFor={id}><strong>{label}: </strong></label>
            <input 
            name={name}
            id={id} 
            type="text"
            value={value} 
            onChange={onChange}/>
            
        </div>
    )
}
const ComponenteTextArea = ({id, label, name, value, onchange}) => {
    return (
        <div className="form-field">
                <label htmlFor={id}><strong>{label}: </strong></label>
                <textarea 
                name={name}
                id={id}
                value={value}
                onChange={onchange}
                />
                
            </div>
    )
}


export default TarjetaTarea