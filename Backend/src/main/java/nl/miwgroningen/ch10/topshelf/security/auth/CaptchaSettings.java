package nl.miwgroningen.ch10.topshelf.security.auth;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * Gets the captcha secret key out of config directory
 */

@Component
@Data
public class CaptchaSettings {

    @Value("${recaptcha.secret.key}")
    private String secretKey;

}
