/*
 * JavaTipoCurso2SqlTipoCursoFieldConversion.java
 * 
 * Created on 21 de Novembro de 2002, 15:57
 */

package net.sourceforge.fenixedu.persistenceTier.Conversores;

import net.sourceforge.fenixedu.util.TipoCurso;

import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;

public class JavaTipoCurso2SqlTipoCursoFieldConversion implements FieldConversion {

    /*
     * @see FieldConversion#javaToSql(Object)
     */
    public Object javaToSql(Object source) {
        if (source instanceof TipoCurso) {
            TipoCurso s = (TipoCurso) source;
            return s.getTipoCurso();
        }

        return source;

    }

    /*
     * @see FieldConversion#sqlToJava(Object)
     */
    public Object sqlToJava(Object source) {
        if (source instanceof Integer) {
            Integer src = (Integer) source;
            return new TipoCurso(src);
        }

        return source;

    }
}