package sprexor.v2.cosmos;

import java.io.File;

import sprexor.v2.IOCenter;
import sprexor.v2.SManager;
import sprexor.v2.components.SCommand;
import sprexor.v2.components.SParameter;
import sprexor.v2.lib.Utils;

public class SprexorChangeD implements SCommand {

	@Override
	public String name() {
		return "cd";
	}

	@Override
	public int main(IOCenter io, SManager Environment) {
		SParameter args = io.getComponent();
		String sum = Utils.join(args.get(), " ") + " ";
		File f = new File(sum.toCharArray()[1] == ':' || sum.startsWith("/")?
				sum.trim() : Environment.SystemVar.getData("CURRENT_DIRECTORY").toString().concat(File.separator).concat(sum).trim());
		System.out.println(f.getAbsolutePath());
		if(f.isDirectory()) Environment.SystemVar.modifyData("CURRENT_DIRECTORY", f.getAbsoluteFile());
		else {
			io.out.printf("no directory : %s %n", sum);
			return 1;
		}
		return 0;
	}

	@Override
	public String version() {
		return "0.0.1";
	}

}
