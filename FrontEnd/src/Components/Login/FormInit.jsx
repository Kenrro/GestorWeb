import IconoUser from "../iconos/IconoUser"
import FieldForm from "./FieldForm"
import "../stilos/forminit.css"

const FormInit = ({titulo, id,childrens, error, handleChange,handleSubmit, mensajeerror = {}, formTouched}) => {
    const hayErrores = Object.values(mensajeerror).some(val => val === true)
    return (
        <div className="wrap-content" id="wrap-register">
            <form
            onSubmit={handleSubmit}>
                <div className="wrap-titulo-form" >
                    <IconoUser></IconoUser>
                    <h2>{titulo}</h2>
                </div>
                {childrens.map(child => (
                        <FieldForm
                        label={child.label}
                        type={child.type}
                        name={child.name}
                        handleChange={handleChange}
                        className={formTouched && mensajeerror[child.name] === true ? "input-error" : ""}></FieldForm>
                    ))}
                {hayErrores && formTouched && (
                    <div className="text-error">
                        <p>
                        Valor incorrecto en: {Object.entries(mensajeerror)
                            .filter(([key, value]) => value === true)
                            .map(([key]) => key)
                            .join(", ")}
                        </p>
                    </div>
                )}
                <div className="wrap-boton-submit">
                    <input 
                    type="submit"
                    className={"boton-submit "+(hayErrores ? "boton-submit-desactivado" : "")}
                    disabled={hayErrores}>
                    </input>
                </div>
            </form>
        </div>
    )
}

export default FormInit