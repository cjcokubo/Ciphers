package question2;

import java.util.*;

public class RandomCipher{

	protected char[] encoder = new char[30]; // Encryption Array 
	protected char[] decoder = new char[30]; // Decryption array
	
	protected Character[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '#', '$', '&', '='};
	List<Character> alphabet_arr = Arrays.asList(alphabet);
	Random random = new Random();
	
	protected Character[] randomAlphabet = new Character[30];

	public Character[] RandomAlphabet(int seed) {
		 Random random = new Random();
		 random.setSeed(seed);
		 
		 List<Integer> arr = new ArrayList<Integer>();
		 for(int k=0; k<30; k++){ 
		     int r = random.nextInt(30);
			 while (arr.contains(r)){
			     r = random.nextInt(30);
			 }
			 arr.add(r);
			 
			 randomAlphabet[k] = (char) (alphabet[r]);
		 }
		 return randomAlphabet;
	}

	public RandomCipher(int seed){
		random.setSeed(seed);
		List<Integer> arr = new ArrayList<Integer>();
		for(int k=0; k<30; k++){
			int r = random.nextInt(30);
			while (arr.contains(r)){
				r = random.nextInt(30);
				}
			arr.add(r);
			
			int x = Arrays.asList(RandomAlphabet(seed)).indexOf(alphabet[k]);
			encoder[k] = (char) (alphabet[r%30]);
			decoder[k] = (char) (alphabet[x%30]);
		}
	}

	public String encrypt(String message, int seed){
		return transform(message, encoder, seed);
	}


	public String decrypt(String secret, int seed){
		return transform(secret, decoder, seed); 
	}

	private String transform(String original, char[] code, int seed){
		char[] msg = original.toCharArray();
		for(int k=0; k<msg.length; k++){

			if(alphabet_arr.contains(msg[k])){
				int j = alphabet_arr.indexOf(msg[k]);
				msg[k] = code[j];
			}
		}
		return new String(msg);
	}

	public static void main(String[] args){
		/*main method for testing the Random Cipher*/
		System.out.print("Enter integer for pseudorandom shuffling of alphabet: ");
		Scanner input = new Scanner(System.in);
		int seed = input.nextInt();
		
		//int seed = 10;
		RandomCipher cipher = new RandomCipher(seed);
		
		System.out.println("Encryption code = "+ new String(cipher.encoder));
		System.out.println("Decryption code = "+ new String(cipher.decoder));
		String message = "THE EAGLE IS IN PLAY; MEET AT JOE'S";
		String coded = cipher.encrypt(message, seed);
		System.out.println("Secret: "+ coded);
		String answer = cipher.decrypt(coded, seed);
		System.out.println("Message: " + answer);

		input.close();
	}

}