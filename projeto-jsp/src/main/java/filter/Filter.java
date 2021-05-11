package filter;
/*
 * Essa classe serve para fazer a conexão quando o sistema carrega foi instanciada como um filter
 * no arquivo web.xml
 */
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import connection.SingleConnection;
@WebFilter(urlPatterns = {"/*"} )
public class Filter implements javax.servlet.Filter {

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	try {
		chain.doFilter(request, response);	
		connection.SingleConnection.connection.commit();
	}catch(Exception e) {
		try {
			connection.SingleConnection.connection.rollback();
		}catch(Exception ex) {
		System.out.println(ex);
		}
	}
	}

	public void init(FilterConfig arg0) throws ServletException{
		SingleConnection.getConnection();
		
	}
	
	

}
