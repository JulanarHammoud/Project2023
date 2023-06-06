package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
public class WarningEvent {
	private Warning warning;

	public Warning getWarning() {
		return warning;
	}

	public WarningEvent(Warning warning) {
		this.warning = warning;
	}
}
