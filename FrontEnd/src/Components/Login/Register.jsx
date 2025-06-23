import { useContext, useEffect, useState } from "react"
import IconoUser from "../iconos/IconoUser"
import axios from "axios"
import { useNavigate, useNavigation } from "react-router-dom"
import UserProvider, { UserContext } from "../../context/UserContext"
import FormInit from "./FormInit"
import {verificarUsername, verificarNombreApellido, VerificarContrasena} from "../../services/VerificarCampos.js"

const Register = () => {
    const navigate = useNavigate()
    const {user, setUser} = useContext(UserContext)
    const url_usuarios = import.meta.env.VITE_API_USER
    const [passwordMatch, setpasswordMatch] = useState(true)
    const [formData, setFormData] = useState({
        userName: "",
        contrasena: "",
        Confirmarcontrasena: "",
        nombre: "",
        apellido: ""
    })
    const [errors, setErrors] = useState({
        userName: false,
        contrasena: false,
        nombre: false,
        apellido: false,
        contrasenaMatch: true
    })
    const [formTouched, setFormTouched] = useState(false)

    useEffect(()=> {
        setpasswordMatch(formData.contrasena === formData.Confirmarcontrasena)
    }, [formData.contrasena, formData.Confirmarcontrasena])
    const validarField = (name, value) => {
        switch (name) {
            case "userName":
                return verificarUsername(value)
            case "Confirmarcontrasena":
            case "contrasena":
                return VerificarContrasena(value)
            case "nombre":
            case "apellido":
                return verificarNombreApellido(value)
        }
    }
    const handleChange = (e) =>{
        if(!formTouched) setFormTouched(true)
        const {name, value} = e.target
        let result = validarField(name, value)
        
        const updatedFormData = { ...formData, [name]: value }
        setFormData(updatedFormData)

        const fieldValid = !validarField(name, value);
        const passwordMatchValid = updatedFormData.contrasena === updatedFormData.Confirmarcontrasena;


        setErrors((prev) => ({
    ...prev,
    [name]: fieldValid,
    contrasenaMatch: !passwordMatchValid,
  }));
};
    const isFormValid = () => {
        return (
        Object.values(errors).every(err => err === false) &&
        Object.values(formData).every(val => val.trim() !== "") &&
        formData.contrasena === formData.Confirmarcontrasena
        )
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        if (!isFormValid()) return
        try {
            axios.post(`${url_usuarios}`, {
                userName: formData.userName,
                contrasena: formData.contrasena,
                nombre: formData.nombre,
                apellido: formData.apellido
            })
                .then(data => {
                    setUser(data.data)
                    navigate("/")
                })

        } catch (e){
            console.error("Error al crear usuarios", e)
        }
    }
    const childrens = [{
        label : "UserName",
        type: "text",
        name: "userName",
    }, {
        label : "Nombre",
        type: "text",
        name: "nombre",
    },
    {
        label : "apellido",
        type: "text",
        name: "apellido",
    },
    {
        label : "Contraseña",
        type: "password",
        name: "contrasena",
    },
    {
        label : "Contraseña",
        type: "password",
        name: "Confirmarcontrasena",
    }]
    return (
       <FormInit
       titulo="Registrate"
       childrens={childrens}
       error={passwordMatch}
       handleChange={handleChange}
       handleSubmit={handleSubmit}
       mensajeerror={errors}
       formTouched={formTouched}></FormInit>
    )
}
export default Register

