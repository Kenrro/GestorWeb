import { useContext, useState } from "react"
import "../stilos/login.css"
import UserProvider, { UserContext } from "../../context/UserContext"
import axios from "axios"
import { useNavigate, useNavigation } from "react-router-dom"

const Login = () => {
    const url_usuarios = import.meta.env.VITE_API_USER
    const {user, setUser} = useContext(UserContext)
    
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

    return(
        <div className="wrap-content">
            <form
            onSubmit={handleSubmit}>
                <div className="field-nombre">
                    <legend>Nombre:</legend>
                    <input 
                    name="userName"
                    type="text"
                    required
                    onChange={handleChange}/>
                </div>
                <div className="field-contrasena">
                    <legend>Contraseña</legend>
                    <input 
                    name="contrasena"
                    type="password"
                    required 
                    onChange={handleChange}/>
                </div>
                <div className="field-submit">
                    <input type="submit" />
                </div>
            </form>
        </div>
    )
}
export default Login