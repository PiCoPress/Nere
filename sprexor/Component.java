package sprexor;

import java.util.Iterator;

public class Component implements Iterable<String> {
	String[] v;
	boolean[] isw;
	enum A{
		Default,
		Str,
		SetType1,
		SetType2
	}
	/**
	 * This Unit Class provide methods for a unit of Arguments.
	 * @since 0.2.18
	 * @version 1.0
	 */
	public class Unit{
		String k = "";
		boolean b;
		public final A ArgumentType;
		protected Unit(String k, boolean b) {
			this.k = k;
			this.b = b;
			if(b) ArgumentType = A.Str;
			else if(k.startsWith("--")) ArgumentType = A.SetType2;
			else if(k.startsWith("-")) ArgumentType = A.SetType1;
			else ArgumentType = A.Default;
		}
		@Override
		public String toString() {
			return k;
		}
		public boolean isWrapped() {
			return b;
		}
	}
	protected Component(String[] v, boolean[] isw) {
		this.v = v;
		this.isw = isw;
	}
	/**
	 *Return all of arguments to string array. 
	 * @return
	 */
	public String[] get() {
		return v;
	}
	/**
	 * Return the Unit class.
	 * @param i - The index to get unit of arguments.
	 * @return Unit Class
	 */
	public Unit get(int i) {
		return new Unit(v[i], isw[i]);
	}
	/**
	 * 
	 * @param i - The index to get unit of arguments.
	 * @return String
	 */
	public String gets(int i) {
		return v[i];
	}
	/**
	 * It gets String by Ignoring option.
	 * @param i
	 * @return String
	 */
	public String getsWithoutOption(int i) {
		int c = 0;
		for(String s : v) {
			if(!s.startsWith("-") || isw[c]) {
				if(c == i) return s;
				c ++;
			}
		}
		return null;
	}
	/**
	 * Return Options to Array in order.
	 * @return String[]
	 */
	public String[] getAllOption() {
		int c = 0,
			i = 0;
		String[] arr = {};
		for(String s : v) {
			if(s.startsWith("-") && !isw[c ++]) {
				arr[i ++] = s.substring(s.startsWith("--") ? 2 : 1);
			}
		}
		return null;
	}
	public static String[] Parse(String str) {
		String[] pops = str.split(""),
				units = new String[pops.length];
		short mod = 0,
				count = 0,
				pr = 0,
				smod = 0;
		int allCount = 0;
		String cache = "";
		
		//------------------Parse start---------------------
		for(;allCount < pops.length; allCount ++) {
			String c = pops[allCount];
			if(mod + smod == 1 && c.contentEquals("\\") && pr == 0) {
					pr = 1;
					continue;
				}
				if(c.contentEquals(" ") && smod == 0 && mod == 0) {
					if(cache.trim().isEmpty())continue;
					units[count ++] = cache.trim();
					cache = "";
					continue;
				}
				if(c.contentEquals("\"")) {
					if(mod == 0 && smod == 0) {
						mod = 1;
					}else {
						if(pr == 1) {
							pr = 0;
							cache += c;
							continue;
						}else if(smod == 1) {
							cache +=  c;
							mod = 0;
							continue;
						}
						units[count ++] = cache.trim();
						cache = "";
						mod = 0;
					}
					continue;
					
				}else if(c.contentEquals("'")) {
					if(mod == 0 && smod == 0) {
						smod = 1;
					}else {
						if(pr == 1) {
							pr = 0;
							cache += c;
							continue;
						}else if(mod == 1) {
							cache +=  c;
							smod = 0;
							continue;
						}
						units[count ++] = cache.trim();
						cache = "";
						smod = 0;
					}
					continue;
				}else if(pr == 1) {
					if(c.contentEquals("n")){
						if(mod == 0 && smod == 0) {
							smod = 1;
						}else {
							if(pr == 1) {
								pr = 0;
								cache += "\n";
								continue;
							}else if(mod == 1) {
								cache +=  "\n";
							}
							smod = 0;
						}
						continue;
					}else if(c.contentEquals("t")){
						if(mod == 0 && smod == 0) {
							smod = 1;
						}else {
							if(pr == 1) {
								pr = 0;
								cache += "\t";
								continue;
							}else if(mod == 1) {
								cache +=  "\t";
							}
							smod = 0;
						}
						continue;
					}else {
					cache += "\\";
					pr = 0;
					continue;
				}
			}
			cache += c;
		}
		return units;
	}
	/**
	 * Return the size of arguments.
	 * @return size
	 */
	public int length() {
		return v.length;
	}
	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			int cursor = 0;
			@Override
			public boolean hasNext() {
				return cursor >= v.length ? false : true;
			}
			@Override
			public String next() {
				return v[ cursor ++ ];
			}
		};
	}
}
