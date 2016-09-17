package functionality;

import java.io.IOException;

import IO.level;
import visual.ImageAndVideo;
import visual.endOfLevel;
import visual.statsAndScore;

public class newSpelling implements checkLogic{

// could have new spelling extend buttonsFunctionality
	
	public int _wordCount = 1;
	
	festival textToSay = new festival();
	level score = new level();
	level statsNum =  new level();
	level getWord = new level();
	ImageAndVideo image = new ImageAndVideo();
	endOfLevel end = new endOfLevel();
	buttonsFunctionality checkingAnswerCountField = new buttonsFunctionality();
	@Override
	public void chanceOne(String ans, String word) throws IOException {
		
		if (ans.equalsIgnoreCase(word)){
		
		_wordCount++;
			
			textToSay.festivalSaysText("correct");
			
			image.correctImage();
			
			if (_wordCount == 10){
				
				end.endOfLevelProcess();
				
				
				
			}else {
			image.image(_wordCount);
			
			
			
			
				
			
			// festival says next word
			// get next word from IO
			String currentWord = getWord.getCurrentWord();
			textToSay.festivalSaysText(currentWord);
			}
			score.increaseScore();
			statsNum.increaseStatsNum();
			
			checkingAnswerCountField._checkingAnswerCount=0;
			//stats and score updated in GUI.
		} else {
			
			
			textToSay.festivalSaysText("incorrect, try once more");
			
			image.incorrectTryOnceMoreImage();
			
			
			
			
		}
		
		
		
		
		
	}


	@Override
	public void chanceTwo(String ans, String word) throws IOException {
		
		if (ans.equalsIgnoreCase(word)){
			
			textToSay.festivalSaysText("correct");
			
			image.correctImage();
			
			
			
		} else {
			textToSay.festivalSaysText("incorrect");
			
			image.incorrectImage();
			// add word to failed words file through level IO
			
		}
		_wordCount++;
		if (_wordCount ==10 ){
			end.endOfLevelProcess();
		}else{
		image.image(_wordCount);
		
		// festival says next word
		// get next word from IO
		String currentWord = getWord.getCurrentWord();
		textToSay.festivalSaysText(currentWord);
		}
		checkingAnswerCountField._checkingAnswerCount=0;
	}

	
}
