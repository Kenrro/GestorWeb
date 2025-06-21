import { useContext, useEffect, useState } from "react"
import "../stilos/register.css"
import IconoUser from "../iconos/IconoUser"
import axios from "axios"
import { useNavigate, useNavigation } from "react-router-dom"
import UserProvider, { UserContext } from "../../context/UserContext"

const Register = () => {
    const navigate = useNavigate()
    const {user, setUser} = useContext(UserContext)
    const url_usuarios = import.meta.env.VITE_API_USER
    const [passwordMatch, setpasswordMatch] = useState(true)
    const [regis, setRegis] = useState({
        userName: "",
        contrasena: "",
        contrasenaCon: "",
        nombre: "",
        apellido: ""
    })

    useEffect(()=> {
        setpasswordMatch(regis.contrasena === regis.contrasenaCon)
    }, [regis.contrasena, regis.contrasenaCon])

    const handleChange = (e) =>{
        const {name, value} = e.target
        setRegis(prev => ({
            ...prev,
            [name]: value
        }))
    }
    const handleSubmit = (e) => {
        e.preventDefault()
        try {
            axios.post(`${url_usuarios}`, {
                userName: regis.userName,
                contrasena: regis.contrasena,
                nombre: regis.nombre,
                apellido: regis.apellido
            })
                .then(data => {
                    setUser(data.data)
                    navigate("/")
                })

        } catch (e){
            console.error("Error al crear usuarios", e)
        }
    }
    return (
        <div className="wrap-content" id="wrap-register">
            <form 
            onSubmit={handleSubmit}>
            <div className="wrap-titulo-form">
                <IconoUser></IconoUser>
                <h2>Registrate</h2>
            </div>
            <FieldForm
            label="Username"
            type={"text"}
            name={"userName"}
            handleChange={handleChange}
            ></FieldForm>
            <FieldForm
            label="Nombre"
            type={"text"}
            name={"nombre"}
            handleChange={handleChange}
            ></FieldForm>
            <FieldForm
            label="Apellido"
            type={"text"}
            name={"apellido"}
            handleChange={handleChange}
            ></FieldForm>
            <FieldForm
            label="Contraseña"
            type={"password"}
            name={"contrasena"}
            handleChange={handleChange}
            error={!passwordMatch}
            ></FieldForm>
            <FieldForm
            label="Contraseña"
            type={"password"}
            name={"contrasenaCon"}
            error={!passwordMatch}
            handleChange={handleChange}
            ></FieldForm>
            <div className="wrap-boton-submit">
                <input 
                type="submit"
                className={"boton-submit "+(!passwordMatch ? "boton-submit-desactivado" : "")}
                disabled={!passwordMatch}></input>
            </div>
        </form>
        </div>
    )
}
export default Register

const FieldForm = ({label, type, name, handleChange, error}) => {
    return (
        <div className="field-form">
            <fieldset>
                <legend>{label}</legend>
                <input 
                type={type} 
                name={name} 
                onChange={handleChange}
                className={error ? "input-error" : ""}
                required/>
                
            </fieldset>
                
        </div>
    )
}