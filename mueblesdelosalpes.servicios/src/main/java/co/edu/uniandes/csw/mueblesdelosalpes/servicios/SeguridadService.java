package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.ejb.ServicioSeguridadMock;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioSeguridadMockLocal;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/seguridad")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeguridadService {

    
    private IServicioSeguridadMockLocal seguridad = new ServicioSeguridadMock();

    @GET
    public String estado() {
        return "servicio seguridad activo";
    }

    @POST
    @Path("/login")
    public Usuario login(@QueryParam("user") String user,
                         @QueryParam("pass") String pass) {
        try {
            return seguridad.ingresar(user, pass);
        } catch (Exception e) {
            return null;
        }
    }

    @PUT
    public String actualizar(Usuario u) {
        return "usuario actualizado";
    }

    @DELETE
    public String logout() {
        return "logout exitoso";
    }
}
