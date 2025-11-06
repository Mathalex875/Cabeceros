package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductoService;
import service.ProductoServiceImplement;
import models.Producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet({"/productos.xls","/productos.html","/producto.json"})
public class ProductosXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();


        resp.setContentType("text/html;charset=UTF-8");
        //Obtener el path del servlet para determinar el formato de salida
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith("xls");
        boolean esJson = servletPath.endsWith("json");//Nuevo: bandera para JSON

        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment; filename=productos.xls");
        } else if (esJson) {//Nuevo: configuracion para JSON
            resp.setContentType("application/vnd.ms-json");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
        }
        try (PrintWriter out = resp.getWriter()) {
            if (esJson) {
                //Construimos la respuesta Json manualmente (se puede mejorar con una libreria como JSON)
                StringBuilder json = new StringBuilder();
                json.append("[\n");
                for (int i = 0; i < productos.size(); i++) {
                    Producto p = productos.get(i);
                    json.append("{\n");
                    json.append("    \"id\": ").append(p.getId()).append(",\n");
                    json.append("    \"nombre\": \"").append(p.getNombre()).append("\",\n");
                    json.append("    \"tipo\": \"").append(p.getTipo()).append("\",\n");
                    json.append("    \"precio\": ").append(p.getPrecio()).append("\n");
                    json.append("  }");
                    if (i < productos.size() - 1) {
                        json.append(",\n");
                    }
                }
                json.append("\n]");
                out.println(json.toString());
            } else {//logica para html o xls (que comparte la estructura de la tabla)


                if (!esXls) {
                    //creo la plantilla html
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset='utf-8'>");
                    out.println("<title>Listado de productos</title>");
                    //creamos los estilos para la pagina
                    out.println("<style>");
                    out.println("body {");
                    out.println("font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
                    out.println("    background: linear-gradient(135deg, #a8edea, #fed6e3);");
                    out.println("    display: flex;");
                    out.println("    flex-direction: column;");
                    out.println("    align-items: center;");
                    out.println("    justify-content: center;");
                    out.println("    min-height: 100vh;");
                    out.println("    margin: 0;");
                    out.println("}");
                    out.println("h1 {");
                    out.println("    color: #333;");
                    out.println("    background-color: #fff;");
                    out.println("    padding: 15px 30px;");
                    out.println("    border-radius: 12px;");
                    out.println("    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);");
                    out.println("}");
                    out.println("table {");
                    out.println("    border-collapse: collapse;");
                    out.println("    width: 80%;");
                    out.println("    margin-top: 20px;");
                    out.println("    background-color: white;");
                    out.println("    border-radius: 10px;");
                    out.println("    overflow: hidden;");
                    out.println("    box-shadow: 0 4px 10px rgba(0,0,0,0.2);");
                    out.println("}");
                    out.println("th, td {");
                    out.println("    border: 1px solid #ddd;");
                    out.println("    text-align: center;");
                    out.println("    padding: 12px;");
                    out.println("}");
                    out.println("th {");
                    out.println("    background-color: #4CAF50;");
                    out.println("    color: white;");
                    out.println("}");
                    out.println("tr:nth-child(even) {background-color: #f2f2f2;}");
                    out.println("a {");
                    out.println("    display: inline-block;");
                    out.println("    margin: 10px;");
                    out.println("    padding: 10px 20px;");
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
                    out.println("<h1>Listado de productos</h1>");
                    out.println("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a excel</a></p>");
                    out.println("<p><a href=\"" + req.getContextPath() + "/producto.json" + "\">Mostrar JSON</a></p>");
                }
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Tipo</th>");
                out.println("<th>Precio</th>");
                out.println("</tr>");
                productos.forEach(p -> {
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getTipo() + "</td>");
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("</tr>");
                });
                out.println("</table>");
                if (!esXls) {
                    out.println("<br>");
                    out.println("<a href='index.html'>Regresar</a>");
                    out.println("</body>");
                    out.println("</html>");
                }

            }
        }
    }
}
