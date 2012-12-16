package experiments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IterVsAccuracyJ48 {

	static int[] iterations = { 10, 100, 200, 500, 1000, 2000, 5000, 10000 };

	static String[] files10 = {
		"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/linear-2000/linear-10.arff",
		"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/spherical-2000/spherical-10.arff",
	"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/gaussian-2000/gaussian-10.arff" };

	static String[] files20 = {
		"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/linear-2000/linear-20.arff",
		"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/spherical-2000/spherical-20.arff",
	"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/gaussian-2000/gaussian-20.arff" };

	static String[] files33 = {
		"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/linear-2000/linear-30.arff",
		"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/spherical-2000/spherical-30.arff",
	"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/gaussian-2000/gaussian-30.arff" };

	static String[] testFiles = {
		"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/linear-200/linear.arff",
		"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/spherical-200/spherical.arff",
	"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/gaussian-200/gaussian.arff" };

	private static void test(int iterations, String trainFile, String testFile,
			String outputFile) throws IOException, InterruptedException {

		// Run the experiment
		String[] env = {"CLASSPATH=bin:/Volumes/weka-3-7-7/weka-3-7-7/weka.jar"};
//		String argument1 = "weka.classifiers.meta.AdaBoostM1";
		String argument2 = "";
		String argument1 = "-t "+trainFile;
//		argument1 += " -T "+testFile;
//		argument1 += " -P 100 -S 1 -I "+iterations+" -W weka.classifiers.trees.REPTree -- -M 2 -V 0.0010 -N 3 -S 1 -L -1 -I 0.0";
		String[] argumentArray = {"java", "weka.classifiers.meta.AdaBoostM1", "-t", trainFile, "-T", testFile, "-P", "100", "-S", "1", "-I", String.valueOf(iterations), "-W", "weka.classifiers.trees.REPTree", "--", "-M", "2", "-V", "0.0010", "-N", "3", "-S", "1", "-L", "-1", "-I", "0.0"}; 
//		String[] argumentArray = {"java", "weka.classifiers.meta.AdaBoostM1", argument1}; 

//		arguments += " -t "+trainFile;
		
//		arguments += " -T "+testFile;

		Runtime run = Runtime.getRuntime();

		String cmd = "java";
//				+ arguments;
		
		String[] cmdArray = {cmd, argument1};

		System.out.println(cmd);
		
		Process pr = run.exec(argumentArray, env);
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
		bw1.write("%%%%%%%%" + argument1 + argument2 + "%%%%%%%%%%");
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
//			String outputFile = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/iteraccuracy10.txt";
//			for (int i = 0; i < files10.length; i++) {
//				String trainFile = files10[i];
//				String testFile = testFiles[i];
//				for (int iteration : iterations) {
//					test(iteration, trainFile, testFile, outputFile);
//				}
//			}
//			
//			outputFile = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/iteraccuracy20.txt";
//			
//			for (int i = 0; i < files20.length; i++) {
//				String trainFile = files20[i];
//				String testFile = testFiles[i];
//				for (int iteration : iterations) {
//					test(iteration, trainFile, testFile, outputFile);
//				}
//			}
			
			String outputFile = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/raw-results/j48/iteraccuracy33.txt";

			for (int i = 0; i < files33.length; i++) {
				String trainFile = files33[i];
				String testFile = testFiles[i];
				for (int iteration : iterations) {
					test(iteration, trainFile, testFile, outputFile);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
