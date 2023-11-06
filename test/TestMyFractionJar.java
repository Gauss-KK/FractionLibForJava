// Filename: TestMyFractionJar.java
//
// 
// Compile: javac -cp fraction-lib-1.0.3.jar TestMyFractionJar.java
// Execute: java -cp ".;fraction-lib-1.0.3.jar" TestMyFractionJar
//   Or
// Execute: java -cp .;fraction-lib-1.0.3.jar TestMyFractionJar
//
//
// Date: 2023.11.06 ~ 2023.11.06      Use fraction-lib-1.0.3.jar


import java.math.*;
import java.util.ArrayList;


import net.knumerics.numbers.*;


public class TestMyFractionJar {
    public static void main(String[] args) {
      	  
        MyFraction zero = new MyFraction(0, 1);
        System.out.printf("zero = %s\n", zero);
        System.out.print("zero = ");
        System.out.println(zero);

        MyFraction mzero = new MyFraction(0, -1);
        System.out.printf("mzero = %s\n", mzero);

        MyFraction one = new MyFraction(1, 1);
        System.out.printf("one = %s\n", one);

        MyFraction two = new MyFraction(2, 1);
        System.out.printf("two = %s\n", two);

        MyFraction ten = new MyFraction(10, 1);
        System.out.printf("ten = %s\n" ,ten);

        MyFraction half = new MyFraction(1, 2);
        System.out.printf("half = %s\n", half);

        MyFraction third = new MyFraction(1, 3);
        System.out.printf("third = %s\n", third);

        MyFraction inf= new MyFraction(1, 0);
        System.out.printf("inf = %s\n", inf);

        MyFraction minf= new MyFraction(-1, 0);
        System.out.printf("minf = %s\n", minf);

        MyFraction nan = new MyFraction(0, 0);
        System.out.printf("nan = %s\n", nan);

        System.out.printf("one + two = %s\n", one.add(two));
        System.out.printf("two - one = %s\n", two.subtract(one));
        System.out.printf("two * ten = %s\n", two.multiply(ten));
        System.out.printf("ten / two = %s\n", ten.divide(two));
        System.out.printf("ten %% two = %s\n", ten.mod(two));


        MyFraction f = new MyFraction(75, 10);
        MyFraction g = new MyFraction(-12, 18);
        MyFraction h = f.add(g);
        System.out.printf("f = %s\n", f);
        System.out.printf("g = %s\n", g);
        System.out.printf("f + g = %s\n", h);

        h = f.subtract(g);
        System.out.printf("f - g = %s\n", h);

        h = f.multiply(g);
        System.out.printf("f * g = %s\n", h);

        h = f.divide(g);
        System.out.printf("f / g = %s\n", h);

        h = f.mod(g);
        System.out.printf("f %% g = %s\n", h);
        System.out.println("");

        System.out.printf("f = %s\n", f);
        System.out.printf("f.abs() = %s\n", f.abs());
        System.out.printf("f.intPart() = %s\n", f.intPart());
        System.out.printf("f.fracPart() = %s\n", f.fracPart());
        System.out.printf("f.floor() = %s\n", f.floor());
        System.out.printf("f.ceil() = %s\n", f.ceil());
        System.out.printf("f.round() = %s\n", f.round());
        System.out.printf("f.trunc() = %s\n", f.trunc());
        System.out.println("");


        System.out.printf("g = %s\n", g);
        System.out.printf("g.abs() = %s\n", g.abs());
        System.out.printf("-g = g.negate() =  %s\n", g.negate());
        System.out.printf("1/g = g.invert() =  %s\n", g.invert());
        System.out.printf("g.intPart() = %s\n", g.intPart());
        System.out.printf("g.fracPart() = %s\n", g.fracPart());
        System.out.printf("g.floor() = %s\n", g.floor());
        System.out.printf("g.ceil() = %s\n", g.ceil());
        System.out.printf("g.round() = %s\n", g.round());
        System.out.printf("g.trunc() = %s\n", g.trunc());
        System.out.println("");


        System.out.printf("inf = %s\n", inf);
        System.out.printf("inf.intPart() = %s\n", inf.intPart());
        System.out.printf("inf.fracPart() = %s\n", inf.fracPart());
        System.out.printf("inf.floor() = %s\n", inf.floor());
        System.out.printf("inf.ceil() = %s\n", inf.ceil());
        System.out.printf("inf.round() = %s\n", inf.round());
        System.out.printf("inf.trunc() = %s\n", inf.trunc());
        System.out.printf("inf.abs() = %s\n", inf.abs());
        System.out.printf("inf.negate() = %s\n", inf.negate());
        System.out.printf("inf.invert() = %s\n", inf.invert());
        System.out.println("");


        System.out.printf("minf = %s\n", minf);
        System.out.printf("minf.intPart() = %s\n", minf.intPart());
        System.out.printf("minf.fracPart() = %s\n", minf.fracPart());
        System.out.printf("minf.floor() = %s\n", minf.floor());
        System.out.printf("minf.ceil() = %s\n", minf.ceil());
        System.out.printf("minf.round() = %s\n", minf.round());
        System.out.printf("minf.trunc() = %s\n", minf.trunc());
        System.out.printf("minf.abs() = %s\n", minf.abs());
        System.out.printf("minf.negate() = %s\n", minf.negate());
        System.out.printf("minf.invert() = %s\n", minf.invert());
        System.out.println("");


        System.out.printf("zero = %s\n", zero);
        System.out.printf("zero.intPart() = %s\n", zero.intPart());
        System.out.printf("zero.fracPart() = %s\n", zero.fracPart());
        System.out.printf("zero.floor() = %s\n", zero.floor());
        System.out.printf("zero.ceil() = %s\n", zero.ceil());
        System.out.printf("zero.round() = %s\n", zero.round());
        System.out.printf("zero.trunc() = %s\n", zero.trunc());
        System.out.printf("zero.abs() = %s\n", zero.abs());
        System.out.printf("zero.negate() = %s\n", zero.negate());
        System.out.printf("zero.invert() = %s\n", zero.invert());
        System.out.println("");


        System.out.printf("mzero = %s\n", mzero);
        System.out.printf("mzero.intPart() = %s\n", mzero.intPart());
        System.out.printf("mzero.fracPart() = %s\n", mzero.fracPart());
        System.out.printf("mzero.floor() = %s\n", mzero.floor());
        System.out.printf("mzero.ceil() = %s\n", mzero.ceil());
        System.out.printf("mzero.round() = %s\n", mzero.round());
        System.out.printf("mzero.trunc() = %s\n", mzero.trunc());
        System.out.printf("mzero.abs() = %s\n", mzero.abs());
        System.out.printf("mzero.negate() = %s\n", mzero.negate());
        System.out.printf("mzero.invert() = %s\n", mzero.invert());
        System.out.println("");


       System.out.printf("Let f = %s = %s and g = %s = %s\n", f, f.toDecimalString(), g, g.toDecimalString());
       System.out.printf("Then we have\n    f / g = f.dvide(g) = %s = %s = %s\n", f.divide(g), f.divide(g).toMixedForm(), f.divide(g).toDecimalString());
       System.out.printf("and\n    g / f = g.dvide(f) = %s = %s = %s\n", g.divide(f), g.divide(f).toMixedForm(), g.divide(f).toDecimalString());
       System.out.println("");

       MyFraction h7 = f.divide(g).floor();
       System.out.printf("h7 = f.dvide(g).floor() = %s\n", h7);

       MyFraction h8 = f.divide(g).ceil();
       System.out.printf("h8 = f.dvide(g).ceil() = %s\n", h8);

       MyFraction h9 = f.divide(g).round();
       System.out.printf("h9 = f.dvide(g).round() = %s\n", h9);

       MyFraction h10 = f.divide(g).trunc();
       System.out.printf("h10 = f.dvide(g).trunc() = %s\n", h10);

       MyFraction h11 = f.divide(g).intPart();
       System.out.printf("h11 = f.dvide(g).intPart() = %s\n", h11);

       MyFraction h12 = f.divide(g).fracPart();
       System.out.printf("h12 = f.dvide(g).fracPart() = %s\n", h12);
       System.out.println("");


       f = new MyFraction(-29, 5);
       g = new MyFraction(7, 2);
       System.out.printf("f = %s = %s = %s\n", f, f.toMixedForm(), f.toDecimalString());
       System.out.printf("g = %s = %s = %s\n", g, g.toMixedForm(), g.toDecimalString());
       System.out.printf("f / g = f.divide(g) = %s = %s = %s\n", f.divide(g), f.divide(g).toMixedForm(), f.divide(g).toDecimalString());
       System.out.printf("f = %s = %s = %s\n", f, f.toMixedForm(), f.toDecimalString(40));
       System.out.printf("g = %s = %s = %s\n", g, g.toMixedForm(), g.toDecimalString(40));
       System.out.printf("f / g = f.dvide(g) = %s = %s = %s\n", f.divide(g), f.divide(g).toMixedForm(), f.divide(g).toDecimalString(40));


        h = new MyFraction(1.9999);
        System.out.printf("h = %s = %s = %s\n", h, h.toMixedForm(), h.toDecimalString(7));
        System.out.printf("h = %s = %s = %s\n" , h, h.toMixedForm(), h.toDecimalString(3));
        System.out.printf("-h = h.negate() = %s = %s = %s\n", h.negate(), h.negate().toMixedForm(), h.negate().toDecimalString(7));
        System.out.printf("-h = h.negate() = %s = %s = %s\n" , h, h.negate().toMixedForm(),h.negate().toDecimalString(3));

        h = new MyFraction(1.9995);
        System.out.printf("h = %s = %s = %s\n", h, h.toMixedForm(), h.toDecimalString(7));
        System.out.printf("h = %s = %s = %s\n" , h, h.toMixedForm(), h.toDecimalString(3));
        System.out.printf("-h = h.negate() = %s = %s = %s\n", h.negate(), h.negate().toMixedForm(), h.negate().toDecimalString(7));
        System.out.printf("-h = h.negate() = %s = %s = %s\n" , h, h.negate().toMixedForm(),h.negate().toDecimalString(3));
       System.out.println("");


       System.out.printf("Let f = %s = %s and g = %s = %s\n", f, f.toDecimalString(), g, g.toDecimalString());
       System.out.printf("Then we have\n    f / g = f.dvide(g) = %s = %s = %s\n", f.divide(g), f.divide(g).toMixedForm(), f.divide(g).toDecimalString());
       System.out.printf("and\n    g / f = g.dvide(f) = %s = %s = %s\n", g.divide(f), g.divide(f).toMixedForm(), g.divide(f).toDecimalString());
       System.out.println("");

        MyFraction r12 = new MyFraction(0, 1);

        MyFraction qr[] = f.divRem(g);
        h12 = qr[0];
        r12 = qr[1];
        System.out.printf("And, after qr = f.dvRem(g), we have\n");
        System.out.printf("     quotient: qr[0] = %s\n", h12);
        System.out.printf("    remainder: qr[1] = %s\n", r12);
        System.out.printf("Check results:\n");
       System.out.printf("      dividend: f = %s\n ", f);
       System.out.printf("      divider: g = %s\n", g);
        System.out.printf("And so\n");
        System.out.printf("     divider*quotient = g*qr[0] = %s\n", g.multiply(h12));
        System.out.printf("     dividend - divider*quotient = f - g*qr[0] = %s\n", f.subtract(g.multiply(h12)));
        System.out.printf("     remainder: qr[1] = %s\n", r12);
        System.out.println("");


        qr = f.intDiv(g);
        MyFraction h13 = qr[0];
        MyFraction r13 = qr[1];
        System.out.printf("And, after qr = f.intDiv(g), we have\n");
        System.out.printf("     quotient: qr[0] = %s\n", h13);
        System.out.printf("    remainder: qr[1] = %s\n", r13);
        System.out.printf("Check results:\n");
        System.out.printf("      dividend: f = %s\n ", f);
        System.out.printf("      divider: g = %s\n", g);
        System.out.printf("And so\n");
        System.out.printf("     divider*quotient = g*qr[0] = %s\n", g.multiply(h13));
        System.out.printf("     dividend - divider*quotient = f - g*qr[0] = %s\n", f.subtract(g.multiply(h13)));
        System.out.printf("     remainder: qr[1] = %s\n", r13);
        System.out.println("");

        BigInteger x0 = new BigInteger("256");
        BigInteger y0 = new BigInteger("205");
        BigInteger s0 = new BigInteger("0");
        BigInteger t0 = new BigInteger("0");
        BigInteger[] gst = MyFraction.ext_gcd(x0, y0);
        BigInteger g0 = gst[0];
        s0 = gst[1];
        t0 = gst[2];
        System.out.printf("Given x0 =%d  and  y0 = %d\n", x0, y0);
        System.out.printf("Then we get  g0 = GCD(x0, y0) = %d\n", g0);
        System.out.printf("  and Bezout coefficients: s0 = %d, t0 = %d\n", s0, t0);
        System.out.printf("Check Bezout identity:\n");
        System.out.printf("  (s0)*x0 + (t0)*y0 = (%d)*(%d) + (%d)*(%d) = (%d) + (%d) = %s\n", s0, x0, t0, y0, s0.multiply(x0), t0.multiply(y0), s0.multiply(x0).add( t0.multiply(y0) ));
        System.out.println("");


        f = new MyFraction("16", "100000000000");
        g = new MyFraction("25", "100000000000");

        System.out.printf("Let f = %s, g = %s\n", f, g);
        System.out.printf("Then we get\n    f + g = %s\n", f.add(g));
        System.out.printf("    f - g = %s\n", f.subtract(g));
        System.out.printf("    f * g = %s\n", f.multiply(g));
        System.out.printf("    f / g = %s\n", f.divide(g));
        System.out.printf("    f %% g = %s\n", f.mod(g));
        System.out.printf("and\n");
        System.out.printf("    g + f = %s\n", g.add(f));
        System.out.printf("    g - f = %s\n", g.subtract(f));
        System.out.printf("    g * f = %s\n", g.multiply(f));
        System.out.printf("    g / f = %s\n", g.divide(f));
        System.out.printf("    g %% f = %s\n", g.mod(f));
        System.out.println("");


        f = new MyFraction("32", "100000000000");
        g = new MyFraction("100000000000", "125");

        System.out.printf("Let f = %s, g = %s\n", f, g);
        System.out.printf("Then we get\n    f / g = %s\n", f.divide(g));
        System.out.printf("    g / f = %s\n", g.divide(f));
        System.out.println("");


        System.out.printf("new MyFraction(0, -1) = %s\n", new MyFraction(0, -1) );
        System.out.printf("new MyFraction(0, 1) = %s\n", new MyFraction(0,1) );
        System.out.printf("new MyFraction(-1, 0) = %s\n", new MyFraction(-1, 0) );
        System.out.printf("new MyFraction(0, 1) = %s\n", new MyFraction(1, 0) );
        System.out.printf("new MyFraction(0, 0) = %s\n", new MyFraction(0, 0) );
        System.out.println("");


        System.out.printf("new MyFraction(0.3E+19) = %s\n", new MyFraction(0.3E+19));
        System.out.printf("new MyFraction(0.2E+19) = %s\n", new MyFraction(0.2E+19));
        System.out.printf("new MyFraction(0.1E+19) = %s\n", new MyFraction(0.1E+19));
        System.out.printf("new MyFraction(0.3E-17) = %s\n", new MyFraction(0.3E-17));
        System.out.printf("new MyFraction(0.2E-17) = %s\n", new MyFraction(0.2E-17));
        System.out.printf("new MyFraction(0.1E-17) = %s\n", new MyFraction(0.1E-17));
        System.out.println("");


        System.out.printf("0.3E+19 = %.18f\n", 0.3E+19);
        System.out.printf("0.2E+19 = %.18f\n", 0.2E+19);
        System.out.printf("0.1E+19 = %.18f\n", 0.1E+19);
        System.out.printf("0.3E-17 = %.18f\n", 0.3E-17);
        System.out.printf("0.2E-17 = %.18f\n", 0.2E-17);
        System.out.printf("0.1E+17 = %.18f\n", 0.1E-17);
        System.out.printf("0.3 = %.18f\n", 0.3);
        System.out.printf("0.2 = %.18f\n", 0.2);
        System.out.printf("0.1 = %.18f\n", 0.1);
        System.out.printf("0.3 - 0.2 - 0.1 = %.20f\n", 0.3 - 0.2 - 0.1);
        System.out.printf("0.1 + 0.2 = %.20f\n", 0.1 + 0.2);
        System.out.printf("0.3 = %.18f\n", 0.3);
        System.out.println("");


        System.out.printf("Let f = %s, g = %s\n", f, g);
        System.out.printf("Then we get\n    f / g = %s\n", f.divide(g));
        System.out.printf("    g / f = %s\n", g.divide(f));
        System.out.println("");
;
        zero = new MyFraction(0);
        mzero = zero.negate();     /// new MyFraction(-0.0);
        one = new MyFraction(1);
        two = new MyFraction(2);
        ten = new MyFraction(10);
        half = new MyFraction(0.5);
        MyFraction mhalf = new MyFraction(-1, 2);
        inf = new MyFraction(Double.POSITIVE_INFINITY);
        /// minf = inf.negate();    /// new MyFraction(Double.NEGATIVE_INFINITY);
        minf = new MyFraction(Double.NEGATIVE_INFINITY);

        System.out.printf("Let zero = %s and mzero = %s\n", zero, mzero);
        System.out.printf("Let nan = %s and one = %s\n", nan, one);
        System.out.printf("Let two = %s and ten = %s\n", two, ten);
        System.out.printf("Let half = %s and mhalf = %s\n", half, mhalf);
        System.out.printf("Let inf = %s and minf = %s\n", inf, minf);
        System.out.println("");


        System.out.printf("one.lessThan(two) ? %s\n", one.lessThan(two));
        System.out.printf("one.lessThan(half) ? %s\n", one.lessThan(half));
        System.out.printf("one.lessThan(inf) ? %s\n", one.lessThan(inf));
        System.out.printf("one.lessThan(minf) ? %s\n", one.lessThan(minf));
        System.out.printf("one.lessThan(zero) ? %s\n", one.lessThan(zero));
        System.out.printf("one.lessThan(mzero) ? %s\n", one.lessThan(mzero));
        System.out.println("");


        System.out.printf("inf.lessThan(inf) ? %s\n", inf.lessThan(inf));
        System.out.printf("minf.lessThan(minf) ? %s\n", minf.lessThan(minf));
        System.out.printf("inf.lessThan(minf) ? %s\n", inf.lessThan(minf));
        System.out.printf("minf.lessThan(inf) ? %s\n", minf.lessThan(inf));
        System.out.printf("inf.lessThan(half) ? %s\n", inf.lessThan(half));
        System.out.printf("minf.lessThan(half) ? %s\n", minf.lessThan(half));
        System.out.println("");


        System.out.printf("one.greaterThanOrEqual(two) ? %s\n", one.greaterThanOrEqual(two));
        System.out.printf("one.greaterThanOrEqual(half) ? %s\n", one.greaterThanOrEqual(half));
        System.out.printf("one.greaterThanOrEqual(inf) ? %s\n", one.greaterThanOrEqual(inf));
        System.out.printf("one.greaterThanOrEqual(minf) ? %s\n", one.greaterThanOrEqual(minf));
        System.out.printf("one.greaterThanOrEqual(zero) ? %s\n", one.greaterThanOrEqual(zero));
        System.out.printf("one.greaterThanOrEqual(mzero) ? %s\n", one.greaterThanOrEqual(mzero));
        System.out.println("");


        System.out.printf("one.greaterThan(two) ? %s\n", one.greaterThan(two));
        System.out.printf("one.greaterThan(half) ? %s\n", one.greaterThan(half));
        System.out.printf("one.greaterThan(inf) ? %s\n", one.greaterThan(inf));
        System.out.printf("one.greaterThan(minf) ? %s\n", one.greaterThan(minf));
        System.out.printf("one.greaterThan(zero) ? %s\n", one.greaterThan(zero));
        System.out.printf("one.greaterThan(mzero) ? %s\n", one.greaterThan(mzero));
        System.out.println("");


        System.out.printf("inf.greaterThan(inf) ? %s\n", inf.greaterThan(inf));
        System.out.printf("minf.greaterThan(minf) ? %s\n", minf.greaterThan(minf));
        System.out.printf("inf.greaterThan(minf) ? %s\n", inf.greaterThan(minf));
        System.out.printf("minf.greaterThan(inf) ? %s\n", minf.greaterThan(inf));
        System.out.printf("inf.greaterThan(half) ? %s\n", inf.greaterThan(half));
        System.out.printf("minf.greaterThan(half) ? %s\n", minf.greaterThan(half));
        System.out.println("");


        System.out.printf("one.greaterThanOrEqual(two) ? %s\n", one.greaterThanOrEqual(two));
        System.out.printf("one.greaterThanOrEqual(half) ? %s\n", one.greaterThanOrEqual(half));
        System.out.printf("one.greaterThanOrEqual(inf) ? %s\n", one.greaterThanOrEqual(inf));
        System.out.printf("one.greaterThanOrEqual(minf) ? %s\n", one.greaterThanOrEqual(minf));
        System.out.printf("one.greaterThanOrEqual(zero) ? %s\n", one.greaterThanOrEqual(zero));
        System.out.printf("one.greaterThanOrEqual(mzero) ? %s\n", one.greaterThanOrEqual(mzero));
        System.out.println("");


        System.out.printf("inf.greaterThanOrEqual(inf) ? %s\n", inf.greaterThanOrEqual(inf));
        System.out.printf("minf.greaterThanOrEqual(minf) ? %s\n", minf.greaterThanOrEqual(minf));
        System.out.printf("inf.greaterThanOrEqual(minf) ? %s\n", inf.greaterThanOrEqual(minf));
        System.out.printf("minf.greaterThanOrEqual(inf) ? %s\n", minf.greaterThanOrEqual(inf));
        System.out.printf("inf.greaterThanOrEqual(half) ? %s\n", inf.greaterThanOrEqual(half));
        System.out.printf("minf.greaterThanOrEqual(half) ? %s\n", minf.greaterThanOrEqual(half));
        System.out.println("");

        System.out.printf("1/inf = %s\n", inf.invert());
        System.out.printf("1/minf = %s\n", minf.invert());
        System.out.printf("1/zero = %s\n",zero.invert());
        System.out.printf("1/mzero = %s\n", mzero.invert());
        System.out.printf("1/zero == inf ?  %s\n", zero.invert().equalTo(inf));
        System.out.printf("1/mzero = minf ? %s\n", mzero.invert().equalTo(minf));
        System.out.printf("1/zinf == zero ?  %s\n", inf.invert().equalTo(zero));
        System.out.printf("1/minf = mzero ? %s\n", minf.invert().equalTo(mzero));
        System.out.printf("1/zero == ninf ?  %s\n", zero.invert().equalTo(minf));
        System.out.printf("1/mzero = inf ? %s\n", mzero.invert().equalTo(inf));
        System.out.printf("-zero == mzero ?  %s\n", zero.negate().equalTo(mzero));
        System.out.printf("-minf = inf ? %s\n", minf.negate().equalTo(inf));
        System.out.println("");


        double x1 = -1.71e+100;
    /// fmt.Println("x1 =", x1)
    //// fmt.Println("x1.exp =", x1.neg)
      System.out.printf("x1 =  %.20E\n", x1);

    // See: https://programmer.group/go-golang-quick-start-8.3-deep-understanding-of-ieee754-floating-point-numbers.html
        // bits := math.Float64bits(x1)
        // See: http://www.java2s.com/Tutorials/Java/Data_Type/Double/Convert_double_to_bit_string_in_Java.htm
        long bits = 	Double.doubleToLongBits(x1);

       // See: https://mkyong.com/java/java-convert-an-integer-to-a-binary-string/
       String binary1 = "0b" + Long.toBinaryString(bits);     /// String.format("%.64b", bits);
       System.out.println("binary1 = " + binary1);
       System.out.println("            +eeeeeeeeeeemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
       System.out.println("                        2109876543210987654321098765432109876543210987654321");
      String hex1 = "0x" + Long.toHexString(bits).toUpperCase();    /// String.format("%08X", bits);
      System.out.println("hex1 = " + hex1);
      System.out.println("            mmmmmmmmmmmmm");

      String oct1 = "0o" + Long.toOctalString(bits).toUpperCase();    /// String.format("%08X", bits);
      System.out.println("oct1 = " + oct1);
      System.out.println("         +eee*mmmmmmmmmmmmmmmmm");

       String dec1 = Long.toString(bits).toUpperCase();    /// String.format("%08X", bits);
       System.out.println("dec1 = " + dec1);
        System.out.println("");

        System.out.printf("ten = %s\n", ten);
        System.out.printf("ten + 3 = %s\n", ten.add(3));
        System.out.printf("ten - 3 = %s\n", ten.subtract(3));
        System.out.printf("ten * 3 = %s\n", ten.multiply(3));
        System.out.printf("ten / 3 = %s\n", ten.divide(3));
        System.out.printf("ten %% 3 = %s\n", ten.mod(3));
        System.out.printf("ten.intDiv(3) = [ %s , %s ]\n", ten.intDiv(3)[0],ten.intDiv(3)[1] );
        System.out.printf("ten.intDiv(-3) = [ %s , %s ]\n", ten.intDiv(-3)[0],ten.intDiv(-3)[1] );
        System.out.printf("ten.divRem(3) = [ %s , %s ]\n", ten.divRem(3)[0],ten.divRem(3)[1] );
        System.out.printf("ten.divRem(-3) = [ %s , %s ]\n", ten.divRem(-3)[0],ten.divRem(-3)[1] );
        System.out.printf("(-ten).intDiv(3) = [ %s , %s ]\n", ten.negate().intDiv(3)[0], ten.negate().intDiv(3)[1] );
        System.out.printf("(-ten).intDiv(-3) = [ %s , %s ]\n", ten.negate().intDiv(-3)[0], ten.negate().intDiv(-3)[1] );
        System.out.printf("(-ten).divRem(3) = [ %s , %s ]\n", ten.negate().divRem(3)[0], ten.negate().divRem(3)[1] );
        System.out.printf("(-ten).divRem(-3) = [ %s , %s ]\n", ten.negate().divRem(-3)[0], ten.negate().divRem(-3)[1] );
        System.out.println("");

        System.out.printf("ten + 8.7 = %s\n", ten.add(8.7));
        System.out.printf("ten - 8.7 = %s\n", ten.subtract(8.7));
        System.out.printf("ten * 8.7 = %s\n", ten.multiply(8.7));
        System.out.printf("ten / 8.7 = %s\n", ten.divide(8.7));
        System.out.printf("ten %% 8.7 = %s\n", ten.mod(8.7));
        System.out.printf("ten.intDiv(8.7) = [ %s , %s ]\n", ten.intDiv(8.7)[0],ten.intDiv(8.7)[1] );
        System.out.printf("ten.intDiv(-8.7) = [ %s , %s ]\n", ten.intDiv(-8.7)[0],ten.intDiv(-8.7)[1] );
        System.out.printf("ten.divRem(8.7) = [ %s , %s ]\n", ten.divRem(8.7)[0],ten.divRem(8.7)[1] );
        System.out.printf("ten.divRem(-8.7) = [ %s , %s ]\n", ten.divRem(-8.7)[0],ten.divRem(-8.7)[1] );
        System.out.printf("(-ten).intDiv(8.7) = [ %s , %s ]\n", ten.negate().intDiv(8.7)[0], ten.negate().intDiv(8.7)[1] );
        System.out.printf("(-ten).intDiv(-8.7) = [ %s , %s ]\n", ten.negate().intDiv(-8.7)[0], ten.negate().intDiv(-8.7)[1] );
        System.out.printf("(-ten).divRem(8.7) = [ %s , %s ]\n", ten.negate().divRem(8.7)[0], ten.negate().divRem(8.7)[1] );
        System.out.printf("(-ten).divRem(-8.7) = [ %s , %s ]\n", ten.negate().divRem(-8.7)[0], ten.negate().divRem(-8.7)[1] );
        System.out.println("");
    }
}
