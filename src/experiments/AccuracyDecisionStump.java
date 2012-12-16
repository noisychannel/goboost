package experiments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.REPTree;
import weka.core.Instances;

public class AccuracyDecisionStump {

	static int[] iterations = { 1, 2, 5, 10, 20, 50, 100 };
	
	static String[] models = {"linear", "spherical", "gaussian"};

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

	public static void main(String[] args) {
		String outputFileTrain = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/raw-results/stump/iteraccuracy33";
		String outputFileTest = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/raw-results/stump/iteraccuracyTest33";

		for (int i = 0; i < files33.length; i++) {
			String trainFile = files33[i];
			String testFile = testFiles[i];
			for (int iteration : iterations) {
				runClassifier(trainFile, testFile, iteration, outputFileTrain+models[i]+".txt", outputFileTest+models[i]+".txt");
			}
		}
	}

	private static void runClassifier(String trainFile, String testFile, int iteration, String outputFileTrain, String outputFileTest) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(trainFile));
			Instances train = null;
			train = new Instances(reader);

			reader = new BufferedReader(new FileReader(testFile));

			Instances test = null;

			test = new Instances(reader);

			train.setClassIndex(train.numAttributes() - 1);
			test.setClassIndex(test.numAttributes() - 1);

			AdaBoostM1 adaboost = new AdaBoostM1();
			adaboost.setNumIterations(iteration);
			adaboost.setWeightThreshold(100);
			adaboost.setSeed(1);
			// classifier
			
			DecisionStump baseClassifier = new DecisionStump();

			adaboost.setClassifier(baseClassifier);

			// train and make predictions

			adaboost.buildClassifier(train);
			
			File file1 = new File(outputFileTrain);
			FileWriter fw1 = new FileWriter(file1.getAbsoluteFile(), true);
			File file2 = new File(outputFileTest);
			FileWriter fw2 = new FileWriter(file2.getAbsoluteFile(), true);

			BufferedWriter buffy1 = new BufferedWriter(fw1);
			BufferedWriter buffy2 = new BufferedWriter(fw2);

			if (!file1.exists()) {
				file1.createNewFile();
			}
			if (!file2.exists()) {
				file2.createNewFile();
			}
			
			// evaluate classifier and print some statistics
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(adaboost, train);
			
			buffy1.write(iteration + "\t\t" + eval.pctIncorrect());
			buffy1.newLine();
			
			eval = new Evaluation(train);
			eval.evaluateModel(adaboost, test);
			
			buffy2.write(iteration + "\t\t" + eval.pctIncorrect());
			buffy2.newLine();
			
			buffy1.close();
			buffy2.close();
			
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}

}
