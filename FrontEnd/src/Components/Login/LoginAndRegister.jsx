import { Outlet } from "react-router-dom"
import MenuLoginRegister from "./MenuLoginRegister"
import { useNavigate, useNavigation } from "react-router-dom"
import UserProvider, { UserContext } from "../../context/UserContext"
import { use, useEffect } from "react"
import { useContext, useState } from "react"

const LoginAndRegister = () => {
    const {user, setUser} = useContext(UserContext)
    const navigate = useNavigate()
    useEffect(()=>{
        if(user.id) navigate("/")
    },[user, navigate])
    
    return (
        <>
        <MenuLoginRegister></MenuLoginRegister>
        <Outlet></Outlet>
        </>
    )
}

export default LoginAndRegister