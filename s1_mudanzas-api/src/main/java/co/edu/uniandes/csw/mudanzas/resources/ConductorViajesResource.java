/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mudanzas.resources;
import co.edu.uniandes.csw.mudanzas.dtos.ViajeDTO;
import co.edu.uniandes.csw.mudanzas.dtos.ViajesDetailDTO;
import co.edu.uniandes.csw.mudanzas.ejb.ViajesLogic;
import co.edu.uniandes.csw.mudanzas.entities.ViajesEntity;
import co.edu.uniandes.csw.mudanzas.exceptions.BusinessLogicException;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author je.osorio
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ConductorViajesResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    /**
     * atributo de la lógica de viajes
     */
    @Inject
    private ViajesLogic viajesLogic;

   
    
    
    /**
     * mètodo que crea un nuevo viaje dado un json con la informaciòn de sus atributos
     * @param viajeDTO
     * @param idConductor
     * @return un nuevo objeto que hereda de la clase ViajeDTO
     * @throws co.edu.uniandes.csw.mudanzas.exceptions.BusinessLogicException
     */
    @POST
    public ViajeDTO createViaje(ViajeDTO viajeDTO, @PathParam("idConductor") Long idConductor) throws BusinessLogicException, WebApplicationException{
        try{
            ViajeDTO nuevoViajeEntity = new ViajeDTO(viajesLogic.createViaje(viajeDTO.toEntity(), idConductor));
            return nuevoViajeEntity;
        }
        catch(BusinessLogicException e){
            throw new WebApplicationException("No se pudo crear el viaje:" + e.getMessage());
        }
    }
    
    /**
     * mètodo que retorna un viaje dado el id 
     * @param idViaje
     * @return el objeto ViajeDTO el cual corresponde al id especificado
     * @throws co.edu.uniandes.csw.mudanzas.exceptions.BusinessLogicException
     */
    @GET
    @Path("{idViaje: \\d+}")
    public ViajeDTO getViajeDTOPorId(@PathParam("idViaje") Long idViaje) throws BusinessLogicException, WebApplicationException{
        ViajesEntity viajesEntity;
        try{
        viajesEntity = viajesLogic.getViaje(idViaje);
       
        }
        catch(BusinessLogicException e){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new ViajeDTO(viajesEntity);
    }
    /**
     * retornar todos los viajes
     * @return
     * @throws BusinessLogicException
     * @throws WebApplicationException 
     */
    @GET
    public List<ViajeDTO> getViajes()throws BusinessLogicException, WebApplicationException{
        List<ViajesEntity> viajesEntity;
        try{
        viajesEntity = viajesLogic.getViajes();      
        }
        catch(BusinessLogicException e){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return List2Entity(viajesEntity);
    }
    /**
     * mètodo que elimina un viaje dado el id 
     * @param idViaje
     * @throws co.edu.uniandes.csw.mudanzas.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{idViaje: \\d+}")
    public void deleteViajeDTO(@PathParam("idViaje") Long idViaje) throws BusinessLogicException, WebApplicationException{
        try{
            viajesLogic.getViaje(idViaje);
        }
        catch(BusinessLogicException e){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        viajesLogic.deleteViaje(idViaje);
    }
    
    public List<ViajeDTO> List2Entity(List<ViajesEntity> entity){
        List<ViajeDTO> Viajes= new LinkedList<>();
        for(ViajesEntity enti: entity){
            Viajes.add(new ViajeDTO(enti));
        }
        return Viajes;
    }
    /**
     * 
     * @param idViaje
     * @return 
     */
   @Path("{idViaje: \\d+}/cargas")
    public Class<ViajesCargaResource> getViajesCargaResource(@PathParam("idViaje") long idViaje)
    {
        return ViajesCargaResource.class;
    }
}
