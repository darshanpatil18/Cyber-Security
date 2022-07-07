	import java.io.File;
	import java.io.IOException;
	import java.io.RandomAccessFile;
	import java.util.Scanner;

public class EncDec {
		int flag=0,shiftby=2;String key="";
		double percent;
		Scanner inputscanner =new Scanner(System.in);
		
		void encrypt(String filename,String dirname,String key)			//encrypt function
		{
			try{
				File dir = new File("TempFiles");
				if(!dir.exists())
					dir.mkdir();			//make a folder(if donot exist) for temporary files which will b deleted at end of prg

				RandomAccessFile fn = new RandomAccessFile(filename, "rw");  
				RandomAccessFile in = new RandomAccessFile("TempFiles/temp.files", "rw");
				RandomAccessFile outTemp = new RandomAccessFile("TempFiles/enc-T.files", "rw");
				RandomAccessFile out = new RandomAccessFile(dirname+"/enc.files", "rw"); 
				
				FunctionSet.copyFile(filename, "TempFiles/temp.files");//Faster FileCopy using File Channels

				FunctionSet.rounds(in, outTemp, key, shiftby,"Encrypting");	//xor

				FunctionSet.shuffle(outTemp, out);		//shuffle
				
				File f1 = new File("TempFiles/temp.files");
				File f2 = new File("TempFiles/enc-T.files");
				f1.delete();
				f2.delete();
				fn.close();
				in.close();
				out.close();	//Release Resources
		    	}    	
		catch ( IOException e) {
			System.out.println(e);
			}
	    }
			
		
		
		void decrypt(String filename,String extname, String dirname,String key) //decrypt fxn
		{
			try{
				File dir = new File("TempFiles");
				if(!dir.exists())
					dir.mkdir();			//make a folder(if donot exist) for temporary files which will b deleted at end of prg

		 		RandomAccessFile fn = new RandomAccessFile(filename, "rw");
		 		RandomAccessFile in = new RandomAccessFile("TempFiles/temp.files", "rw");	
		 		RandomAccessFile out = new RandomAccessFile(dirname+"/dec."+extname, "rw");
		    	
				FunctionSet.shuffle(fn, in);							//deshuffle 
				FunctionSet.rounds(in, out, key, shiftby,"Decrypting");	//xor
	
				File f = new File("TempFiles/temp.files");
				f.delete();		
		    	 //release resources
		    	 in.close();out.close();fn.close();
		    	}
	catch ( IOException e) {
		 System.out.println(e);
					}		
		}

}