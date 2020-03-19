package ajin.sf.icms.commons;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ConvertPrintstackTrace {

	public String getPrintstackTrace(Exception e) {

		StringWriter errMsg = new StringWriter();
		e.printStackTrace(new PrintWriter(errMsg));

		return errMsg.toString();
	}
}
