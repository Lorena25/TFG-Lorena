package es.upm.dit.gsi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import atg.taglib.json.util.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
import es.upm.dit.gsi.agent.RouterAgent;
import es.upm.dit.gsi.agent.RouterAgent.MyAgent;


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
	ArrayList<String> menu= new ArrayList<String>();
	String hola="";
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
		
		HttpSession session= (HttpSession)request.getSession();
		//Creamos sesion y guardamos el agente1 en una variable
		if ( (RouterAgent)session.getAttribute("misessionagent") == null){
			RouterAgent agente1 = new RouterAgent();
			session.setAttribute("misessionagent", agente1);
		}
		
		RouterAgent agente= (RouterAgent)session.getAttribute("misessionagent");
		Interaction interaction = agente.interaction();
		Disco disco = interaction.getDisco();
		interaction.load(path);
		agente.agente(interaction, disco);
		
		String nombre=(String)request.getParameter("submit");
		System.out.println(nombre);
		if(nombre!=null){
		if(nombre.equals(menu.get(1))){
			System.out.println("LORENA");
			agente.lore(interaction, disco);
			menu=agente.menu(interaction);
			MyAgent agent = agente.new MyAgent("agent");
			hola=agent.getAtributo();
			session.setAttribute("agente", hola);
			session.setAttribute("json1", menu);	
			response.sendRedirect("Agent.jsp");
		}else if(nombre.equals(menu.get(0))){
			agente.reject(interaction, disco);
			MyAgent agent = agente.new MyAgent("agent");
			hola=agent.getAtributo();
			session.setAttribute("agente", hola);
			response.sendRedirect("Agent.jsp");
		}
		
		}else{
		menu= agente.menu(interaction);
		MyAgent agent = agente.new MyAgent("agent");
		hola=agent.getAtributo();
		//System.out.println(menu);
		//interaction.doTurn();
		//agente.segunda(interaction, disco);
		session.setAttribute("agente", hola);	
		session.setAttribute("json", menu);	
		response.sendRedirect("Agent.jsp");
		
		}
	}
		
		
		//session.invalidate();
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
