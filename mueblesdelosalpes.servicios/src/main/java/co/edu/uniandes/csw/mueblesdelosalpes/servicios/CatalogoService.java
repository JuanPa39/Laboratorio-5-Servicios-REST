package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/catalogo")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogoService {

    @EJB
    private IServicioCatalogoMockLocal catalogoEjb;

    @GET
    @Path("muebles/")
    public List<Mueble> getTodosLosMuebles() {
        return catalogoEjb.darMuebles();
    }

    @POST
    @Path("muebles/")
    public String agregar(Mueble mueble) {
        catalogoEjb.agregarMueble(mueble);
        return "mueble agregado";
    }

    @PUT
    @Path("muebles/")
    public String actualizar(Mueble mueble) {
        catalogoEjb.eliminarMueble(mueble.getReferencia());
        catalogoEjb.agregarMueble(mueble);
        return "mueble actualizado";
    }

    @DELETE
    @Path("muebles/")
    public String eliminar(@QueryParam("id") long id) {
        catalogoEjb.eliminarMueble(id);
        return "mueble eliminado";
    }
}
