package com.appspot.dmutti.calculator.support;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.*;

import com.google.appengine.api.users.*;

public class UserFilter implements Filter {

    private FilterConfig filterConfig;
    private static final String SEPARATOR = ",";
    private static final Logger log = Logger.getLogger(UserFilter.class.getName());
    
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        
        HttpServletRequest req = HttpServletRequest.class.cast(request);
        UserService userService = UserServiceFactory.getUserService();
        
        InputStream is = null;
        
        try {
            
            boolean achou = false;
            is = filterConfig.getServletContext().getResourceAsStream("/WEB-INF/users.list");
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter(SEPARATOR);
            String email = userService.getCurrentUser().getEmail();
            while (scanner.hasNext()) {
                String allowed = scanner.next().trim();
                log.info("[Allowed]: " + allowed);
                if (allowed.equals(email)) {
                    achou = true;
                    break;
                }
            }
            
            if (achou) {
                fc.doFilter(request, response);
            } else {
                req.getRequestDispatcher("/notAuthorized.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);

        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void init(FilterConfig arg) throws ServletException {
        this.filterConfig = arg;
    }
}
