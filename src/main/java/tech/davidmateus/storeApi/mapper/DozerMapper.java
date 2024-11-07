package tech.davidmateus.storeApi.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

// classe resposansavel por transforma DTO em entity e entity em DTO
public class DozerMapper {
    private static ModelMapper mapper = new ModelMapper();


    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }
    public static <O, D> List<D> parseListObject(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<D>();
        for (O o:origin){
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}
