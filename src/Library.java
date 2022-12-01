public class Library {
   
    public static String[] getNumberFromString(String string){

        String temp = string.replaceAll("[^-?\\d+]", " ");
		temp = temp.replaceAll(" +", " ");
		temp = temp.trim();
		
		return temp.split(" ");

    }

    public static int[][] initArray(int x, int y){

        return new int[y][x];       // for traversing to be right, y has to come first

    }
    
}
