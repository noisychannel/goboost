package experiments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import weka.classifiers.Evaluation;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.DecisionStump;
import weka.core.Instances;

public class TrainingSizeVsAccuracy {

	static int[] trainingSize = { 100, 200, 400, 1000, 2000 };

	static int[] noiseLevels = { 10, 20, 30 };
	
	static String[] models = {"linear", "spherical", "gaussian"};

	static String[] testFiles = {
			"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/linear-200/linear.arff",
			"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/spherical-200/spherical.arff",
			"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/gaussian-200/gaussian.arff" };

	public static void main(String[] args) {
		for (int size : trainingSize) {
			for (int noise : noiseLevels) {
				String[] trainingFiles = {
						"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/linear-"
								+ size + "/linear-" + noise + ".arff",
						"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/spherical-"
								+ size + "/spherical-" + noise + ".arff",
						"/Applications/MAMP/htdocs/goboost/res/weka/raw-data/gaussian-"
								+ size + "/gaussian-" + noise + ".arff" };

//					String outputFile = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/sizevsaccuracy-"
//							+ size + ".txt";
				
				for (int i = 0; i < trainingFiles.length; i++) {
					String trainingFile = trainingFiles[i];
					String testFile = testFiles[i];
//						test(100, trainingFile, testFile, outputFile);
					String outputFileTrain = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/raw-results/sizeAccuracy/sizeVsAccuracy-"+models[i]+noise+".txt";
					String outputFileTest = "/Applications/MAMP/htdocs/goboost/res/weka/experiments/raw-results/sizeAccuracy/sizeVsAccuracyTest-"+models[i]+noise+".txt";
					runClassifier(trainingFile, testFile, 100, outputFileTrain, outputFileTest);
				}
				
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
			
			buffy1.write(train.size() + "\t\t" + eval.pctIncorrect());
			buffy1.newLine();
			
			eval = new Evaluation(train);
			eval.evaluateModel(adaboost, test);
			
			buffy2.write(train.size() + "\t\t" + eval.pctIncorrect());
			buffy2.newLine();
			
			buffy1.close();
			buffy2.close();
			
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}

}
