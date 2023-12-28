package caw.studios.reports;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

public class Retry implements IRetryAnalyzer {
	private static Logger log = Logger.getLogger(Retry.class);
	private int minRetryCount = 0;
	private static int maxRetryCount = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (this.minRetryCount <= maxRetryCount) {
				log.trace(("Retrying test " + result.getMethod().getMethodName() + " with status "
						+ getResultStatus(result.getStatus()) + " for the " + (this.minRetryCount + 1)
						+ " time(s) . "));
				this.minRetryCount++;
				return true;
			}
		}
		return false;
	}

	private String getResultStatus(int status) {
		// TODO Auto-generated method stub
		String result = null;
		if (status == 1) {
			result = "SUCCESS";
		} else if (status == 2) {
			result = "FAILURE";
		} else if (status == 3) {
			result = "SKIP";
		}
		return result;
	}

}
