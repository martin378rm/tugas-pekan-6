package Controller;

import Model.Laptop;
import Service.LaptopService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/")
public class LaptopResource {

    @Inject
    LaptopService laptopService;
    @GET
    @Path("laptop")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Laptop> getAllLaptop() {
        return Laptop.listAll();
    }


    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject findByName(@PathParam("name") String name) {
        JsonObject object = new JsonObject();
        if (laptopService.findByName(name) == null) {
            object.put("message","tidak ada data");
            return object;
        }
        object.put("datas",laptopService.findByName(name));
        return object;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addLaptop(Laptop laptop) {
        if (laptopService.tambah(laptop)){
            Laptop.persist(laptop);
            return Response.created(URI.create("/laptop/"+laptop.getBrands())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("{name}")
    public JsonObject updateLaptop(@PathParam("name") String name, Laptop laptop) {
        Object lap = laptopService.update(laptop,name);
        JsonObject obj = new JsonObject();
        obj.put("payload",lap);
        return obj;
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("{name}")
    public JsonObject delete(@PathParam("name") String name){
        JsonObject object = new JsonObject();

        // pengecekan apakah data yang ingin di hapus ada atau tidak
        if (laptopService.delete(name) == 0) {
            object.put("data", name);
            object.put("message","tidak ditemukan");
            return object;
        }

        object.put("message","berhasil dihapus");
        return object;
    }

}
