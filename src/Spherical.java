import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Spherical
{
    private static Random rnd = new Random();

    public static void main(String[] args)
    {

        BufferedWriter bw;
        File file = new File("E:\\code\\MachineLearning\\ML-CS475\\res3\\gaussian\\spherical-20.dev");
        double radius = 0.6;

        try
        {
            if (!file.exists()) file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            for (int example = 0; example < 100; example++)
            {
                int label = 1;
                double[] X = new double[10];

                for (int i = 0; i < X.length; i++)
                {
                    X[i] = rnd.nextDouble();
                    if (Math.abs(X[i]) > radius && label == 1)
                        label = 0;
                }

                // Add 10% noise
                if (example % 5 == 0)
                {
                    label = toggle(label);
                }

                String featureVector = label + " ";
                for (int j = 0; j < X.length; j++)
                {
                    featureVector = featureVector + (j + 1) + ":" + X[j] + " ";
                }

                bw.write(featureVector);
                bw.newLine();
            }

            bw.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }


    }

    static int toggle(int label)
    {
        return label == 1 ? 0 : 1;
    }

}





