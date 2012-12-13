import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WekaLinearDataSet {

	private static Random rnd = new Random();

	public static void main(String[] args) {
		File file1 = new File(
				"/Applications/MAMP/htdocs/goboost/res/weka/linear-200/linear.arff");
		File file2 = new File(
				"/Applications/MAMP/htdocs/goboost/res/weka/linear-200/linear-1.arff");
		BufferedWriter bw1, bw2;
		try {
			if (!file1.exists()) {
				file1.createNewFile();
			}
			if (!file2.exists()) {
				file2.createNewFile();
			}

			FileWriter fw1 = new FileWriter(file1.getAbsoluteFile());
			FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());

			bw1 = new BufferedWriter(fw1);
			bw2 = new BufferedWriter(fw2);

			writeDefinitionToArffFile(bw1);
			writeDefinitionToArffFile(bw2);

			double[] weightVector = { 0.26302574190381356, 0.35970283982751217,
					0.4591264825045235, 0.8104021593622109, 0.3362905692620827,
					0.8347215710316205, 0.08145738762501631, 0.839838184175911,
					0.7542028993081025, 0.5021832961493949 };

			double b = 0.0;
			double epsilon = 100.0;

			double[] maxX = { 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0,
					100.0, 100.0, 100.0 };
			double maxWDotX = 0.0;

			for (int i = 0; i < 10; i++) {
				maxWDotX += maxX[i] * weightVector[i];
			}
			double maxY = maxWDotX + b;
			int incorrectCount = 0;

			for (int iter = 0; iter < 200; iter++) {
				double[] randomX = new double[10];

				for (int i = 0; i < 10; i++) {
					randomX[i] = rnd.nextDouble() * 100;
				}

				double randomY = rnd.nextDouble() * maxY;

				double wDotX = 0.0;

				for (int i = 0; i < 10; i++) {
					wDotX += randomX[i] * weightVector[i];
				}

				String trueLabel;
				String noisyLabel;
				if (randomY - wDotX - b > 0) {
					trueLabel = "\"A\"";
				} else {
					trueLabel = "\"B\"";
				}

				if (randomY - wDotX - b - epsilon > 0) {
					noisyLabel = "\"A\"";
				} else {
					noisyLabel = "\"B\"";
				}
				
				if (trueLabel != noisyLabel) {
					incorrectCount++;
				}

				writeDataSet(bw1, trueLabel, randomX);
				writeDataSet(bw2, noisyLabel, randomX);
			}

			bw1.close();
			bw2.close();
			System.out.println((double) incorrectCount / 2);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private static void writeDataSet(BufferedWriter bw, String label,
			double[] randomX) throws IOException {

		String featureVector = "";

		for (int i = 0; i < 10; i++) {
			if (featureVector.equals("")) {
				featureVector = featureVector + randomX[i];
			} else {
				featureVector = featureVector + "," + randomX[i];
			}
		}
		featureVector += "," + label;
		bw.write(featureVector);
		bw.newLine();
	}

	private static void writeDefinitionToArffFile(BufferedWriter bw)
			throws IOException {
		String definition = "";
		definition += "@RELATION Linear \n";
		definition += "@ATTRIBUTE f1 NUMERIC\n";
		definition += "@ATTRIBUTE f2 NUMERIC\n";
		definition += "@ATTRIBUTE f3 NUMERIC\n";
		definition += "@ATTRIBUTE f4 NUMERIC\n";
		definition += "@ATTRIBUTE f5 NUMERIC\n";
		definition += "@ATTRIBUTE f6 NUMERIC\n";
		definition += "@ATTRIBUTE f7 NUMERIC\n";
		definition += "@ATTRIBUTE f8 NUMERIC\n";
		definition += "@ATTRIBUTE f9 NUMERIC\n";
		definition += "@ATTRIBUTE f10 NUMERIC\n";
		definition += "@ATTRIBUTE class {A,B}\n\n";
		definition += "@Data";
		bw.write(definition);
		bw.newLine();
	}

}
