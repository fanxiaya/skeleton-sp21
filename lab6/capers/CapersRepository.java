package capers;

import java.io.File;
import java.io.IOException;
import static capers.Utils.*;

/** A repository for Capers
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = join(CWD,".capers"); // TODO Hint: look at the `join`
                                            //      function in Utils

    static final File STORY_File = join(CWD,".capers/story.txt");
    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence()  {
        if (!CAPERS_FOLDER.exists()) {
            CAPERS_FOLDER.mkdir();
        }
        // 创建 dogs 目录
        File dogsFolder = join(CAPERS_FOLDER, "dogs");
        if (!dogsFolder.exists()) {
            dogsFolder.mkdir();
        }
        // 创建 story.txt 文件
        if (!STORY_File.exists()) {
            try {
                STORY_File.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) throws IOException {
        // TODO
    if (!STORY_File.exists()){STORY_File.createNewFile();}
    String oldStory  =readContentsAsString(STORY_File);
    StringBuffer sb = new StringBuffer();
    sb.append(oldStory);
    sb.append(text);
    sb.append("\n");

    String newStory = sb.toString();
    writeContents(STORY_File, newStory);
    System.out.print(newStory.trim());

    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) throws IOException {
        //TODO
        if (!Dog.DOG_FOLDER.exists()){
            Dog.DOG_FOLDER.mkdirs(); // 用 mkdirs()
        }
        Dog newDog = new Dog(name,breed,age);

        newDog.saveDog();
        System.out.println(newDog); // println 保证换行
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) throws IOException {
        Dog dog = Dog.fromFile(name);
        dog.haveBirthday();
        dog.saveDog();
    }
}