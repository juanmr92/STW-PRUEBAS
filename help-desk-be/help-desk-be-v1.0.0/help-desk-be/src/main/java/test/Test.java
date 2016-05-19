package test;



public class Test {

	public static void main(String[] args) {
//		SecureRandom random = new SecureRandom();
//		
//		String code = new BigInteger(130, random).toString().substring(0, 10) ;
//		
//		System.out.println(code);
//		System.out.println(code.length());
		
		String img = "imagen.jpg";
		System.out.println(img.substring(img.indexOf("."),img.length()));

	}

}
