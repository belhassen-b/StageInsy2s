package dto.request;


import com.example.tpecommerce.model.Role;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String username;
  private String email;
  private String password;
  private Role role;

}
