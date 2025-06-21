import { useContext, useState } from 'react'
import { Navigate, Outlet, useNavigate } from 'react-router-dom'
import Menu from "./Components/Globales/Menu"
import UserProvider, { UserContext } from "./context/UserContext"


function App() {
  const [count, setCount] = useState(0)
  const {user, setUser} = useContext(UserContext)
  const navigation = useNavigate()

  if(user.userName==="") return <Navigate to={"/user/login"}></Navigate>

  return (
    <>
    
      <Menu/>
      <Outlet></Outlet>
    </>
  )
}

export default App
