package org.neu.util;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShellUtil {

	private static Logger LOG = LogManager.getLogger("shell util");

	public static void printMemory() {
		
		System.gc();
		String os = System.getProperty("os.name"); 
		LOG.info("Operation System => " + os);
		if(!os.contains("Windows")) {
			ShellUtil.runShell("top -bcn 1 |grep java |grep " + ShellUtil.getPid());
		}
		LOG.info("Memory Usage: Total({})M\tFree({})M\tUsed({})M", Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime()
		        .freeMemory() / 1024 / 1024,
		        (Runtime.getRuntime().totalMemory() / 1024 / 1024 - Runtime.getRuntime().freeMemory() / 1024 / 1024));
	}

	public static void runShell(String command) {
		try {
			LOG.info("runShell start: " + command);
			Process process = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", command });
			int exitCode = process.waitFor();
			LOG.info("exit code: " + exitCode);
			LOG.info("std out: \n" + IOUtils.toString(process.getInputStream()));
			LOG.info("err out: \n" + IOUtils.toString(process.getErrorStream()));
			LOG.info("runShell end: " + command);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static void runShellNotWait(final String command) {
		try {
			LOG.info("runShell start: " + command);
			Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", command });
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static int getPid() {
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
		String name = runtime.getName(); // format: "pid@hostname"
		try {
			return Integer.parseInt(name.substring(0, name.indexOf('@')));
		} catch (Exception e) {
			return -1;
		}
	}

	public static void main(String[] args) {
		printMemory();
	}
}
