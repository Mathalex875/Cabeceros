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
           out.println("<style>");
           out.println("body {");
           out.println("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
           out.println("    background: linear-gradient(135deg, #a8edea, #fed6e3);");
           out.println("    display: flex;");
           out.println("    flex-direction: column;");
           out.println("    align-items: center;");
           out.println("    justify-content: flex-start;");
           out.println("    min-height: 100vh;");
           out.println("    margin: 0;");
           out.println("    padding-top: 40px;");
           out.println("}");
           out.println("h1 {");
           out.println("    color: #333;");
           out.println("    background-color: #fff;");
           out.println("    padding: 15px 30px;");
           out.println("    border-radius: 12px;");
           out.println("    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);");
           out.println("    text-align: center;");
           out.println("}");
           out.println("ul {");
           out.println("    list-style-type: none;");
           out.println("    background-color: #fff;");
           out.println("    padding: 20px 40px;");
           out.println("    border-radius: 12px;");
           out.println("    width: 60%;");
           out.println("    box-shadow: 0 4px 10px rgba(0,0,0,0.2);");
           out.println("    margin-top: 20px;");
           out.println("}");
           out.println("li {");
           out.println("    background: #f9f9f9;");
           out.println("    margin: 8px 0;");
           out.println("    padding: 10px 15px;");
           out.println("    border-left: 5px solid #4CAF50;");
           out.println("    border-radius: 5px;");
           out.println("    color: #333;");
           out.println("    transition: background 0.3s ease;");
           out.println("}");
           out.println("li:hover {");
           out.println("    background: #e8f5e9;");
           out.println("}");
           out.println("a {");
           out.println("    display: inline-block;");
           out.println("    margin: 20px;");
           out.println("    padding: 12px 24px;");
           out.println("    background-color: #4CAF50;");
           out.println("    color: white;");
           out.println("    text-decoration: none;");
           out.println("    font-weight: bold;");
           out.println("    border-radius: 8px;");
           out.println("    transition: all 0.3s ease;");
           out.println("}");
           out.println("a:hover {");
           out.println("    background-color: #45a049;");
           out.println("    transform: scale(1.05);");
           out.println("}");
           out.println("</style>");
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
           out.println("</br>");
           out.println("<a href ='index.html'>Regresar</a>");
           out.println("</body>");
           out.println("</html>");
       }
   }
}