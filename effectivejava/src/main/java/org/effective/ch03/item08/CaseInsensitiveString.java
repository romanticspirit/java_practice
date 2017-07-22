package org.effective.ch03.item08;

/**
 * Created by stephen on 17/7/9.
 */
public class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString (String s){
        if (s==null){
            throw new NullPointerException();
        }

        this.s  =s;
    }

    //broken
    @Override
    public boolean equals (Object o){
        if (o instanceof CaseInsensitiveString){
            return s.equalsIgnoreCase(((CaseInsensitiveString)o).s);
        }


        // here comes issue since it is not valid for String. String has own equals
        if (o instanceof String){
            return s.equalsIgnoreCase((String) o);
        }

        return false;
    }
/*   correct version
    @Override
    public boolean equals (Object o){
        return o instanceof CaseInsensitiveString && ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
    }
*/


}
