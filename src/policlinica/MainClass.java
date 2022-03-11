package policlinica;

public class MainClass {
	
	// Lansarea aplicatiei

	public static void main(String[] args) {
		
		Conexiune activeConnection = new Conexiune();
		if(null != activeConnection.getConnection()) {
			new WelcomeScreen().getFrame().setVisible(true);
		}
	}

}
