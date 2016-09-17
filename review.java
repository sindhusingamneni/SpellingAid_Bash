package functionality;

import java.io.IOException;

import IO.level;
import visual.ImageAndVideo;
import visual.statsAndScore;

public class review implements checkLogic{

	
	festival textToSay = new festival();
	buttonsFunctionality checkingAnswerCountReviewField = new buttonsFunctionality();
	level getReviewWord = new level();
	ImageAndVideo image = new ImageAndVideo();
	@Override
	public void chanceOne(String ans, String word) throws IOException {
		
		if (ans.equalsIgnoreCase(word)){
			
			textToSay.festivalSaysText("correct");
			
			image.correctImage();
			
			// append onto text area the next word to be spelt.
			
			
			String currentReviewWord = getReviewWord.getReviewCurrentWord();
			textToSay.festivalSaysText(currentReviewWord);
			
			
			
			checkingAnswerCountReviewField._checkingReviewAnswerCount=0;
			// remove from the failed list
			
		} else {
		
			textToSay.festivalSaysText("incorrect, try once more");
			for(int i=0; i< word.length(); i++){
			textToSay.festivalSaysText(word.charAt(i)+"");
			}
			image.incorrectTryOnceMoreImage();
		
		
		
		
	}
		
		
		
	}


	@Override
	public void chanceTwo(String ans, String word) throws IOException {
		
		if (ans.equalsIgnoreCase(word)){
			
			textToSay.festivalSaysText("correct");
			
			image.correctImage();
		
		// remove from the failed list
		
		
	} else {
	
	
	
		textToSay.festivalSaysText("incorrect");
		
		image.incorrectImage();
	
	
	
	}
		// append onto text area the next word to be spelt.
		
		
					String currentReviewWord = getReviewWord.getReviewCurrentWord();
					textToSay.festivalSaysText(currentReviewWord);
					
					
					
					checkingAnswerCountReviewField._checkingReviewAnswerCount=0;
	
	}	
}
