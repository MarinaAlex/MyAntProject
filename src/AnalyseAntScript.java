
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Создать приложение анализируещее файлы типа ant script
 */
public class AnalyseAntScript {
    private String nameFile;
    private String nameTarget;

    public String readFileName(){
        Scanner s = new Scanner(System.in);
        System.out.println("Input file name with path");
        String name = s.next();

            if(!name.contains(".xml")) {

            System.out.println("This is not ant file");
        }
        return name;
    }

    public void choiceMethod(){
       while (true){
           System.out.println("If you want to get list target input 1," +
                           "\n if you want to get trace target with depends input 2"+
                            "\n if you want exit input 0");
           Scanner sc = new Scanner(System.in);
           int number = sc.nextInt();
           if(number == 1){
               listTarget();
           }else if(number == 2){

               traceTarget(nameTarget);
           }else if(number == 0){
               break;
           }
       }
    }

    public ArrayList listTarget(){
        nameFile = readFileName();
        FileInputStream fis = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            fis = new FileInputStream( nameFile);
            Scanner sc = new Scanner(fis);

            while (sc.hasNext()) {
                String str = sc.nextLine();
                if(str.contains("<target name=")){
                    String tmp = str.substring(str.indexOf('"'), str.indexOf(" d"));
                    list.add(tmp);
                }
            }
            System.out.println(list);
          fis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }
     public String nameTarget(){
             Scanner s = new Scanner(System.in);
               System.out.println("Input name target");
                String name = s.next();
               return name;
     }
     public ArrayList traceTarget(String name){
            nameTarget = nameTarget();

            FileInputStream fis = null;
            ArrayList<String> list = new ArrayList<String>();
            try {
                fis = new FileInputStream(nameFile);
                Scanner sc = new Scanner(fis);

                while (sc.hasNext()) {
                    String str = sc.nextLine();
                    if(str.contains("<target name=" + '"' + nameTarget + '"') && str.contains(" depends=")){//
                        String tmp = str.substring( str.indexOf(" depends=")+10,str.lastIndexOf('"'));
                        if(tmp.contains(",")){
                            String [] mas = tmp.split(",");
                            for (int i = 0; i < mas.length ; i++) {
                                list.add(mas[i]);
                            }
                        }else{
                            list.add(tmp);
                        }
                    }
                }

                if(list!=null){
                    System.out.println(list);
                }else{
                    System.out.println("not depends");
                }
                for (int i = 0; i <list.size() ; i++) {
                    traceTarget(list.get(i));
                }
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return list;
     }
    public static void main(String[] args) {
        AnalyseAntScript antScript = new AnalyseAntScript();
        antScript.choiceMethod();

    }

}
