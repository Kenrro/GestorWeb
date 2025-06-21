import axios from "axios";
import { useContext, useEffect, useState } from "react"
import UserProvider, { UserContext } from "../../context/UserContext"
const TarjetaNuevaTarea = ({recargarTareas})=>{

    const {user, setUser} = useContext(UserContext) 
    const [tarea, setTarea] = useState(
        {
        id_usuario: user.id,
        nombre: "",
        descripcion: "",
        fech_limit: null
    }
    )
    const url_tarea = import.meta.env.VITE_API_TAREA 
    const handleChange = (e) =>{
        const {name, value, type, checked} = e.target;
        setTarea(prev => ({
            ...prev,
            [name]: type ==="checkbox"? checked : value

        }))
    }
    async function handleSubmit(e) {
        e.preventDefault();
        try {
            await axios.post(url_tarea, tarea)
                .then((data)=>{
                    recargarTareas()
                })
        }
        catch(e) {
            console.error(e)
        }
        

    }
        return (
        <form
        onSubmit={handleSubmit}
        id="tarjeta-crear-tarea"
         className={`tarjeta-tarea`}>
            <ComponenteTexto
            id={`nombre-${tarea.id}`}
            label="Nombre"
            name="nombre"
            onchange={handleChange}
            />
            <ComponenteTextArea
            id={`descripcion-${tarea.id}`}
            label="Descripcion"
            name="descripcion"
            onchange={handleChange}
            />
            <div className="form-field">
                <input className="from-submit" type="submit" value="Crear"/>
            </div>
        </form>
    )
}
export default TarjetaNuevaTarea
// Componentes
const ComponenteTexto = ({id, label, name, value, onchange}) => {
    return (
        <div className="form-field">
            <label htmlFor={id}><strong>{label}: </strong></label>
            <input 
            name={name}
            id={id} 
            type="text"
            value={value} 
            required
            onChange={onchange}/>
            
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
                required
                onChange={onchange}
                />
                
            </div>
    )
}