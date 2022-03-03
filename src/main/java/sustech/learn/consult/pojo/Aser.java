package sustech.learn.consult.pojo;

import sustech.learn.consult.auth.AserRole;

import java.util.List;

public class Aser {
    private Long userId;
    private List<AserRole> roles;
    private boolean isExpiration;

    public Aser(Long userId, List<AserRole> roles, boolean isExpiration) {
        this.userId = userId;
        this.roles = roles;
        this.isExpiration = isExpiration;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AserRole> roles) {
        this.roles = roles;
    }

    public boolean isExpiration() {
        return isExpiration;
    }

    public void setExpiration(boolean expiration) {
        isExpiration = expiration;
    }
}