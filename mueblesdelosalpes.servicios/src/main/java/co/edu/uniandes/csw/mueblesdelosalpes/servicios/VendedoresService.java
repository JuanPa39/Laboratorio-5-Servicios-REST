package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Vendedor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/vendedores")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VendedoresService {

    // Lista estática para persistir entre requests
    private static List<Vendedor> vendedores = new ArrayList<Vendedor>();

    @GET
    public List<Vendedor> listar() {
        return vendedores;
    }

    @POST
    public String agregar(Vendedor vendedor) {
        vendedores.add(vendedor);
        return "vendedor agregado";
    }

    @PUT
    public String actualizar(Vendedor vendedor) {
        for (int i = 0; i < vendedores.size(); i++) {
            if (vendedores.get(i).getIdentificacion() == vendedor.getIdentificacion()) {
                vendedores.set(i, vendedor);
                return "vendedor actualizado";
            }
        }
        return "vendedor no encontrado";
    }

    @DELETE
    public String eliminar(@QueryParam("id") long id) {
        for (int i = 0; i < vendedores.size(); i++) {
            if (vendedores.get(i).getIdentificacion() == id) {
                vendedores.remove(i);
                return "vendedor eliminado";
            }
        }
        return "vendedor no encontrado";
    }
}
