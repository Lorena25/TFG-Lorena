package es.upm.dit.gsi.agent;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import es.upm.dit.gsi.*;
import edu.wpi.cetask.*;
import edu.wpi.disco.*;
import edu.wpi.disco.Agenda.Plugin;
import edu.wpi.disco.lang.*;
import edu.wpi.disco.Agent;
import net.sf.json.JSONObject;

/**
 * This class illustrates the appropriate methods to use to embed Disco 
 * as a component of a larger software system.  
 * <p>
 * Important note re threads:  This example is thread-safe because it only
 * uses methods documented as thread-safe in {@link Interaction} and {@link Actor}.
 * (Note newInstance() methods for built-in utterance types are all thread-safe.)
 * <p>
 * Important note re turn-taking: Disco does not include a built-in algorithm for
 * turn-taking, as this is a complex topic and typically depends heavily on the
 * application context, e.g., spoken versus text versus graphical interfaces.
 * (The Disco debugging console provides a simple "automatic" mode in which each
 * user turn consists of zero or more non-utterance actions terminated by an 
 * utterance---this can be turned off using the 'next false' command---see
 * console 'help'). 
 * <p>
 * Invoke the main method below to run this example.
 */
public class Agent1 {

   public  void main (String[] args) { new Agent1(); }
   //Interaction interaction = interact2();
private final Agent agent = new MyAgent("agent");
private final User user = new User("user");
private ArrayList<String> menu= new ArrayList<String>();
  // private final Disco disco = interaction.getDisco(); 
   //private final boolean 
     //    guess = interaction.getProperty("interaction@guess", true),
       // retry = interaction.getProperty("interaction@retry", true);
public String agente (Interaction interaction, Disco disco) {
    
  
       user(Propose.Should.newInstance(disco, true, newInstance("Borrow", interaction)),
             null, interaction); 

       agent(interaction);
       
      
       // print out formatted choices on system console
    // menu(interaction);
       // choose second utterance from menu
      // Plugin.Item item = items1.get(1);
     //  user(item.task, item.contributes, interaction);
     return "";
}
public String segunda(Interaction interaction, Disco disco){
       user(newInstance("GoToLibrary", interaction), null, interaction);


       agent(interaction);

  
  menu(interaction);
 return "";
}

public String tercera (Interaction interaction, Disco disco){
	


       user(new Propose.What(disco, true, 
             newInstance("ChooseBook", interaction), 
             "input", 
             interaction.eval("new Book(\"Sawyer\", \"Mindscan\")", "ComponentExample")),
             null, interaction);

       agent(interaction);

       // example of generating menu of possible user utterances, e.g., for GUI
       // or to restrict grammar for speech recognition

   //  menu(interaction);
       // choose second utterance from menu
        //item = items.get(0);
      // user(item.task, item.contributes, interaction);

       return "";
    }

public ArrayList<String> menu (Interaction interaction){
	  menu.clear();
	List<Plugin.Item> items = interaction.getExternal().generate(interaction);
     // print out formatted choices on system console
	 for (Plugin.Item item : items) {
    	 for (int i = 0; i < 1; i++) {
    	String p = interaction.format(item, true, true);
    	System.out.println("MENU ROUTER: "+p);
    	menu.add(i, p);
    	 }
     }
	
	 return menu;
     
}
 
   public void lorena(Interaction interaction, Disco disco) {
     
	   	Boolean retry = interaction.getProperty("interaction@retry", true);
	         List<Plugin.Item> items = user.generate(interaction);
	         
	        // agent.occurred(interaction, items.get(0), retry);
	        System.out.println(items.get(0));
	         // print out formatted choices on system console
	         for (Plugin.Item item : items) {
	            System.out.println("MENU: "+interaction.format(item, true, true));
	            ;}
	         // choose second utterance from menu
	         Plugin.Item item = items.get(0);
	        // System.out.println(items.get(0));
	        user(item.task, item.contributes, interaction);
	      
	       System.out.println(items);
	      
	        
	         // if optional Disco console window used, go to window now and try typing in 
	         // commands, such as 'history'
	      
	         // next line is here only to keep optional Disco console window open in 
	         // this demo until you type 'quit' or close it
	       
	      }
   
   public Interaction interact2() { 
	   Interaction interaction = new Interaction(agent, new User("user"));
	  return interaction;
	   } 
   


  public boolean agent (Interaction interaction) {      
       //see simple model for agent turn at Agent.respond()
      return interaction.getSystem().respond(interaction, false, interaction.getProperty("interaction@guess", true),
    		  interaction.getProperty("interaction@retry", true));
   }
   
   public void user (Task task, Plan contributes, Interaction interaction) {
      interaction.occurred(true, task, contributes);
   }
   
   public Task newInstance (String task, Interaction interaction) { 
      return interaction.getTaskClass(task).newInstance();
   }
   

  
  public class MyAgent extends Agent {
      
     public MyAgent (String name) { super(name); }
     
     public void occurred (Interaction interaction, Plugin.Item item, boolean retry) { 
         synchronized (interaction) { // typically used in dialogue loop
            interaction.occurred(this == interaction.getExternal(), 
                  item.task, item.contributes);
            if ( item.task instanceof Utterance ) { // after occurred
               lastUtterance = (Utterance) item.task;
               say(interaction, (Utterance) item.task);
            }
            if ( retry ) retry(interaction.getDisco());  // see also in respond
         }
      }
      
     // @Override
      public void say (Interaction interaction, Utterance utterance) {
         // here is where you would put natural language generation
         // and/or pass utterance string to TTS or GUI
         // for now we just call Disco's default formatting and print
         // out result on system console
    	System.out.println();
         System.out.println("AGENT: "+interaction.format(utterance));
      }
   }
}
