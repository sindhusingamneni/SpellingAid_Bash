package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Level {
	
	private int _score=0;
	HashMap<String, Integer> _stats = new HashMap<String, Integer>();
	
	public enum Levels{
		level1(1),level2(2),level3(3),level4(4),level5(5),level6(6),level7(7),level8(8),level9(9),level10(10),level11(11);
		private int value;
		 
		private Levels(int value) {
			this.value = value;
		}
	}
	
	public void getInput() throws IOException{
		File f=new File("./wordlist");
		if(f.exists()){
			String scan;
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			while(br.ready()){
				scan=br.readLine();
				//wordlist.add(scan);
			}
		}
	}
}
