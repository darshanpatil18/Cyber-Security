import java.math.BigInteger;
import java.util.Scanner;

public class start {
	static boolean flag = true;
	static String filename="", dirname="", key="";
	static int chk=0,ch=0;
 
	public static void main(String[] args) {
		EncDec obj = new EncDec();
		Scanner chscanner =new Scanner(System.in);		

		while(flag)
		{
		 System.out.println("\n===============================================================\n 1.ENCRYPT a file \t 2.DECRYPT a file \t 3.Exit Program\n===============================================================");
		 System.out.println("Enter Your Choice : \t");
		 	if(chscanner.hasNextInt())
			    ch=chscanner.nextInt();
		 switch(ch)
		  {
		    case 1:
		    	do {
		    		System.out.println("Enter Name of the File to be Encrypted : ");				
				filename = chscanner.next();
				filename=filename.replaceAll("\\\\", "/");				
				chk=FunctionSet.check(filename);
				}while(chk!=1);
					
				do {
				System.out.println("Enter Name of Directory to store Encrypted file : ");				
				dirname=chscanner.next();
				dirname=dirname.replaceAll("\\\\", "/");
				chk=FunctionSet.check(dirname);
				}while(chk!=2);
						
				do{
				System.out.println("Enter Your Private Key (Length > 10) : ");
				key=chscanner.nextLine();
				if(key.length()<10)
					System.out.println("\t\t--Private Key Size should be > 10--");
				}while(key.length()<10);
				
				key=FunctionSet.KeyGen(key);
				
				BigInteger m=new BigInteger(key);
				BigInteger Enkey = RsaFunctionClass.EncDec(m, RsaFunctionClass.e,RsaFunctionClass.n);	//RSA-Encrypt the key
				String keyloc=RsaFunctionClass.WriteEncKey(Enkey, dirname, filename);	//write encrypted key to file for further use
				obj.encrypt(filename,dirname,key);
					
				System.out.println("\n File ENCRYPTED Successfully, Stored at "+"'" + dirname + "'");
				System.out.println("NOW Your Encrypted Private Key is : "+Enkey+"\n\t It is Saved as '"+keyloc+"'");
		    		break;		
		    	
		    case 2: 
		    		do
				{
				System.out.println("Enter Name of the Encrypted File that is to be Decrypted : ");				
				filename=chscanner.next();
				filename=filename.replaceAll("\\\\", "/");
				chk=FunctionSet.check(filename);
				}while(chk!=1);

				System.out.println("Enter FILE EXTENSION to which file is to be Decrypted(e.g txt,pdf) : ");
				String extname = chscanner.next();
				extname=extname.substring(extname.lastIndexOf(".") + 1);	//if user provided a '.' with extension
			  
				do{
				System.out.println("Enter Name of Directory where Decrypted file is to be Stored:");				
				dirname=chscanner.next();
				dirname=dirname.replaceAll("\\\\", "/");
				chk=FunctionSet.check(dirname);
				}while(chk!=2);
				
				String regex = "[0-9]+";		//Regular Expression for string to make sure key contains only numbers		
				do{
				System.out.println("Enter Your Encrypted Private Key of the file : ");
				key=chscanner.next();
				if(key.length()<500||!(key.matches(regex)))
					System.out.println("\t\t--Encrypted-Key Size must be > 500 and Must only contain Numeric Values!");
				}while((key.length()<500)||!(key.matches(regex)));
				
				BigInteger c=new BigInteger(key);	//convert to BI
				BigInteger Deckey=RsaFunctionClass.EncDec(c, RsaFunctionClass.d,RsaFunctionClass.n);				
				key=Deckey.toString();
				obj.decrypt(filename,extname,dirname,key);	
				System.out.println("\nFile DECRYPTED Successfully as 'dec."+extname+",' Stored at "+"'"+dirname+"'");
	    			
				break;
		    		
	   		case 3:
		 		flag=false;
		  		System.out.println("Thank You");	
		   		chscanner.close();
		   		break;
		    			
			default:
	  			flag=false;
	  			System.out.println("Sorry, invalid option!");	
	   			chscanner.close();
		   	}	
		}	
	}
}