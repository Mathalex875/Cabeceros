package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceros-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
       String metodoHttp = request.getMethod();
       String requestURI = request.getRequestURI().toString();
       String requestURL = request.getRequestURL().toString();
       String contextPath = request.getContextPath();
       String servletPath = request.getServletPath();
       String ip = request.getRemoteAddr();
       int port = request.getServerPort();

       try (PrintWriter out = response.getWriter()) {
           //generar plantilla
           out.println("<!DOCTYPE html>");
           out.println("<html>");
           out.println("<head>");
           out.println("<title>Manejo de cabeceros 2025-2026</title>");
           out.println("</head>");
           out.println("<body>");
           out.println("<h1>Manejo de cabeceros</h1>");
           out.println("<ul>");
           out.println("<li>Obteniendo el método de petición: " + metodoHttp + "</li>");
           out.println("<li>Obtniendo la URI: " + requestURI + "</li>");
           out.println("<li>Obteniendo la URL: " + requestURL + "</li>");
           out.println("<li>Obteniendo el contexto: " + contextPath + "</li>");
           out.println("<li>Obteniendo el servlet; "+ servletPath + "</li>");
           out.println("<li>Obteniendo la ip: " + ip + "</li>");
           out.println("<li>Obteniendo el port: " + port + "</li>");
           Enumeration<String> headersNames = request.getHeaderNames();
           while(headersNames.hasMoreElements()) {
               String cabecera = headersNames.nextElement();
               out.println("<li>" + cabecera + " : " + request.getHeader(cabecera) + "</li>");
           }
           out.println("</ul>");
           out.println("</body>");
           out.println("</html>");
       }
   }
}