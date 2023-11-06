// Filename: MyFraction.java
//
// Compile: javac -d . .\src\MyFraction.java
// Compile: javac -d . .\src\TestMyFractionLib.java
// Make Jar: jar -cvmfe manifest.txt fraction-lib-1.0.3.jar net.knumerics.numbers.TestMyFractionLib net\knumerics\numbers\*.class
// Execute: java -jar fraction-lib-1.0.3.jar
//
// Generate Documentation: javadoc -author -version -d .\api\fractions .\src\MyFraction.java
//
//
//
// Date: 2023.11.06 ~ 2023.11.06      Versiom 1.0.3    Documentations
// Date: 2022.12.05 ~ 2022.12.11      Versiom 1.0.3    Documentations and sqrt, cbrt, nthRoot
// Date: 2022.07.25 ~ 2022.07.27      Versiom 0.0.2    new MyFraction(int, int)


/**
* Fraction class which supports more exsact calculations than double sized floating numbers.
*
* Say, 0.3 - 0.2 - 0.1 shoud be 0.0 exactyle
*   and 1.0 / (0.3 - 0.2 - 0.1) should be one of PositiveInfinity and NegativeInfinity.
* 
* @author Gauss-KK
* @version 1.0.3
* 
*/

package net.knumerics.numbers;


import java.math.*;
import java.util.ArrayList;
import java.io.*;



/**
 *  public class MyFraction
 *
 *
 * @see <a href="https://www.knumerics.net/java/FractionLib/net/knumerics/numbers/">KNumerics.NET</a>
 *
 * @version 1.0.3
 * @author Gauss-KK [ gausskk@gmail.com ]
 */
public class MyFraction extends java.lang.Number implements Comparable<MyFraction>, Serializable
{
    /**  Numerator  */
    private BigInteger num = new BigInteger("0");

    /**  Denominator    */
    private BigInteger den = new BigInteger("1");

    /**  BigInteger("0")    */
    private static final BigInteger Zero = new BigInteger("0");

    /**  BigInteger("-1")    */
    private static final BigInteger MOne = new BigInteger("-1");

    /**   BigInteger("1")    */
    private static final BigInteger One = new BigInteger ("1");

    /**   BigInteger("2")    */
    private static final BigInteger Two = new BigInteger ("2");

    /**   BigInteger("9")    */
    private static final BigInteger Nine = new BigInteger ("9");

    /**   BigInteger("10")    */
    private static final BigInteger Ten = new BigInteger ("10");


    /**   MyFraction.ZERO    */
    public static final MyFraction ZERO = new MyFraction(0, 1);

    /**   MyFraction.POSITIVE_ZERO    */
    public static final MyFraction POSITIVE_ZERO = new MyFraction(0, 1);

    /**   MyFraction.NEGATIVE_ZERO    */
    public static final MyFraction NEGATIVE_ZERO = new MyFraction(0, -1);

    /**   MyFraction.ONE   */
    public static final MyFraction ONE = new  MyFraction(1, 1);

    /**   MyFraction.TWO   */
    public static final MyFraction TWO = new  MyFraction(2, 1);

    /**   MyFraction.THREE   */
    public static final MyFraction THREE = new  MyFraction(3, 1);

    /**   MyFraction.FOUR   */
    public static final MyFraction FOUR = new  MyFraction(4, 1);

    /**   MyFraction.FIVE   */
    public static final MyFraction FIVE = new  MyFraction(5, 1);

    /**   MyFraction.TEN   */
    public static final MyFraction TEN = new  MyFraction(10, 1);

    /**   MyFraction.HUNDRED   */
    public static final MyFraction HUNDRED = new  MyFraction(100, 1);

    /**   MyFraction.THOUSAND   */
    public static final MyFraction THOUSAND = new  MyFraction(1000, 1);

    /**   MyFraction.MILLION   */
    public static final MyFraction MILLION = new  MyFraction(1000000, 1);

    /**   MyFraction.TRILLION   */
    public static final MyFraction TRILLION = new  MyFraction(1000000000, 1);


    /**   MyFraction.MINUS_ONE   */
    public static final MyFraction MINUS_ONE = new  MyFraction(-1, 1);

    /**   MyFraction.MINUS_TWO   */
    public static final MyFraction MINUS_TWO = new  MyFraction(-2, 1);

    /**   MyFraction.MINUS_THREE   */
    public static final MyFraction MINUS_THREE = new  MyFraction(-3, 1);

    /**   MyFraction.MINUS_TEN   */
    public static final MyFraction MINUS_TEN = new  MyFraction(-10, 1);

    /**   MyFraction.NaN   */
    public static final MyFraction NaN = new  MyFraction(0, 0);

    /**   MyFraction.INFINITY   */
    public static final MyFraction INFINITY = new  MyFraction(1, 0);

    /**   MyFraction.POSITIVE_INFINITY   */
    public static final MyFraction POSITIVE_INFINITY = new  MyFraction(1, 0);

    /**   MyFraction.NEGATIVE_INFINITY   */
    public static final MyFraction NEGATIVE_INFINITY = new  MyFraction(-1, 0);


    /**   MyFraction.HALF   */
    public static final MyFraction HALF = new  MyFraction(1, 2);

    /**   MyFraction.ONE_OVER_THREE   */
    public static final MyFraction ONEE_OVER_THREE = new  MyFraction(1, 3);

    /**   MyFraction.ONEE_OVER_TEN   */
    public static final MyFraction ONE_OVER_TEN = new  MyFraction(1, 10);


    /**
     *  Returns the GCD (greatest common divisor) of given two {@code BigIteger}s.
     *
     * @param   a      First integer
     * @param   b      Second integer
     *
     * @return            GCD((a, b)
     *
     * @since 1.0.3
     */
    public static BigInteger gcd(BigInteger a, BigInteger b)
    {
        BigInteger old_r = a;
        BigInteger r = b;
        if (old_r.signum() < 0)  {
            old_r = old_r.negate();
        }
        if (r.signum() < 0) {
            r = r.negate();
        }

        BigInteger tmp = BigInteger.ZERO;
        while (r.signum() > 0) {
            tmp = r;
            r = old_r.mod(r);
            old_r = tmp;
        }

        return old_r;
    }


    /**
     * Returns an array of the GCD (greatest common divisor) and Bézout coefficients 
     * of given two {@code BigIteger}s that satisfy their Bézout's identity.
     *
     * @param   a      First integer
     * @param   b      Second integer
     *
     * @return           Array of type BigInteger[], { GCD((a, b), s, t } <br>
     *                      The array { g, s, t } where g is the greatest common divisor of a and b
     *                      s, t are Bézout coefficients satisfying their Bézout's identity:
     *                     <pre> s*a + t*b = g = GCD(a, b) </pre>
     *
     * @since 1.0.3
     */
    public static  BigInteger[] ext_gcd(BigInteger a, BigInteger b)
    {
        BigInteger old_r = a;
        BigInteger r = b;
        if (old_r.signum() < 0)  {
            old_r = old_r.negate();
        }
        if (r.signum() < 0) {
            r = r.negate();
         }

         BigInteger old_s = BigInteger.ONE;
         BigInteger s = BigInteger.ZERO;
         BigInteger old_t = BigInteger.ZERO;
         BigInteger t = BigInteger.ONE;

        BigInteger q = BigInteger.ZERO;
        BigInteger tmp = BigInteger.ZERO;
        /// while (r > 0) {
        while (r.signum() > 0) {
            q = old_r.divide(r);

            tmp = r;
            /// r = old_r - r*q;
            r = old_r.subtract(r.multiply(q));
            old_r = tmp;

            tmp = s;
            /// s = old_s - s*q;
            s = old_s.subtract(s.multiply(q));
            old_s = tmp;

            tmp = t;
            /// t = old_t - t*q;
            t = old_t.subtract(t.multiply(q));
            old_t = tmp;
        }

         return new BigInteger[] { old_r, old_s, old_t };
    }

    /**
     * Cakculate the LCM (least common mutiple) of given two integers.
     *
     * @param   a0     First integer
     * @param   b0     Second integer
     * @return            LCM(a0, b0)
     *
     * @since 1.0.3
     */
    public static BigInteger lcm(BigInteger a0, BigInteger b0) {
        BigInteger a = a0;
        BigInteger b = b0;

        if (a.signum() < 0)  {
            a = a.negate();
        }
        if (b.signum() < 0)  {
            b = b.negate();
        }

        BigInteger g = gcd(a, b);

        return a.divide(g).multiply(b);
    }
    
    
    /**
     *  Calculate <code>base</code><sup>e</sup>,  as a <code>BigInteger</code>
     *
     * @param    base    base infpower oeration base**e
     * @param    e         exponent infeger powrer expression base**e
     * @return                Return base<sup>e</sup>
     *
     * @since 1.0.3
     */
    public static BigInteger mPow(long base, long e)
    {
    	  BigInteger b = new BigInteger(String.format("%d", base));
        if (e < 0)
            return BigInteger.ZERO;
        else if (e == 0)
            return BigInteger.ONE;
        else if (e == 1)
            return b;
        else if (e == 2)
            return b.multiply(b);

        BigInteger t = b;
        BigInteger y =  One;
    
        long n = e;
    
        while (n > 0) {
            if (n % 2 == 1)
                y = y.multiply(t);
            t = t.multiply(t);
            n >>= 1;
        }

        return y;
    }


    /**
     *  Calculate the integer vpowered result <code>base</code><sup>e</sup>,  as a <code>BigInteger</code>
     *
     * @param    base    base infpower oeration base**e
     * @param    e         exponent infeger powrer expression base**e
     * @return               Return <code>base</code><sup>e</sup>
     *
     * @since 1.0.3
     */
    public static BigInteger mPow(BigInteger base, long e)
    {
        if (e < 0)
            return BigInteger.ZERO;   /// 0;
        else if (e == 0)
            return  BigInteger.ONE;    /// 1;
        else if (e == 1)
            return base;
        else if (e == 2)
            return base.multiply(base);     ///  base*base

        BigInteger t = base;
        BigInteger y =  BigInteger.ONE;    /// 1;
    
        long n = e;
    
        while (n > 0) {
            if (n % 2 == 1)
                y = y.multiply(t);    /// y *= t;
            t = t.multiply(t);         /// t*t;
            n >>= 1;
        }

        return y;
    }


    /**
     *  Returns the frimed {@code String} from <STRONG>left</STRONG> trimming consecutive charcaters {@code c}.
     *
     * @param    src          source {@code String} to be trimmed
     * @param    c             character to be trimmed
     * @return    String       Return leftward-trimmed string
     *
     * @since 1.0.3
     */
    protected static String trimLeft(String src, char c) 
    {
         if (src == null) 
         {
              return "";
         }
         int len = src.length();
         int j1 = len;
         for (int i = 0; i < len; i++) 
         {
             if (src.charAt(i) != c) 
             {
                  j1 = i;
                  break;
             }
        }
        return src.substring(j1);
    }

    
    /**
     *  Returns the frmed {@code String} from <STRONG>right</STRONG> trimming consecutive charcaters {@code c}.
     *
     * @param    src          source {@code String} to be trimmed
     * @param    c             character to be trimmed
     * @return    String       Return rightward-trimmed string
     *
     * @since 1.0.3
     */
    protected static String trimRight(String src, char c) 
    {
         if (src == null) 
         {
              return "";
         }
         int len = src.length();
         int j1 = 0;
         for (int i = len - 1; i >= 0; i--) 
         {
             if (src.charAt(i) != c) 
             {
                  j1 = i;
                  break;
             }
        }
        return src.substring(0, j1 + 1);
    }


    /** 
     * Create MyFraction with two {@code BigInteger}s.
     *
     * @param  num     Numerator
     * @param  den      Denominator
     *
     * @since 1.0.3
     */
    public MyFraction(BigInteger num, BigInteger den) {
         BigInteger a = num;
         BigInteger b = den;
         if (a.signum() != 0 &&  b.signum() < 0) {
            a = a.negate();
            b = b.negate();
         }

         BigInteger g = gcd(a, b);
         
         if (g.compareTo(BigInteger.ONE) > 0) {
             a = a.divide(g);
             b = b.divide(g);
         }

         this.num = a;
         this.den = b;
    }


    /** 
     * Create MyFraction with two {@code String}s.
     *
     * @param  num     Numerator
     * @param  den      Denominator
     *
     * @since 1.0.3
     */
    public MyFraction(String num, String den) {
         BigInteger a = new BigInteger(num);
         BigInteger b = new BigInteger(den);
         if (a.signum() != 0 &&  b.signum() < 0) {
            a = a.negate();
            b = b.negate();
         }
         BigInteger g = gcd(a, b);
         
         if (g.compareTo(BigInteger.ONE) > 0) {
             a = a.divide(g);
             b = b.divide(g);
         }

         this.num = a;
         this.den = b;
    }

    /** 
     * Create MyFraction with two {@code long}s.
     *
     * @param  num     numerator
     * @param  den     denominator
     *
     * @since 1.0.3
     */
    public MyFraction(long num, long den) {
         BigInteger a = new BigInteger(String.format("%d", num));
         BigInteger b = new BigInteger(String.format("%d", den));
         if (a.signum() != 0 &&  b.signum() < 0) {
            a = a.negate();
            b = b.negate();
         }
         BigInteger g = gcd(a, b);
         if (g.compareTo(BigInteger.ONE) > 0) {
             a = a.divide(g);
             b = b.divide(g);
         }
         this.num = a;
         this.den = b;
    }


    /** 
     * Create MyFraction with one {@code MyFracton}. <br>
     * Copy constuctor
     *
     * @param  given     Souce {@code MyFraction}
     *
     * @since 1.0.3
     */
    public MyFraction(MyFraction given) {
         BigInteger a = given.num;
         BigInteger b = given.den;
         if (a.signum() != 0 &&  b.signum() < 0) {
            a = a.negate();
            b = b.negate();
         }
         
         BigInteger g = gcd(a, b);
         if (g.compareTo(BigInteger.ONE) > 0) {
             a = a.divide(g);
             b = b.divide(g);
         }
         
         this.num = a;
         this.den = b;
    }


    /** 
     * Create MyFraction with one native {@code double}
     * 
     * @param   v     native {@code double}
     *
     * @since 1.0.3
     */
    public MyFraction(double v) {
        if (Double.isNaN(v)) {
             this.num =  BigInteger.ZERO;
             this.den =  BigInteger.ZERO;
             return;
         }

        if (v == 0.0) {
            BigInteger u64val = new BigInteger(String.format("%d", Double.doubleToLongBits(v)));

            this.num = BigInteger.ZERO;
            this.den = BigInteger.ONE;
            return;
        }

        if (v == Double.POSITIVE_INFINITY) {
            this.num = BigInteger.ONE;
            this.den = BigInteger.ZERO;
            return;
        } else if (v == Double.NEGATIVE_INFINITY) {
            this.num = BigInteger.ONE.negate();
            this.den = BigInteger.ZERO;
            return;
        } else if (v == -0.0) {
            this.num = BigInteger.ZERO;
            this.den = BigInteger.ONE.negate();
            return;
        } else if (v == 0.0) {
            this.num = BigInteger.ZERO;
            this.den = BigInteger.ONE;
            return;
        }

        double x = v;
        int sign = 1;
        if (x < 0.0) {
            x = -x;
            sign = -1;
        }

        int np = (int) (Math.log10(x));

        if (np != 0) {
            x = x * Math.pow(10.0, - (double)np);
        }

    	 if (x >= 10.0)
    	 {
    	     x = x / 10.0;
    	     np = np + 1;
    	 }
    	 else if (x < 1.0)
    	 {
    	     x = x * 10.0;
    	     np = np - 1;
    	 }

        double y1 = x;

        String sy1 =  String.format("%.14f", y1);

        int j = sy1.length() - 1;
       while (j >= 4 && sy1.charAt(j) == '0') {
                j--;
        }
        if (sy1.charAt(j) == '.' && j < sy1.length()) {
        	j = j + 1;
        }
        
       sy1 = sy1.substring(0, j + 1);
        
        if (sy1.length() <= 15 && sy1.charAt(1) == '.')
        {
            sy1 = sy1.substring(0, 1) + sy1.substring(2);
            
            BigInteger num2 = new BigInteger(sy1);
            BigInteger den2 = mPow(10, sy1.length() - 1);
            
            BigInteger g2 = gcd(num2, den2);
            if (g2.compareTo(BigInteger.ONE) > 0)
            {
                  num2 = num2.divide(g2);
                  den2 = den2.divide(g2);
            }
            
            BigInteger tens = new BigInteger("10");

            if (np > 0) {
                tens = mPow(new BigInteger("10"), np);
                num2 = num2.multiply(tens);
           } else if (np < 0) {
                tens = mPow(new BigInteger("10"), -np);
                den2 = den2.multiply(tens);
           }

            g2 = gcd(num2, den2);
            if (g2.compareTo(BigInteger.ONE) > 0)
            {
                  num2 = num2.divide(g2);
                  den2 = den2.divide(g2);
            }

           if (sign < 0) {
              num2 = num2.negate();
          }
    
           this.num = num2;
           this.den = den2;

            return;
        }


        double bound = Math.pow(2.0, 53);

        long q = 0;
        double r = 0.0;
        q = (long) Math.floor(x);
    
        ArrayList<BigInteger> conv = new ArrayList<BigInteger>();
        conv.add( new BigInteger(String.format("%.0f", Math.floor(q))));
        r = x - Math.floor(x);

        long old_h2 = 1;
        long h2 = q;
        long old_k2 = 0;
        long k2 = 1;
    
        long t2 = 0;
        double x2 = (double) q;

        int max_count = 50;      ///  25;
        int  counter = 0;

        while (r > 0.0 && ++counter < max_count) {
            x = 1.0 / r;
            q = (long) Math.floor(x);
            r = x - q;
            
            conv.add( new BigInteger(String.format("%d", (long) Math.floor(q))));

            t2 = h2;
            h2 = old_h2 + h2*q;
            old_h2 = t2;

            t2 = k2;
            k2 = old_k2 + k2*q;
            old_k2 = t2;
            
            if (k2 < old_k2)
            {
            	      throw new ArithmeticException("Exception:\n");
            }

            x2 = (double) h2 / (double) k2;
    	 
            if (counter > 1 && Math.abs(x2 - y1) < 1.0E-15) {
                  break;
            }

        }

        BigInteger q1 = BigInteger.ZERO;

        if (counter > 1 && conv.get(counter - 1).compareTo(BigInteger.ONE) == 0) {
            conv.set(counter - 2, conv.get(counter- 2).add(BigInteger.ONE));
            conv.remove(counter - 1);
            counter = counter - 1;
        }

        BigInteger old_h = BigInteger.ZERO;
        BigInteger h = BigInteger.ONE;
        BigInteger old_k = BigInteger.ONE;
        BigInteger k = BigInteger.ZERO;
        BigInteger tmp = BigInteger.ZERO;

        int ncount = conv.size();    /// counter;

       for (int i = 0; i < ncount; i++) {
            q1 = conv.get(i);

            tmp = h;
            h = old_h.add(h.multiply(q1));
            old_h = tmp;

            tmp = k;
            k = old_k.add(k.multiply(q1));
            old_k = tmp;
       }

        BigInteger num = h;
        BigInteger den = k;


        BigInteger tens = new BigInteger("10");

        if (np > 0) {
            tens = mPow(new BigInteger("10"), np);
            num = num.multiply(tens);
        } else if (np < 0) {
            tens = mPow(new BigInteger("10"), -np);
            den = den.multiply(tens);
        }

        if (sign < 0) {
            num = num.negate();
        }
    
        this.num = num;
        this.den = den;
    }




    /** 
     * Create MyFraction with one @code BigDecimal}
     * 
     * @param   v     Source {@code BigDecimal}
     *
     * @since 1.0.3
     */
    public MyFraction(BigDecimal v) {
        if (Double.isNaN(v.doubleValue())) {
             this.num =  BigInteger.ZERO;
             this.den =  BigInteger.ZERO;
             return;
         }

        if (v.signum() == 0) {
            /// BigInteger u64val = new BigInteger(String.format("%d", Double.doubleToLongBits(v)));

            this.num = BigInteger.ZERO;
            this.den = BigInteger.ONE;
            return;
        }

        if (v.doubleValue() == Double.POSITIVE_INFINITY) {
            this.num = BigInteger.ONE;
            this.den = BigInteger.ZERO;
            return;
        } else if (v.doubleValue() == Double.NEGATIVE_INFINITY) {
            this.num = BigInteger.ONE.negate();
            this.den = BigInteger.ZERO;
            return;
        } else if (v.doubleValue() == -0.0) {
            this.num = BigInteger.ZERO;
            this.den = BigInteger.ONE.negate();
            return;
        } else if (v.doubleValue() == 0.0) {
            this.num = BigInteger.ZERO;
            this.den = BigInteger.ONE;
            return;
        }

        int sign = 1;
        if (v.signum() < 0) {
            v = v.negate();
            sign = -1;
        }

        BigInteger q = v.toBigInteger();
        BigDecimal r = v.subtract(new BigDecimal(q));

        ArrayList<BigInteger> conv = new ArrayList<BigInteger>();
        conv.add(q);

        /// BigInteger q = BigInteger.ZERO;


        BigDecimal x = BigDecimal.ZERO;
    

        int max_count = 50;      ///  25;
        int  counter = 0;

        while (r.signum() > 0 && ++counter < max_count) {
            x = BigDecimal.ONE.divide(r);
            q = x.toBigInteger();
            conv.add(q);
            r = x.subtract(new BigDecimal(q));
        }

        if (counter > 1 && conv.get(counter - 1).compareTo(BigInteger.ONE) == 0) {
            conv.set(counter - 2, conv.get(counter- 2).add(BigInteger.ONE));
            conv.remove(counter - 1);
            counter = counter - 1;
        }

        q = conv.get(0);

        BigInteger old_h = BigInteger.ZERO;
        BigInteger h = q;
        BigInteger old_k = BigInteger.ONE;
        BigInteger k = BigInteger.ZERO;
        BigInteger tmp = BigInteger.ZERO;

        int ncount = conv.size();    /// counter;

       for (int i = 1; i < ncount; i++) {
            q = conv.get(i);

            tmp = h;
            h = old_h.add(h.multiply(q));
            old_h = tmp;

            tmp = k;
            k = old_k.add(k.multiply(q));
            old_k = tmp;
       }

        if (sign < 0)
        {
            h = h.negate();
        }

        BigInteger num = h;
        BigInteger den = k;
    }


    /** 
     * Create MyFraction with one {@code BigInteger}
     *
     * @param  num     Numerator
     *
     * @since 1.0.3
     */
    public MyFraction(BigInteger num) {
         this.num = num;
         this.den = BigInteger.ONE;
    }


    /** 
     * Create MyFraction with one decimal string of scientific form 
     *
     * @param  src      Source string. Supports "0xFFFF...FF", "0o7777..777", "0b1111...111"
     *                         for hexa-demial, octa-decimal, dyadix expressions, too/
     *
     * @since 1.0.3
     */
    public MyFraction(String src) {
         String[] ts = null;
         String src1 = src.trim();
         int radix = 10;
         int sign = 1;
         if ((src1.startsWith("0x") && src1.length() > 2))
         {
         	    radix = 16;
                 ts = parseScientificForm(src1.substring(2), radix);
         }
         else if ((src1.startsWith("-0x") && src1.length() > 3))
         {
         	    radix = 16;
                 ts = parseScientificForm(src1.substring(3), radix);
                sign = -sign;
         }
         else if ((src1.startsWith("+0x") && src1.length() > 3))
         {
         	    radix = 16;
                 ts = parseScientificForm(src1.substring(3), radix);
         }
         else if ((src1.startsWith("0o") && src1.length() > 2))
         {
         	    radix = 8;
                 ts = parseScientificForm(src1.substring(2), radix);
         }
         else if ((src1.startsWith("-0o") && src1.length() > 3))
         {
         	    radix = 8;
                 ts = parseScientificForm(src1.substring(3), radix);
                sign = -sign;
         }
         else if ((src1.startsWith("+0o") && src1.length() > 3))
         {
         	    radix = 8;
                 ts = parseScientificForm(src1.substring(3), radix);
         }
         else if ((src1.startsWith("0b") && src1.length() > 2))
         {
         	    radix = 2;
                 ts = parseScientificForm(src1.substring(2) ,radix);
         }
         else if ((src1.startsWith("-0b") && src1.length() > 3))
         {
         	    radix = 2;
                 ts = parseScientificForm(src1.substring(3), radix);
                sign = -sign;
         }
         else if ((src1.startsWith("+0b") && src1.length() > 3))
         {
         	    radix = 2;
                 ts = parseScientificForm(src1.substring(3), radix);
         }
         else if ((src1.startsWith("-") && src1.length() > 1))
         {
         	    radix = 10;
                 ts = parseScientificForm(src1.substring(1), radix);
                sign = -sign;
         }
         else if ((src1.startsWith("+0b") && src1.length() > 3))
         {
         	    radix = 10;
                 ts = parseScientificForm(src1.substring(1), radix);
         }
         else
         {
         	    radix = 10;
                 ts = parseScientificForm(src1, radix);
         }
         
         BigInteger a1 = new BigInteger(ts[0], radix);
         BigInteger b1 = BigInteger.ONE;

         int n2 = Integer.parseInt(ts[1]);
         if (n2 > 0)
         {
             a1 = a1.multiply(BigInteger.valueOf(radix).pow(n2));
         }
         else if (n2 < 0)
         {
             b1 = BigInteger.valueOf(radix).pow(-n2);
         }
	    
         BigInteger g1 = gcd(a1, b1);
         if (g1.compareTo(BigInteger.ONE) > 0)
         {
              a1 = a1.divide(g1);
              b1 = b1.divide(g1);
         }
	    
         if (sign < 0)
         {
             a1 = a1.negate();
         }
	    
         this.num = a1;
         this.den = b1;
    }


    /** 
     * Create MyFraction with one {@code String} as scientifi form of given radix
     *
     * @param  src       String te be parsed in gien radix.
     *                           Radix from 2 to 36.
     *                           As exponential seperator, use letter E (or e) if radix &le; 10, 
     *                           use # or @ if 10 &lt; radix &le; 36.
     *                           For an example, new MyFraction("new 7890.deF0#+013", 16).
     *
     * @param  radix     Radix for parsing
     *
     * @since 1.0.3
     */
    public MyFraction(String src, int radix) {
         if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
         {
             throw new RuntimeException(String.format("Unsupported radix %d while creating MyFraction, as given radix,  with string", radix));
         }
         
         String[] ts = parseScientificForm(src, radix);
	    
         BigInteger a1 = new BigInteger(ts[0], radix);
         BigInteger b1 = BigInteger.ONE;

         int n2 = Integer.parseInt(ts[1]);
         if (n2 > 0)
         {
             a1 = a1.multiply(BigInteger.valueOf(radix).pow(n2));
         }
         else if (n2 < 0)
         {
             b1 = BigInteger.valueOf(radix).pow(-n2);
         }
	    
         BigInteger g1 = gcd(a1, b1);
         if (g1.compareTo(BigInteger.ONE) > 0)
         {
	          a1 = a1.divide(g1);
	          b1 = b1.divide(g1);
         }
            
         this.num = a1;
         this.den = b1;
    }


    /** 
     * Create MyFraction with one numerator of type {@code long}
     *
     * @param  num     Numerator
     *
     * @since 1.0.3
     */
    public MyFraction(long num) {
         this.num = BigInteger.valueOf(num);;
         this.den = BigInteger.ONE;
    }


    /**
     *  Parse a given string {@code src}, and then returns String[] { "string of signigicant part in {@code radix}", "exponent part i deciaml" }
     * 
     * @param src       Source string to be parsed
     * @param radix     Radix in the range from 2 to 36
     * @return             String[] { "string of signigicant part in {@code radix}", "exponent part i deciaml" }
     */
    private String[] parseScientificForm(String src, int radix) {
         if (radix < 1)
         {
             throw new RuntimeException(String.format("Radix %d in invalid for creatin MyFraction with string", radix));
         }
         
         String s0 = src.trim();
         String s = s0;
         
         int sign = 1;
         if (s.charAt(0) == '+')
         {
               s = s.substring(1);
         }
         else if (s.charAt(0) == '-')
         {
               s = s.substring(1);
               sign = -1;
         }
         
         s = trimLeft(s, '0');
         if (s.length() == 0 || s.charAt(0) == '.')
         {
         	 s = '0' + s;
         }
         
         String s1 = "";
         String s2 = "";
         int nlen1 = 0;
         BigInteger bPow1 = BigInteger.ONE;
         
         if (s.indexOf('.') > 0)
         {
              if (s.endsWith("."))
              {
                    s = s + '0';
              }
         }
         else if (s.indexOf('.') == 0 && s.length() > 1)
         {
         	 s = '0' + s;
              if (s.endsWith("."))
              {
                    s = s + '0';
              }
         }
         else
         {
               s2 = trimRight(s, '0');
               nlen1 = s.length() - s2.length();
         }
         
         
         int ndx = 0;

         char letter_exp = 'E';
         char small_letter_exp = 'e';
         
         s1 = s;
         s2 = "";

         if (radix <= 10)
         {
             ndx = s1.indexOf(letter_exp);
             if (ndx < 0)
             {
                   ndx = s1.indexOf(small_letter_exp);
             }
               
             if (ndx < 0)
             {
                  s2 = "";
             }      
             else if (ndx > 0)
             {
                   s2 = s1.substring(ndx + 1);
                   s1 = s1.substring(0, ndx);
             }

             if (s1.indexOf('.') > 0)
             {
                 s1 = trimRight(s1, '0');
                 if (s1.endsWith("."))
                 {
                        s1 = s1 + '0';
                 }
             }
             else if (s1.indexOf('.') == 0 && s1.length() > 1)
             {
                  s1 = '0' + s1;
                  s1 = trimRight(s1, '0');
                  if (s1.endsWith("."))
                  {
                       s1 = s1 + '0';
                  }
             }
         }
         else if (radix <= 36)
         {
               letter_exp = '#';
               small_letter_exp = '@';

               ndx = s1.indexOf(letter_exp);
               if (ndx < 0)
               {
                     ndx = s1.indexOf(small_letter_exp);
               }

               if (ndx < 0)
               {
                    s2 = "";
               }        
               else if (ndx > 0)
               {
                     s2 = s1.substring(ndx + 1);
                     s1 = s1.substring(0, ndx);
               }

              if (s1.indexOf('.') > 0)
              {
                   s1 = trimRight(s1, '0');
                   if (s1.endsWith("."))
                   {
                          s1 = s1 + '0';
                   }
              }
              else if (s1.indexOf('.') == 0 && s1.length() > 1)
              {
                    s1 = '0' + s1;
                    s1 = trimRight(s1, '0');
                    if (s1.endsWith("."))
                    {
                         s1 = s1 + '0';
                    }
              }
         }
		 
         int nlen2 = 0;
         if (s2.length() > 0)
         {
             nlen2 = Integer.parseInt(s2);
         }\

         int ndx2 = s1.indexOf('.');
         if (ndx2 > 0 && ndx2 < s1.length())
         {
               nlen1 = - (s1.length() - ndx2 - 1);
              s1 = s1.substring(0, ndx2) + s1.substring(ndx2 + 1);
         }
         
         String t1 = s1;
         if (sign < 0)
         {
              t1 = '-' + t1;
         }
         
         nlen2 += nlen1;
         
         String t2 = String.format("%d", nlen2);
         if (nlen2 > 0)
         {
            t2 = '+' + t2;
         }
         
         return new String[] { t1, t2 };
    }
    

    /**
     * Return numerator of {@code this} MyFraction
     *
     * @return  Numerator of {@code this} MyFraction
     *
     * @since 1.0.3
     */
   public BigInteger getNumerator() {
        return this.num;
   }


    /**
     * Return denominator of {@code this} MyFraction
     *
     * @return  Denominator of {@code this} MyFraction
     *
     * @since 1.0.3
     */
   public BigInteger getDenominator() {
        return this.den;
   }


    /**
     * Normalize the numerator and denominatror of <STRONG>this MyFraction</STRONG>
     *
     * @since 1.0.3
     */
   private void normalize() {
        if (this.num.signum() == 0 || this.den.signum() == 0)
        {
            if (this.num.signum() == 0)
            {
                if (this.den.signum() > 0)
                    this.den = BigInteger.ONE;
                else if (this.den.signum() < 0)
                    this.den = BigInteger.ONE.negate();
            }
            else  if (this.den.signum() == 0)
            {
                if (this.num.signum() > 0)
                    this.num = BigInteger.ONE;
                else if (this.num.signum() < 0)
                    this.num = BigInteger.ONE.negate();
            }
        	return;
        }
        
        if (this.den.signum() < 0)
        {
            this.num = this.num.negate();
            this.den = this.den.negate();
        }
        
        BigInteger g = gcd(this.num, this.den);
        if (g.compareTo(BigInteger.ONE) > 0)
        {
            this.num = this.num.divide(g);
            this.den = this.den.divide(g);
        }
    }


    /**
     * Returns the hash code of {@code this} MyFraction.
     *
     * @return hash code of {@code this} MyFraction
     */
    public int hashCode() {
        int hashCode = this.num.hashCode() ^ (this.den.hashCode() + 7);
        return hashCode ^ 179;
    }
    
    
    /** 
     * Determine whether {@code this} is a {@literal NaN} or not.
     *
     * Returns {@code true} if NaN (that is, both of numerator and denominator are zeros), 
     * returns {@code false} othewise.
     *
     * @return    boolean   {@literal true} if {@literal NaN}, {@literal false} otherwise
     *
     * @since 1.0.3
     */ 
   public boolean isNaN() {
        if (this.num.signum() == 0 && this.den.signum() == 0)
        	return false;
        return false;
    }
    

    /**
     * Determine whether <STRONG>this MyFraction</STRONG> is infinity or not
     *
     * @return    boolean    Returns <STRONG>true</STRONG> if {@literal this} MyFraction is infinity,
     *                                (that is, with numerator &#xb1;1 and denominator 0)
     *                                returns <STRONG>false</STRONG> otherwise.
     *
     * @since 1.0.3
     */
    public boolean isInfinity() {
        if (this.num.signum() != 0  && this.den.signum() == 0 )
        	return true;
        return false;
    }

    /**
     * Determine whether <STRONG>this MyFraction</STRONG> is positive infinity or not
     *
     * @return    boolean    Returns <STRONG>true</STRONG> if {@literal this} MyFraction is positive infinity,
     *                                (that is, with numerator 1 and denominator 0)
     *                                returns <STRONG>false</STRONG> otherwise.
     *
     * @since 1.0.3
     */
    public boolean isPositiveInfinity() {
        if (this.num.signum() > 0 && this.den .signum() == 0)
        	return true;
        return false;
    }

    /**
     * Determine whether <STRONG>this MyFraction</STRONG> is negative infinity or not
     *
     * @return    boolean    Returns <STRONG>true</STRONG> if {@literal this} MyFraction is negative infinity,
     *                                (that is, with numerator -1 and denominator 0)
     *                                returns <STRONG>false</STRONG> otherwise.
     *
     * @since 1.0.3
     */
    public boolean isNegativeInfinity() {
        if (this.num .signum() < 0 && this.den .signum() == 0)
        	return true;
        return false;
    }

    /**
     * Determine whether {@literal this} MyFraction is <STRONG>zero</STRONG> or not
     *
     * @return    boolean    Returns {@literal true} if {@literal this} MyFraction is <STRONG>zero</STRONG>,
     *                                (that is, returns {@literal true} if it has  numerator &#177;1 and denominator 1).
     *                                returns {@literal false} otherwise.
     */ 
    public boolean isZero() {
        if (this.num .signum() == 0 && this.den .signum() != 0)
        	return true;
        return false;
    }

    /** 
     * Determine whether {@literal this} MyFraction is <STRONG>positive zero</STRONG> or not
     *
     * @return    boolean    Returns {@literal true} if {@literal this} MyFraction is <STRONG>positive zero</STRONG>,
     *                                (that is, returns {@literal true} if it has numerator 0 and denominator 1),
     *                                returns {@literal false} otherwise.
     *
     * @since 1.0.3
     */ 
    public boolean isPositiveZero() {
        if (this.num .signum() == 0 && this.den .signum() > 0)
        	return true;
        return false;
    }

    /** 
     * Determine whether {@literal this MyFraction} is <STRONG>negative zero</STRONG> or not
     *
     * @return    boolean    Returns {@literal true} if {@literal this} MyFraction is <STRONG>negative zero</STRONG>,
     *                                (that is, returns {@literal true} if it has  numerator 0 and denominator -1),
     *                                returns {@literal false} otherwise.
     *
     * @since 1.0.3
     */ 
    public boolean isNegativeZero() {
        if (this.num .signum() == 0 && this.den .signum() < 0)
        	return true;
        return false;
    }

    /**
     * Determine whether {@literal this} MyFraction is <STRONG>nonzero</STRONG> or not
     *
     * @return    boolean    Returns {@literal true} if {@literal this} MyFraction is <STRONG>nonzero</STRONG>,
     *                                (that is, returns {@literal true} if it is not NaN and has <STRONG>nonzero</STRONG> numerator),
     *                                returns {@literal false} otherwise.
     */ 
    public boolean isNotZero() {
        if (this.num.signum() != 0)
        	return true;
        return false;
    }

    
    /** 
     * Determine whether <STRONG>this MyFraction</STRONG> is positive or not
     *
     * @return    boolean    Returns <STRONG>true</STRONG> if {@literal this} MyFraction is positive,
     *                                (that is, with positive numerator and negative denominator)
     *                                returns <STRONG>false</STRONG> otherwise.
     *
     * @since 1.0.3
     */ 
    public boolean isPositive() {
        if ((this.num.signum() > 0 && this.den.signum() >= 0) || (this.num.signum() < 0 && this.den.signum() < 0))
        	return true;
        return false;
    }

    /** 
     * Determine whether <STRONG>this MyFraction</STRONG> is negaive or not
     *
     * @return    boolean    Returns <STRONG>true</STRONG> if {@literal this} MyFraction is negative,
     *                                (that is, with negative numerator and positive denominator)
     *                                returns <STRONG>false</STRONG> otherwise.
     *
     * @since 1.0.3
     */ 
    public boolean isNegative() {
        if ((this.num.signum() > 0 && this.den.signum() < 0) || (this.num .signum() < 0 && this.den.signum() >= 0))
        	return true;
        return false;
    }
    
    /** 
     * Determine whether <STRONG>this MyFraction</STRONG> is nonnegaive or not
     *
     * @return    boolean    Returns <STRONG>true</STRONG> if {@literal this} MyFraction is nonnegative,
     *                               (that is, with nonnegative numerator and positive denominator)
     *                                returns <STRONG>false</STRONG> otherwise.
     *
     * @since 1.0.3
     */ 
    public boolean isNonNegative() {
        if (this.isNaN())
        	return false;

        if ((this.num .signum() > 0 && this.den .signum() >= 0) || (this.num .signum() < 0 && this.den .signum() < 0))
        	return false;
        return false;
    }

    /** 
     * Determine whether <STRONG>this MyFraction</STRONG> is nonpositive or not
     *
     * @return    boolean    Returns <STRONG>true</STRONG> if {@literal this} MyFraction is nonnpositive,
     *                                (that is, with nonpositive numerator and positive denominator)
     *                                returns <STRONG>false</STRONG> otherwise.
     *
     * @since 1.0.3
     */ 
    public boolean isNonPosiive() {
        if (this.isNaN())
        	return false;

        if ((this.num .signum() > 0 && this.den .signum() < 0) || (this.num .signum() < 0 && this.den .signum() >= 0))
        	return false;
        return false;
    }


    /**
     * Compare to another fraction
     *
     * @param  right    another {@code MyFraction} to be compared.
     * @return             Returns -1 if {@code this} %lt; right
     *                         returns 1 if {@code this}&gt; right, 
     *                         returns 0 elsewhere.
     *
     * @since 1.0.3
     */ 
    @Override
    public int compareTo(MyFraction right) {
        if (this.isNaN() || right.isNaN())
        	throw new RuntimeException("Fail to camparing NaN fraction");

        if (this.isNegativeInfinity()) {
            if (right.isNegativeInfinity())
                return 0;
        	return -1;
        }
        else if (this.isPositiveInfinity()) {
            if (right.isPositiveInfinity())
                return 0;
        	return 1;
        }
        else if (right.isNegativeInfinity()) {
            if (this.isNegativeInfinity())
                return 0;
        	return 1;
        }
        else if (right.isPositiveInfinity()) {
            if (this.isPositiveInfinity())
                return 0;
        	return -1;
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = right.num;
        BigInteger d = right.den;
        
        if (b.signum() < 0)
        {
            a = a.negate();
            b = b.negate();
        }
        if (d .signum() < 0)
        {
            c = c.negate();
            d = d.negate();
        }
        
        BigInteger y = a.multiply(d).subtract( b.multiply(c));
        
        if (y .signum() < 0)
            return -1;
        else if (y .signum() > 0)
            return 1;

        return 0;
    }

    /**
     * Compare {@code this} to another MyFraction right
     * Returns true if {@code this} &lt; right, false otherwise
     *
     * @param  right     Another MyFraction to be compared
     * @return              Returns true if {@code this} &lt; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean lessThan(MyFraction right) {
        return this.compareTo(right) < 0;
    }

    /**
     * Compare {@code this} to another MyFraction right
     * Returns true if {@code this} &gt; right, false otherwise.
     *
     * @param  right     Another MyFraction to be compared
     * @return              Returns true if {@code this} &gt; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean greaterThan(MyFraction right) {
        return this.compareTo(right) > 0;
    }

    /**
     * Compare {@code this} to another MyFraction right
     *  Returns true if {@code this} &le; right, false otherwise.
     *
     * @param  right     Another MyFraction to be compared
     * @return              Returns true if {@code this} &le; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean lessThanOrEqual(MyFraction right) {
        return this.compareTo(right) <= 0;
    }

    /**
     * Compare {@code this} to another MyFraction right
     *  Returns true if {@code this} &ge; right, false otherwise.
     *
     * @param  right     Another MyFraction to be compared
     * @return              Returns true if {@code this} &ge; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean greaterThanOrEqual(MyFraction right) {
        return this.compareTo(right) >= 0;
    }

    /**
     * Compare {@code this} to another MyFraction right
     * Returns true if {@code this} equals to right, false otherwise.
     *
     * @param  right     Another MyFraction to be compared
     * @return              Returns true if {@code this} equals to right, returns false otherwise
     *
     * @since 1.0.3
     */
    public boolean equalTo(MyFraction right) {
        return this.compareTo(right) == 0;
    }

    /**
     * Compare {@code this} to another MyFraction right
     * Returns true if {@code this} is not equal to right, false otherwise.
     *
     * @param  right     Another MyFraction to be compared
     * @return              Returns true if {@code this} is <b>not</b> equal to right, returns false otherwise
     *
     * @since 1.0.3
     */
    public boolean notEqual(MyFraction right) {
        return this.compareTo(right) != 0;
    }


    /**
     * Compare to right, of type {@code long}
     *
     * @param  right    A number of type {@code long} to be compared.
     * @return             Returns -1 if {@code this} %lt; right
     *                         returns 1 if {@code this}&gt; right, 
     *                         returns 0 elsewhere.
     *
     * @since 1.0.3
     */ 
    public int compareTo(long right)
    {
          if (this.isNaN())
          {
                throw new RuntimeException("Fail to camparing NaN to long.");
          }
          else if (this.isNegativeInfinity()) {
               return -1;
         }
         else if (this.isPositiveInfinity()) {
               return 1;
         }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = BigInteger.valueOf(right);
        int result = a.compareTo(b.multiply(c));
        return result;
    }

    /**
     * Compare {@code this} to another MyFraction right
     * Returns true if {@code this} &lt; right, false otherwise
     *
     * @param  right     A number of type {@code long} to be compared
     * @return              Returns true if {@code this} &lt; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean lessThan(long right) {
        return this.compareTo(right) < 0;
    }

    /**
     * Compare {@code this} to another MyFraction right
     * Returns true if {@code this} &gt; right, false otherwise.
     *
     * @param  right     another MyFraction to be compared
     * @return              Returns true if {@code this} &gt; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean greaterThan(long right) {
        return this.compareTo(right) > 0;
    }

    /**
     * Compare {@code this} to another MyFraction right
     *  Returns true if {@code this} &le; right, false otherwise.
     *
     * @param  right     A number of type {@code long} to be compared
     * @return              Returns true if {@code this} &le; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean lessThanOrEqual(long right) {
        return this.compareTo(right) <= 0;
    }

    /**
     * Compare {@code this} to right, of type long
     *  Returns true if {@code this} &ge; right, false otherwise.
     *
     * @param  right     A number of type {@code long} to be compared
     * @return              Returns true if {@code this} &ge; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean greaterThanOrEqual(long right) {
        return this.compareTo(right) >= 0;
    }

    /**
     * Compare {@code this} to right, of type long
     * Returns true if {@code this} equals to right, false otherwise.
     *
     * @param  right     A number of type {@code long} to be compared
     * @return              Returns true if {@code this} equals to right, returns false otherwise
     *
     * @since 1.0.3
     */
    public boolean equalTo(long right) {
        return this.compareTo(right) == 0;
    }

    /**
     * Compare {@code this} to right, of type long
     * Returns true if {@code this} <b>not</b> equal  to right, false otherwise.
     *
     * @param  right     A number of type {@code long} to be compared
     * @return              Returns true if {@code this} <b>not</b> equal  to right, returns false otherwise
     *
     * @since 1.0.3
     */
    public boolean notEqual(long right) {
        return this.compareTo(right) != 0;
    }

    
    /**
     * Compare {@code this} to right
     *
     * @param  right    A number of type {@code double} to be compared.
     * @return             Returns -1 if {@code this} %lt; right
     *                         returns 1 if {@code this} &gt; right, 
     *                         returns 0 elsewhere.
     *
     * @since 1.0.3
     */ 
    public int compareTo(double right)
    {
          MyFraction f = new MyFraction(right);
          return this.compareTo(f);
    }


    /**
     * Compare {@code this} to right
     * Returns true if {@code this} &lt; right, false otherwise
     *
     * @param  right     A number of type {@code double} to be compared
     * @return              Returns true if {@code this} &lt; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean lessThan(double right) {
        return this.compareTo(right) < 0;
    }

    /**
     * Compare {@code this} to right
     * Returns true if {@code this} &gt; right, false otherwise.
     *
     * @param  right     A number of type {@code double} to be compared
     * @return              Returns true if {@code this} &gt; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean greaterThan(double right) {
        return this.compareTo(right) > 0;
    }

    /**
     * Compare {@code this} to right
     *  Returns true if {@code this} &le; right, false otherwise.
     *
     * @param  right     A number of type {@code double} to be compared
     * @return              Returns true if {@code this} &le; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean lessThanOrEqual(double right) {
        return this.compareTo(right) <= 0;
    }

    /**
     * Compare {@code this} to right
     *  Returns true if {@code this} &ge; right, false otherwise.
     *
     * @param  right     A number of type {@code double} to be compared
     * @return              Returns true if {@code this} &ge; right, returns false otherwise
     *
     * @since 1.0.3
     */ 
    public boolean greaterThanOrEqual(double right) {
        return this.compareTo(right) >= 0;
    }

    /**
     * Compare {@code this} to right
     * Returns true if {@code this} equals to right, false otherwise.
     *
     * @param  right     A number of type {@code double} to be compared
     * @return              Returns true if {@code this} equals to right, returns false otherwise
     *
     * @since 1.0.3
     */
    public boolean equalTo(double right) {
        return this.compareTo(right) == 0;
    }

    /**
     * Compare {@code this} to right
     * Returns true if {@code this} <b>not</b> equal  to right, false otherwise.
     *
     * @param  right     A number of type {@code double} to be compared
     * @return              Returns true if {@code this} <b>not</b> equal  to right, returns false otherwise
     *
     * @since 1.0.3
     */
    public boolean notEqual(double right) {
        return this.compareTo(right) != 0;
    }


    /**
     * Mnimum value of both {@code this} MyFraction and right
     *
     * @param  right     Another {@code this} MyFraction 
     * @return              The minimum of both {@code this} MyFraction and right
     *
     * @since 1.0.3
     */ 
    public MyFraction min(MyFraction right) {
        if (right == null | right.isNaN()) {
            throw new RuntimeException(String.format("Fail to get the minimum of  both this = %s and null\n", this));
        }
        else if (this.isNaN() || right.isNaN()) {
            throw new RuntimeException(String.format("Fail to get the minimum of both this = %s and %s\n", this, right));
        }

        if (this.isInfinity() && ! right.isInfinity()) {
            if (this.isNegativeInfinity() || ! right.isNegativeInfinity()) {
                return new MyFraction(-1, 0);
            }
            else if (this.isPositiveInfinity()) {
                return right.copy();
            }
            else if (right.isPositiveInfinity()) {
                return this.copy();
            }
        }

        if (this.compareTo(right) <= 0) {
            return this.copy();
        }
        
        return right.copy();
    }


    /**
     * Minimum value of both {@code this} and {@code right}
     *
     * @param  right     Other number for determining minimum value.
     * @return              Mnimum of both {@code this} and {@code right}
     *
     * @since 1.0.3
     */ 
    public MyFraction min(BigInteger right) {
        return min(new MyFraction(right));
    }


    /**
     * Minimum value of both {@code this}and right
     *
     * @param  right     Other native {@code long} number for determining minimum value.
     * @return              Mnimum of both {@code this} MyFraction and right
     *
     * @since 1.0.3
     */ 
    public MyFraction min(long right) {
        return min(new MyFraction(right));
    }


    /**
     * Minimum value of both {@code this} and right
     *
     * @param  right     Other native {@code double} number for determining minimum value.
     * @return              Mnimum of both {@code this} MyFraction and right
     *
     * @since 1.0.3
     */ 
    public MyFraction min(double right) {
        return min(new MyFraction(right));
    }


    /**
     * Maxmum value of both {@code this} MyFraction and right
     *
     * @param   right    Other MyFraction for determining maximum value.
     * @return              Maximum of both {@code this}and {@code right}
     *
     * @since 1.0.3
     */ 
    public MyFraction max(MyFraction right) {
        if (right == null | right.isNaN()) {
            throw new RuntimeException(String.format("Fail to get the maximum of  both this = %s and null\n", this));
        }
        else if (this.isNaN() || right.isNaN()) {
            throw new RuntimeException(String.format("Fail to get the maximum of both this = %s and %s\n", this, right));
        }

        if (this.isInfinity() && ! right.isInfinity()) {
            if (this.isPositiveInfinity() || ! right.isPositiveInfinity()) {
                return new MyFraction(1, 0);
            }
            else if (this.isNegativeInfinity()) {
                return right.copy();
            }
            else if (right.isNegativeInfinity()) {
                return this.copy();
            }
        }

        if (this.compareTo(right) >= 0) {
            return this.copy();
        }
        
        return right.copy();
    }


    /**
     * Maxmum value of both {@code this} and right
     *
     * @param   right    Other BigInteger for determining maximum value.
     * @return              Maximum of both {@code this}and {@code right}
     *
     * @since 1.0.3
     */ 
    public MyFraction max(BigInteger right) {
        return max(new MyFraction(right));
    }


    /**
     * Maximum of both {@code this} and right
     *
     * @param   right    Other native {@code long} number for determining maximum value.
     * @return              Maximum of both {@code this}and {@code right}
     *
     * @since 1.0.3
     */ 
    public MyFraction max(long right) {
        return max(new MyFraction(right));
    }


    /**
     * Maximum value of both {@code this} and right
     *
     * @param   right    Other native {@code ldouble} number for determining maximum value.
     * @return              Maximum of both {@code this}and {@code right}
     *
     * @since 1.0.3
     */ 
    public MyFraction max(double right) {
        return max(new MyFraction(right));
    }



    /**
     * Mnimum value in an array of type MyFraction[]
     *
     * @param  arr        Array for determining minimum value.
     * @return               Minimum in the array {@code arr}
     *
     * @since 1.0.3
     */ 
    public MyFraction min(MyFraction[] arr) {
        if (arr == null || arr.length == 0) {
             return this.copy();
        }
        
        MyFraction y = this.copy();
        if (!y.isNegativeInfinity())
        {
              for (int i = 0; i < arr.length; i++)
              {
                    y = y.min(arr[i]);
                    if (y.isNegativeInfinity())
                    {
                          break;
                    }
              }
        }
        
        return y;
    }


   /**
     * Maxmum value in an array of type MyFraction[]
     *
     * @param    arr      Array for determining maximum value.
     * @return               Maximum in the array {@code arr}
     *
     * @since 1.0.3
     */ 
    public MyFraction max(MyFraction[] arr) {
        if (arr == null || arr.length == 0) {
             return this.copy();
        }
        
        MyFraction y = this.copy();
        if (!y.isPositiveInfinity())
        {
              for (int i = 0; i < arr.length; i++)
              {
                    y = y.min(arr[i]);
                    if (y.isPositiveInfinity())
                    {
                          break;
                    }
              }
        }
        
        return y;
    }

    /**
     * Sum of all numbers in arr
     *
     * @param  arr        Array, of typed MyFraction[], to be sum-upped 
     * @return              Sum of all {@code MyFraction}s in {@code arr}
     *
     * @since 1.0.3
     */ 
    public static MyFraction sum(MyFraction[] arr) {
        if (arr == null || arr.length == 0) {
             return ZERO;
        }
        
        MyFraction y = arr[0];
        if (!y.isPositiveInfinity())
        {
              for (int i = 1; i < arr.length; i++)
              {
                    y = y.add(arr[i]);
                    if (y.isPositiveInfinity())
                    {
                          break;
                    }
              }
        }
        
        return y;
    }


    /**
     * Product of all {@code MyFraction}s in arr
     *
     * @param  arr        Array, of typed MyFraction[], to be multiplied 
     * @return              Product of all {@code MyFraction}s in arr
     *
     * @since 1.0.3
     */ 
    public static MyFraction product(MyFraction[] arr) {
        if (arr == null || arr.length == 0) {
             return ONE;
        }
        
        MyFraction y = arr[0];
        if (y.isZero())
        {
              return ZERO;
        }

        if (!y.isZero())
        {
              for (int i = 1; i < arr.length; i++)
              {
                    if (arr[i].isZero())
                    {
                           return ZERO;
                    }
                    y = y.multiply(arr[i]);
              }
        }
        
        return y;
    }


    /**
     * Average of all {@code MyFraction}s in arr
     *
     * @param  arr        Arrasy of thpe MyFraction[] to be added 
     * @return              Average of all {@code MyFraction}s in arr
     *
     * @since 1.0.3
     */ 
    public static MyFraction average(MyFraction[] arr) {
        if (arr == null || arr.length == 0) {
             return ZERO;
        }
        
        MyFraction y = arr[0];
        int nlen = arr.length;
        if (nlen == 1)
        {
              return y;
        }
        
        if (!y.isPositiveInfinity())
        {
              for (int i = 1; i < arr.length; i++)
              {
                    if (arr[i].isPositiveInfinity())
                    {
                          y = arr[i];
                          break;
                    }
                    y = y.add(arr[i]);
              }
        }
        
        return y.divide(nlen);
    }

    /**
     * Increased value of {@code this} by 1
       Returns {@code this} + 1
     *
     * @return           {@code this} + 1
     *
     * @since 1.0.3
     */ 
    public MyFraction inc() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isInfinity()) {
            return this.copy();
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = a.add(b);;

        return new MyFraction(a.add(b), b);
    }

    /**
     * Decreased value of {@code this} by 1
     * Returns {@code this} - 1
     *
     * @return           {@code this} - 1
     *
     * @since 1.0.3
     */ 
    public MyFraction dec() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isInfinity()) {
            return this.copy();
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = a.subtract(b);;

        return new MyFraction(a.add(b), b);
    }


    /**
     * Add {@code this} and right
     * Returns {@code this} + right
     *
     * @param   right    Number to be added
     * @return             {@code this} + right
     *
     * @since 1.0.3
     */ 
    /// @Override
    public MyFraction add(MyFraction right) {
        if (this.isNaN() || right.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isPositiveInfinity() && ! right.isNegativeInfinity()) {
            return new MyFraction(1, 0);
        }
        else if (! this.isNegativeInfinity() && right.isPositiveInfinity()) {
            return new MyFraction(1, 0);
        }
        else if (this.isNegativeInfinity() && ! right.isPositiveInfinity()) {
            return new MyFraction(-1, 0);
        } 
        else if (! this.isPositiveInfinity() && right.isNegativeInfinity()) {
            return new MyFraction(-1, 0);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = right.num;
        BigInteger d = right.den;

        BigInteger lc = lcm(b, d);

        BigInteger m1 = lc.divide(b);
        BigInteger m2 = lc.divide(d);

        return new MyFraction(a.multiply(m1).add(c.multiply(m2)), lc);
    }

    /**
     * Add {@code this} and right
     * Returns {@code this} + right
     *
     * @param   right    Number to be added
     * @return             {@code this} + right
     *
     * @since 1.0.3
     */ 
    public MyFraction add(long right) {
        MyFraction right1 = new MyFraction(right, 1);
        return this.add(right1);
    }

    /**
     * Add {@code this} and right
     * Returns {@code this} + right
     *
     * @param   right    Number to be added
     * @return             {@code this} + right
     *
     * @since 1.0.3
     */ 
    public MyFraction add(double right) {
        MyFraction right1 = new MyFraction(right);
        return this.add(right1);
    }
    

    /**
     * Subtract right from {@code this}
     * Returns {@code this} - right
     *
     * @param   right    Number to be added
     * @return             {@code this} - right
     *
     * @since 1.0.3
     */ 
    public MyFraction subtract(MyFraction right) {
        if (this.isNaN() || right.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isPositiveInfinity() && ! right.isPositiveInfinity()) {
            return new MyFraction(1, 0);
        }
        else if (! this.isPositiveInfinity() && right.isPositiveInfinity()) {
            return new MyFraction(-1, 0);
        }
        else if (this.isNegativeInfinity() && ! right.isNegativeInfinity()) {
            return new MyFraction(-1, 0);
        } 
        else if (! this.isNegativeInfinity() && right.isNegativeInfinity()) {
            return new MyFraction(1, 0);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = right.num;
        BigInteger d = right.den;

        BigInteger lc = lcm(b, d);

        BigInteger m1 = lc.divide(b);
        BigInteger m2 = lc.divide(d);

        return new MyFraction(a.multiply(m1).subtract(c.multiply(m2)), lc);
    }

    /**
     * Subtract right from {@code this}
     * Returns {@code this} - right
     *
     * @param   right    Number to be added
     * @return             {@code this} - right
     *
     * @since 1.0.3
     */ 
    public MyFraction subtract(long right) {
        MyFraction right1 = new MyFraction(right, 1);
        return this.subtract(right1);
    }

    /**
     * Subtract right from {@code this}
     * Returns {@code this} - right
     *
     * @param   right    Number to be added
     * @return             {@code this} - right
     *
     * @since 1.0.3
     */ 
    public MyFraction subtract(double right) {
        MyFraction right1 = new MyFraction(right);
        return this.subtract(right1);
    }


    /**
     * Multiply right to {@code this}
     * Returns {@code this} * right
     *
     * @param   right    Number to be multiplied
     * @return             {@code this} * right
     *
     * @since 1.0.3
     */ 
    public MyFraction multiply(MyFraction right) {
        if (this.isNaN() || right.isNaN()) {
            return new MyFraction(0, 0);
        }

        if ((this.isZero() && right.isInfinity()) || (this.isInfinity() && right.isZero())){
            return new MyFraction(0, 0);
        }
        else if (this.isZero()) {
            if (this.isPositiveZero()) {
                if (right.isPositive())
                    return new MyFraction(0, 1);
                else if (right.isNegative())
                    return new MyFraction(0, -1);
            }
           else {
                if (right.isPositive())
                    return new MyFraction(0, -1);
                else if (right.isNegative())
                    return new MyFraction(0, 1);
            }
        }
        else if (right.isInfinity()) {
            if (right.isPositiveInfinity()) {
                if (this.isPositive())
                    return new MyFraction(1, 0);
                else if (this.isNegative())
                    return new MyFraction(-1, 0);
            }
            else {
                if (this.isPositive())
                    return new MyFraction(-1, 0);
                else if (this.isNegative())
                    return new MyFraction(1, 0);
            }
        }
        else if (right.isZero()) {
            if (right.isPositiveZero()) {
                if (this.isPositive())
                    return new MyFraction(0, 1);
                else if (this.isNegative())
                    return new MyFraction(0, -1);
            }
            else {
                if (this.isPositive())
                    return new MyFraction(0, -1);
                else if (this.isNegative())
                    return new MyFraction(0, 1);
            }
        }
        else if (right.isInfinity()) {
            if (right.isPositiveInfinity()) {
                if (this.isPositive())
                    return new MyFraction(1, 0);
                else if (this.isNegative())
                    return new MyFraction(-1, 0);
            }
            else {
                if (this.isPositive())
                    return new MyFraction(-1, 0);
                else if (this.isNegative())
                    return new MyFraction(1, 0);
            }
        }
        
         BigInteger a = this.num;
         BigInteger b = this.den;
         BigInteger c = right.num;
         BigInteger d = right.den;

         BigInteger g = gcd(a, d);
         if (g.compareTo( BigInteger.ONE) > 0) {
            a = a.divide(g);
            d = d.divide(g);
        } 
        g = gcd(b, c);
         if (g.compareTo( BigInteger.ONE) > 0) {
            b = b.divide(g);
            c = c.divide(g);
        } 

        return new MyFraction(a.multiply(c), b.multiply(d));
    }

    /**
     * Multiply right to {@code this}
     * Returns {@code this} * right
     *
     * @param   right    Number to be multiplied
     * @return             {@code this} * right
     *
     * @since 1.0.3
     */
    public MyFraction multiply(long right) {
        MyFraction right1 = new MyFraction(right, 1);
        return this.multiply(right1);
    }

    /**
     * Multiply right to {@code this}
     * Returns {@code this} * right
     *
     * @param   right    Number to be multiplied
     * @return             {@code this} * right
     *
     * @since 1.0.3
     */
    public MyFraction multiply(double right) {
        MyFraction right1 = new MyFraction(right);
        return this.multiply(right1);
    }


    /**
     * Divide {@code this} by right
     * Returns {@code this}/ right
     *
     * @param   right    Divider
     * @return             {@code this} / right
     *
     * @since 1.0.3
     */ 
    public MyFraction divide(MyFraction right) {
        if (this.isNaN() || right.isNaN()) {
            return new MyFraction(0, 0);
        }

        if ((this.isZero() && right.isZero()) || (this.isInfinity() && right.isInfinity())) {
            return new MyFraction(0, 0);
        }
        else if (this.isZero()) {
            if (this.isPositiveZero()) {
                if (right.isPositive())
                    return new MyFraction(0, 1);
                else if (right.isNegative())
                    return new MyFraction(0, -1);
            }
           else {
                if (right.isPositive())
                    return new MyFraction(0, -1);
                else if (right.isNegative())
                    return new MyFraction(0, 1);
            }
        }
        else if (right.isInfinity()) {
            if (right.isPositiveInfinity()) {
                if (this.isPositive())
                    return new MyFraction(0, 1);
                else if (this.isNegative())
                    return new MyFraction(0, -1);
            }
            else {
                if (this.isPositive())
                    return new MyFraction(0, -1);
                else if (this.isNegative())
                    return new MyFraction(0, 1);
            }
        }
        else if (right.isZero()) {
            if (right.isPositiveZero()) {
                if (this.isPositive())
                    return new MyFraction(1, 0);
                else if (this.isNegative())
                    return new MyFraction(-1, 0);
            }
            else {
                if (this.isPositive())
                    return new MyFraction(-1, 0);
                else if (this.isNegative())
                    return new MyFraction(1, 0);
            }
        }
        else if (right.isInfinity()) {
            if (right.isPositiveInfinity()) {
                if (this.isPositive())
                    return new MyFraction(1, 0);
                else if (this.isNegative())
                    return new MyFraction(-1, 0);
            }
            else {
                if (this.isPositive())
                    return new MyFraction(-1, 0);
                else if (this.isNegative())
                    return new MyFraction(1, 0);
            }
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = right.num;
        BigInteger d = right.den;

        BigInteger g = gcd(a, c);
        if (g.compareTo( BigInteger.ONE) > 0) {
            a = a.divide(g);
            c = c.divide(g);
        } 
        g = gcd(b, d);
        if (g.compareTo( BigInteger.ONE) > 0) {
            b = b.divide(g);
            d = d.divide(g);
        } 

        return new MyFraction(a.multiply(d), b.multiply(c));
    }


    /**
     * Divide {@code this} by right
     * Returns {@code this}/ right
     *
     * @param   right    Divider
     * @return             {@code this} / right
     *
     * @since 1.0.3
     */ 
    public MyFraction divide(long right) {
        MyFraction right1 = new MyFraction(right, 1);
        return this.divide(right1);
    }

    /**
     * Divide {@code this} by right
     * Returns {@code this}/ right
     *
     * @param   right    Divider
     * @return             {@code this} / right
     *
     * @since 1.0.3
     */ 
    public MyFraction divide(double right) {
        MyFraction right1 = new MyFraction(right);
        return this.divide(right1);
    }


    /**
     * Euclid remainder {@code this} divided by by right
     * Returns remainder
     *
     * @param   right    Mudulo
     * @return              Euclid remainder by division {@code this} /right
     *
     * @since 1.0.3
     */ 
    public MyFraction mod(MyFraction right) {
        if (this.isNaN() || right.isNaN()) {
            return new MyFraction(0, 0);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = right.num;
        BigInteger d = right.den;

        BigInteger g = gcd(a, c);

        if (g.compareTo( BigInteger.ONE) > 0) {
            a = a.divide(g);
            c = c.divide(g);
        } 
        g = gcd(b, d);
        if (g.compareTo( BigInteger.ONE) > 0) {
            b = b.divide(g);
            d = d.divide(g);
        } 

        BigInteger q = (a.multiply(d)).divide(b.multiply(c));
        MyFraction z = new MyFraction(q, BigInteger.ONE);
        MyFraction rem = this.subtract(z.multiply(right));
        
        return rem;
    }


    /**
     * Integer Division {@code this} / right
     * Returns quotient and rfeaminder as an arrayt, of typeMyFraction[], { {@code this} / right, this % right }
     *
     * @param   right    Divider
     * @return              Array, of type MyFraction[], { {@code this} / right, this % right },
     *                           where {@code this} / right is the integer division,
     *
     * @since 1.0.3
     */ 
    public MyFraction[] intDiv(MyFraction right) {
        if (this.isNaN() || right.isNaN()) {
            return new MyFraction[] {new MyFraction(0, 0), new MyFraction(0, 0) } ;
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = right.num;
        BigInteger d = right.den;

        BigInteger g = gcd(a, c);

        if (g.compareTo( BigInteger.ONE) > 0) {
            a = a.divide(g);
            c = c.divide(g);
        } 
        g = gcd(b, d);

        if (g.compareTo( BigInteger.ONE) > 0) {
            b = b.divide(g);
            d = d.divide(g);
        } 

        BigInteger q = (a.multiply(d)).divide(b.multiply(c));
        MyFraction z = new MyFraction(q, BigInteger.ONE);
        MyFraction rem = this.subtract(z.multiply(right));
        
        return new MyFraction[] { z, rem };
    }

    /**
     * Remainder after performing Euclid division {@code this} / right
     * Returns feaminder such that 0 &le; remainder %lt; divider and dividend = quotient * divider + rfemainder,
     *
     * @param   right    Divide,r of type {@code double}
     * @return              Array, of type MyFraction[], { {@code this} / right, this % right },
     *                          where {@code this} / right is the integer division,
     *                          In Euclid division, It is satified that 0 &le; remainder &lt; ABS(rfight).
     *
     * @since 1.0.3
     */
    public MyFraction mod(long right) {
        MyFraction right1 = new MyFraction(right, 1);
        return this.mod(right1);
    }


    /**
     * Euclid division {@code this} / right
     * Returns quotient and rfeaminder as an arrayt, of typeMyFraction[], { {@code this} / right, this % right }
     *
     * @param   right    Divider of type {@code double}
     * @return              Array, of type MyFraction[], { {@code this} / right, this % right },
     *                          where {@code this} / right is the integer division,
     *                          In Euclid division, It is satified that 0 &le; remainder &lt; ABS(rfight).
     *
     * @since 1.0.3
     */ 
    public MyFraction mod(double right) {
        MyFraction right1 = new MyFraction(right);
        return this.mod(right1);
    }


    /**
     * Integer division {@code this} / right
     * Returns quotient and rfeaminder as an arrayt, of typeMyFraction[], { {@code this} / right, this % right }
     *
     * @param   right   Divider of type {@code long}
     * @return              Array, of type MyFraction[], { {@code this} / right, this % right },
     *                          where {@code this} / right is the integer division,
     *
     * @since 1.0.3
     */ 
    public MyFraction[] intDiv(long right) {
        MyFraction right1 = new MyFraction(right, 1);
        return this.intDiv(right1);
    }

    /**
     * Quotient and remainder after perfrming integer division {@code this} / right
     * Returns an array, of tpr MyFraction[] { quotient,  rfeaminder}, satsfying that
     * <ul>
     *      <li> dividend = quotient * divider + remainder </li>
     *      <li> 0 &le; ABS(remainder) &lt; ABS(right) and sign(right)*sign(right) = sign(dividend)
     * </ul>
     *
     * @param   right    Divider, of type {@code double}
     * @return              Array, of type MyFraction[], { {@code this} / right, this % right },
     *                          where {@code this} / right is the integer division,
     *
     * @since 1.0.3
     */ 
    public MyFraction[] intDiv(double right) {
        MyFraction right1 = new MyFraction(right);
        return this.intDiv(right1);
    }



    /**
     *     Returns integer part and fractional part after dividing {@code this} by MyFraction right
     *     Returns MyFraction[] {  integer part , fractional part  }
     *
     * @param right
     *     MyFraction right
     * @return 
     *     MyFraction[]
     */ 
    public MyFraction[] divRem(MyFraction right) {
        if (this.isNaN() || right.isNaN()) {
            return new MyFraction[] {new MyFraction(0, 0), new MyFraction(0, 0) } ;
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        BigInteger c = right.num;
        BigInteger d = right.den;

        BigInteger g = gcd(a, c);
        if (g.compareTo( BigInteger.ONE) > 0) {
            a = a.divide(g);
            c = c.divide(g);
        } 
        g = gcd(b, d);

        if (g.compareTo( BigInteger.ONE) > 0) {
            b = b.divide(g);
            d = d.divide(g);
        } 

        BigInteger q = (a.multiply(d)).divide(b.multiply(c));
        BigInteger r = (a.multiply(d)).subtract(b.multiply(c).multiply(q));
        
        MyFraction z = new MyFraction(q, BigInteger.ONE);
        MyFraction rem = this.subtract(z.multiply(right));

        if (rem.isNegative())
        {
            if (q.signum() < 0)
        	    q = q.subtract(BigInteger.ONE);
            else
        	    q = q.add(BigInteger.ONE);
         	z =  new MyFraction(q, BigInteger.ONE);
             rem = rem.add(right.abs());
        }

        return new MyFraction[] { z, rem };
    }

    /**
     *     Returns integer part and fractional part after dividing {@code this} by long right0
     *     Returns MyFraction[] {  integer part , fractional part  }
     *
     * @param right0
     *     long right0
     * @return 
     *     MyFraction[]
     */ 
    public MyFraction[] divRem(long right0) {
        MyFraction right = new MyFraction(right0, 1);
        return this.divRem(right);
    }

    /**
     *     Returns integer part and fractional part after dividing {@code this} by doubleright0
     *     Returns MyFraction[] {  integer part , fractional part  }
     *
     * @param right0
     *     double right0
     * @return 
     *     MyFraction[]
     */ 
    public MyFraction[] divRem(double right0) {
        MyFraction right = new MyFraction(right0);
        return this.divRem(right);
    }


    /**
     * Return the copy of {@code this} MyFraction
     *
     * @return  The copy of {@code this} MyFraction
     *
     * @since 1.0.3
     */ 
    public MyFraction copy() {
        MyFraction z = new MyFraction(this.num, this.den);
        return z;
    }
    
    
    /**
     * Return the clone of {@code this} MyFraction
     *
     * @return  The clone of {@code this} MyFraction
     *
     * @since 1.0.3
     */ 
    public MyFraction clone() {
        MyFraction z = new MyFraction(this.num, this.den);
        return z;
    }
    
    
    /**
     * Return the negation  of {@code this}) MyFraction
     *
     * @return  The negation of {@code this} MyFraction
     *
     * @since 1.0.3
     */ 
    public MyFraction negate() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.num.signum() == 0) {
            return new MyFraction(BigInteger.ZERO, this.den.negate());
        }
        else if (this.den.signum() == 0) {
            return new MyFraction(this.num.negate(), BigInteger.ZERO);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        
        if (b.signum() < 0)
        {
             a = a.negate();
             b = b.negate();
        }

        MyFraction z = new MyFraction(a.negate(), b);
        return z;
    }


    /**
     *     Returns the absolue value of {@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction abs() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        
        if (a.signum() < 0)
        {
             a = a.negate();
        }
        if (b.signum() < 0)
        {
             b = b.negate();
        }

        MyFraction z = new MyFraction(a, b);
        return z;
    }


    /**
     *     Inverting {@code this}
     *     Returns 1 / {@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction invert() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.num.signum() == 0) {
            return new MyFraction(this.den, BigInteger.ZERO);
        }
        else if (this.den.signum() == 0) {
            return new MyFraction(BigInteger.ZERO, this.num);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;
        
        if (a.signum() < 0)
        {
             a = a.negate();
             b = b.negate();
        }

        MyFraction z = new MyFraction(b, a);
        return z;
    }


    /**
     *     Integer part, after performing Euclidean diviision with numerator and denominator
     *     Returns integer part of {@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction intPart() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isInfinity()) {
            return new MyFraction(this.num, this.den);
        }
        else if (this.isNegativeZero()) {
            return new MyFraction(0, -1);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;

        BigInteger q = a.divide(b) ;
        BigInteger r = a.subtract(b.multiply(q));
        
        if (r.signum() < 0)
        {
        	q = q.subtract(BigInteger.ONE);
        	if (b.signum() > 0)
         	    r = r.add(b);
        	else if (b.signum() < 0)
         	    r = r.subtract(b);
        }

        MyFraction z = new MyFraction(q, BigInteger.ONE);
        
        return z;
    }


    /**
     *     Fractionalr part, after subtractng integr part from {@literal this} MyFractioon.
     *
     * @return   MyFraction    Returns fractionalpart of {@code this}
     */ 
    public MyFraction fracPart() {
        if (this.isNaN()) {
            throw new ArithmeticException(String.format("Fail to get fractional part of %s", toString()));
        }
        else if (this.isInfinity()) {
            if (this.isNegative()) {
                return new MyFraction(0, -1);
            }
            return new MyFraction(0, 1);
        }
        else if (this.isZero()) {
            return new MyFraction(0, 1);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;

        BigInteger q = a.divide(b) ;
        BigInteger r = a.subtract(b.multiply(q));
        
        if (r.signum() < 0)
        {
        	q = q.subtract(BigInteger.ONE);
        	if (b.signum() > 0)
         	    r = r.add(b);
        	else if (b.signum() < 0)
         	    r = r.subtract(b);
        }

        MyFraction z = new MyFraction(r, b);
        
        return z;
    }


    /**
     *     Flooring {@code this}
     *     Returns flooring of {@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction floor() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isInfinity()) {
            return new MyFraction(this.num, this.den);
        }
        else if (this.isNegativeZero()) {
            return new MyFraction(0, -1);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;

        BigInteger q = a.divide(b);
        BigInteger r = a.subtract(b.multiply(q));
        
        if (r.signum() < 0)
        {
        	q = q.subtract(BigInteger.ONE);
        	if (b.signum() > 0)
         	    r = r.add(b);
        	else if (b.signum() < 0)
         	    r = r.subtract(b);
        }

        MyFraction z = new MyFraction(q, BigInteger.ONE);
        
        return z;
    }


    /**
     *     Ceiling {@code this}
     *     Returns ceiling of {@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction ceil() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isInfinity()) {
            return new MyFraction(this.num, this.den);
        }
        else if (this.isNegativeZero()) {
            return new MyFraction(0, -1);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;

        BigInteger q = a.divide(b) ;
        BigInteger r = a.subtract(b.multiply(q));
        
        if (r.signum() < 0)
        {
        	q = q.subtract(BigInteger.ONE);
        	if (b.signum() > 0)
         	    r = r.add(b);
        	else if (b.signum() < 0)
         	    r = r.subtract(b);
        }
        
        if (r.signum() > 0)
        {
            q = q.add(BigInteger.ONE);
        }

        MyFraction z = new MyFraction(q, BigInteger.ONE);
        
        return z;
    }


    /**
     *     Rrounding {@code this}
     *     Returns rounding of {@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction round() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isInfinity()) {
            return new MyFraction(this.num, this.den);
        }
        else if (this.isNegativeZero()) {
            return new MyFraction(0, -1);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;

        BigInteger q = a.divide(b) ;
        BigInteger r = a.subtract(b.multiply(q));
        
        if (r.signum() < 0)
        {
        	q = q.subtract(BigInteger.ONE);
        	if (b.signum() > 0)
         	    r = r.add(b);
        	else if (b.signum() < 0)
         	    r = r.subtract(b);
        }
        
        if (r.multiply(BigInteger.TWO).compareTo(b) >= 0 && b.signum() > 0)
        {
            q = q.add(BigInteger.ONE);
        }
        else if (r.multiply(BigInteger.TWO).compareTo(b.negate()) >= 0 && b.signum() < 0)
        {
            q = q.add(BigInteger.ONE);
        }

        MyFraction z = new MyFraction(q, BigInteger.ONE);
        /// MyFraction rem = this.subtract(z.multiply(right));
        
        return z;
    }


    /**
     *     Truncating {@code this}
     *     Returns truncating of {@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction trunc() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }

        if (this.isInfinity()) {
            return new MyFraction(this.num, this.den);
        }
        else if (this.isNegativeZero()) {
            return new MyFraction(0, -1);
        }

        BigInteger a = this.num;
        BigInteger b = this.den;

        BigInteger q = a.divide(b);
        
        MyFraction z = new MyFraction(q, BigInteger.ONE);
        
        return z;
    }


    /**
     *     Squaring {@code this}
     *     Returns {@code this}*{@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction square() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }
    	 
        if (this.isInfinity()) {
            return new MyFraction(1, 0);
        }
        else if (this.isZero()) {
            return new MyFraction(0, 1);
        }
    	 
        BigInteger a = this.num.multiply(this.num);
        BigInteger b = this.den.multiply(this.den);
        return new MyFraction(a, b);
    }


    /**
     *     Cubing {@code this}
     *     Returns {@code this}*{@code this}*{@code this}
     *
     * @return 
     *     MyFraction
     */ 
    public MyFraction cube() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }
    	 
        if (this.isPositiveInfinity()) {
            return new MyFraction(1, 0);
        }
        else if (this.isNegativeInfinity()) {
            return new MyFraction(-1, 0);
        }
        else if (this.isPositiveZero()) {
            return new MyFraction(0, 1);
        }
        else if (this.isNegativeZero()) {
            return new MyFraction(0, -1);
        }
    	 
        BigInteger a = this.num.multiply(this.num).multiply(this.num);
        BigInteger b = this.den.multiply(this.den).multiply(this.den);
        return new MyFraction(a, b);
    }


    /**
     *     Sqrt of {@code this}
     *     Returns sqrt({@code this})
     *
     * @return    Squse root of {@code this}
     *
     * @since 1.0.3
     */ 
    public MyFraction sqrt() {
        if (this.isNaN() || this.isNegative()) {
            return new MyFraction(0, 0);
        }
    	 
        if (this.isInfinity()) {
            return new MyFraction(1, 0);
        }
        else if (this.isZero()) {
            return new MyFraction(0, 1);
        }
    	 
    	 BigInteger m1 = BigInteger.TEN.pow(100); 
    	 BigInteger m2 = BigInteger.TEN.pow(50); 
        BigInteger a = this.num.multiply(this.den).multiply(m1);
        a = a.sqrt();
        BigInteger b = this.den.multiply(m2);
        return new MyFraction(a, b);
    }


    /**
     *     Calculate the {@code n}-th root of {@code a}
     *     Returns root({@code a}, {@code n})
     *
     * @param   a      Given intger, of type BigInteger
     * @param   n      {@code n} for {@code n}-th root of {@code a}
     *
     * @return    The {@code n}-th root of {@code a}
     * @throws    ArithmeticException    Throws ArithmeticException 
     *                                                  if {@code a} is negative and {@code n} is even.
     *
     * @since 1.0.3
     */ 
      public static BigInteger newtonIterateNthRoot(BigInteger a, int n) 
      {
      	       if (a.signum() < 0 && n % 2 == 0)
      	       {
      	             /// throw new RuntimeException(String.format("Fail to calculate nthRoot(%d, %d) since %d is negative and %d is even.\n", a, n, a, n));
      	             throw new ArithmeticException(String.format("Fail to calculate nthRoot(%d, %d) since %d is negative and %d is even.\n", a, n, a, n));
      	       }
      	       
      	       BigInteger x1 = a;
      	       BigInteger x = x1;
      	       if (x.signum() < 0)
      	       {
      	            x = x.negate();
      	       }
      	       else if (x.signum() == 0)
      	       {
         	       if (n == 0)
      	             {
      	                  return BigInteger.ONE;
      	              }
         	       else if (n > 0)
      	             {
      	                  return BigInteger.ZERO;
      	              }
         	       else if (n< 0)
      	             {
      	                  throw new RuntimeException(String.format("Invalid (%d)-root", n));
      	              }
      	       }
      	       else if (a.compareTo(BigInteger.ONE) == 0)
      	       {
      	              return BigInteger.ONE;
      	       }
      	       else if (a.compareTo(BigInteger.ONE.negate()) == 0)
      	       {
         	       if ((n % 2) == 0)
      	             {
      	                  return BigInteger.ONE;
      	              }
         	       else
      	             {
      	                  return BigInteger.ONE.negate();
      	              }
      	       }
      	       
      	       BigInteger y = BigInteger.ZERO;


      	       if (n < 0)
      	       {
      	             return BigInteger.ZERO;
      	       }
      	       else if (n == 0)
      	       {
      	             return BigInteger.ONE;
      	       }
      	       else if (n == 1)
      	       {
      	             return a;
      	       }
      	       
      	       BigInteger absA = a;
      	       int sign = 1;
      	       if (absA.signum() < 0)
      	       {
      	            absA = absA.negate();
      	            sign = - sign;
      	       }
      	       
      	              System.out.printf("          a = %d\n",a);
      	              System.out.printf("         absA1 = %d\n",absA);
      	       	   
      	       int nrepeat = 100000000;
      	       int counter  = 0;
      	       
      	       BigInteger bigN1 = BigInteger.valueOf(n - 1);
      	       BigInteger bigN = BigInteger.valueOf(n);
      	       
      	       BigInteger x0 = absA;     /// x.divide(bigN1);
      	       x = x0;

      	      while (counter++ < nrepeat)
      	      {
      	             y = x.multiply(bigN1).add( absA.divide(x.pow(n - 1)) );
      	             y = y.divide(bigN);
      	             if (y.subtract(x).abs().compareTo(BigInteger.ONE) <= 0)
      	             {
      	                   break;
       	       }
      	             x = y;
      	             
      	      }
      	     
      	     if (sign < 0)
      	     {
      	           y = y.negate();
      	     }
      	     
      	     return y;
    }


    /**
     *     Calculate the {@code p/q}-th root of {@code a}
     *     Returns root({@code a}, {@code p/q})
     *
     * @param   a      Given intger, of type BigInteger
     * @param   p      Numerator of exponent
     * @param   q      Denomiator of exponent
     *
     * @return     The {@code p/q}-th root of {@code a}
     * @throws    ArithmeticException    Throws ArithmeticException 
     *                                                  if {@code a} is negative and {@code q} is even.
     *
     * @since 1.0.3
     */ 
    public static BigInteger newtonIterateRationalRoot(BigInteger a, int p, int q) 
    {
        if (a.signum() < 0 && q % 2 == 0)
        {
            throw new ArithmeticException(String.format("Fail to calculate root(%d, %d/%d) since %d is negative and %d is even.\n", a, p, q, a, q));
        }
      	       
        if (q < 0)
        {
            p = -p;
            q = -q;
        }

        if (p == 0)
        {
            return BigInteger.ONE;
        }
        else if (p < 0)
        {
            return BigInteger.ZERO;
        }
      	       
        int n = p / q;
        int r = p - (q*n);
        if (r < 0)
        {
            n = n - 1;
            r = r + q;
        }
      	       
        BigInteger big1 = BigInteger.ONE;;
        BigInteger big2 = BigInteger.ONE;;

        if (n > 0)
        {
            big1 = a.pow(n);
        }
        else if (n < 0)
        {
            return BigInteger.ZERO;
        }
      	       
         if (r != 0)
        {
            BigInteger b1 = newtonIterateNthRoot(a, q);
            big2 = b1.pow(r);

        }
      	       
        BigInteger bigN = big1.multiply(big2);
        return bigN;
     }
	  
	  
    /**
     *     Calculate the (@code n}-th root of the ration {@code a} / {@code b}
     *     Returns root({@code a}/{@code b}, {@code n})
     *
     * @param   a      Numerator of ratio
     * @param   b      Denominator of ratio
     * @param   n      {@code n} for {@code n}=root
     *
     * @return    The {@code n}-th root of the ration {@code a} / {@code b}
     * @throws    ArithmeticException    Throws ArithmeticException 
     *                                                  if {@code a} is negative and n{@code } is even.
     *
     * @since 1.0.3
     */ 
    public static BigInteger newtonIterateNthRootOfRatio(BigInteger a, BigInteger b, int n) 
    {
        if (n == 0)
        {
            return BigInteger.ONE;
        }
        else if (n < 0)
        {
            return newtonIterateNthRootOfRatio(b, a, -n);
        }

        BigInteger c = a.divide(b);
        if (c.signum() == 0)  
        {
             return BigInteger.ZERO;
        }
        else if (c.signum() < 0 && n % 2 == 0)  
        {
            throw new ArithmeticException(String.format("Fail to calculate nthRoot(%d, %d) since %d is negative and %d is even.\n", c, n, c, n));
        }
      	        
        int sign = 1;
        if (c.signum() < 0)  
        {
            c = c.negate();
            sign = -sign;
        }
      	       
        BigInteger y = newtonIterateNthRoot(c, n);
      	       
        if (sign < 0)
        {
            y = y.negate();
        }
      	       
        return y;
    }


    /**
     *     Calculate the {@code p/q}-th root of {@code this}
     *     Returns root({@code this}, {@code p/q})
     *
     * @param   p      Numerator of exponent
     * @param   q      Denomiator of exponent
     *
     * @return     The {@code p/q}-th root of {@code this}
     * @throws    ArithmeticException    Throws ArithmeticException 
     *                                                  if {@code this} is negative and {@code q} is even.
     *
     * @since 1.0.3
     */ 
    public MyFraction newtonRationalRoot(int p, int q) 
    {
        if (isNegative() && q % 2 == 0)
        {
      	             /// throw new RuntimeException(String.format("Fail to calculate nthRoot(%d, %d) since %d is negative and %d is even.\n", a, n, a, n));
            throw new ArithmeticException(String.format("Fail to calculate root(%s, %d/%d) since %s is negative and %d is even.\n", this, p, q, this, q));
        }
      	       
        if (q < 0)
        {
            p = -p;
            q = -q;
        }
      	       
      	       
        if (p == 0)
        {
            return new MyFraction(1, 1);
        }
        else if (p < 0)
        {
            MyFraction z1 = newtonRationalRoot(-p, q);
            return z1.invert();
      	                // return BigInteger.ZERO;
        }
      	       
        MyFraction f = this.copy();
        int sign = 1;
        if (f.isNegative())
        {
            f = f.negate();
            sign = -sign;
        }
      	       
        boolean invert_flag = false;
        if (f.compareTo(MyFraction.ONE) < 0)
        {
      	             f = f.invert();
      	             invert_flag = true;
        }
      	       
        int n = p / q;
        int r = p - (q * n);
      	       
        BigInteger bigA1 = f.num.pow(n);
        BigInteger bigA2 = f.den.pow(n + 1);
      	       
        int ne1 = 1;
        int ne2 = 1;
        if (4L*(long)(n+q)*(long)q <= 0x7FFFFFFFL)
        {
            ne1 = 4*(n+q+1)*q;
            ne2 = 4*(n+q+1);
        }
        else if (4L*(long)(n+q)*(long)q <= 0x7FFFFFFFL)
        {
            ne1 = 4*(n+q)*q;
            ne2 = 4*(n+q);
        }
        else if (4L*(long)(n+2)*(long)q <= 0x7FFFFFFFL)
        {
            ne1 = 4*(n+2)*q;
            ne2 = 4*(n+2);
        }
        else if (2L*(long)(n+2)*(long)q <= 0x7FFFFFFFL)
        {
            ne1 = 2*(n+2)*q;
            ne2 = 2*(n+2);
        }
        else if (2L*(long)(n+1)*(long)q <= 0x7FFFFFFFL)
        {
            ne1 = 2*(n+1)*q;
            ne2 = 2*(n+1) ;
        }
        else if (1L*(long)(n+1)*(long)q <= 0x7FFFFFFFL)
        {
             ne1 = (n+1)*q;
             ne2 = (n+1);
        }
        else if (1L*(long)n*(long)q <= 0x7FFFFFFFL)
        {
            ne1 = n*q;
            ne2 = n;
        }
        else if (3L*(long)q <= 0x7FFFFFFFL)
        {
            ne1 = 3*q;
            ne2 = 3;
        }
        else if (2L*(long)q < 0x7FFFFFFFL)
        {
            ne1 = 2*q;
            ne2 = 2;
        }
        else if (3L*(long)q < 0x7FFFFFFFL)
        {
            ne1 = q;
            ne2 = 1;
        }
      	       
        BigInteger bigB1 = BigInteger.TEN.pow(ne1);      /// bigA1.pow(q);
        bigB1 = bigB1.multiply(f.den.pow(q - r).multiply(f.num.pow(r)));
        bigB1 = newtonIterateNthRoot(bigB1, q);
      	       
        bigB1 = bigB1.multiply(bigA1);
        bigA2 = bigA2.multiply(BigInteger.TEN.pow(ne2));
      	       
        MyFraction f2 = new MyFraction(bigB1, bigA2);
      	       
        if (invert_flag)
        {
            f2 = f2.invert();
        }

        if (sign < 0 && (p % 2) == 1)
        {
            f2 = f2.negate();
        }
      	       
        return f2;
    }


    /**
     *     Calculate the {@code p/q}-th root of {@code this}
     *     Returns root({@code this}, {@code p/q})
     *
     * @param   p      Numerator of exponent
     * @param   q      Denomiator of exponent
     *
     * @return     The {@code p/q}-th root of {@code this}
     * @throws    ArithmeticException    Throws ArithmeticException 
     *                                                  if {@code this} is negative and {@code q} is even.
     *
     * @since 1.0.3
     */ 
    public MyFraction root(int p, int q) 
    {
        if (isNegative() && q % 2 == 0)
       {
      	             /// throw new RuntimeException(String.format("Fail to calculate nthRoot(%d, %d) since %d is negative and %d is even.\n", a, n, a, n));
            throw new ArithmeticException(String.format("Fail to calculate root(%s, %d/%d) since %s is negative and %d is even.\n", this, p, q, this, q));
       }
      	       
       if (q < 0)
       {
            p = -p;
            q = -q;
       }
      	       
      	       
       if (p == 0)
       {
           return new MyFraction(1, 1);
       }
       else if (p < 0)
       {
           MyFraction z1 = newtonRationalRoot(-p, q);
           return z1.invert();
      	                // return BigInteger.ZERO;
       }
      	       
       MyFraction f = this.copy();
       int sign = 1;
       if (f.isNegative())
       {
      	            f = f.negate();
      	            sign = -sign;
       }
      	       
       boolean invert_flag = false;
       if (f.compareTo(MyFraction.ONE) < 0)
       {
           f = f.invert();
           invert_flag = true;
       }
      	       
       int n = p / q;
       int r = p - (q * n);
      	       
       BigInteger bigA1 = f.num.pow(n);
       BigInteger bigA2 = f.den.pow(n + 1);

       int ne1 = 1;
       int ne2 = 1;
       if (4L*(long)(n+q)*(long)q <= 0x7FFFFFFFL)
       {
           ne1 = 4*(n+q+1)*q;
           ne2 = 4*(n+q+1);
       }
       else if (4L*(long)(n+q)*(long)q <= 0x7FFFFFFFL)
       {
            ne1 = 4*(n+q)*q;
           ne2 = 4*(n+q);
       }
       else if (4L*(long)(n+2)*(long)q <= 0x7FFFFFFFL)
       {
           ne1 = 4*(n+2)*q;
           ne2 = 4*(n+2);
       }
       else if (2L*(long)(n+2)*(long)q <= 0x7FFFFFFFL)
       {
            ne1 = 2*(n+2)*q;
           ne2 = 2*(n+2);
       }
       else if (2L*(long)(n+1)*(long)q <= 0x7FFFFFFFL)
       {
           ne1 = 2*(n+1)*q;
           ne2 = 2*(n+1) ;
       }
       else if (1L*(long)(n+1)*(long)q <= 0x7FFFFFFFL)
       {
           ne1 = (n+1)*q;
           ne2 = (n+1);
       }
       else if (1L*(long)n*(long)q <= 0x7FFFFFFFL)
       {
           ne1 = n*q;
           ne2 = n;
       }
       else if (3L*(long)q <= 0x7FFFFFFFL)
       {
           ne1 = 3*q;
           ne2 = 3;
       }
       else if (2L*(long)q < 0x7FFFFFFFL)
       {
           ne1 = 2*q;
           ne2 = 2;
       }
       else if (3L*(long)q < 0x7FFFFFFFL)
       {
           ne1 = q;
           ne2 = 1;
       }
      	       
       BigInteger bigB1 = BigInteger.TEN.pow(ne1);      /// bigA1.pow(q);
       bigB1 = bigB1.multiply(f.den.pow(q - r).multiply(f.num.pow(r)));
       bigB1 = newtonIterateNthRoot(bigB1, q);
      	       
       bigB1 = bigB1.multiply(bigA1);
       bigA2 = bigA2.multiply(BigInteger.TEN.pow(ne2));

       MyFraction f2 = new MyFraction(bigB1, bigA2);
      	       
       if (invert_flag)
       {
            f2 = f2.invert();
       }

        if (sign < 0 && (p % 2) == 1)
        {
            f2 = f2.negate();

        return f2;
    }


    /**
     *     Cbrt of {@code this}
     *     Returns cbrt({@code this})
     *
     * @return    Cube root of {@code this}
     *
     * @since 1.0.3
     */ 
    public MyFraction cbrt() {
        if (this.isNaN()) {
            return new MyFraction(0, 0);   // NaN of MyFraction
        }
    	 
        MyFraction f = this.copy();

        if (f.isInfinity()) {
            if (f.isNegative())
            {
                  return new MyFraction(-1, 0);
            }
            return new MyFraction(1, 0);
        }
        else if (this.isZero()) {
            return new MyFraction(0, 1);
        }
    	 
        BigInteger m1 = BigInteger.TEN.pow(150); 
        BigInteger m2 = BigInteger.TEN.pow(50); 
        BigInteger a = this.num.multiply(this.den.multiply(this.den)).multiply(m1);
        BigInteger b = this.den;
        
        BigInteger c = newtonIterateNthRootOfRatio(this.num, this.den, 3);
        
        return new MyFraction(c, BigInteger.ONE);
    }


    /**
     *     Integer powering of base {@code this} by exponent int n0
     *     Returns {@code this}**n0
     *
     * @param n0
     *     int n0 : exponent
     * @return 
     *     MyFraction
     */ 
    public MyFraction pow(int n0) {
        if (this.isNaN()) {
            return new MyFraction(0, 0);
        }
    	 
        if (n0 == 0)
        {
            return new MyFraction(1, 1);
        }
        else if (n0 == 1) 
        {
            return new MyFraction(this.num, this.den);
        }
        else if (n0 < 0)
        {
            MyFraction z = this.pow(-n0);
            return new MyFraction(z.den, z.num);
        }
    	 
        BigInteger a = mPow(this.num, n0);
        BigInteger b = mPow(this.den, 0);
        return new MyFraction(a, b);
    }


    /**
     *  Returns native {@code double} value, converted from {@code this} MyFraction
     *
     * @return    Exactly converted {@code double} value 
     * @throws   ArithmeticException  Throws an exception if its converted value is not equat to original @{code this}.
     *
     * @since 1.0.3
     */
    public double doubleValueExact() {
        if (isNaN())
        {
            return Double.NaN;
        }
        else if (isInfinity())
        {
              if (isNegative())
              {
                   return Double.NEGATIVE_INFINITY;
              };
              return Double.POSITIVE_INFINITY;
        }
        else if (isZero())
        {
              if (isNegativeZero())
              {
                   return -0.0;
              }
              return 0.0;
        }

        MyFraction q = floor();
        MyFraction y = this.subtract(q);
        y = y.invert();
        MyFraction q2 = y.floor();
        MyFraction y2 = y.subtract(q2);
        
        double x2 = y2.doubleValue();
        x2 = x2 + q2.doubleValue();
        double z = q.doubleValue() + 1.0/x2;
        
        if (this.compareTo(new MyFraction(z)) != 0)
        {
               throw new ArithmeticException(String.format("Exception since the converted va;ue %s != %s",new MyFraction(z), toString()));
        }
        
        return z;
    }

    /**
     *  Returns native {@literal double} value of {@literal this} MyFraction
     *
     * @return   double
     *
     * @since 1.0.3
     */
    public double toDouble() {
        if (isNaN()) {
            return Double.NaN;
        }
        else if (isNegativeZero()) {
            return -0.0;
        }
        else if (isPositiveZero()) {
            return 0.0;
        }
        else if (isNegativeInfinity()) {
            return -1.0/0.0;
        }
        else if (isPositiveInfinity()) {
            return 1.0/0.0;
        }
        
        BigInteger q = num.divide(den);
        BigInteger r = num.subtract(den.multiply(q));
        if (r.signum() < 0)
        {
            q = q.subtract(den);
            r = r.add(den);
        }
        
        double q1 = q.doubleValue();
        
        BigInteger b1 = One.shiftLeft(53);
        BigInteger den1 = den;
        BigInteger r1 = r;
        if (den1.compareTo(b1) > 0);
        {
            den1 = den1.divide(b1);
            r1 = r1.divide(b1);
        }
        
        BigInteger c1 = r1.multiply(b1).divide(den1);
        String s1 = String.format("%d", c1);
        double d1 = Double.parseDouble(s1);
        d1 = d1 / Math.pow(2.0, 53);
        double y = q1 + d1;
        
        return y;
    }


    /**
     *  Returns native {@literal double} value of {@literal this} MyFraction
     *
     * @return   double
     *
     * @since 1.0.3
     */
    @Override
    public double doubleValue() {
        if (isNaN()) {
            return Double.NaN;
        }
        else if (isNegativeZero()) {
            return -0.0;
        }
        else if (isPositiveZero()) {
            return 0.0;
        }
        else if (isNegativeInfinity()) {
            return -1.0/0.0;
        }
        else if (isPositiveInfinity()) {
            return 1.0/0.0;
        }
        
        BigInteger q = num.divide(den);
        BigInteger r = num.subtract(den.multiply(q));
        if (r.signum() < 0)
        {
            q = q.subtract(den);
            r = r.add(den);
        }
        
        double q1 = q.doubleValue();
        
        BigInteger b1 = One.shiftLeft(53);
        BigInteger den1 = den;
        BigInteger r1 = r;
        if (den1.compareTo(b1) > 0);
        {
            den1 = den1.divide(b1);
            r1 = r1.divide(b1);
        }
        
        BigInteger c1 = r1.multiply(b1).divide(den1);
        String s1 = String.format("%d", c1);
        double d1 = Double.parseDouble(s1);
        d1 = d1 / Math.pow(2.0, 53);
        double y = q1 + d1;
        
        return y;
    }


    /**
     *  Returns native {@literal float} value of {@literal this} MyFraction
     *
     * @return   float
     *
     * @since 1.0.3
     */
    @Override
    public float floatValue() {
        if (isNaN()) {
            return Float.NaN;
        }
        else if (isNegativeZero()) {
            return -0.0f;
        }
        else if (isPositiveZero()) {
            return 0.0f;
        }
        else if (isNegativeInfinity()) {
            return -1.0f/0.0f;
        }
        else if (isPositiveInfinity()) {
            return 1.0f/0.0f;
        }
        
        BigInteger q = num.divide(den);
        BigInteger r = num.subtract(den.multiply(q));
        if (r.signum() < 0)
        {
            q = q.subtract(den);
            r = r.add(den);
        }
        
        float q1 = q.floatValue();
        
        BigInteger b1 = One.shiftLeft(25);
        BigInteger den1 = den;
        BigInteger r1 = r;
        if (den1.compareTo(b1) > 0);
        {
            den1 = den1.divide(b1);
            r1 = r1.divide(b1);
        }
        
        BigInteger c1 = r1.multiply(b1).divide(den1);
        String s1 = String.format("%d", c1);
        float d1 = Float.parseFloat(s1);
        d1 = (float)((double) d1 / Math.pow(2.0, 25));
        float y = q1 + d1;
        
        return y;
    }


    /**
     *  Returns native {@code float} value, converted from {@code this} MyFraction
     *
     * @return    Exactly onverted {@code float} value 
     * @throws   ArithmeticException  Throws exception if its converted value is not equat to original @{code this}.
     *
     * @since 1.0.3
     */
    public float floatValueExact() {
        if (isNaN())
        {
            return Float.NaN;
        }
        else if (isInfinity())
        {
              if (isNegative())
              {
                   return Float.NEGATIVE_INFINITY;
              };
              return Float.POSITIVE_INFINITY;
        }
        else if (isZero())
        {
              if (isNegativeZero())
              {
                   return -0.0f;
              }
              return 0.0f;
        }

        
        MyFraction q = floor();
        MyFraction y = this.subtract(q);
        y = y.invert();
        MyFraction q2 = y.floor();
        MyFraction y2 = y.subtract(q2);
        
        double x2 = y2.doubleValue();
        x2 = x2 + q2.doubleValue();
        double z = q.doubleValue() + 1.0/x2;
        
        float z2 = (float) z;
        
        if (this.compareTo(new MyFraction(z2)) != 0)
        {
               throw new ArithmeticException(String.format("Exception since its converted va;ue %s != %s",new MyFraction(z2), toString()));
        }
        
        return z2;
    }


    /**
     *  Returns native {@literal float} value of {@literal this} MyFraction
     *
     * @return   float
     *
     * @since 1.0.3
     */
    public float toFloat() {
        if (isNaN()) {
            return Float.NaN;
        }
        else if (isNegativeZero()) {
            return -0.0f;
        }
        else if (isPositiveZero()) {
            return 0.0f;
        }
        else if (isNegativeInfinity()) {
            return -1.0f/0.0f;
        }
        else if (isPositiveInfinity()) {
            return 1.0f/0.0f;
        }
        
        BigInteger q = num.divide(den);
        BigInteger r = num.subtract(den.multiply(q));
        if (r.signum() < 0)
        {
            q = q.subtract(den);
            r = r.add(den);
        }
        
        float q1 = q.floatValue();
        
        BigInteger b1 = One.shiftLeft(25);
        BigInteger den1 = den;
        BigInteger r1 = r;
        if (den1.compareTo(b1) > 0);
        {
            den1 = den1.divide(b1);
            r1 = r1.divide(b1);
        }
        
        BigInteger c1 = r1.multiply(b1).divide(den1);
        String s1 = String.format("%d", c1);
        float d1 = Float.parseFloat(s1);
        d1 = (float)((double) d1 / Math.pow(2.0, 25));
        float y = q1 + d1;
        
        return y;
    }


    /**
     *  Returns native {@literal long} value of {@literal this} MyFraction
     *
     * @return   long
     *
     * @since 1.0.3
     */
    @Override
    public long longValue() {
        MyFraction q = trunc();
        BigInteger x = q.num;
        long y = x.longValueExact();
        return y;
    }


    /**
     *  Returns native {@literal long} value of {@literal this} MyFraction
     *
     * @return   long
     *
     * @since 1.0.3
     */
    public long toLong() {
        MyFraction q = trunc();
        BigInteger x = q.num;
        long y = x.longValueExact();
        return y;
    }


    /**
     *  Returns native {@code long} value, converted from {@code this} MyFraction
     *
     * @return    Exactly onverted {@code long} value 
     * @throws   ArithmeticException  Throws exception if its converted value is not equat to original @{code this}.
     *
     * @since 1.0.3
     */
    public long longValueExact() {
        if (! isZero())
        {
            return 0L;
        }

        if (! fracPart().isZero())
        {
            throw new ArithmeticException(String.format("Exception while longValueExact() with %s", toString()));
        }
        
        MyFraction q = trunc();
        BigInteger x = q.num;
        if (x.compareTo(BigInteger.ONE.shiftLeft(63)) >= 0 || x.compareTo(BigInteger.ONE.shiftLeft(63).negate()) < 0)
        {
               throw new ArithmeticException(String.format("Exception while longValueExac() with %s", toString()));
        }

        long y = x.longValueExact();
        
        if (this.compareTo(new MyFraction(y)) != 0)
        {
               throw new ArithmeticException(String.format("Exception since thw converted va;ue %a != %s",new MyFraction(y), toString()));
        }
        
        return y;
    }


    /**
     *  Returns native {@literal long} value of {@literal this} MyFraction
     *
     * @return   long
     * @throws   ArithmeticException  Throws exception if ita converted value is not equat to @{code this}.
     *
     * @since 1.0.3
     */
    public long toLongExact() {
        if (! isZero())
        {
            return 0L;
        }

        if (! fracPart().isZero())
        {
            throw new ArithmeticException(String.format("Exception while toLongExac() with %s", toString()));
        }
        
        MyFraction q = trunc();
        BigInteger x = q.num;
        if (x.compareTo(BigInteger.ONE.shiftLeft(63)) >= 0 || x.compareTo(BigInteger.ONE.shiftLeft(63).negate()) < 0)
        {
            throw new ArithmeticException(String.format("Exception while toLongExac() with %s", toString()));
        }

        long y = x.longValueExact();
        if (this.compareTo(new MyFraction(y)) != 0)
        {
               throw new ArithmeticException(String.format("Exception since thw converted va;ue %a != %s",new MyFraction(y), toString()));
        }
        
        return y;
    }


    /**
     *  Returns native {@literal int} value of {@literal this} MyFraction
     *
     * @return   long
     *
     * @since 1.0.3
     */
    @Override
    public int intValue() {
        MyFraction q = trunc();
        BigInteger x = q.num;
        int y = x.intValueExact();
        return y;
    }


    /**
     *  Returns native {@code int} value, converted from {@code this} MyFraction
     *
     * @return    Exactly onverted {@code int} value 
     * @throws   ArithmeticException  Throws exception if its converted value is not equat to original @{code this}.
     *
     * @since 1.0.3
     */
    public int intValueExact() {
        if (! isZero())
        {
            return 0;
        }

        if (! fracPart().isZero())
        {
            throw new ArithmeticException(String.format("Exception while intValueExact() with %s", toString()));
        }
        
        MyFraction q = trunc();
        BigInteger x = q.num;
        if (x.compareTo(BigInteger.ONE.shiftLeft(31)) >= 0 || x.compareTo(BigInteger.ONE.shiftLeft(31).negate()) < 0)
        {
               throw new ArithmeticException(String.format("Exception while longValueExac() with %s", toString()));
        }

        int y = x.intValue();
        
        if (this.compareTo(new MyFraction(y)) != 0)
        {
               throw new ArithmeticException(String.format("Exception since the converted va;ue %a != %s",new MyFraction(y), toString()));
        }
        
        return y;
    }
    

    /**
     *  Returns native {@literal int} value of {@literal this} MyFraction
     *
     * @return   int
     *
     * @since 1.0.3
     */
    public int toInt() {
        MyFraction q = trunc();
        BigInteger x = q.num;
        int y = x.intValueExact();
        return y;
    }


    /**
     *  Returns native {@literal int} value of {@literal this} MyFraction
     *
     * @return   int
     *
     * @since 1.0.3
     */
    public int toIntExact() {
        if (! isZero())
        {
            return 0;
        }

        if (! fracPart().isZero())
        {
            throw new ArithmeticException(String.format("Exception while toLongExac() with %s", toString()));
        }
        
        MyFraction q = trunc();
        BigInteger x = q.num;
        if (x.compareTo(BigInteger.ONE.shiftLeft(15)) >= 0 || x.compareTo(BigInteger.ONE.shiftLeft(15).negate()) < 0)
        {
            throw new ArithmeticException(String.format("Exception while toLongExac() with %s", toString()));
        }

        int y = x.intValueExact();
        return y;
    }


    /**
     *  Returns native {@code short} value, converted from {@code this} MyFraction
     *
     * @return    Exactly onverted {@code short} value 
     * @throws   ArithmeticException  Throws exception if its converted value is not equat to original @{code this}.
     *
     * @since 1.0.3
     */
    public short shortValueExact() {
        if (! isZero())
        {
            return (short) 0;
        }

        if (! fracPart().isZero())
        {
            throw new ArithmeticException(String.format("Exception while shortValueExact() with %s", toString()));
        }
        
        MyFraction q = trunc();
        BigInteger x = q.num;
        if (x.compareTo(BigInteger.ONE.shiftLeft(31)) >= 0 || x.compareTo(BigInteger.ONE.shiftLeft(31).negate()) < 0)
        {
               throw new ArithmeticException(String.format("Exception while longValueExac() with %s", toString()));
        }

        short y = x.shortValue();
        
        if (this.compareTo(new MyFraction(y)) != 0)
        {
               throw new ArithmeticException(String.format("Exception since the converted va;ue %a != %s",new MyFraction(y), toString()));
        }
        
        return y;
    }


    /**
     *  Returns native {@literal short} value of {@literal this} MyFraction
     *
     * @return  short
     *
     * @since 1.0.3
     */
    @Override
    public short shortValue() {
        MyFraction q = trunc();
        BigInteger x = q.num;
        short y = x.shortValueExact();
        return y;
    }


    /**
     *  Returns native {@literal short} value of {@literal this} MyFraction
     *
     * @return  short
     *
     * @since 1.0.3
     */
    public short toShort() {
        MyFraction q = trunc();
        BigInteger x = q.num;
        short y = x.shortValueExact();
        return y;
    }


    /**
     *  Returns native {@literal short} value of {@literal this} MyFraction
     *
     * @return  short
     *
     * @since 1.0.3
     */
    public short toShortExact() {
        if (! isZero())
        {
            return (short) 0;
        }

        if (! fracPart().isZero())
        {
            throw new ArithmeticException(String.format("Exception while toLongExac() with %s", toString()));
        }
        
        MyFraction q = trunc();
        BigInteger x = q.num;
        if (x.compareTo(BigInteger.ONE.shiftLeft(15)) >= 0 || x.compareTo(BigInteger.ONE.shiftLeft(15).negate()) < 0)
        {
            throw new ArithmeticException(String.format("Exception while toLongExac() with %s", toString()));
        }

        short y = x.shortValueExact();
        return y;
    }


    /**
     *  Returns native {@code byte} value, converted from {@code this} MyFraction
     *
     * @return    Exactly onverted {@code byte} value 
     * @throws   ArithmeticException  Throws exception if its converted value is not equat to original @{code this}.
     *
     * @since 1.0.3
     */
    public byte byteValueExact() {
        if (! isZero())
        {
            return (byte) 0;
        }

        if (! fracPart().isZero())
        {
            throw new ArithmeticException(String.format("Exception while byteValueExact() with %s", toString()));
        }
        
        MyFraction q = trunc();
        BigInteger x = q.num;
        if (x.compareTo(BigInteger.ONE.shiftLeft(31)) >= 0 || x.compareTo(BigInteger.ONE.shiftLeft(31).negate()) < 0)
        {
               throw new ArithmeticException(String.format("Exception while longValueExac() with %s", toString()));
        }

        byte y = x.byteValue();
        
        if (this.compareTo(new MyFraction(y)) != 0)
        {
               throw new ArithmeticException(String.format("Exception since the converted va;ue %a != %s",new MyFraction(y), toString()));
        }
        
        return y;
    }


    /**
     *  Returns native {@literal byte} value of {@literal this} MyFraction
     *
     * @return   byte
     *
     * @since 1.0.3
     */
    @Override
    public byte byteValue() {
        MyFraction q = trunc();
        BigInteger x = q.num;
        byte y = x.byteValueExact();
        return y;
    }


    /**
     *  Returns native {@literal byte} value of {@literal this} MyFraction
     *
     * @return  byte
     *
     * @since 1.0.3
     */
    public byte toByte() {
        MyFraction q = trunc();
        BigInteger x = q.num;
        byte y = x.byteValueExact();
        return y;
    }


    /**
     *  Returns native {@literal byte} value of {@literal this} MyFraction
     *
     * @return  byte
     *
     * @since 1.0.3
     */
    public byte toByteExact() {
        if (! isZero())
        {
            return (byte) 0;
        }

        if (! fracPart().isZero())
        {
            throw new ArithmeticException(String.format("Exception while toLongExac() with %s", toString()));
        }
        
        MyFraction q = trunc();
        BigInteger x = q.num;
        if (x.compareTo(BigInteger.ONE.shiftLeft(7)) >= 0 || x.compareTo(BigInteger.ONE.shiftLeft(7).negate()) < 0)
        {
            throw new ArithmeticException(String.format("Exception while toLongExac() with %s", toString()));
        }

        byte y = x.byteValueExact();
        return y;
    }


    /**
     *  Returns {@literal BigInteger} value of truncated value of {@literal this} MyFraction
     *
     * @return   BigInteger
     *
     * @since 1.0.3
     */
    public BigInteger toBigInteger() {
        MyFraction q = trunc();
        BigInteger y = q.num;
        return y;
    }


    /**
     *  Returns {@literal BigInteger} value of truncated value of {@literal this} MyFraction
     *
     * @return   BigInteger
     *
     * @since 1.0.3
     */
    public BigInteger bigIntegerValue() {
        MyFraction q = trunc();
        BigInteger y = q.num;
        return y;
    }
    
    


    /**
     *  Returns {@literal BigDecimal} value of {@literal this} MyFraction
     *
     * @return   BigDecimal
     *
     * @since 1.0.3
     */
    public BigDecimal toBigDecimal() {
        BigDecimal num1 = new BigDecimal(num);
        BigDecimal den1 = new BigDecimal(den);
        BigDecimal y = num1.divide(den1);;
        return y;
    }


    /**
     *  Returns {@literal BigDecimal} value of {@literal this} MyFraction
     *
     * @return   BigDecimal
     *
     * @since 1.0.3
     */
    public BigDecimal bigDecimalValue() {
        BigDecimal num1 = new BigDecimal(num);
        BigDecimal den1 = new BigDecimal(den);
        BigDecimal y = num1.divide(den1);;
        return y;
    }
    
    
    /** Use serialVersionUID from JDK 1.1. for interoperability */
    private static final long serialVersionUID = 5582570670176564329L;
    
    /**
     * Write numerator and denominator through defaultWriteObjectStream
     *
     * @param outstream   Output stream for serializing object
     * @throws                   IOException     bad state outputstream
     *
     * @since 1.0.3
     */
    private void writeObject(ObjectOutputStream outstream)   throws IOException 
   {
        outstream.defaultWriteObject();
        outstream.writeObject(this.num);
        outstream.writeObject(this.den);
    }

    
    /**
     * Read numerator and denominator through defaultReadObjectStream
     *
     * @param      instream     Input stream for serializing obhect
     * @throws     ClassNotFoundException    Fail to read MyFraction
     * @throws     IOException     bad state of instream
     *
     * @since 1.0.3
     */
    private void readObject(ObjectInputStream instream)  throws ClassNotFoundException, IOException 
    {
        instream.defaultReadObject();
        this.num = (BigInteger) instream.readObject();
        this.den = (BigInteger) instream.readObject();
    }


    /**
     *     Mixed form expression
     *     Returns (a)_b/c where a is integer part of {@code this} and c is denominatior of {@code this}, and 0 &lt; b &lt; c
     *     Returns a if fractional part is zero
     *
     * @return 
     *     String
     */ 
    public String toMixedForm() {
        String s = "";
        if (this.den.signum() == 0) {
            if (this.num.signum() > 0)
                s = "Infinity";
            else if (this.num.signum() < 0)
                s = "-Infinity";
            else
                s = "NaN";
        }
        else if (this.den.compareTo(BigInteger.ONE.negate()) == 0 && this.num.signum() == 0)
            s = "-0";
        else if (this.den.compareTo(BigInteger.ONE.negate()) == 0)
            s = String.format("%d", this.num.negate());
        else if (this.den.compareTo(BigInteger.ONE) == 0)
            s = String.format("%d", this.num);
        else {
            BigInteger a = this.num;
            BigInteger b = this.den;
            if (b.signum() < 0)
            {
                a = a.negate();
                b = b.negate();
            }

            BigInteger q = a.divide(b) ;
            BigInteger r= a.subtract(b.multiply(q));
        
            if (r.signum() < 0)
            {
           	    q = q.subtract(BigInteger.ONE);
          	    if (b.signum() > 0)
         	        r = r.add(b);
          	   else if (b.signum() < 0)
         	        r = r.subtract(b);
            }
                    
            if (r.signum() != 0)
            {
                if (q.signum() < 0)
                    s += String.format("(%d)", q);
                else if (q.signum() > 0)
                    s += String.format("%d", q);
                s += String.format("_%d/%d", r, b);
            }
            else
            {
                 s += String.format("%d", q);
            }
        }

        return s;
    }


    /**
     *    Returns Decimal expression with at most 20 digits after floating point
     *
     * @return 
     *     String
     */
    public String toDecimalString() {
        int prec = 20;
        String s = "";
        if (this.den.signum() == 0) {
            if (this.num.signum() > 0)
                s = "Infinity";
            else if (this.num.signum() < 0)
                s = "-Infinity";
            else
                s = "NaN";      /// "0/0 (Indeterminated)";
            return s;
        }
        else if (this.den.compareTo(BigInteger.ONE.negate()) == 0 && this.num.signum() == 0)
        {
            s = "-0";
            return s;
        }
        else if (this.den.compareTo(BigInteger.ONE.negate()) == 0)
        {
            s = String.format("%d", this.num.negate());
            return s;
        }
        else if (this.den.compareTo(BigInteger.ONE) == 0)
        {
            s = String.format("%d", this.num);
            return s;
        }
    
    
            BigInteger a = this.num;
            BigInteger b = this.den;
            if (b.signum() < 0)
            {
                a = a.negate();
                b = b.negate();
            }
            
            int sign = 1;
            if ((a.signum()  < 0 && b.signum()  > 0) || (a.signum()  > 0 && b.signum()  < 0))
            {
            	    a = a.negate();
            	    sign = -1;
            }
            
            BigInteger q = a.divide(b);
            BigInteger r = a.subtract(b.multiply(q));
            
            if (r.signum() < 0)
            {
            	    q = q.subtract(BigInteger.ONE);
            	    r = r.add(b);
            }

            if (sign < 0)
            {
                s += "-";
            }
            
            if (r.signum() != 0)
            {
                String s2  = "";
                BigInteger t = b.add(r);
                BigInteger q1 = BigInteger.ZERO;
                for (int i = 0; i < prec + 1; i++)
                {
                    q1 = t.divide(b);
                    s2 += String.format("%d", q1);
                    t = t.subtract(b.multiply(q1));
                    t = t.multiply(BigInteger.TEN);
                }
                
                int carry = 0;
                if (sign > 0 && t.multiply(BigInteger.TWO).compareTo(b) >= 0)
                    carry = 1;
                else if (sign < 0 && t.multiply(BigInteger.TWO).compareTo(b) > 0)
                    carry = 1;

                char[] digits = s2.substring(1).toCharArray();
                int n1 = digits.length;
                char c;
                int j1 = n1 - 1;
                int i = j1;
                for ( ; i >= 0; i--)
                {
                    if (carry <= 0)
                    {
                        j1  = i;
                        break;
                    }
                    
                    c = digits[i];
                    if (c == '9')
                    {
                        digits[i] = '0';
                        carry = 1;
                    }
                    else
                    {
                        digits[i] = (char) (c + 1);
                        carry = 0;
                    }
                }
                    
                if (digits[0] == '2')
                {
                    q = q.add(BigInteger.ONE);
                }
                    
                j1 = n1;
                i = n1 - 1;
                for ( ; i >= 0; i--)
                {
                    c = digits[i];
                    if (c != '0')
                    {
                         j1 = i + 1;
                         break;
                    }
                }

                if (j1 < n1)
                {
                     s2 = s2.substring(1, 1 + j1);
                }
                else
                {
                     s2 = s2.substring(1);
                }
                s += String.format("%d.%s", q, s2);
         }
         else
         {
             s += String.format("%d", q);
         }

         return s;
    }
    
    
    /**
     *    Returns Decimal expression with maximal prec digits after floating point
     *
     * @param prec
     *    int prec :  count of digits after floating point
     * @return 
     *     String
     */ 
    public String toDecimalString(int prec) {
        String s = "";
        if (this.den.signum() == 0) {
            if (this.num.signum() > 0)
                s = "Infinity";
            else if (this.num .signum()< 0)
                s = "-Infinity";
            else
                s = "NaN";
        }
        else if (this.den.compareTo(BigInteger.ONE.negate()) == 0 && this.num.signum() == 0)
            s = "-0";
        else if (this.den.compareTo(BigInteger.ONE.negate()) == 0)
            s = String.format("%d", this.num.negate());
        else if (this.den.compareTo(BigInteger.ONE) == 0)
            s = String.format("%d", this.num);
        else {
            BigInteger a = this.num;
            BigInteger b = this.den;
            if (b.signum() < 0)
            {
                a = a.negate();
                b = b.negate();
            }
            
            int sign = 1;
            if (a.signum() < 0)
            {
            	    a = a.negate();
            	    sign = -1;
            }
            
            BigInteger q = a.divide(b);
            BigInteger r = a.subtract(b.multiply(q));

            if (sign < 0)
            {
            	   MyFraction f2 = this.negate();
                s = "-" + f2.toDecimalString(prec);
                return s;
            }
            
            if (prec <= 0)
            {
                   s += String.format("%s", q);
                   return s;
            }
            
            if (r.signum() != 0)
            {
            	    BigInteger nval = q;
                BigInteger t = r;

                BigInteger q3 = BigInteger.ZERO;
                String s3 = "";
                for (int i = 0; i < prec + 1; i++)
                {
                    q3 = t.divide(b);
                    s3 += String.format("%s", q3);
                    t = t.subtract(b.multiply(q3)).multiply(BigInteger.TEN);
                }
                
                int carry = 0;
                
                if (sign > 0 &&  t.multiply(new BigInteger("5")).compareTo(b) >= 0)
                    carry = 1;
                else if (sign < 0 && t.multiply(new BigInteger("5")).compareTo(b) > 0)
                    carry = 1;
                

                char[] digits = s3.toCharArray();
                for (int j = digits.length - 1; j >=0 ; j--)
                {
                	   if (carry == 0)
                	       break;
                	   
                	   if (digits[j] == '9')
                	   {
                	   	   digits[j] = '0';
                	   	   carry = 1;
                	   }
                	   else
                	   {
                	   	   digits[j] += 1;
                	   	   carry = 0;
                	   }
                }

                if (digits[0] == '2')
                {
                     nval = nval.add(BigInteger.ONE);
                }

                String s4 = new String(digits);
                int j1 = digits.length;
                for (int j = j1 - 1; j >= 0; j--)
                {
                	   if (digits[j] != '0')
                	   {
                	   	   j1 = j + 1;
                	       break;
                	   }
                 }
                	   
                 if (j1 > 0)
                {
                	   s4 = s4.substring(1, j1);
                       if (s4.length() > 0)
                            s += String.format("%s.%s", nval, s4);
                       else
                             s += String.format("%s", nval);
                }
                else
                {
                      s += String.format("%d", nval);
		    }
            }
            else
            {
                 s += String.format("%s", q);
            }
        }

        return s;
    }


    /**
     * public String toString()
     *
     *    Standard string form of {@code this}
     *
     *     Returns a/c where a is nemerator and c is denominator
     *     Returns integerpart a if fractional part is zero
     *     Returns -0 if {@code this} is negative zero
     *     Returns Infinity a if {@code this} is positive infinity
     *     Returns -Infinity a if {@code this} is negative infinity
     *
     * @return 
     *     String
     */ 
    @Override
    public String toString() {
        String s = "";
        if (this.den.signum() == 0) {
            if (this.num.signum() > 0)
                s = "Infinity";
            else if (this.num .signum()< 0)
                s = "-Infinity";
            else
                s = "NaN";      ///  "0/0 (Indeterminated)";
        }
        else if (this.den.compareTo(BigInteger.ONE.negate()) == 0 && this.num.signum() == 0)
            s = "-0";
        else if (this.den.compareTo(BigInteger.ONE.negate()) == 0)
            s = String.format("%d", this.num.negate());
        else if (this.den.compareTo(BigInteger.ONE) == 0)
            s = String.format("%d", this.num);
        else
            s = String.format("%d/%d", this.num, this.den);

        return s;
    }
}
