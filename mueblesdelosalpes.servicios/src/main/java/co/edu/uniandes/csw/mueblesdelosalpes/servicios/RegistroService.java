package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.RegistroVenta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/registro")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistroService {

    // static para que persista entre requests
    private static List<RegistroVenta> registros = new ArrayList<RegistroVenta>();

    @GET
    public List<RegistroVenta> getRegistros() {
        return registros;
    }

    @POST
    public String agregar(RegistroVenta r) {
        registros.add(r);
        return "registro agregado";
    }

    @PUT
    public String actualizar(RegistroVenta r) {
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getProducto().getReferencia() == r.getProducto().getReferencia()) {
                registros.set(i, r);
                return "registro actualizado";
            }
        }
        return "registro no encontrado";
    }

    @DELETE
    public String eliminar() {
        registros.clear();
        return "registros eliminados";
    }
}
