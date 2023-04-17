package themes;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Vector;

public class Themes {
    public String couleurPolice;
    public String couleurBody;
    public String couleurMenu;
    public String couleurFormContent;
    public String couleurNavigationbar;
    public String couleurFooter;
    public String couleurSubMenu;
    public String couleurSubMenu2;
    public String divCouleur1;
    public String divCouleur2;
    public String couleurPolice2;
    public String couleurPoliceTableau;

    public String getCouleurPolice() {
        return couleurPolice;
    }

    public void setCouleurPolice(String couleurPolice) {
        this.couleurPolice = couleurPolice;
    }

    public String getCouleurBody() {
        return couleurBody;
    }

    public void setCouleurBody(String couleurBody) {
        this.couleurBody = couleurBody;
    }

    public String getCouleurMenu() {
        return couleurMenu;
    }

    public void setCouleurMenu(String couleurMenu) {
        this.couleurMenu = couleurMenu;
    }

    public String getCouleurFormContent() {
        return couleurFormContent;
    }

    public void setCouleurFormContent(String couleurFormContent) {
        this.couleurFormContent = couleurFormContent;
    }

    public String getCouleurNavigationbar() {
        return couleurNavigationbar;
    }

    public void setCouleurNavigationbar(String couleurNavigationbar) {
        this.couleurNavigationbar = couleurNavigationbar;
    }

    public String getCouleurFooter() {
        return couleurFooter;
    }

    public void setCouleurFooter(String couleurFooter) {
        this.couleurFooter = couleurFooter;
    }

    public String getCouleurSubMenu() {
        return couleurSubMenu;
    }

    public void setCouleurSubMenu(String couleurSubMenu) {
        this.couleurSubMenu = couleurSubMenu;
    }

    public String getCouleurSubMenu2() {
        return couleurSubMenu2;
    }

    public void setCouleurSubMenu2(String couleurSubMenu2) {
        this.couleurSubMenu2 = couleurSubMenu2;
    }

    public String getDivCouleur1() {
        return divCouleur1;
    }

    public void setDivCouleur1(String divCouleur1) {
        this.divCouleur1 = divCouleur1;
    }

    public String getDivCouleur2() {
        return divCouleur2;
    }

    public void setDivCouleur2(String divCouleur2) {
        this.divCouleur2 = divCouleur2;
    }

    public String getCouleurPolice2() {
        return couleurPolice2;
    }

    public void setCouleurPolice2(String couleurPolice2) {
        this.couleurPolice2 = couleurPolice2;
    }

    public String getCouleurPoliceTableau() {
        return couleurPoliceTableau;
    }

    public void setCouleurPoliceTableau(String couleurPoliceTableau) {
        this.couleurPoliceTableau = couleurPoliceTableau;
    }

    public Themes() {
    }

    public static void FileWrite(String textes, File file, boolean append){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, append))){
            bw.write(textes);
            bw.newLine();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static Vector<String> FileRead(File file){
        Vector<String> lignes = new Vector<>();
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line = br.readLine()) != null){
                lignes.add(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lignes;
    }

    public static void createDarkThemes(String path){
        File dir = new File(path);
        File current = new File(path+"/current");
        if(!dir.exists()){
            dir.mkdir();
        }
        if(dir.exists() && !current.exists()){
            try {
                current.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Vector<String> lignes = FileRead(current);
        boolean complet = true;
        for(String s : lignes){
            if (s.equals("")) {
                complet = false;
                break;
            }
        }
        if(lignes.size() <= 4){
            complet = false;
        }
        if(!complet){
            try {
                current.createNewFile();
                BlackTheme(current);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createLightThemes(String path){
        File dir = new File(path);
        File current = new File(path+"/current");
        if(!dir.exists()){
            dir.mkdir();
        }
        if(dir.exists() && !current.exists()){
            try {
                current.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Vector<String> lignes = FileRead(current);
        boolean complet = true;
        for(String s : lignes){
            if (s.equals("")) {
                complet = false;
                break;
            }
        }
        if(lignes.size() <= 4){
            complet = false;
        }
        if(!complet){
            try {
                current.createNewFile();
                LightTheme(current);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void BlackTheme(File current){
        Vector<String> themes = new Vector<>();
        themes.add("dimgrey"); // Police
        themes.add("rgba(48, 56, 65, 0.9)"); // body
        themes.add("rgb(48, 56, 65)");  // menu
        themes.add("rgb(48, 56, 65)"); // formContent
        themes.add("rgb(48, 56, 65)");    // Navigationbar
        themes.add("rgb(48, 56, 65)"); // Footer
        themes.add("rgb(48, 56, 65)"); // SubMenu
        themes.add("rgb(48, 56, 65)"); // SubMenu2
        themes.add("linear-gradient(45deg, rgba(255, 255, 255, 1), rgba(255, 255, 255, 0.8))"); // divCouleur1
        themes.add("linear-gradient(45deg, mediumseagreen, lightseagreen)"); // divCouleur2
        themes.add("mediumspringgreen");    // police des sous-titres
        themes.add("white");    // police des tableaux

        int it = 0;
        for(String color : themes){
            FileWrite(color, current, it != 0);
            it++;
        }
    }

    public static void LightTheme(File current){
        Vector<String> themes = new Vector<>();
        themes.add("black"); // Police
        themes.add("white"); // body
        themes.add("lightgrey");  // menu
        themes.add("lightgrey"); // formContent
        themes.add("lightgrey");    // Navigationbar
        themes.add("lightgrey"); // Footer
        themes.add("white"); // SubMenu
        themes.add("white"); // SubMenu2
        themes.add("linear-gradient(45deg, rgba(255, 255, 255, 1), rgba(255, 255, 255, 0.8))"); // divCouleur1
        themes.add("linear-gradient(45deg, mediumseagreen, lightseagreen)"); // divCouleur2
        themes.add("lightseagreen");    // police des sous-titres
        themes.add("black");    // police des tableaux

        int it = 0;
        for(String color : themes){
            FileWrite(color, current, it != 0);
            it++;
        }
    }

    public static Themes getCurrentTheme(String path){
        try{
            File temp = new File(path+"/current");
            //System.out.println("AbsolutePath: "+temp.getAbsolutePath());
        }catch (Exception e){
            createDarkThemes(path);
            System.out.println("Creation d'un fichier");
        }
        File current = new File(path+"/current");
        Vector<String> lignes = FileRead(current);
        Field[] fields = Themes.class.getFields();
        if(lignes.size() < fields.length){
            BlackTheme(current);
        }

        Themes themes = new Themes();
        themes.setCouleurPolice(lignes.get(0));
        themes.setCouleurBody(lignes.get(1));
        themes.setCouleurMenu(lignes.get(2));
        themes.setCouleurFormContent(lignes.get(3));
        themes.setCouleurNavigationbar(lignes.get(4));
        themes.setCouleurFooter(lignes.get(5));
        themes.setCouleurSubMenu(lignes.get(6));
        themes.setCouleurSubMenu2(lignes.get(7));
        themes.setDivCouleur1(lignes.get(8));
        themes.setDivCouleur2(lignes.get(9));
        themes.setCouleurPolice2(lignes.get(10));
        themes.setCouleurPoliceTableau(lignes.get(11));

        return themes;
    }
}
