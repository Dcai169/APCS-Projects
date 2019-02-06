import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;

public class CVN65 {
    public CVN65(){}
    public void drawCV(Graphics2D g2){
        //Colors, Strokes, & Gradients
        Color lowerhull = new Color(130,130,130);
        Color upperhull = new Color(100,100,100);
        Color forwdspst = new Color(145,145,145);
        Color deck      = new Color(64 ,50 ,64 );
        Color foam      = new Color(255,255,255,30);
        Color deckmarks = new Color(255,255,255);
        BasicStroke deckstroke = new BasicStroke(4);
        GradientPaint hullShadow = new GradientPaint(600,555,lowerhull,600,500, upperhull);
        GradientPaint sptfShadow = new GradientPaint(680,390,upperhull,680,425, forwdspst);
        //Polygon Definitions
        //Lower Starboard Hull
        int[] lowerhullx = {715 ,725 ,740 ,755 ,1025,1090,1100,1070,810 ,425 ,425 ,455 ,450 };
        int[] lowerhully = {465 ,500 ,525 ,540 ,485 ,465 ,460 ,490 ,550 ,610 ,580 ,530 ,510 };
        //Upper Starboard Hull
        int[] upperhullx = {715 ,725 ,740 ,755 ,1025,1090,1100,1140,840 };
        int[] upperhully = {465 ,500 ,525 ,540 ,485 ,465 ,460 ,410 ,470 };
        //For'd Hull
        int[] foredhullx = {455 ,330 ,330 ,360 ,425 ,460 };
        int[] foredhully = {510 ,500 ,520 ,535 ,580 ,530 };
        //Flight Deck
        int[] flghtdeckx = {760 ,760 ,740 ,650 ,465 ,500 ,330 ,455 ,720 ,840 ,1140,1110,930 ,800 };
        int[] flghtdecky = {410 ,420 ,430 ,420 ,455 ,465 ,500 ,510 ,470 ,470 ,410 ,400 ,390 ,410 };
        //Superstructure Base For'd Face
        int[] spstbasefx = {740 ,740 ,680 ,680};
        int[] spstbasefy = {390 ,430 ,425 ,390};
        //Superstructure Base Starboard Face
        int[] spstbasesx = {740 ,760 ,760 ,740};
        int[] spstbasesy = {390 ,390 ,420 ,430};
        //Superstructure Head For'd Face
        int[] spstheadfx = {660 ,660 ,750 ,750};
        int[] spstheadfy = {390 ,320 ,325 ,400};
        //Superstructure Head Starboard Face
        int[] spstheadsx = {775 ,750 ,750 ,775};
        int[] spstheadsy = {320 ,325 ,400 ,390};
        //Superstructure Roof
        int[] spstrooftx = {660 ,750 ,775 ,700 };
        int[] spstroofty = {320 ,325 ,320 ,315 };
        //Superstructure Windows
        int[] spstwind1x = {660 ,750 ,775 ,775 ,750 ,660 };
        int[] spstwind1y = {330 ,337 ,332 ,342 ,347 ,340 };
        int[] spstwind2x = {660 ,750 ,775 ,775 ,750 ,660 };
        int[] spstwind2y = {348 ,355 ,350 ,360 ,365 ,358 };
        //Mast
        int[] spstmastvx = {735 ,725 ,725 ,735};
        int[] spstmastvy = {325 ,325 ,251 ,251};
        //Waterline
        int[] waterlinex = {1070,810 ,425 ,425 ,810 ,1070};
        int[] waterliney = {490 ,550 ,610 ,620 ,560 ,500 };

        //Stern Wake
        g2.setStroke(deckstroke);
        g2.setColor(deckmarks);
        g2.setColor(foam);
        QuadCurve2D qwake = new QuadCurve2D.Double();
        for (int i = 0; i < 50; i++) {
            qwake.setCurve(1075,(int)(Math.random() * 50) + 440,(int)(Math.random() * 400) + 1100, (int)(Math.random() * 50) + 400,1440,(int)(Math.random() * 50) + 375);
            g2.draw(qwake);
        }
        g2.setPaint(sptfShadow);
        //Superstructure Base For'd Face
        g2.fillPolygon(spstbasefx,spstbasefy,spstbasefx.length);
        //Superstructure Base Starboard Face
        g2.fillPolygon(spstbasesx,spstbasesy,spstbasesx.length);

        g2.setColor(lowerhull);
        //Lower Starboard Hull
        g2.fillPolygon(lowerhullx,lowerhully,lowerhullx.length);
        //Superstructure Head Starboard Face
        g2.fillPolygon(spstheadsx,spstheadsy,spstheadsx.length);
        //Superstructure Roof
        g2.fillPolygon(spstrooftx,spstroofty,spstrooftx.length);
        //Mast
        g2.fillPolygon(spstmastvx,spstmastvy,spstmastvx.length);
        g2.setStroke(deckstroke);
        g2.drawLine(680,280,775,285);
        g2.drawLine(685,250,770,255);
        //Waterline
        g2.setColor(Color.black);
        g2.fillPolygon(waterlinex,waterliney,waterlinex.length);

        g2.setColor(forwdspst);
        //Superstructure Head For'd Face
        g2.fillPolygon(spstheadfx,spstheadfy,spstheadfx.length);

        g2.setPaint(hullShadow);
        //Upper Starboard Hull
        g2.fillPolygon(upperhullx,upperhully,upperhullx.length);
        //For'd Hull
        g2.fillPolygon(foredhullx,foredhully,foredhullx.length);

        //Flight Deck
        g2.setColor(deck);
        g2.fillPolygon(flghtdeckx,flghtdecky,flghtdeckx.length);
        //Superstructure Windows
        g2.fillPolygon(spstwind1x,spstwind1y,spstwind1x.length);
        g2.fillPolygon(spstwind2x,spstwind2y,spstwind2x.length);
        //Deck Markings
        g2.setColor(deckmarks);
        g2.drawLine(725,465,930 ,395);
        g2.drawLine(840,470,1030,400);
        g2.drawLine(375,500,650 ,440);
        g2.drawLine(445,505,685 ,460);
        g2.setColor(Color.yellow);
        g2.drawLine(787,465,980,397);
        //Starboard Wake
        g2.setColor(foam);
        CubicCurve2D cwake = new CubicCurve2D.Float();
        for (int i = 0; i < 25; i++) {
            cwake.setCurve((int)(Math.random() * 10) + 430 ,(int)(Math.random() * 10) + 620,(int)(Math.random() * 10) + 700 , (int)(Math.random() * 10) + 570,(int)(Math.random() * 10) + 775 ,(int)(Math.random() * 10) + 595,(int)(Math.random() * 50) + 1300,(int)(Math.random() * 40) + 400);
            g2.draw(cwake);
        }
    }
}
