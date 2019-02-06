import java.awt.*;
import java.awt.geom.*;

public class NCC1701A {
    public NCC1701A(){}
    public void drawEA(Graphics2D g2){
        //Color definitions
        Color ddark = new Color(40 ,40 ,40 );
        Color ndark = new Color(25 ,25 ,25 );
        Color midpt = new Color(86 ,86 ,86 );
        Color light = new Color(80 ,80 ,80 );
        Color blueA = new Color(30 ,75 ,100);
        GradientPaint neck = new GradientPaint(600,475,ddark,800 ,450,light);
        GradientPaint lowh = new GradientPaint(0  ,585,ddark,1000,650,ndark);
        GradientPaint lith = new GradientPaint(400,800,midpt,1000,300,light);
        //Polygon definitions
        //Neck
        int[] neckx = {545 ,530 ,520 ,490 ,515 ,545 ,585 ,645 ,680 ,675 ,675 ,680 ,710 ,610};
        int[] necky = {540 ,495 ,475 ,460 ,450 ,450 ,445 ,450 ,450 ,465 ,485 ,500 ,520 ,535};
        //Central Nacelle Top
        int[] cnctx = {710 ,740 ,810 ,795 ,785 ,700 ,555 ,610};
        int[] cncty = {520 ,530 ,550 ,555 ,580 ,570 ,555 ,535};
        //Central Nacelle Bottom
        int[] cncbx = {795 ,785 ,700 ,555 ,610 ,545 ,530 ,515 ,505 ,500 ,500 ,500 ,525 ,590 ,640 ,660 ,675 ,740 ,1030,1035,1035,1020,1000,985 ,1000,985 ,980 ,910 ,820 ,810 };
        int[] cncby = {555 ,580 ,570 ,555 ,535 ,540 ,540 ,550 ,555 ,565 ,615 ,620 ,640 ,650 ,660 ,650 ,640 ,635 ,640 ,640 ,630 ,620 ,615 ,605 ,590 ,605 ,595 ,585 ,570 ,550 };
        //Central Nacelle Stern
        int[] cncsx = {980 ,1000,1020,1000,985 };
        int[] cncsy = {595 ,605 ,620 ,615 ,605 };
        //Starboard Nacelle
        int[] sbncx = {750 ,890 ,1005,1010,1105,1100,1080,985 ,855 ,790 ,835 ,810 ,760 ,735 ,710 ,700 ,700 ,730 };
        int[] sbncy = {415 ,435 ,460 ,450 ,475 ,490 ,500 ,500 ,505 ,505 ,540 ,550 ,540 ,495 ,490 ,485 ,415 ,415 };
        //Port Nacelle
        int[] ptncx = {810 ,835 ,865 ,910 ,890 ,890 ,940 ,1265,1275,1340,1370,1355,1190,1040,990 ,965 ,950 ,950 ,980 ,910 ,820 };
        int[] ptncy = {550 ,540 ,490 ,380 ,370 ,300 ,295 ,360 ,350 ,385 ,400 ,415 ,410 ,415 ,410 ,430 ,450 ,470 ,595 ,585 ,570 };
        //Draw
        //Starboard Nacelle
        g2.setColor(light);
        g2.fillPolygon(sbncx,sbncy,sbncx.length);
        g2.setColor(blueA);
        g2.fill(new Ellipse2D.Double(680,415,40,70));
        //Disk Rim
        g2.setColor(ndark);
        g2.rotate(Math.toRadians(3.0));
        g2.fill(new Ellipse2D.Double(75 ,250,700,220));
        g2.rotate(Math.toRadians(-3.0));
        //Port Nacelle
        g2.setPaint(lowh);
        g2.fillPolygon(ptncx,ptncy,ptncx.length);
        g2.setColor(blueA);
        g2.fill(new Ellipse2D.Double(870,300,40,70));
        //Disk Ventral
        g2.setColor(ddark);
        g2.rotate(Math.toRadians(3.75));
        g2.fill(new Ellipse2D.Double(135,305,605,160));
        g2.rotate(Math.toRadians(-3.75));
        //Central Nacelle Top
        g2.setPaint(neck);
        g2.fillPolygon(cnctx,cncty,cnctx.length);
        //Central Nacelle Bottom
        g2.setPaint(lowh);
        g2.fillPolygon(cncbx, cncby, cncbx.length);
        g2.fill(new QuadCurve2D.Double(675,452,575,425,490,460));
        g2.fillPolygon(neckx,necky,neckx.length);
        g2.setColor(blueA);
        g2.fill(new Ellipse2D.Double(480,555,30,65));
        g2.setColor(ndark);
        g2.fill(new Ellipse2D.Double(490,567,20,40));
        //Central Nacelle Stern
        g2.setPaint(lith);
        g2.fillPolygon(cncsx,cncsy,cncsx.length);
    }
}
