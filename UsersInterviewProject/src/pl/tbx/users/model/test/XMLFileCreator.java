package pl.tbx.users.model.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLFileCreator {

	private final static String MAIN_DIRECTORY = System
			.getProperty("user.home") + "/users";
	private String fileName = "users.xml";

	public static void main(String[] args) {
		XMLFileCreator creator = new XMLFileCreator();
		creator.prepareDirectory();
		creator.create();
	}

	private void prepareDirectory() {
		File mainDirectory = new File(MAIN_DIRECTORY);
		if (!mainDirectory.exists()) {
			mainDirectory.mkdirs();
		}
	}

	public void create() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(MAIN_DIRECTORY + "/"
					+ fileName, true));

			writer.write("<users>");
			writer.newLine();
			for (int i = 1; i < 50001; i++) {
				writer.write("<user>");
				writer.newLine();

				writer.write("<name>name" + i + "</name>");
				writer.newLine();
				writer.write("<surname>surname" + i + "</surname>");
				writer.newLine();
				writer.write("<login>login" + i + "</login>");
				writer.newLine();

				writer.write("</user>");
				writer.newLine();
			}

			writer.write("</users>");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException ignored) {
			}
		}
	}
}
