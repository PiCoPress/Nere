package sprexor.v2.cosmos.utils;

import sprexor.v2.IOCenter;
import sprexor.v2.SManager;
import sprexor.v2.components.SCommand;
import sprexor.v2.components.SParameter;
import sprexor.v2.components.annotations.CommandInfo;
/**
 * with javaparser
 */
@CommandInfo(name = "javadaptor")
public class JavaAdaptor implements SCommand {

	@Override
	public int main(IOCenter io, SParameter args, SManager Environment) {
		return 0;
	}
}
