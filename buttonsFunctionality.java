package functionality;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import IO.level;
import visual.Review;
import visual.mainMenu;
import visual.newQuiz;
import visual.playVideo;
import visual.settingsVisual;

public class buttonsFunctionality extends JFrame implements ActionListener {

	public int _checkingAnswerCount=0;
	public int _checkingReviewAnswerCount=0;
	newSpelling check = new newSpelling();
	festival textToSay = new festival();
	level getWord = new level();
	level getReviewWord = new level();
	
	
	public void submitButton() {
		// get text from textbox
		String userInput= txt.getText();
		
		
		if (!(userInput.matches("[a-zA-Z]+"))){

			if (!(userInput.matches(""))){
				JOptionPane.showMessageDialog(null, "Enter letters please");
			} else{
				_checkingAnswerCount++;
			
		
		
		if(_checkingAnswerCount==1){
		
		check.chanceOne(userInput, word);
		
		}
		
		if(_checkingAnswerCount==2){
			
			check.chanceTwo(userInput, word);
		}
		
		
		
		}
		}
		txt.setText("");
			
		
	}
	// created a new submit review button
	public void submitForReviewButton() {
		String userReviewInput= txt.getText();
		
		
		if (!(userReviewInput.matches("[a-zA-Z]+"))){

			if (!(userReviewInput.matches(""))){
				JOptionPane.showMessageDialog(null, "Enter letters please");
			} else{
				_checkingReviewAnswerCount++;
		
				if(_checkingReviewAnswerCount==1){
					
					check.chanceOne(userReviewInput, word);
					
					}
					
					if(_checkingReviewAnswerCount==2){
						
						check.chanceTwo(userReviewInput, word);
					}
					
					
					
					}
					}
					txt.setText("");
		
		
	}
	
	public void relistenButton() throws IOException {
		
		String currentWord = getWord.getCurrentWord();
		textToSay.festivalSaysText(currentWord);
		
		
	}
	public void relistenForReviewButton() throws IOException {
		
		String currentReviewWord = getReviewWord.getReviewCurrentWord();
		textToSay.festivalSaysText(currentReviewWord);
		
		
	}
	
	public void backToMainButton() {
		
		mainMenu menu = new mainMenu();
		menu.createMenu();
		
	}
	
    public void playVideoButton() {
    	playVideo menu = new playVideo();
		menu.createPlayWindow();
		
		
	}
    
    public void clearButton() throws FileNotFoundException {
		settings clearStats = new settings();
		clearStats.clear();
		
		
	}
	
    public void festivalOneButton() {
		settings festivalOne = new settings();
		festivalOne.festivalOneVoice();
		
		
	}
	
    public void festivalTwoButton() {
		
		settings festivalTwo = new settings();
		festivalTwo.festivalTwoVoice();
		
	}
	
    public void nextLevelButton() {
		level nextLevel = new level();
		nextLevel.level();
		
		
	}
	
	
    public void practiceButton() {
		newQuiz practice = new newQuiz();
		practice.newSpellingSetUp();
		
		
	}
	
	
    public void playAndPauseButton() {
		playVideo playAndPause = new playVideo();
		playAndPause.playAndPauseControl();
		
		
	}
    public void newSpellingButton() {
		// sets up the new quiz initial in the GUI with word 1 of 10
    	newQuiz newSpelling = new newQuiz();
		newSpelling.newSpellingSetUp();
		
	}
	
    public void reviewButton() {
    	Review review = new Review();
		review.newReviewSetUp();
		
		
	}
    private void settingsButton() {
		settingsVisual settings = new settingsVisual();
		settings.settingsSetUp();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== submit){
			submitButton();
			
		}else if (e.getSource()== relisten){
			relistenButton();
			
		}else if (e.getSource()== backToMain){
			backToMainButton();
			
		}else if (e.getSource()== playVideo){
			playVideoButton();
			
		}else if (e.getSource()== clear){
			clearButton();
			
		}else if (e.getSource()== festivalOneButton){
			festivalOneButton();
			
		}else if (e.getSource()== festivalTwoButton){
			festivalTwoButton();
			
		}else if (e.getSource()== nextLevel){
			nextLevelButton();
			
		}else if (e.getSource()== practice){
			practiceButton();
			
		}else if (e.getSource()== playAndPause){
			playAndPauseButton();
			
		}else if (e.getSource()== newSpelling){
			newSpellingButton();
			
		}else if (e.getSource()== review){
			reviewButton();
			
		}else if (e.getSource()== settings){
			settingsButton();
			
		}
	
	}
	
	
	
}
