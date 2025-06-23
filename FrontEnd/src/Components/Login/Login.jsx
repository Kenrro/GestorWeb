import { useContext, useState } from "react"
import UserProvider, { UserContext } from "../../context/UserContext"
import axios from "axios"
import { useNavigate, useNavigation } from "react-router-dom"
import FormInit from "./FormInit"

const Login = () => {
    const url_usuarios = import.meta.env.VITE_API_USER
    const {user, setUser} = useContext(UserContext)
    const {state, setUserState} = useState(true)
    
    const navigation = useNavigate()
    const [userProv, setuserProv] = useState(
        {userName: "",
        contrasena: ""}
    )

    const handleChange = (e) => {
        const {name, value} = e.target;

        setuserProv(prev => ({
            ...prev,
            [name]: value
        }))

    }
   async function handleSubmit(e) {
        e.preventDefault()
        console.log(userProv)
        await axios.post(`${url_usuarios}/login`, userProv)
            .then((res) => {
                if(res.data && res.data.id){
                    setUser(res.data)
                    console.log(res.data)
                    navigation("/")
                }
                else {
                    alert("Usuario no encontrado")
                }
            })
    }
    const childrens = [{
        label : "UserName",
        type: "text",
        name: "userName",
    }, {
        label : "Contraseña",
        type: "password",
        name: "contrasena",
    }]
    return(
        <FormInit
        titulo="Login"
        childrens={childrens}
        error={!state}
        handleChange={handleChange}
        handleSubmit={handleSubmit}></FormInit>
    )
}
export default Login