package experiments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrainingSizeVsAccuracy {

	static int[] trainingSize = { 100, 200, 400, 1000, 2000 };

	static int[] noiseLevels = { 10, 20, 30 };

	static String[] testFiles = {
			"/Applications/MAMP/htdocs/goboost/res/weka/linear-200/linear.arff",
			"/Applications/MAMP/htdocs/goboost/res/weka/spherical-200/spherical.arff",
			"/Applications/MAMP/htdocs/goboost/res/weka/gaussian-200/gaussian.arff" };

	private static void test(int iterations, String trainFile, String testFile,
			String outputFile) throws IOException, InterruptedException {

		// Run the experiment
		String arguments = "-P 100 -S 1 -I " + iterations
				+ " -W weka.classifiers.trees.DecisionStump -o";
		arguments += " -t " + trainFile;
		arguments += " -T " + testFile;

		Runtime run = Runtime.getRuntime();

		String cmd = "java -cp bin:/Volumes/weka-3-7-7/weka-3-7-7/weka.jar weka.classifiers.meta.AdaBoostM1 "
				+ arguments;

		System.out.println(cmd);

		Process pr = run.exec(cmd);
		pr.waitFor();
		BufferedReader buf = new BufferedReader(new InputStreamReader(
				pr.getInputStream()));
		BufferedReader bufErr = new BufferedReader(new InputStreamReader(
				pr.getErrorStream()));

		File file1 = new File(outputFile);
		FileWriter fw1 = new FileWriter(file1.getAbsoluteFile(), true);

		BufferedWriter bw1 = new BufferedWriter(fw1);

		if (!file1.exists()) {
			file1.createNewFile();
		}
		String line;
		bw1.write("%%%%%%%%" + arguments + "%%%%%%%%%%");
		bw1.newLine();
		bw1.newLine();
		while ((line = buf.readLine()) != null) {
			try {
				bw1.write(line);
				bw1.newLine();

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		while ((line = bufErr.readLine()) != null) {
			try {
				bw1.write(line);
				bw1.newLine();

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		bw1.close();
	}

	public static void main(String[] args) {
		try {

			for (int size : trainingSize) {
				for (int noise : noiseLevels) {
					String[] trainingFiles = {
							"/Applications/MAMP/htdocs/goboost/res/weka/linear-"
									+ size + "/linear-" + noise + ".arff",
							"/Applications/MAMP/htdocs/goboost/res/weka/spherical-"
									+ size + "/spherical-" + noise + ".arff",
							"/Applications/MAMP/htdocs/goboost/res/weka/gaussian-"
									+ size + "/gaussian-" + noise + ".arff" };

					String outputFile = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/sizevsaccuracy-"
							+ size + ".txt";
					
					for (int i = 0; i < trainingFiles.length; i++) {
						String trainingFile = trainingFiles[i];
						String testFile = testFiles[i];
						test(100, trainingFile, testFile, outputFile); 
					}
					
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
