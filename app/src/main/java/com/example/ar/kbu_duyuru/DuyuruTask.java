package com.example.ar.kbu_duyuru;
import java.util.List;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
public class DuyuruTask extends AsyncTask<Void, Void, Void>{
    private ProgressDialog pd;
    private Context context;
    private List<DuyuruModel> duyuruItems;
    public static String duyuru;

    public DuyuruTask(Context context) {
        this.context = context;
        duyuruItems = new ArrayList<>();;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Lütfen bekleyiniz...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();
    }
    @Override
    protected Void doInBackground(Void... params) {
        // TODO Auto-generated method stub
        String tarih, phone;
        Document doc;
        //String url = "https://www.nobetcieczanebul.com/bursa-nobetci-eczane";
        //String url = "https://www.karabuk.edu.tr/duyurular/";
        String url = "http://muh.karabuk.edu.tr/index.php?page=announcements";
        int i2 = 1;

        try {
            duyuruItems.remove(duyuruItems);
            doc = Jsoup.connect(url).ignoreContentType(true).get();
            /*for (Element row : doc.select("div.col")) {
                Elements header = row.select("div.card-header");
                Elements body = row.select("div.card-body");
                address = body.text().substring(0, body.text().indexOf("©"));
                phone = body.text().substring(body.text().indexOf("Tel :") + 6, body.text().indexOf("Tel :") + 21);
                DuyuruModel item = new DuyuruModel(header.text(), address, phone);
                duyuruItems.add(item);
                duyuru = header.text();
            }*/
            Elements ele = doc.select("div#main");
            Elements table = ele.select("table");
            //Elements rows = table.select("tr");
            Elements cols = table.select("tr[align=left]");
            //Elements colTime = table.select("tr");
            for(Element aTitle : cols.select("a.duyuru")){  // Listede gösterilen duyuru başlıklarını alır
                Elements dSayi = table.select("tr.duyurutd"); // Duyuru listesindeki duyuru sayısını çeker
                Elements a = cols.select("a[href]");// Listede gösterilen duyuru linklerini alır
                //Elements aTitle = cols.select("a.duyuru");// Listede gösterilen duyuru başlıklarını alır
                Elements p = dSayi.select("p");// Listedeki duyuru tarihlerini alır

                DuyuruModel item = new DuyuruModel(aTitle.text(), p.get(i2).text());
                duyuruItems.add(item);
                duyuru = aTitle.text();
                i2 += 3;
            }


           /*
            Elements row = doc.select("div.haberYaziTxt");
        for (Element header : row.select("li")) {
            Elements aTitle = header.select("a");
            DuyuruModel item = new DuyuruModel(aTitle.text());
            duyuruItems.add(item);
            duyuru = aTitle.text();
        } */
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        pd.dismiss();
        DuyuruAdapter adapter = new DuyuruAdapter(context, duyuruItems);
        NavDrawerActivity.listDuyuru.setAdapter(adapter);
    }
}
