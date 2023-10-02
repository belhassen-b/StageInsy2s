package dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {

    private Integer id;
    private String name;

    private String description;

    private int price;

    private String category;

    private int stock;

    private String image;

    private ProductLineDTO productLine;



}
