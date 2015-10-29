package de.ur.mi.bouncer.events;

import java.util.EventListener;

public interface OnWorldChangedListener extends EventListener {
	void onWorldStateChanged();
}
