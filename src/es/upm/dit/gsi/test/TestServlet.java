package es.upm.dit.gsi.test;

import javax.servlet.http.HttpSession;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import edu.wpi.disco.Disco;
import edu.wpi.disco.Interaction;
import es.upm.dit.gsi.WebAgentServlet;
import es.upm.dit.gsi.agent.RouterAgent;
import es.upm.dit.gsi.agent.RouterAgent.MyAgent;
import static junit.framework.Assert.*;

import com.meterware.servletunit.*;
import com.meterware.httpunit.*;



public class TestServlet {

	@SuppressWarnings("deprecation")
	@Test
	   public void testServlet() throws Exception , NullPointerException {
		
		   ServletRunner sr = new ServletRunner();
		   sr.registerServlet( "myServlet", WebAgentServlet.class.getName() );
		   ServletUnitClient sc= sr.newClient(); 
		   WebRequest request = new PostMethodWebRequest("http://test.meterware.com/myServlet");
		   request.setParameter("lorena", "lorena");
		   InvocationContext ic = sc.newInvocation(request);
		   WebResponse response= sc.getResponse(ic);
		   
		   
		   assertNotNull("no response received", response);
		   assertNull("a session already exsist", ic.getRequest().getSession(false));
		   assertNotNull("session was not created", ic.getRequest().getSession(true));
		   
		   //Creamos session
		   RouterAgent agente1 = new RouterAgent();
		   HttpSession session=(HttpSession)ic.getRequest().getSession(false);
		   session.setAttribute("misession", agente1);
		   agente1=(RouterAgent)session.getAttribute("misession");
			
			//Parámetros necesarios para las pruebas
		   Interaction interaction = agente1.interaction();
		   Disco disco = interaction.getDisco();
		   String taskPath = session.getServletContext().getResource("/WebContent/WEB-INF/models/Agent.xml").getPath();
		   interaction.load(taskPath);
		   agente1.agente(interaction, disco);
		   MyAgent agent = agente1.new MyAgent("agent");
			
			//pruebas agente
			assertEquals("Should we achieve WebAgent?", agent.getAtributo());
			assertTrue(interaction.getSystem().respond(interaction, false, interaction.getProperty("interaction@guess", true), interaction.getProperty("interaction@retry", true)));
			assertEquals("No.",agente1.menu(interaction).get(0));
			assertEquals("Yes.",agente1.menu(interaction).get(1));
			
			agente1.agente1(interaction, disco);
			
			assertEquals("Should we set up the basic operation of a router?", agent.getAtributo());
			assertEquals("Let's achieve WebAgent.",agente1.menu(interaction).get(0));
			assertEquals("No.",agente1.menu(interaction).get(1));
			assertEquals("Yes.",agente1.menu(interaction).get(2));
			
			agente1.reject(interaction, disco);
			assertEquals("Ok.", agent.getAtributo());
			
			agente1.agente3(interaction, disco);
			assertEquals("Do you want to connect router?", agent.getAtributo());
	
			
			


	}
}
