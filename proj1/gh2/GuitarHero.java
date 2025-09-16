package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final double CONCERT_A = 440.0;
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString[] strings = new GuitarString[37];

        // 初始化数组中的每个GuitarString对象，频率为440 * 2^((i-24)/12)
        for (int i = 0; i < strings.length; i++) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24.0) / 12.0);
            strings[i] = new GuitarString(frequency);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                /* compute the superposition of samples */
                if (index != -1) {
                    strings[index].pluck();
                }

            }
            double sample = 0;
            for (GuitarString g : strings) {
                sample += g.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString g : strings) {
                g.tic();
            }
        }
    }
}