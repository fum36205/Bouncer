package de.ur.mi.bouncer.mock;

public class MethodParameter {
	private final String type;
	private final Object value;

	public static MethodParameter intParameter() {
		return create("int", null);
	}

	public static MethodParameter create(int value) {
		return create("int", Integer.valueOf(value));
	}

	public static MethodParameter booleanParameter() {
		return create("boolean", null);
	}

	public static MethodParameter create(boolean value) {
		return create("boolean", Boolean.valueOf(value));
	}

	public static MethodParameter doubleParameter() {
		return create("double", null);
	}

	public static MethodParameter create(double value) {
		return create("double", Double.valueOf(value));
	}

	public static MethodParameter create(String type, Object value) {
		return new MethodParameter(type, value);
	}

	public static MethodParameter parameterOfType(String type) {
		return create(type, null);
	}

	private MethodParameter(String type, Object value) {
		this.type = type;
		this.value = value;
	}

	public boolean equals(MethodParameter other) {
		if (!this.type.equals(other.type)) {
			return false;
		}
		if (!this.value.equals(other.value)) {
			return false;
		}
		return true;
	}

	public boolean equalsIgnoreValues(MethodParameter other) {
		if (!this.type.equals(other.type)) {
			return false;
		}
		return true;
	}

	public String toString(boolean ignoreValues) {
		if (ignoreValues) {
			return this.type;
		} else {
			String result = "";
			if (type.equals("String")) {
				result += "\"";
			}
			if (this.value != null) {
				result += this.value;
			}
			if (type.equals("String")) {
				result += "\"";
			}
			return result;
		}
	}

	public Object value() {
		return value;
	}
}
