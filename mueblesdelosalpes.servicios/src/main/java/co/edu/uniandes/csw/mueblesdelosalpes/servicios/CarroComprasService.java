package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCarritoMockRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/carrito")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarroComprasService {

    @EJB
    private IServicioCarritoMockRemote carroEjb;

    @GET
    @Path("muebles/")
    public List<Mueble> getTodosLosMuebles() {
        return carroEjb.getInventario();
    }

    @POST
    @Path("muebles/")
    public List<Mueble> agregarMuebles(List<Mueble> mb) {
        for (Mueble mueble : mb) {
            carroEjb.agregarItem(mueble);
        }
        return carroEjb.getInventario();
    }

    @PUT
    @Path("muebles/")
    public List<Mueble> actualizarMuebles(List<Mueble> mb) {
        carroEjb.limpiarLista();
        for (Mueble m : mb) {
            carroEjb.agregarItem(m);
        }
        return carroEjb.getInventario();
    }

    @DELETE
    @Path("muebles/")
    public String eliminarMuebles(@QueryParam("referencia") long referencia) {
        List<Mueble> inventario = carroEjb.getInventario();
        Mueble aEliminar = null;
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getReferencia() == referencia) {
                aEliminar = inventario.get(i);
                break;
            }
        }
        if (aEliminar != null) {
            // Reducir hasta 0 para forzar eliminación
            while (aEliminar.getCantidad() > 0) {
                aEliminar.reducirCantidad();
            }
            carroEjb.removerItem(aEliminar, true);
            return "mueble eliminado del carrito";
        }
        return "mueble no encontrado";
    }
}
