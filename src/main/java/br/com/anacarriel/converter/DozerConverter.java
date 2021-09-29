package br.com.anacarriel.converter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import jdk.dynalink.linker.LinkerServices;

import java.util.ArrayList;
import java.util.List;

public class DozerConverter {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination){//para fazer o parser de um objeto simples
        return mapper.map(origin, destination);
    }
    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<D>();
        for (Object o: origin) {
            destinationObjects.add(mapper.map(o, destination));//para fazer o parser de listas
        }
        return destinationObjects;
    }
}
