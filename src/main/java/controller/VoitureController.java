package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import interfaces.IVoiture;
import model.Voiture;
import repository.VoitureRepository;

/**
 * Servlet implementation class VoitureController
 */
@WebServlet(name="voitureController", urlPatterns = {"/voiture","*.voiture","*.*.voiture"})
@MultipartConfig
public class VoitureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IVoiture repository;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		repository = new VoitureRepository();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoitureController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/voiture")) {
			int page,nbElement;
			List<Voiture> voitures = new ArrayList<>();
			String motCle = request.getParameter("motCle");
			
			if(motCle == null || motCle.isEmpty()) {
				voitures = repository.findAll();
			}else {
				voitures = repository.findByMotCle(motCle);
			}
			
			if(request.getParameter("page") == null) {
				page = 1;
			}else {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			if(request.getParameter("nbElement") == null ) {
				nbElement = 5;
			}else {
				nbElement = Integer.parseInt(request.getParameter("nbElement"));
			}
			
			int startIndex = (page - 1) * nbElement;
			int endIndex = Math.min(startIndex + nbElement, voitures.size());
			
			List<Voiture> paginatedVoitures = voitures.subList(startIndex, endIndex);
			
			request.setAttribute("voitures", paginatedVoitures);
			
			int totalPages = (int) Math.ceil((double) voitures.size() / nbElement );
			
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("nbElement", nbElement);
			request.setAttribute("motCle", motCle);
			
			request.getRequestDispatcher("views/voiture/list.jsp").forward(request, response);							
		}
		else if(path.equals("/nouveau.voiture")) {
			request.getRequestDispatcher("views/voiture/ajouter.jsp").forward(request, response);
		}
		else if(path.equals("/enregistrer.nouveau.voiture") && request.getMethod().equals("POST")) {
			HashMap<String, String> fields = new HashMap<>();

			try {
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				// Configure a repository (to ensure a secure temp location is used)
				ServletContext servletContext = this.getServletConfig().getServletContext();
				File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				factory.setRepository(repository);
				
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);
				
				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				
				while (iter.hasNext()) {
				    FileItem item = iter.next();

				    if (item.isFormField()) {
				    	fields.put(item.getFieldName(), item.getString());
				    } 
				    else {
				    	String illustration = item.getName();
				    	fields.put("illustration", illustration);
				    	
				    	if(illustration == null || illustration.equals("")) {
				    		break;
				    	}else {
				    		Path filePath = Paths.get(illustration);
				    		String storePath = this.getServletContext().getRealPath("/uploads");
				    		File uploadFile = new File(storePath + "/" + filePath.getFileName());
				    		item.write(uploadFile);
				    	}
				    }    
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String marque = fields.get("marque");
			String modele = fields.get("modele");
			String couleur = fields.get("couleur");
			int annee = Integer.parseInt(fields.get("annee"));
			Double prixJournalier = Double.parseDouble(fields.get("prixJournalier"));
			String illustration = fields.get("illustration");
			String description = fields.get("description");
			int nbPlace = Integer.parseInt(fields.get("nbPlace"));
			
			this.repository.create(new Voiture(marque,modele,couleur,prixJournalier,annee,illustration,description,nbPlace));			
			response.sendRedirect("voiture");

		}else if(path.equals("/modifier.voiture")) { 
			Long id = Long.parseLong(request.getParameter("id"));
			Voiture voiture = repository.findOne(id);
			request.setAttribute("voiture", voiture);
			request.getRequestDispatcher("views/voiture/modifier.jsp").forward(request, response);
		}
		else if(path.equals("/enregistrer.modifier.voiture")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String marque = request.getParameter("marque");
			String modele = request.getParameter("modele");
			String couleur = request.getParameter("couleur");
			int annee = Integer.parseInt(request.getParameter("annee"));
			Double prixJournalier = Double.parseDouble(request.getParameter("prixJournalier"));
			int nbPlace = Integer.parseInt(request.getParameter("nbPlace"));
			String description = request.getParameter("description");
			
			Voiture voiture = repository.findOne(id);
			voiture.setMarque(marque);
			voiture.setModele(modele);
			voiture.setCouleur(couleur);
			voiture.setAnnee(annee);
			voiture.setPrixJournalier(prixJournalier);
			voiture.setNbPlace(nbPlace);
			voiture.setDescription(description);
			
			repository.update(voiture);
			response.sendRedirect("voiture");
		}
		else if(path.equals("/supprimer.voiture")) {
			Long id = Long.parseLong(request.getParameter("id"));
			repository.delete(id);
			response.sendRedirect("voiture");
		}
		else if(path.equals("/regarder.voiture")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Voiture voiture = repository.findOne(id);
			request.setAttribute("voiture", voiture);
			request.getRequestDispatcher("views/voiture/regarder.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
