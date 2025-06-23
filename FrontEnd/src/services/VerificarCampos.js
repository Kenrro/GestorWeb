// Expresiones
const usernameRegex = /^[a-zA-Z0-9!@#$%^&_\-+=.,:;]+$/;
const nameLastnameRegex = /^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+(['-]?[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$/;
const passwordRegex = /^[A-Za-z0-9!@#$%^&*()_\-+=.?]{6,20}$/;


function verificarUsername(nombre) {
    return usernameRegex.test(nombre)
}
function verificarNombreApellido(string) {
    return nameLastnameRegex.test(string)
}
function VerificarContrasena(password) {
    return passwordRegex.test(password)
}


export {verificarUsername, verificarNombreApellido, VerificarContrasena}