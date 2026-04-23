package org.example.blackholetourismagencybook.auth.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain)
    throws ServletException, IOException {


/*                              HTTP request
        GET /api/user/profile HTTP/1.1         <-- request line
        Host: example.com                      <-- request header
        User-Agent: Mozilla/5.0...             <-- header
        Authorization: Bearer eyJhbGci...      <-- header
        Accept: application/json               <-- header

        { "id": 123 }                          <-- body
*/


        String header = request.getHeader("Authorization");// Bearer eyJhbGci...
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);//eyJhbGci.xxxxxx.yyyyyy
        if (jwtService.isValid(token)){
            String username = jwtService.extractUsername(token);

            //Encapsulate as "Authorized" and save in system context
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            username, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

}
