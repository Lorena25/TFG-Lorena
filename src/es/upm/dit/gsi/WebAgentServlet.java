package es.upm.dit.gsi;


import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
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
 * Servlet implementation class WebAgentServlet
 */

public class WebAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected String path = null;
	private String misession= null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebAgentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig servletConfig) throws ServletException{
        this.path = servletConfig.getInitParameter("path");
     }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= (HttpSession)request.getSession();
		//Creamos sesion y guardamos el agente1 en una variable
		if ( (RouterAgent)session.getAttribute(misession) == null){
			RouterAgent agente1 = new RouterAgent();
			session.setAttribute("misession", agente1);
		}
		

		json(request.getParameter("userAgent"),request.getParameter("type"),request.getParameter("q"));
		/*RouterAgent agente= (RouterAgent)session.getAttribute("misession");
		Interaction interaction = agente.interaction();
		Disco disco = interaction.getDisco();
		String taskPath = session.getServletContext().getResource(path).getPath();
		interaction.load(taskPath);
		agente.agente(interaction, disco);
		agente.menu(interaction);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public JSONObject json(String userAgent, String type, String q){
		JSONObject json= new JSONObject();
		JSONObject dialog= new JSONObject();
		json.put("userAgent", userAgent);
		json.put("type", type);
		json.put("mood", "");
		json.put("q", q);
		dialog.put("dialog", json);
		System.out.println(dialog);
	return json;
	
	}
		
	
	}
	
	

