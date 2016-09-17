package functionality;

import java.io.IOException;

public class festival {

public String _festivalVoice= "voice_rab_diphone";	
	public void festivalSaysText( String textToSay) throws IOException{
		String command = "(_festivalVoice)";
		ProcessBuilder pb = new ProcessBuilder ("/bin/bash", "-c", command);
		pb.start();
		
		
		command = "(SayText " + textToSay + ")";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		
		
		
		
	}
	
	
	
	
	
	
	
}
