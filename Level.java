package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Level {
	
	ArrayList<String> words = new ArrayList<String>();
	private int _score=0;
	private boolean getout=false;;
	HashMap<String, Integer> _stats = new HashMap<String, Integer>();
	
	public enum Levels{
		level1(1),level2(2),level3(3),level4(4),level5(5),level6(6),level7(7),level8(8),level9(9),level10(10),level11(11);
		private int value;
		 
		private Levels(int value) {
			this.value = value;
		}
	}
	
	public void getInput(Levels lvl) throws IOException{
		int level=lvl.value;
		File f=new File("./NZCER-spelling-lists.txt");
		if(f.exists()){
			String scan;
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String equate="%Level "+level;
			String end="%Level "+(level++);
			while(br.ready()){
				scan=br.readLine();
				if(scan.equals(equate)){
				while(true){
				scan=br.readLine();
				if(scan.equals(end)){
					getout=true;
					break;
				}
				words.add(scan);
				}
				}
				if(getout){
					break;
				}
			}
		}
	}

	
	public void updateStats(){
		
	}
}
