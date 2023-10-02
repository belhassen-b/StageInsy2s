package dto;


import com.example.tpecommerce.model.Card;
import com.example.tpecommerce.model.Role;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientDTO {

    private String name;
    private String username;
    private String email;
    private String password;

    private Role role = new Role();

    private List<Card> cards;
}
