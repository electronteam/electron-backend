package md.electron.electronbackend.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class AuthenticationService
{
    // 1 day in milliseconds
    private static final long EXPIRATIONTIME = 864_000_00;

    //an algorithm-specific signing key used to digitally sign the JWT
    //should be used a base64 encoded string
    //Should be changed for production; kept in a separate file and read it from there
    private static final String SIGNINGKEY = "SecretKey";

    //PREFIX defines the prefix of the token and the Bearer schema is typically used
    static final String PREFIX = "Bearer";

    // addToken method creates the token and adds it to the request's Authorization header
    public static void addToken(final HttpServletResponse response, final String username)
    {
        final Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATIONTIME);
        final String jwtToken = Jwts.builder().setSubject(username)
                .setExpiration(expirationDate)
                //The signing key is encoded using the SHA-512 algorithm
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
                .compact();

        response.addHeader("Authorization", PREFIX + " " + jwtToken);
        // adding Access-Control-Expose-Headers to the header with the Authorization value.
        // This is needed because we are not able to access the Authorization header through a JavaScript frontend by default.
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    // Get token from Authorization header
    public static Authentication getAuthentication(final HttpServletRequest request)
    {
        final String token = request.getHeader("Authorization");

        if (token != null)
        {
            String user = Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null)
            {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }

        return null;
    }
}
