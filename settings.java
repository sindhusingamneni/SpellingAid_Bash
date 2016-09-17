package functionality;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class settings {

	public void clear() throws FileNotFoundException{
		
		PrintWriter writer = new PrintWriter("attemptedwords");
		writer.print("");
		writer.close();

		writer = new PrintWriter("failedlist");
		writer.print("");
		writer.close();
		
		// clearing all arraylists or what ever in files IO
				
	}
	
	public void festivalOneVoice(){
		
		festival changeVoiceOne = new festival();
		changeVoiceOne._festivalVoice= "voice_kal_diphone";
		
		
	}
	
	public void festivalTwoVoice(){
		
		festival changeVoiceTwo = new festival();
		changeVoiceTwo._festivalVoice= "voice_rab_diphone";
		
	}

	
	
	
	
}
