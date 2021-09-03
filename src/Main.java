import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress player1 = new GameProgress(15, 2, 3, 3.2);
        GameProgress player2 = new GameProgress(24, 5, 6, 17.5);
        GameProgress playerAdmin = new GameProgress(1337, 1337, 1337, 1337);

        saveGame("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\games\\savegames\\player1.dat", player1);
        saveGame("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\games\\savegames\\player2.dat", player2);
        saveGame("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\games\\savegames\\playerAdmin.dat", playerAdmin);

        File save1 = new File("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\Games\\savegames\\", "player1.dat");
        File save2 = new File("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\Games\\savegames\\", "player2.dat");
        File save3 = new File("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\Games\\savegames\\", "playerAdmin.dat");
        zipFiles2("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\Games\\savegames\\zip.zip", save1, save2, save3);


        removeFile(save1);
        removeFile(save2);
        removeFile(save3);


    }

    public static void saveGame(String path, GameProgress gp) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public static void zipFiles2(String pathZip, File file1, File file2, File file3) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(pathZip));
             FileInputStream fis1 = new FileInputStream(file1)) {
            ZipEntry entry = new ZipEntry(file1.getName());
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis1.available()];
            fis1.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

            try {
                FileInputStream fis2 = new FileInputStream(file2);
                entry = new ZipEntry(file2.getName());
                zout.putNextEntry(entry);
                buffer = new byte[fis2.available()];
                fis2.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            try {
                FileInputStream fis3 = new FileInputStream(file3);
                entry = new ZipEntry(file3.getName());
                zout.putNextEntry(entry);
                buffer = new byte[fis3.available()];
                fis3.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void removeFile(File file) {
        if (file.delete()) {
            System.out.println(file.getName() + " удален");
        }
    }
}
