package dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientDtoResponse {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private UUID uuid;
    private RoleDto role;
}
