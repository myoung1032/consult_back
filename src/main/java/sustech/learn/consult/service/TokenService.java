package sustech.learn.consult.service;

import sustech.learn.consult.auth.AserRole;
import sustech.learn.consult.pojo.Aser;
import sustech.learn.consult.pojo.TokenEntity;

import java.util.List;

public interface TokenService {

    /* TOKEN for user auth */

    TokenEntity encode(Long userId, List<AserRole> roles);

    TokenEntity encode(Aser aser);

    Aser decode(String token);
}
