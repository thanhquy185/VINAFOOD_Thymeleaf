package vn.tuhoc.foodshop.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestLoginDTO {
    // Properties
    @JsonProperty("access_token")
    private String accessToken;
    private UserLogin userLogin;

    // Classes
    public static class UserLogin {
        // Properties
        private Long id;
        private String fullname;
        private String email;
        // private Role role;

        // Constructors
        public UserLogin() {
        }

        public UserLogin(Long id, String fullname, String email) {
            this.id = id;
            this.fullname = fullname;
            this.email = email;
        }

        // Getter - Setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class UserGetAccount {
        // Properties
        private UserLogin userLogin;

        // Constructors
        public UserGetAccount() {
        }

        public UserGetAccount(UserLogin userLogin) {
            this.userLogin = userLogin;
        }

        // Getter - Setter
        public UserLogin getUserLogin() {
            return userLogin;
        }

        public void setUserLogin(UserLogin userLogin) {
            this.userLogin = userLogin;
        }
    }

    // Constructors
    public RestLoginDTO() {
    }

    public RestLoginDTO(String accessToken, UserLogin userLogin) {
        this.accessToken = accessToken;
        this.userLogin = userLogin;
    }

    // Getter - Setter
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

}
