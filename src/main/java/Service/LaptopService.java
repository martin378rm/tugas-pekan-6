package Service;

import Model.Laptop;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class LaptopService {


    public Laptop findByName(String name) {
        return Laptop.find("name",name).firstResult();
    }


    public Response update(Laptop laptop, String name) {
        // query untuk mendapatkan data yang di cari
        Optional<Laptop> optionalLaptop = Laptop.find("name",name).firstResultOptional();

        // pengecekan jika data yang dicari ada
        if (optionalLaptop.isPresent()) {
            // tampung data yang dicari pada objek old
            Laptop oldLaptop = optionalLaptop.get();

            // jika user mengirim parameter, ubah data lama menjadi data yang dikirim
            if (Objects.nonNull(laptop.getName())) {
                oldLaptop.setName(laptop.getName());
            }
            if (Objects.nonNull(laptop.getBrands())) {
                oldLaptop.setBrands(laptop.getBrands());
            }
            if (Objects.nonNull(laptop.getDescription())) {
                oldLaptop.setDescription(laptop.getDescription());
            }
            if (Objects.nonNull(laptop.getRam())) {
                oldLaptop.setRam(laptop.getRam());
            }
            if (Objects.nonNull(laptop.getInternalStorage())) {
                oldLaptop.setInternalStorage(laptop.getInternalStorage());
            }
            oldLaptop.persist();
            if (oldLaptop.isPersistent()) {
//                return Response.created(URI.create("/laptop/" + laptop.getBrands())).build();
                return Response.ok(oldLaptop).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    public long delete(String name) {
       return Laptop.delete("name",name);
    }

    public boolean tambah(Laptop laptop) {
        if (Objects.isNull(laptop.getName()) || Objects.isNull(laptop.getBrands()) || Objects.isNull(laptop.getDescription()) || Objects.isNull(laptop.getRam()) || Objects.isNull(laptop.getInternalStorage()) ){
            return false;
        }
        return true;
    }

//    public boolean checked(Laptop laptop) {
//        Laptop laptops = new Laptop();
//        laptops.setName(laptop.getName());
//        laptops.setBrands(laptop.getBrands());
//        laptops.setDescription(laptop.getDescription());
//        laptops.setRam(laptop.getRam());
//        laptops.setInternalStorage(laptop.getInternalStorage());
//
//        Laptop getResult = Laptop.find("name = ?1 AND brands = ?2 AND description = ?3 AND ram = ?4 AND internal_storage = ?5",laptops.getName(),laptops.getBrands(),laptops.getDescription(),laptops.getRam(),laptops.getInternalStorage()).firstResult();
//
//        if (getResult.equals(laptops)){
//            System.out.println("benar");
//            return true;
//        }
//
//        System.out.println("salah");
//        return false;
//    }

}

