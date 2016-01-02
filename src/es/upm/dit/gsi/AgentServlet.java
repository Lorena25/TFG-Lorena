package es.upm.dit.gsi;

import java.io.IOException;
import java.util.List;

import edu.wpi.disco.Agent;
import edu.wpi.disco.Interaction;
import edu.wpi.disco.User;
import edu.wpi.disco.Agenda.Plugin;
import edu.wpi.disco.lang.Propose;
import es.upm.dit.gsi.agent.Agent1;
import es.upm.dit.gsi.agent.Agent1.MyAgent;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AgentServlet
 */

public class AgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected String path = null;

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
		Interaction interaction = new Interaction(new Agent("agent"), new User("user"));
		interaction.load(path);
		agente.user(Propose.Should.newInstance(interaction.getDisco(), true, interaction.getTaskClass("Borrow").newInstance()),
		           // null argument allows plan recognition to determine contributes
		           null); 
		agente.agent();
        List<Plugin.Item> items = interaction.getExternal().generate(interaction);
        for (Plugin.Item item : items) 
           System.out.println("MENU: "+interaction.format(item, true, true));
        // choose second utterance from menu
        Plugin.Item item = items.get(1);
        agente.user(item.task, item.contributes);

		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
