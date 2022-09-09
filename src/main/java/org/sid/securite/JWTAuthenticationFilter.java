package org.sid.securite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

       try{
//           Utilisateur user=new ObjectMapper().readValue(request.getInputStream(),Utilisateur.class);
    	   	CloseableHttpClient client = HttpClients.createDefault();
    	    HttpPost httpPost = new HttpPost("http://localhost:8090/auth/realms/sesame/protocol/openid-connect/token");
    	    List<NameValuePair> params = new ArrayList<NameValuePair>();
    	    @SuppressWarnings("deprecation")
			JSONParser parser = new JSONParser();  
    	    JSONObject json = (JSONObject) parser.parse(request.getReader().lines().collect(java.util.stream.Collectors.joining(System.lineSeparator())));  
    	    params.add(new BasicNameValuePair("username", json.getAsString("username")));
    	    params.add(new BasicNameValuePair("password", json.getAsString("password")));
    	    params.add(new BasicNameValuePair("grant_type", "password"));
    	    params.add(new BasicNameValuePair("client_id", "sesame-client"));
    	    httpPost.setEntity(new UrlEncodedFormEntity(params));
			
    	    CloseableHttpResponse res = client.execute(httpPost);
    	    JSONObject jsonToken = (JSONObject) parser.parse(new String(res.getEntity().getContent().readAllBytes()));
    	    response.addHeader(SecurityParams.JWT_HEADER_NAME, jsonToken.getAsString("access_token"));
    	    return authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(json.getAsString("username"),json.getAsString("password")));

       }catch (IOException | ParseException e){
           throw new RuntimeException(e);
       }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        
    }


}
