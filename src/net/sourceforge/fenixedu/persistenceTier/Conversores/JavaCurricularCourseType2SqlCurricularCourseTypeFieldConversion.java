package net.sourceforge.fenixedu.persistenceTier.Conversores;

import net.sourceforge.fenixedu.util.CurricularCourseType;

import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;

/**
 * @author dcs-rjao
 * 
 * 19/Mar/2003
 */

public class JavaCurricularCourseType2SqlCurricularCourseTypeFieldConversion implements FieldConversion {

    public Object javaToSql(Object source) {
        if (source instanceof CurricularCourseType) {
            CurricularCourseType src = (CurricularCourseType) source;
            return src.getCurricularCourseType();
        }
        return source;

    }

    public Object sqlToJava(Object source) {
        if (source instanceof Integer) {
            Integer src = (Integer) source;
            return new CurricularCourseType(src);
        }
        return source;

    }

}