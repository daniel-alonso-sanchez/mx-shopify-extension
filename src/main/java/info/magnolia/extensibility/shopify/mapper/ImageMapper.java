package info.magnolia.extensibility.shopify.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


import com.shopify.model.Image;
@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface ImageMapper {
    List<String> map(List<Image> value);
    default String map(Image value){
        return value.getSource();
    };
}
