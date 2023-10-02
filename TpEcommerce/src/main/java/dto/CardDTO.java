package dto;


import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CardDTO {

    private Date date;

    private List<ProductLineDTO> productLines;

}
