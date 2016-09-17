import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class spelling_aid extends JFrame implements ActionListener {
	// setting up all the data structures required
	private JButton NewSpellingQuiz;
	private JButton ReviewMistakes;
	private JButton ViewStatistics;
	private JButton Clear;
	private JButton NextWord;
	private JTextField txt;
	private JTextArea txtArea;
	private JButton Check;
	private ArrayList<String> _list = new ArrayList<String>();
	private ArrayList<String> _attemptedWords = new ArrayList<String>();
	private ArrayList<String> _failed = new ArrayList<String>();
	private ArrayList<String> _failedUnchanged = new ArrayList<String>();
	private ArrayList<String> _faulted = new ArrayList<String>();
	private ArrayList<String> _mastered = new ArrayList<String>();

	// setting up all the required fields
	String _randomWord;
	boolean _checkStatus= false;
	int _i=0;
	boolean _correct= false;
	boolean _failedboolean= false;
	int _loopNumber=3;
	int _count=1;
	boolean _reviewBoolean=false;
	int _numOfWords;
	int _overallCount=0;

	public spelling_aid() {
		super("Spelling Aid");
		txt = new JTextField(30);
		txtArea = new JTextArea(20,20);

		JPanel jPanel = new JPanel();

		NewSpellingQuiz = new JButton("New Spelling Quiz");
		ReviewMistakes = new JButton("Review Mistakes");
		ViewStatistics = new JButton("View Statistics");
		Clear = new JButton("Clear");
		Check = new JButton("Check");
		NextWord = new JButton("Next Word");



		setSize(400, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(jPanel);

		// adding the buttons to the GUI
		jPanel.add(NewSpellingQuiz);
		jPanel.add(ReviewMistakes);
		jPanel.add(ViewStatistics);
		jPanel.add(Clear);
		jPanel.add(txtArea);
		jPanel.add(txt);
		jPanel.add(Check);
		jPanel.add(NextWord);


		// adding action listeners to all these buttons
		NewSpellingQuiz.addActionListener(this);
		ReviewMistakes.addActionListener(this);
		ViewStatistics.addActionListener(this);
		Clear.addActionListener(this);
		Check.addActionListener(this);
		txt.addActionListener(this);
		NextWord.addActionListener(this);


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String inputText = txt.getText();

		// if the user has pressed new quiz
		if (e.getSource()== NewSpellingQuiz){

			_i=0;
			// get the words from the files and add them to the
			//corresponding arraylists
			inputMethodWordList();
			inputMethodAttemptedWords();
			inputMethodFailedWords();
			inputMethodFailedUnchangedWords();
			inputMethodMasteredWords();
			inputMethodFaultedWords();
			try {
				newQuizMenu();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// call the new quiz method
			try {
				_count=1;
				newSpellingQuiz();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			// if the user has pressed the review button
		}else if (e.getSource()== ReviewMistakes){
			_count=1;
			// getting the words from the files and adding them to the
			// corresponding arraylists
			inputMethodWordList();
			inputMethodAttemptedWords();
			inputMethodFailedWords();
			inputMethodFailedUnchangedWords();
			inputMethodMasteredWords();
			inputMethodFaultedWords();
			_reviewBoolean=true;
			// call the review mistakes method
			
			if (_failed.size()==0){
				JOptionPane.showMessageDialog(null, "no words to review. Please do a new spelling quiz");
				try {
					writeToAttemptedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					writeToFaultedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					writeToMasteredWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				try {
					writeToFailedUnchangedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				
				_attemptedWords.clear();
				_failed.clear();
				_faulted.clear();
				_mastered.clear();
				_failedUnchanged.clear();
				_overallCount=0;
				_reviewBoolean=false;
			}else{
			try {
				reviewMistakes();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			}
			// if the user has pressed the view statistics button
		}else if (e.getSource()== ViewStatistics){
			// getting the words from the files and storing them in
			// the corresponsing arraylists
			inputMethodWordList();
			inputMethodAttemptedWords();
			inputMethodFailedWords();
			inputMethodFailedUnchangedWords();
			inputMethodMasteredWords();
			inputMethodFaultedWords();
			// call the view statistics method
			if (_attemptedWords.size()==0){
				JOptionPane.showMessageDialog(null, "No words to show. please do new spelling quiz");
			}else{
			viewStatistics();
			}
			// clearing all the arraylists and writing to the
			// files again
			try {
				writeToAttemptedWordsFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			try {
				writeToFaultedWordsFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			try {
				writeToMasteredWordsFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			try {
				writeToFailedWordsFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			try {
				writeToFailedUnchangedWordsFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			_attemptedWords.clear();
			_failed.clear();
			_faulted.clear();
			_mastered.clear();
			_failedUnchanged.clear();
			// if the user has pressed the clear button
		}else if (e.getSource()== Clear){
			// call the clear method
			try {
				clear();
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			}
			// if the user wants to check their spelling
			// call the checkword method
		}else if (e.getSource()== Check){

			_checkStatus=true;

			try {
				checkWord(_randomWord);

			} catch (IOException e1) {

				e1.printStackTrace();
			}
			// write the words back to the files from the corresponding
			// arraylists while in review so we dont write to the failedlist.
			if ((_reviewBoolean==true)&&((_overallCount==3)||(_overallCount==_loopNumber))){


				try {
					writeToAttemptedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					writeToFaultedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					writeToMasteredWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				try {
					writeToFailedUnchangedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				
				_attemptedWords.clear();
				_failed.clear();
				_faulted.clear();
				_mastered.clear();
				_failedUnchanged.clear();
				_overallCount=0;
				_reviewBoolean=false;
				// write the words back to the files from the corresponding
				// arraylists
			}else if((_overallCount==3)||(_overallCount==_loopNumber)){


				try {
					writeToAttemptedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					writeToFaultedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					writeToMasteredWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					writeToFailedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					writeToFailedUnchangedWordsFile();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				_attemptedWords.clear();
				_failed.clear();
				_faulted.clear();
				_mastered.clear();
				_failedUnchanged.clear();
				_overallCount=0;
				_reviewBoolean=false;	
			}
			// if the user wants the next word then we get the next word by
			// calling new quiz method again
		}else if (e.getSource()== NextWord){

			_count=1;
			try {
				newSpellingQuiz();
			} catch (IOException e1) {

				e1.printStackTrace();
			}


		}



	}



	// getting the input from the wordlist and storing it in an arraylist.
	private void inputMethodWordList() {
		String path = "./wordlist";
		Scanner s = null;
		try {
			s = new Scanner(new File(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		while (s.hasNext()){
			_list.add(s.next());
		}
		s.close();
	}
	// getting the input from the attemptedwords file and storing it in an arraylist.
	private void inputMethodAttemptedWords() {
		String path = "./attemptedwords";
		Scanner s = null;
		try {
			s = new Scanner(new File(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		while (s.hasNext()){
			_attemptedWords.add(s.next());
		}
		s.close();		

	}
	// getting the input from the failedwords file and storing it in an arraylist.
	private void inputMethodFailedWords() {
		String path = "./failedlist";
		Scanner s = null;
		try {
			s = new Scanner(new File(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		while (s.hasNext()){
			_failed.add(s.next());
		}
		s.close();	

	}
	// getting the input from the masteredwords file and storing it in an arraylist.
	private void inputMethodMasteredWords() {
		String path = "./masteredlist";
		Scanner s = null;
		try {
			s = new Scanner(new File(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		while (s.hasNext()){
			_mastered.add(s.next());
		}
		s.close();	

	}
	// getting the input from the faultedwords file and storing it in an arraylist.
	private void inputMethodFaultedWords() {
		String path = "./faultedlist";
		Scanner s = null;
		try {
			s = new Scanner(new File(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		while (s.hasNext()){
			_faulted.add(s.next());
		}
		s.close();	

	}
	// getting the input from the failedwordsunchanged file and storing it in an arraylist.
	private void inputMethodFailedUnchangedWords() {
		String path = "./failedUnchangedList";
		Scanner s = null;
		try {
			s = new Scanner(new File(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		while (s.hasNext()){
			_failedUnchanged.add(s.next());
		}
		s.close();	

	}

	// writing the words from the attemptedwords arraylist to the corresponding file	
	private void writeToAttemptedWordsFile() throws IOException {
		PrintWriter writer = new PrintWriter("attemptedwords");
		writer.print("");
		writer.close();
		FileWriter writerActual = new FileWriter("attemptedwords"); 
		for(String str: _attemptedWords ) {
			writerActual.write(str + "\n");
		}
		writerActual.close();

	}

	// writing the words from the faultedwords arraylist to the corresponding file	
	private void writeToFaultedWordsFile() throws IOException {
		PrintWriter writer = new PrintWriter("faultedlist");
		writer.print("");
		writer.close();
		FileWriter writerActual = new FileWriter("faultedlist"); 
		for(String str: _faulted ) {
			writerActual.write(str + "\n");
		}
		writerActual.close();

	}
	// writing the words from the masteredwords arraylist to the corresponding file
	private void writeToMasteredWordsFile() throws IOException {
		PrintWriter writer = new PrintWriter("masteredlist");
		writer.print("");
		writer.close();
		FileWriter writerActual = new FileWriter("masteredlist"); 
		for(String str: _mastered ) {
			writerActual.write(str + "\n");
		}
		writerActual.close();

	}
	// writing the words from the failedwords arraylist to the corresponding file
	private void writeToFailedWordsFile() throws IOException {
		PrintWriter writer = new PrintWriter("failedlist");
		writer.print("");
		writer.close();
		FileWriter writerActual = new FileWriter("failedlist"); 
		for(String str: _failed ) {
			writerActual.write(str + "\n");
		}
		writerActual.close();

	}
	// writing the words from the failedwordsunchanged arraylist to the corresponding file
	private void writeToFailedUnchangedWordsFile() throws IOException {
		PrintWriter writer = new PrintWriter("failedUnchangedList");
		writer.print("");
		writer.close();
		FileWriter writerActual = new FileWriter("failedUnchangedList"); 
		for(String str: _failedUnchanged ) {
			writerActual.write(str + "\n");

		}
		writerActual.close();

	}


	// method to check the spelling of the word
	private void checkWord(String _randomWord) throws IOException {

		String userInput= txt.getText();

		// if the user has typed in an input other then words, display an
		// error message
		if (!(userInput.matches("[a-zA-Z]+"))){

			if (!(userInput.matches(""))){
				JOptionPane.showMessageDialog(null, "Enter letters please");
			}



		}else {
			userInput.toLowerCase();
			// if the word is spelt correctly
			if (userInput.equalsIgnoreCase(_randomWord)){
				if (_count==2){
					// if this is the users second chance at the word,
					// then add the word to the faulted list
					festival("correct , ");
					festival("");
					festival("");
					txtArea.append("correct\n");
					_faulted.add(_randomWord);
					_overallCount++;
					// remove blank lines from the faultedlist
					String command = "sed -i '/^$/d' faultedlist";
					ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
					pb.start();
					// reset testfield
					txt.setText("");
					if (_reviewBoolean==true){

						// if we are in review mode, remove all occurances of the
						// word from the failed list
						_failed.removeAll(Collections.singleton(_randomWord));
						// write this into the file from the arraylist
						writeToFailedWordsFile();
						// remove all blank lines in the file
						command = "sed -i '/^$/d' failedlist";
						pb = new ProcessBuilder("/bin/bash", "-c", command);
						pb.start();	




					}


				}else{
					// if the word has been spelt correctly in new quiz, add to 
					// mastered list
					festival("correct,");
					festival("");
					festival("");
					festival("");
					txtArea.append("correct\n");
					_mastered.add(_randomWord);
					_overallCount++;
					// remove all blank lines in mastered
					String command = "sed -i '/^$/d' masteredlist";
					ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
					pb.start();
					if (_reviewBoolean==true){

						// if we are in review mode, remove all occrances of this
						// word in the failes arraylist and write these changes
						// into the failed file.
						_failed.removeAll(Collections.singleton(_randomWord));
						writeToFailedWordsFile();
						// remove all blank lines in the file
						command = "sed -i '/^$/d' failedlist";
						pb = new ProcessBuilder("/bin/bash", "-c", command);
						pb.start();	




					}
					_correct=true;
					// reset textfield
					txt.setText("");

				}
			}else{
				if (_count==2){
					// if user has failed at their second attempt
					festival("incorrect,");
					festival("");
					festival("");
					festival("");
					_failedboolean=true;
					txt.setText("");
					// add the word into both the failed and failedUnchaged
					// arraylists to be used in statistics.
					_failed.add(_randomWord);
					_failedUnchanged.add(_randomWord);
					_overallCount++;
					txtArea.append("Incorrect\n");
					// remove blank lines in file
					String command = "sed -i '/^$/d' failedlist";
					ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
					pb.start();

				}else{
					// User gets another chance to try the word
					txt.setText("");
					festival("incorrect try once more,");
					festival("");
					festival("");
					festival("");
					txtArea.append("Incorrect, try once more: \n");
					userInput= txt.getText();
					// reset the textfield for new input
					txt.setText("");
					if(_reviewBoolean==true){


						// if in review mode, the word has to be spelled out so
						// call a festival method by passing in one letter at a time
						// to be said by festival
						for (int k=0;k<_randomWord.length(); k++){


							festival(_randomWord.charAt(k)+"  ," +"  ,");
							festival("");
						}

					}else{
						// if not in review mode, the word is said out twice by
						// festival
						festival("");
						festival("");
						festival("");
						String command = "echo " + _randomWord 		+ _randomWord + "| festival --tts";
						ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
						pb.start();
						festival("");
						festival("");
						festival("");
					}
					_count++;
					// if the userinput is anything other than letters
					// display an error message
					if (!(userInput.matches("[a-zA-Z]+"))){
						if (!(userInput.matches(""))){
							JOptionPane.showMessageDialog(null, "Enter letters please");
						}
					}else{

						userInput.toLowerCase();
						// if the word is spelt correctly in try once more
						if (userInput.equalsIgnoreCase(_randomWord)){
							festival("correct,");
							festival("");
							festival("");
							festival("");
							txtArea.append("correct\n");
							// add word to the faulted list
							_faulted.add(_randomWord);

							_overallCount++;
							// remove blank lines from file
							String command = "sed -i '/^$/d' faultedlist";
							ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
							pb.start();
							// reset textfield for next input
							txt.setText("");

							if (_reviewBoolean==true){
								// if we are in review mode, remove all occurances of
								// the word in the failed list

								_failed.removeAll(Collections.singleton(_randomWord));


								// write these changed to the failed file
								writeToFailedWordsFile();
								// remove all blank lines in the failed file
								command = "sed -i '/^$/d' failedlist";
								pb = new ProcessBuilder("/bin/bash", "-c", command);
								pb.start();	



							}


						}else{
							// if the user has not spelt the word correctly even after 2
							// attempts
							festival("incorrect,");
							festival("");
							festival("");
							festival("");
							_failedboolean=true;

							if(_reviewBoolean==false){
								// if not in review mode, then add the word to the failed list
								// and also failedUnchanged list for use in statistics.
								_failed.add(_randomWord);
								_failedUnchanged.add(_randomWord);
							}
							_overallCount++;
							// remove blanklines in file
							String command = "sed -i '/^$/d' failedlist";
							ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
							pb.start();
							// reset textfield
							txt.setText("");
							txtArea.append("Incorrect\n");

						}



					}


				}
			}	
		}
	}
	// method which does the output to festival. Takes in a string
	// and says this string out
	private void festival(String c) throws IOException {
		String command = "echo ... | festival --tts";
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		for (int p=0 ;p<15; p++){
			// for loop to delay the festival commands
			command = "echo ......................................... | festival --tts";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();
		}
		command = "echo "+ ""  + c +",   "+ "..."+ ", | festival --tts";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();




	}


	private void newSpellingQuiz() throws IOException {

		// remove all blank lines in file
		String command = "sed -i '/^$/d' failedlist";
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();

		_numOfWords= _list.size();

		if (_reviewBoolean==false){

			// if not in review, 3 words are to be spelt

			_loopNumber=3;

			Random rand = new Random();
			int  randomNumber = rand.nextInt(_numOfWords);	
			_randomWord= _list.get(randomNumber);
			// while loop to get unique words only to test
			while (_attemptedWords.contains(_randomWord)){

				randomNumber = rand.nextInt(_numOfWords);	
				_randomWord= _list.get(randomNumber);

			}


		}else{
			// if in review mode we access the failed file.
			_numOfWords= _failed.size();

			// get a random word from failed file
			Random rand = new Random();
			int randomNumber = rand.nextInt(_numOfWords);	

			_randomWord= _failed.get(randomNumber);


		}
		if ((!(_attemptedWords.contains(_randomWord)))){
			// add the word to attemptedwords arraylist and deleted
			// blank lines from file
			command = "sed -i '/^$/d' attemptedwords";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();



			_attemptedWords.add(_randomWord);			

		}
		_i++;
		// festival says the word
		command = "echo " + _randomWord + "| festival --tts";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		festival("");
		festival("");
		festival("");
		// text area shows what word is being spelt
		txtArea.append("Spell word " + (_i)+" of " + _loopNumber+": \n");


		checkWord(_randomWord);

		_checkStatus=false;


		// setting the counter correctly
		if((_i>_failed.size())&&(_reviewBoolean==true)){

			_i=_failed.size()-1;
		}else if (_i==3){

			_i=0;



		}

	}

	private void newQuizMenu() throws IOException {

		txtArea.setText(null);
		txtArea.append("---------------------------------\n");
		txtArea.append("New Spelling Quiz \n");
		txtArea.append("---------------------------------\n");
	}

	private void reviewMistakes() throws IOException {

		txtArea.setText(null);
		txtArea.append("---------------------------------\n");
		txtArea.append("Review Mistakes \n");
		txtArea.append("---------------------------------\n");
		Random rand = new Random();
		_numOfWords= _failed.size();
		// getting the loopnumber for how many words to be asked according to
		// the number of words in the failed list
		if (_numOfWords>=3){
			_loopNumber =3;
		}else{
			_loopNumber=_numOfWords;

		}
		_reviewBoolean=true;
		// remove blank lines from file
		String command = "sed -i '/^$/d' failedlist";
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		_count =1;
		// call newspellingquiz to start spelling quiz for review words.
		newSpellingQuiz();








	}
	private void viewStatistics() {
		txtArea.setText(null);
		txtArea.append("---------------------------------\n");
		txtArea.append("View Statistics \n");
		txtArea.append("---------------------------------\n");
		int masteredCount=0;
		int faultedCount=0;
		int failedCount=0;
		// sorts out the attemptedwords arraylist in alphabetical order
		Collections.sort(_attemptedWords, String.CASE_INSENSITIVE_ORDER);
		txtArea.append("Printing attempted words in alphabetical order \n");
		txtArea.append("\n");
		// for loop goes through each of the corresponding masteres, faulted,
		// failedUnchanged arraylists and finds the number of occurances of
		// a particular attemptedword.
		// It then prints these counts out
		for (int i=0; i<_attemptedWords.size(); i++){

			String word= _attemptedWords.get(i);

			masteredCount = Collections.frequency(_mastered, word);
			faultedCount = Collections.frequency(_faulted, word);
			failedCount = Collections.frequency(_failedUnchanged, word);

			txtArea.append(word + ": mastered: " + masteredCount+", faulted: " + faultedCount + ", failed: "+ failedCount+ "\n");

		}








	}
	private void clear() throws FileNotFoundException {
		txtArea.setText(null);
		txtArea.append("---------------------------------\n");
		txtArea.append("Clear \n");
		txtArea.append("---------------------------------\n");
		// clearing all files over overwriting them
		PrintWriter writer = new PrintWriter("attemptedwords");
		writer.print("");
		writer.close();

		writer = new PrintWriter("failedlist");
		writer.print("");
		writer.close();

		writer = new PrintWriter("failedUnchangedList");
		writer.print("");
		writer.close();

		writer = new PrintWriter("faultedlist");
		writer.print("");
		writer.close();

		writer = new PrintWriter("masteredlist");
		writer.print("");
		writer.close();
		// clearing all arraylists
		_attemptedWords.clear();
		_failed.clear();
		_faulted.clear();
		_mastered.clear();
		_failedUnchanged.clear();
		txtArea.append ("Files cleared \n");
	}


	public static void main(String[] args) throws IOException{
		// creating the files using bash commands
		String command = "echo >> attemptedwords";
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		command = "echo >> failedlist";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		command = "echo >> masteredlist";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		command = "echo >> faultedlist";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		command = "echo >> failedUnchangedList";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		// clearing all blank lines using bash commands
		command = "sed -i '/^$/d' failedlist";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		command = "sed -i '/^$/d' masteredlist";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		command = "sed -i '/^$/d' faultedlist";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();
		command = "sed -i '/^$/d' failedUnchangedList";
		pb = new ProcessBuilder("/bin/bash", "-c", command);
		pb.start();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				spelling_aid frame = new spelling_aid();
				frame.setVisible(true);
			}
		});

	}	

}