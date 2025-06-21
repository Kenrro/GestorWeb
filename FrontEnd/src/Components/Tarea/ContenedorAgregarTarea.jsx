import { useState } from "react";
import IconoMas from "../iconos/IconoMas";
import TarjetaNuevaTarea from "./TarjetaNuevaTarea";
import { useParams } from "react-router-dom";

const ContenedorAgregarTarea = ({recargarTareas}) => {
  const [mostrarTarjeta, setMostrarTarjeta] = useState(false);
  const params = useParams();

  const toggleTarjeta = () => setMostrarTarjeta(prev => !prev);

  return (
    <div id="wrap-agregar-tarea">
      <button onClick={toggleTarjeta} aria-label="Agregar tarea">
        <IconoMas />
      </button>
      {mostrarTarjeta && <TarjetaNuevaTarea recargarTareas={recargarTareas} />}
    </div>
  );
};

export default ContenedorAgregarTarea;