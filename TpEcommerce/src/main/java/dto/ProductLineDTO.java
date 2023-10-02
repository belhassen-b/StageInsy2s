package dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductLineDTO {

    private int quantity;

    private int price;

    private int idCard;

    private Double total;

    private ProductDTO product;
}
