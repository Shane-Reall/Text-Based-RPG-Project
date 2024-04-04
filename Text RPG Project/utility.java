public abstract class utility { //This class makes use of final Methods, since these methods should not be changed for the classes they are passed to

    final public static void slowPrint(String str) {
        StringBuilder sb = new StringBuilder(str);

        for (int i =0; i < sb.length(); i++){
            System.out.print(sb.charAt(i));
            try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
        	}
        }
        System.out.println("");
    }

    final public static String endOfRound(){
		return "--------------------End-Of-Round--------------------";
	}

}