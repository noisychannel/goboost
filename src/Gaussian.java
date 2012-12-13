import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Gaussian
{
    private static Random rnd = new Random();

    public static void main(String[] args)
    {
        File file = new File("E:\\code\\MachineLearning\\ML-CS475\\res3\\gaussian\\guassian.arff");
        BufferedWriter bw;
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            for (int l = 0; l < 20; l++)
            {
                String labelA = "1";
                double[] mean = {50, 40, 20, 6, 10, 100, 2, 12000, 8, 120};
                double[] sigma = {25, 15, 3, 1, 5, 12, 0.1, 500, 1, 34};

                for (int i = 0; i < 10; i++)
                {
                    // Add 10% noise
                    if (i % 5 == 0) labelA = "1";
                    String featureVector = labelA + " ";
                    double[] X = generateRandomVector(mean, sigma);
                    for (int j = 0; j < X.length; j++)
                    {
                        featureVector = featureVector + (j + 1) + ":" + X[j] + " ";
                    }

                    bw.write(featureVector);
                    bw.newLine();
                }

                String labelB = "0";
                double[] mean2 = {85, 2000, 0.4, 998, 10, 100, 2, 9000, 67, 120};
                double[] sigma2 = {45, 56, 3, 0.2, 70, 12, 0.1, 1000, 11, 0.4};
                for (int i = 0; i < 10; i++)
                {
                    // Add 10% noise
                    if (i % 5 == 0) labelB = "1";
                    String featureVector = labelB + " ";
                    double[] X = generateRandomVector(mean2, sigma2);

                    for (int j = 0; j < X.length; j++)
                    {
                        featureVector = featureVector + (j + 1) + ":" + X[j] + " ";
                    }

                    bw.write(featureVector);
                    bw.newLine();
                }
            }

            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void writeDefinitionToArffFile(BufferedWriter bw) throws IOException
    {
        String definition = new String();
        definition += "@RELATION Gaussian \n";
        definition += "@ATTRIBUTE f1 NUMERIC \n";
        definition += "@ATTRIBUTE f2 NUMERIC \n";
        definition += "@ATTRIBUTE f3 NUMERIC \n";
        definition += "@ATTRIBUTE f4 NUMERIC \n";
        definition += "@ATTRIBUTE f5 NUMERIC \n";
        definition += "@ATTRIBUTE f6 NUMERIC \n";
        definition += "@ATTRIBUTE f7 NUMERIC \n";
        definition += "@ATTRIBUTE f8 NUMERIC \n";
        definition += "@ATTRIBUTE f9 NUMERIC \n";
        definition += "@ATTRIBUTE label NUMERIC \n";
        bw.write(definition);
        bw.newLine();
    }

    private static double[] generateRandomVector(double[] mu, double[] sigma)
    {
        double[] randomVector = new double[mu.length];

        for (int i = 0; i < randomVector.length; i++)
        {
            randomVector[i] = mu[i] + sigma[i] * rnd.nextGaussian();
        }
        return randomVector;
    }

}





