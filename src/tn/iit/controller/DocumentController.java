package tn.iit.controller;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.iit.entities.Enseignant;
import tn.iit.persistance.EnseignantsDao;
import tn.iit.utils.Pdf;


/**
 * Servlet implementation class DocumentController
 */
@WebServlet("/DocumentController")
public class DocumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnseignantsDao enseignantDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DocumentController() {

	}

	@Override
	public void init() {
		enseignantDAO = new EnseignantsDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id =request.getParameter("id");
	    Enseignant ens = enseignantDAO.findEnsById(id); 
	 
	    ens.setAuthorized(1);
	    
	    enseignantDAO.updateEnseignant(ens);
	    ByteArrayOutputStream outputStream = Pdf.generatePDF(ens);
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=" + ens.getNom() + "_" + ens.getPrenom() + "_authorization.pdf");

	    OutputStream out = response.getOutputStream();
	    outputStream.writeTo(out);
	    out.flush();
	    out.close();
	  

	}

}