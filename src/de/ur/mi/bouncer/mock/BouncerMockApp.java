package de.ur.mi.bouncer.mock;

public abstract class BouncerMockApp {
	private final MethodInvocationLog invocationLog;
	protected final BouncerMock bouncer;

	public BouncerMockApp(MethodInvocationLog invocationLog) {
		this.invocationLog = invocationLog;
		bouncer = new BouncerMock(invocationLog);
	}

	public void loadMap(String mapName) {
		invocationLog.addInvocation("loadMap", MethodParameter.create("String", mapName));
	}
	
	public abstract void bounce();
	
	public BouncerMock bouncer() {
		return bouncer;
	}
}
