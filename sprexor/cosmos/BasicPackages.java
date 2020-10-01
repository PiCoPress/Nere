package sprexor.cosmos;

import sprexor.CommandProvider;
import sprexor.GlobalData;
import sprexor.Tools;
import sprexor.Tools.*;

/**
 * example Convenience Register Command Class.
 * @author PICOPress
 * @since 0.2.4
 */
public class BasicPackages implements CommandProvider{
	public String getCommandName() {
		return "find";
	}
	
	public String help() {
		return "Usage : find (str to find) (str...)";
	}
	
	public Object emptyArgs() {
		return help();
	}
	
	public CommandProvider[] referenceClass() {
		return null; //Tools.toCPClass();
	}
	
	public static BasicPackages call() {
		return new BasicPackages();
	}
	
	public Object code(String[] args, boolean[] isWrapped, GlobalData scope) {
		String tmp = Tools.arg2String(Tools.excludeArr(args, 0));
		String find = args[0];
		return args;
		/*String[] splitedStr = tmp.split("\n");
		String result = "";
		
		for(String it : splitedStr) {
			if(it.indexOf(find) != -1)result += it + "\n";
		}
		return result;*/
	}
}