import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WekaGaussianDataSet
{
    private static Random rnd = new Random();

    public static void main(String[] args)
    {
        File file = new File("E:\\code\\MachineLearning\\ML-CS475\\res3\\weka\\gaussian-2000\\gaussian-10.arff");
        BufferedWriter bw;
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            writeDefinitionToArffFile(bw);

            for (int l = 0; l < 100 ; l++)
            {
                double[] mean = {50, 40, 20, 6, 10, 100, 2, 12000, 8, 120};
                double[] sigma = {25, 15, 3, 1, 5, 12, 0.1, 500, 1, 34};

                for (int i = 1; i <= 10; i++)
                {
                    String labelA = "\"A\"";
                    // Add some noise
                    if (i % 10 == 0)
                        labelA = "\"B\"";
                    writeDataSet(bw, labelA, mean, sigma);
                }


                double[] mean2 = {85, 2000, 0.4, 998, 10, 100, 2, 9000, 67, 120};
                double[] sigma2 = {45, 56, 3, 0.2, 70, 12, 0.1, 1000, 11, 0.4};

                for (int i = 1; i <= 10; i++)
                {
                    String labelB = "\"B\"";
                    // Add some noise
                    if (i % 10 == 0)
                        labelB = "\"A\"";
                    writeDataSet(bw, labelB, mean2, sigma2);
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

    private static void writeDataSet(
            BufferedWriter bw,
            String label,
            double[] mean,
            double[] sigma) throws IOException
    {
        String featureVector = "";
        double[] X = generateRandomVector(mean, sigma);
        for (double gaussian : X)
        {
            if (featureVector.equals(""))
            {
                featureVector = featureVector + gaussian;
            }
            else
            {
                featureVector = featureVector + "," + gaussian;
            }
        }
        featureVector += "," + label;
        bw.write(featureVector);
        bw.newLine();
    }

    private static void writeDefinitionToArffFile(BufferedWriter bw) throws IOException
    {
        String definition = "";
        definition += "@RELATION Gaussian \n";
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





