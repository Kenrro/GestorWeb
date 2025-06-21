import "../stilos/menu.css"
import { Link } from "react-router-dom";

const Menu = ()=> {
    return (
        <>
            <div id="menu">
                <div className="wrap-caja"><h1>Gestor de tareas</h1></div>
                <div className="wrap-caja"></div>
                <div className="wrap-caja"><CajaBotonMenu></CajaBotonMenu></div>
            </div>
        </>
    )
}
export default Menu

const CajaBotonMenu = () => {
    return (
        <>
            <ul id="menu-opciones">
                <li><Link to={"register"}>Register</Link></li>
                <li><Link to={"login"}>Login</Link></li>
               
            </ul>
        </>
    )
}