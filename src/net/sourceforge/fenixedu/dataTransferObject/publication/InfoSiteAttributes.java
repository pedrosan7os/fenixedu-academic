/*
 * Created on Nov 13, 2003
 *  
 */
package net.sourceforge.fenixedu.dataTransferObject.publication;

import java.util.List;

import net.sourceforge.fenixedu.dataTransferObject.DataTranferObject;
import net.sourceforge.fenixedu.dataTransferObject.ISiteComponent;

/**
 * @author Sairf
 *  
 */
public class InfoSiteAttributes extends DataTranferObject implements ISiteComponent {

    private List infoRequiredAttributes;

    private List infoNonRequiredAttributes;

    public InfoSiteAttributes() {
    }

    /**
     * @return Returns the infoNonRequiredAttributes.
     */
    public List getInfoNonRequiredAttributes() {
        return infoNonRequiredAttributes;
    }

    /**
     * @return Returns the infoRequiredAttributes.
     */
    public List getInfoRequiredAttributes() {
        return infoRequiredAttributes;
    }

    /**
     * @param infoNonRequiredAttributes
     *            The infoNonRequiredAttributes to set.
     */
    public void setInfoNonRequiredAttributes(List infoNonRequiredAttributes) {
        this.infoNonRequiredAttributes = infoNonRequiredAttributes;
    }

    /**
     * @param infoRequiredAttributes
     *            The infoRequiredAttributes to set.
     */
    public void setInfoRequiredAttributes(List infoRequiredAttributes) {
        this.infoRequiredAttributes = infoRequiredAttributes;
    }

}