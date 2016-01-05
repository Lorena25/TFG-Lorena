package es.upm.dit.gsi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.wpi.disco.Agent;
import edu.wpi.disco.Disco;
import edu.wpi.disco.Interaction;
import edu.wpi.disco.User;
import edu.wpi.disco.Agenda.Plugin;
import edu.wpi.disco.lang.Propose;
import edu.wpi.disco.lang.Propose.ShouldNot;
import edu.wpi.disco.lang.Utterance;
import edu.wpi.disco.plugin.ProposeShouldNotPlugin;
import es.upm.dit.gsi.agent.Agent1;
import es.upm.dit.gsi.agent.Agent1.MyAgent;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgentServlet
 */

public class AgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected String path = null;
	private String[] args;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //guardamos el parametro inicial, que es la URL referente al modelo a cargar, tenemos varios modelos
    //uno para cada elemento a configurar
    public void init(ServletConfig servletConfig) throws ServletException{
        this.path = servletConfig.getInitParameter("path");
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession misession= (HttpSession)request.getSession();
		//Creamos sesion y guardamos el agente1 en una variable
		if ( (Agent1)misession.getAttribute("misessionagent") == null){
			Agent1 agente1 = new Agent1();
			misession.setAttribute("misessionagent", agente1);
		}
		
		Agent1 agente= (Agent1)misession.getAttribute("misessionagent");
		Interaction interaction = agente.interact2();
		Disco disco = interaction.getDisco();
		interaction.load(path);
		agente.agente(interaction, disco);
		ArrayList<String> menu= agente.menu(interaction);
		System.out.println(menu);
		
		//Creamos el json para mandar la respuesta al jsp
		Gson gson = new Gson();
		String json = gson.toJson(menu);
		misession.setAttribute("json",json);
		request.getRequestDispatcher("/Agent.jsp").forward(request, response);	
		
		
		misession.invalidate();
		
		//System.out.println(agente.agente(interaction, disco));
		//agente.user(Propose.Should.newInstance(disco, true, interaction.getTaskClass("Borrow").newInstance()),
		           // null argument allows plan recognition to determine contributes
		       //    null, interaction); 
		//agente.agent(interaction);
		 //agente.lorena(interaction, disco);
		//System.out.println(Propose.Should.newInstance(disco, true, interaction.getTaskClass("Borrow").newInstance()));
		
		//interaction.occurred(true, interaction.getTaskClass("GoToLibrary").newInstance(), null);
  //  System.out.println(Propose.Should.newInstance(disco, true, interaction.getTaskClass("GoToLibrary").newInstance())); 
	//agente.agent(interaction);
   // agente.lorena(interaction, disco);
	
       
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
