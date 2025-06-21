import { createContext, useState } from "react";

export const UserContext = createContext();

const UserProvider = (props) => {
    const [user, setUser] = useState({
    id: "",
    userName: "",
    nombre: "",
    apellido: "",
    fechCreacion: ""
}
    )
    const valor = {user, setUser};
    return (
        <UserContext.Provider 
        value={valor}>
            {props.children}
        </UserContext.Provider>
    )
}

export default UserProvider