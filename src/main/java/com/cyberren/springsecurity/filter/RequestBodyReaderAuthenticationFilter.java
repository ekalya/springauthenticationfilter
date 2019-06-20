/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberren.springsecurity.filter;

/**
 *
 * @author exk
 */

 
import com.cyberren.springsecurity.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
 
    private static final Log LOG = LogFactory.getLog(RequestBodyReaderAuthenticationFilter.class);
 
    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";
 
    private final ObjectMapper objectMapper = new ObjectMapper();
 
    public RequestBodyReaderAuthenticationFilter() {
    }
 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestBody;
        try {
            requestBody = IOUtils.toString(request.getReader());
            LoginRequest authRequest = objectMapper.readValue(requestBody, LoginRequest.class);
 
            UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password);
 
            // Allow subclasses to set the "details" property
            setDetails(request, token);
 
            return this.getAuthenticationManager().authenticate(token);
        } catch(IOException e) {
            LOG.error(ERROR_MESSAGE, e);
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }
    }
}
