package com.appspot.dmutti.calculator.support;

import java.math.*;
import java.text.*;
import javax.persistence.*;
import javax.servlet.http.*;
import org.joda.time.format.*;

public class Helper {
    
    
    public static final DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
    public static final DateTimeFormatter timeFormat = DateTimeFormat.forPattern("HH:mm:ss");
    public static final DateTimeFormatter ligacaoFormat = DateTimeFormat.forPattern("dd/MM/yy HH'H'mm'M'ss");
    public static final BigDecimal MINUTO = new BigDecimal("60");
    
    public static EntityManager getEntityManagerFrom(HttpServletRequest req) {
    	return (EntityManager) req.getAttribute(Constants.EntityManager.toString());
    }
    
    public static NumberFormat getNumberFormat() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        df.setDecimalFormatSymbols(dfs);
        return df;
    }
    
    public static NumberFormat getNumberFormatDuracao() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(0);
        df.setDecimalSeparatorAlwaysShown(false);
        df.setDecimalFormatSymbols(dfs);
        return df;
    }
}
