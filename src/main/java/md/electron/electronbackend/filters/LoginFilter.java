package md.electron.electronbackend.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import md.electron.electronbackend.data.AccountCredentials;
import md.electron.electronbackend.service.security.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * handles POST requests to the /login endpoint
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter
{
    public LoginFilter(final String url, final AuthenticationManager authManager)
    {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
            throws AuthenticationException, IOException
    {
        final AccountCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);

        return getAuthenticationManager()
                //ProviderManager.authenticate will be called, which will call DaoAuthenticationProvider
                //and which has reference to UserDetailsServiceImpl
                .authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain chain, final Authentication auth)
    {
        AuthenticationService.addToken(response, auth.getName());
    }
}
