import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Islemler {

    static List<Kisi> ogrtList=new ArrayList<>();
    static List<Kisi> ogrncList=new ArrayList<>();
    static Scanner scan=new Scanner(System.in);
   static String kisiTuru;





    public static void girisPaneli(){
        System.out.println("================================");
        System.out.println("OGRENCI VE OGRETMEN YONETIM PANELI");
        System.out.println("================================");
        System.out.println("1- Ogrenci Islemleri");
        System.out.println("2- Ogretmen Islemleri");
        System.out.println("Q- Cikis");

        String secim=scan.next().toUpperCase();
        switch(secim){
            case "1":
                kisiTuru="OGRENCI";
                islemMenusu();
                break;
                
                case "2":
                    kisiTuru="OGRETMEN";
                    islemMenusu();
                break;
                
                case "Q":
                break;
                
            default:
                System.out.println("Hatali giris :(");
                girisPaneli();

        }
    }

    private static void islemMenusu() {
        System.out.println("Sectigin kisi turu : "+ kisiTuru+ "\nLutfen asagidaki islemleri seciniz");

        System.out.println("============Islemler==========\n"+
                " 1-EKLEME\n"+
                " 2-ARAMA\n"+
                " 3-LISTELEME\n"+
                " 4-SILME\n"+
                " 5-ANA MENU\n"+
                " 0-CIKIS");
        
        System.out.println("Islem tercihinizi giriniz");
        int secilenIslem= scan.nextInt();

        switch (secilenIslem){
            case 1:
                ekle();
                islemMenusu();
                break;
            case 2:
                arama();
                islemMenusu();
                break;
            case 3:
                listeleme();
                islemMenusu();
                break;
            case 4:
                silme();
                islemMenusu();
                break;
            case 5:
                girisPaneli();
                break;
            case 0:
                cikis();
                break;
            default:
                System.out.println("duzgun gir");
                islemMenusu();
                break;
                }
    }

    private static void cikis() {
        System.out.println("yine bekleriz");
    }

    private static void silme() {
        System.out.println("******* "+kisiTuru+" silme sayfasina hos geldiniz");
        boolean flag=true;
        
        if (kisiTuru.equalsIgnoreCase("OGRENCI")){
            System.out.println("Silmek istedigin Kimlik no giriniz");
            String silinecekKimlikNo= scan.next().replaceAll(" ","");
            for (Kisi each:ogrncList) {
                if (each.getKimlikNo().equals(silinecekKimlikNo)){
                    System.out.println("silinen ogrenci: "+each.getAdSoyad());
                    ogrncList.remove(each);
                    flag=false;
                    break;
                }
            }
            if (flag){//eger girilen tc list te yok ise bu if calisir
                System.out.println("bu tc ile ogrenci yok silinemedi");
            }
        }
        else {//ogretmen islemleri icin
            System.out.println("silinecek ogretmennin kimlik no gir");
            String silinecekKimlikNo= scan.next().replaceAll(" ","");
            for(Kisi each: ogrtList){
                if (each.getKimlikNo().equalsIgnoreCase(silinecekKimlikNo)){
                    System.out.println("silinen ogretmen: "+ each.getAdSoyad());
                    ogrtList.remove(each);
                    flag=false;
                    break;
                }
            }if (flag){
                System.out.println("bu tc ile ogretmen bulunamadi ve silinemedi");
            }
        }
        
        
    }

    private static void listeleme() {
        System.out.println("******* "+kisiTuru+" listeleme sayfasina hos geldiniz");
        if (kisiTuru.equalsIgnoreCase("OGRENCI")){
            System.out.println("ogrenci listesi = "+ogrncList);
        }else {
            for (Kisi each:ogrtList) {
                System.out.println("ogretmen listesi = "+ each.toString());
            }

        }
    }

    private static void arama() {
        System.out.println("******* "+kisiTuru+" arama sayfasina hos geldiniz");
        boolean flag=true;//true olan her sart devam eder

        if (kisiTuru.equalsIgnoreCase("OGRENCI")){
            System.out.println("kimlik no giriniz");
            String arananKimlikNo=scan.next().replaceAll(" ","");//kullanici girerse diye boslugu yok ettik

            for (Kisi each:ogrncList) {
            if (each.getKimlikNo().equals(arananKimlikNo)){
                System.out.println("Aradiginiz ogrenci : "+ each.getAdSoyad());
                flag=false;
            }

            }
            if (flag){//flag tru ise hala calisir
                System.out.println("Aranan tc ile ogrenci yok");
                }
        }else{//ogretmen

            System.out.println("kimlik no giriniz");
            String arananKimlikNo=scan.next().replaceAll(" ","");

            for (Kisi each:ogrtList) {
                if (each.getKimlikNo().equals(arananKimlikNo)){
                    System.out.println("Aradiginiz ogretmen : "+ each.getAdSoyad());
                    flag=false;
                }

            }
            if (flag){//flag tru ise hala calisir
                System.out.println("Aranan tc ile ogretmen yok");
            }

        }




    }

    private static void ekle() {//hem ogrenci hem ogretmen icin tasarlandi
        System.out.println("**** " + kisiTuru + " ekleme sayfasina hosgeldin");
        System.out.println("isim soyisim gir");
        scan.nextLine();
        String adSoyad = scan.nextLine();
        System.out.println("kimlik gir");
        String kimlikNo = scan.nextLine();
        System.out.println("yas gir");
        int yas = scan.nextInt();
        String ogrenciNo;
        String sinif;

        if (kisiTuru.equals("OGRENCI")){
            System.out.println("ogrenci no gir");
            ogrenciNo= scan.next().replaceAll(" ","");

            System.out.println("sinif gir");
            sinif= scan.next().replaceAll(" ","");
            Ogrenci ogrenci=new Ogrenci(adSoyad,kimlikNo,yas,ogrenciNo,sinif);
            ogrncList.add(ogrenci);


        }else {
            scan.nextLine();
            System.out.println("bolum giriniz");
            String bolum=scan.nextLine();
            //scan.nextLine();//dummy hata almamak icin
            System.out.println("sicil no giriniz");
            String sicilNo= scan.nextLine();

            Ogretmen ogretmen = new Ogretmen(adSoyad,kimlikNo,yas,bolum,sicilNo);
            ogrtList.add(ogretmen);
            System.out.println(ogrtList);

        }

    }
}
