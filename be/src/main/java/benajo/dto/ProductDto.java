package benajo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProductDto implements Serializable {

    private String id;
    private String name;
    private String dimensions;
    private String category;
    private String rank;
}
