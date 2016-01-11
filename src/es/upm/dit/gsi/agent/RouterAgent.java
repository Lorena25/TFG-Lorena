package es.upm.dit.gsi.agent;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cetask.*;
import edu.wpi.disco.*;
import edu.wpi.disco.Agenda.Plugin;
import edu.wpi.disco.lang.*;
import edu.wpi.disco.Agent;


public class RouterAgent {
	
	 public  void main (String[] args) { new RouterAgent(); }
	 
	 
	 
private ArrayList<String> menu= new ArrayList<String>();
private  String agente = "";	 
	 
	 
public void agente(Interaction interaction, Disco disco){
	
	disco.addTop(interaction.getTaskClass("SwitchRouter").newInstance());
	interaction.doTurn(true);	
}
public void lore(Interaction interaction, Disco disco){
	interaction.run();
	disco.addTop(interaction.getTaskClass("ConnectRouter").newInstance());
	interaction.doTurn(true);
	
}
public void reject(Interaction interaction, Disco disco){
	
	interaction.run();
	interaction.doTurn(true);
	
}
public Interaction interaction() { 
	   Interaction interaction = new Interaction(new MyAgent("agent"), new User("user"));
	  return interaction;
	   } 


public ArrayList<String> menu (Interaction interaction){
	  menu.clear();
	List<Plugin.Item> items =interaction.getExternal().generate(interaction);
	
   // print out formatted choices on system console
	System.out.println(items);
	 for (Plugin.Item item : items) {
  	 for (int i = 0; i < 1; i++) {
  	String p = interaction.format(item, true, true);
  	System.out.println("MENU ROUTER: "+p);
  	menu.add(i, p);
  	 }
   }
	
	 return menu;
   
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
     
     public void say(Interaction interaction, Utterance utterance) {
        // here is where you would put natural language generation
        // and/or pass utterance string to TTS or GUI
        // for now we just call Disco's default formatting and print
        // out result on system console
   	System.out.println();
   	agente= interaction.format(utterance);
        System.out.println("AGENT: "+agente);
     
     }
     
     public String getAtributo (){
         return agente;
    }
     
    
     
  }
}
