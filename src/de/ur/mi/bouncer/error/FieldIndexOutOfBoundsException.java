package de.ur.mi.bouncer.error;

public class FieldIndexOutOfBoundsException extends Exception {
	private static final long serialVersionUID = -5842885664022383282L;

	public FieldIndexOutOfBoundsException(String indexName, int maxValue,
			int actualValue) {
		super(messageFor(indexName, maxValue, actualValue));
	}

	private static String messageFor(String indexName, int maxValue,
			int actualValue) {
		return "Sie haben versucht, auf " + indexName + " mit dem Wert " + actualValue
				+ " zuzugreifen, aber der gültige Wertebereich geht von 0 bis einschließlich " + maxValue + ".";
	}

}
