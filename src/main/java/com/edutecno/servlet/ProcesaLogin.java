package com.edutecno.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class ProcesaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// RECIBIMOS EL NOMBRE Y EL PASSWORD MEDIANTE EL REQUEST 
		String NOMBRE = request.getParameter("nombre");
		String PASSWORD = request.getParameter("password");
		
		//LLAMAMOS A UNA PAGINA FORMULARIO.HTML QUE CONTENDRA LAS OPCIONES DE LOGIN
		PrintWriter out = response.getWriter();
		//request.getRequestDispatcher("formulario.html").include(request, response);  //ESTO ES PARA REDIGIR A OTRO SITIO, SERVLET O PAGINA

 
		//Si el NOMBRE es identico al PASSWORD entonces creara la sesion, si no, no creara nada y volvera a formulario.html 
		//con un mensaje de alerta el cual indicara la logica a seguir
		if (PASSWORD.equals(NOMBRE)) {
			
			request.getRequestDispatcher("ingresoValores.html").include(request, response); //Mostrara el HTML ingresoValores.html
			
			//Luego al final mostrara que la sesion se ha creado correctamente con el nombre de usuario
			out.println("Login exitoso");
			out.println("Bienvenido(a) "+ NOMBRE);
			
			
			//ACTIVAMOS "PRENDEMOS" LA HTTP SESSION
			HttpSession sesion = request.getSession(true); 
			
			sesion.setAttribute("nombre", PASSWORD);  //EL NOMBRE ES IDENTICO AL PASSWORD POR LA LOGICA ANTERIOR
			
			out.println("Variable de sesion creada con el nombre de "+ PASSWORD);
			
			//out.println(sesion.getId());
			
			 
		}else {
			request.getRequestDispatcher("formulario.html").include(request, response);
			out.println("Clave y nombre ingresados incorrectos, por favor coloque el mismo valor para nombre y clave para acceder");
			
			
		}
		
		
		
	}

}
