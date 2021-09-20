import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

        final File save1 = new File("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\Games\\savegames\\", "player1.dat");
        final File save2 = new File("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\Games\\savegames\\", "player2.dat");
        final File save3 = new File("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\Games\\savegames\\", "playerAdmin.dat");

        List<File> saveList = new ArrayList<>();
        saveList.add(save1);
        saveList.add(save2);
        saveList.add(save3);

        zipFiles("C:\\Users\\Елисей\\IdeaProjects\\InstallDota2\\Games\\savegames\\zip.zip", saveList);

        for (File file: saveList){
            removeFile(file);
        }


    }

    public static void saveGame(String path, GameProgress gp) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public static void zipFiles(String pathZip, List<File> list) {
        ZipOutputStream zout = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try{
            zout = new ZipOutputStream(fos = new FileOutputStream(pathZip));
            for (File file : list) {
                fis = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                try {
                    if (fis != null){
                        fis.close();
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            if (zout != null) {
                zout.close();
            }
            if (fos != null){
                fos.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void removeFile(File file) {
        try{
            File file2 = new File(file.getCanonicalPath());
            if (file2.delete()) {
                System.out.println(file.getName() + " удален");
            }else{
                System.out.println(file.getName() + " не удален");
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
