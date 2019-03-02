/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mudanzas.resources;
import co.edu.uniandes.csw.mudanzas.dtos.CargaDTO;
import co.edu.uniandes.csw.mudanzas.dtos.ViajesDTO;
import co.edu.uniandes.csw.mudanzas.dtos.ViajesDetailDTO;
import co.edu.uniandes.csw.mudanzas.ejb.ViajesLogic;
import co.edu.uniandes.csw.mudanzas.entities.ViajesEntity;
import co.edu.uniandes.csw.mudanzas.exceptions.BusinessLogicException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import java.util.logging.Logger;
/**
 *
 * @author je.osorio
 */
@Path("viajes")
@Produces("application/JSON")
@Consumes("application/JSON")
@RequestScoped
public class ViajeResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    private ViajesLogic viajesLogic;
    /**
     * mètodo que crea un nuevo viaje dado un json con la informaciòn de sus atributos
     * @param viajeDTO
     * @return un nuevo objeto que hereda de la clase ViajeDTO
     * @throws co.edu.uniandes.csw.mudanzas.exceptions.BusinessLogicException
     */
    @POST
    public ViajesDTO createVije(ViajesDTO viajeDTO) throws BusinessLogicException{
        ViajesEntity viajesEntity = viajeDTO.toEntity();
        ViajesEntity nuevoViajeEntity = viajesLogic.createViajes(viajesEntity);
        ViajesDTO nuevoViajeDTO = new ViajesDTO(nuevoViajeEntity);
        return nuevoViajeDTO;
    }
    /**
     * mètodo que crea un viaje dado el id 
     * @param id
     * @return 
     */
    @POST
    @Path("{id: \\d+}")
    public ViajesDTO crearVije(@PathParam("id") Long id){
        return new ViajesDTO();
    }
    /**
     * mètodo que retorna un viaje dado el id 
     * @param id
     * @return el objeto ViajeDTO el cual corresponde al id especificado
     */
    @GET
    @Path("{id: \\d+}")
    public ViajesDTO getViajeDTOPorId(@PathParam("id") Long id){
        return new ViajesDTO();
    }
    /**
     * mètodo que elimina un viaje dado el id 
     * @param id
     * @return indormaciòn del viaje eliminado
     */
    @DELETE
    @Path("{id: \\d+}")
    public ViajesDTO deleteVIajeDTO(@PathParam("id") Long id){
        return new ViajesDTO();
    }
    /**
     * mètodo que retorna las cargas de un viaje dado el id del viaje
     * @param id
     * @return la lista de cargas que corresponden al viaje con id especificado
     */
    @GET
    @Path("{id: \\d+}/cargas")
    public List<CargaDTO> getCargasDadoUnID(@PathParam("id") Long id){
        return new ViajesDetailDTO().getCargas();
    }
    /**
     * mètodo que elimina las cargas de un viaje dado el id del viaje
     * @param id
     */
    @DELETE 
    @Path("{id: \\d+}/cargas")
    public void eliminarCargasIdEspecificado(@PathParam("id") Long id){       
    }
    
    
}
