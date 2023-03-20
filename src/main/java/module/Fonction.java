package module;

import table.BDObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fonction {
    public static String addMonthToDate(String datedebut, int nombreMois) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(datedebut);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, nombreMois);

        Date resultdate = cal.getTime();
        String datefin = sdf.format(resultdate);

        return datefin;
    }

    public static void main(String[] args) {
        String s = "test";
        if(s.contains("'")){
            System.out.println(s);
        }
    }
}
