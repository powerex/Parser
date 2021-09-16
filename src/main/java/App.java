import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        List<String> dates = new ArrayList<>();
        List<EventLayer> events = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("http://nmu.npu.edu.ua/cgi-bin/timetable.cgi?n=700&teacher=2822&edate=07.10.2021").get();
//            Document doc = Jsoup.connect(
//            "http://nmu.npu.edu.ua/cgi-bin/timetable.cgi?n=700&teacher=3983&edate=30.09.2021"
//            ).get();
//            File input = new File("file.html");
            Elements elements = doc.select("div.col-md-6");
            elements.select("h4").forEach(s -> dates.add(s.toString().substring(4, 14)));

            elements = elements.select("tbody");
            for (int i=0; i<elements.size(); ++i) {
                Elements rows = elements.get(i).select("tr");
                Elements tr = rows.select("tr");
                int finalI = i;
                tr.forEach(s -> {
                    if (s.children().get(2).html().length() > 0) {
                        EventLayerBuilder eb = new EventLayerBuilder();
                        eb.setDate(dates.get(finalI));
                        eb.setStartTime(s.children().get(1).html().substring(0, 5));
                        eb.setEndTime(s.children().get(1).html().substring(9, 14));
                        String[] ss = s.children().get(2).html().split("<br>");
                        int beginIndex = ss[0].indexOf('(');
                        eb.setSubject(ss[0].substring(0, beginIndex).trim());
                        eb.setLection(ss[0].startsWith("(Л)", beginIndex));
                        if (ss[1].startsWith(" з")) {
                            ss[1] = ss[1].substring(8);
                        }
                        eb.setGroup(ss[1].trim());
                        if (ss[2].trim().length() > 0 && ss[2].indexOf("(") > 0)
                            eb.setClassroom(ss[2].trim().substring(4, ss[2].indexOf("(")-1).trim());
                        else
                            eb.setClassroom("---");
                        events.add(eb.build());
                    }
                });
            }

            events.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
