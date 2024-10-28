package bazmar.lastwar.autofl.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class JnativeHookLogFilter extends Filter<ILoggingEvent> {
	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (event.getLoggerName().startsWith("org.jnativehook")) {
			return FilterReply.DENY;
		}
		return FilterReply.NEUTRAL;
	}
}
