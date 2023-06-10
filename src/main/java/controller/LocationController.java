package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import interfaces.IClient;
import interfaces.ILocation;
import interfaces.IVoiture;
import model.Client;
import model.Location;
import model.Voiture;
import repository.ClientRepository;
import repository.LocationRepository;
import repository.VoitureRepository;

/**
 * Servlet implementation class LocationController
 */
@WebServlet(name="locationController", urlPatterns = {"/location","*.location","*.*.location"})
public class LocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ILocation locationRepository;
	private IVoiture voitureRepository;
	private IClient clientRepository;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationController() {
        super();
        locationRepository = new LocationRepository();
        voitureRepository = new VoitureRepository();
        clientRepository = new ClientRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/location")) {
			List<Location> locations = locationRepository.findAll();
			request.setAttribute("locations", locations);
			request.getRequestDispatcher("views/location/list.jsp").forward(request, response);
		}
		else if(path.equals("/filtrer.location")) {
			String filtre = request.getParameter("filtre");
			List<Location> locations = new ArrayList<>();
			
			switch (filtre) {
			case "finished": {
				locations = locationRepository.findByFinished();
				break;
			}
			case "current": {
				locations = locationRepository.findActual();
				break;
			}
			case "late": {
				locations = locationRepository.findByLate();
				break;
			}
			default:
				locations = locationRepository.findAll();
				break;
			}
			request.setAttribute("filtre", filtre);
			request.setAttribute("locations", locations);			
			request.getRequestDispatcher("views/location/list.jsp").forward(request, response);
		}
		else if(path.equals("/nouveau.location")) {
			List<Voiture> voitures = voitureRepository.findAllDisponible();
			List<Client> clients = clientRepository.findAll();
			request.setAttribute("voitures", voitures);
			request.setAttribute("clients", clients);
			request.getRequestDispatcher("views/location/ajouter.jsp").forward(request, response);
		}
		else if(path.equals("/enregistrer.nouveau.location") && request.getMethod().equals("POST")) {
			Long voiture_id = Long.parseLong(request.getParameter("voiture"));
			Long client_id = Long.parseLong(request.getParameter("client"));
			Date date_debut = Date.valueOf(request.getParameter("dateDebut"));
			Date date_fin = Date.valueOf(request.getParameter("dateFin"));
			
//			Conversion en milliseconde
			long timeInMillisecondeDateDebut = date_debut.getTime();
			long timeInMilliSecondeDateFin = date_fin.getTime();
			
//			calcule de la difference en milliseconde
			long differenceInMilliseconde = timeInMilliSecondeDateFin - timeInMillisecondeDateDebut;
			
//			conversion en jour
			long differenceInDays = differenceInMilliseconde / (24* 60*60*1000);
			
			if(differenceInDays<0) {
				response.sendRedirect("nouveau.location");
			}else {
				Voiture voiture = voitureRepository.findOne(voiture_id);
				Client client = clientRepository.findOne(client_id);
				
				Double prix_total = differenceInDays * voiture.getPrixJournalier();
				
				voitureRepository.rent(voiture_id);
				locationRepository.create(new Location(voiture, client, date_debut, date_fin, prix_total));
				response.sendRedirect("location");
			}	
		}
		else if(path.equals("/terminer.location")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Location location = locationRepository.findOne(id);
			Voiture voiture = location.getVoiture();
			voitureRepository.setDisponible(voiture.getId());
			locationRepository.terminer(id);
			request.setAttribute("message", "Location terminer avec success");
			response.sendRedirect("location");
		}else if(path.equals("/supprimer.location")) {
			Long id = Long.parseLong(request.getParameter("id"));
			locationRepository.delete(id);
			response.sendRedirect("location");
		}else {
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
