package interfaces;

public class htmlUI implements Massageable{
	@Override
	public void showMassage(String msg) {
		System.out.println(msg + "<br>");
	}

	@Override
	public String getString(String msg) {
		System.out.println(msg + "<br>");
		
		return null;
	}

	@Override
	public void showErrMassage(String string) {
		// TODO Auto-generated method stub
		System.out.println("<p style=\" color:red>\">This is a paragraph.</p>" + "<br>");
	}

	@Override
	public String dropLineChar() {
		
		return "<br>\n"; // delete \n later 
	}


	
	//<p style="color:red">This is a paragraph.</p>

}
