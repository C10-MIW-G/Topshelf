package nl.miwgroningen.ch10.topshelf.security.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 *
 * This contains the information the UserController needs to change the password.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    private String password;
    private String newPassword;
}
