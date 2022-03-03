package sustech.learn.consult.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TokenEntity {
    private String access_token;
    private String refresh_token;
    private String type;
    private Date issue_date;
    private Date access_token_expiration_date;
    private Date refresh_token_expiration_date;
    private String issue_provider = "南科大学习中心";
    private String website = "https://learningcenter.sustech.edu.cn";
    private String version = "1.0.0-RELEASE";
}