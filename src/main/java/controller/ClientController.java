package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import interfaces.IClient;
import model.Client;
import repository.ClientRepository;

/**
 * Servlet implementation class ClientController
 */
@WebServlet(name="clientController", urlPatterns = {"/client","*.client","*.*.client"})
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IClient repository;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientController() {
        super();
        // TODO Auto-generated constructor stub
        repository = new ClientRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/client")) {
			List<Client> clients = repository.findAll();
			request.setAttribute("clients", clients);
			request.getRequestDispatcher("views/client/list.jsp").forward(request, response);
		}
		else if(path.equals("/nouveau.client")) {
			request.getRequestDispatcher("views/client/ajouter.jsp").forward(request, response);
		}
		else if (path.equals("/enregistrer.nouveau.client") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String adresse = request.getParameter("adresse");
			String telephone = request.getParameter("telephone");
			String cin = request.getParameter("cin");
			
			repository.create(new Client(nom,prenom,telephone,adresse,cin));
			response.sendRedirect("client");
		}
		else if(path.equals("/regarder.client")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Client client = repository.findOne(id);
			request.setAttribute("client", client);
			request.getRequestDispatcher("views/client/regarder.jsp").forward(request, response);
		}
		else if(path.equals("/modifier.client")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Client client = repository.findOne(id);
			request.setAttribute("client", client);
			request.getRequestDispatcher("views/client/modifier.jsp").forward(request, response);
		}
		else if(path.equals("/enregistrer.modifier.client") && request.getMethod().equals("POST")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String adresse = request.getParameter("adresse");
			String telephone = request.getParameter("telephone");
			String cin = request.getParameter("cin");
			
			Client client = new Client(nom,prenom,telephone,adresse,cin);
			client.setId(id);
			
			repository.update(client);
			response.sendRedirect("client");
		}	
		else if(path.equals("/supprimer.client")) {
			Long id = Long.parseLong(request.getParameter("id"));
			repository.delete(id);
			response.sendRedirect("client");
		}
		else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
