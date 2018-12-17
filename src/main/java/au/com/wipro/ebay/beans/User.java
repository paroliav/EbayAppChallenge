package au.com.wipro.ebay.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
