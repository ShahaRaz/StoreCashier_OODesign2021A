package interfaces;

public class SilentUi implements Massageable {

	@Override
	public void showMassage(String msg) {
		System.out.print("");
	}

	@Override
	public String getString(String msg) {
		return "";
	}

	@Override
	public void showErrMassage(String string) {
		System.out.print("");

		
	}

	@Override
	public String dropLineChar() {
		return "";
	}

}
