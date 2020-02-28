package xyz.lavaliva.kezamal1;

public class Doljnosti {

    String imiaDoljnosti="i";
    String Rukovoditel="ru";
    String Prorab="p";
    String brigadir="b";
    String rabohiy="ra";
    String texnadzor="t";
    String geodezist="g";
    String OTK="";
    String Subpodriadhik="su";
    String snabjenec="sn";
    String HR="hr";
    String [] doljnostiArr ={
            Rukovoditel,
            Prorab,
            brigadir,
            rabohiy,
            texnadzor,
            geodezist,
            OTK,
            Subpodriadhik,
            snabjenec,
            HR};
    public String getImiaDoljnosti() {
        return imiaDoljnosti;
    }

    public void setImiaDoljnosti(int i) {

        this.imiaDoljnosti = doljnostiArr[i];
        System.out.println(imiaDoljnosti+" imia doljnosti");
    }

    public static String[] getStringArr(A2RegistraciaPolzovatelia th) {
        String sp2Rukovoditel=th.getResources().getString(R.string.rukovoditel);
        String sp2Prorab=th.getResources().getString(R.string.prorab);
        String sp2brigadir=th.getResources().getString(R.string.brigadir);
        String sp2rabohiy=th.getResources().getString(R.string.rabohiy);
        String sp2texnadzor=th.getResources().getString(R.string.texnadzor);
        String sp2geodezist=th.getResources().getString(R.string.geodezist);
        String sp2OTK=th.getResources().getString(R.string.OTK);
        String sp2Subpodriadhik=th.getResources().getString(R.string.subpodriadhik);
        String sp2snabjenec=th.getResources().getString(R.string.snabjenec);
        String sp2HR=th.getResources().getString(R.string.HR);

String [] data2 ={sp2Rukovoditel,
        sp2Prorab,
        sp2brigadir,
        sp2rabohiy,
        sp2texnadzor,
        sp2geodezist,
        sp2OTK,
        sp2Subpodriadhik,
        sp2snabjenec,
        sp2HR};
        return data2;
    }


}
