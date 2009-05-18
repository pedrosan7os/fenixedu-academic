package net.sourceforge.fenixedu.domain.util;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.fenixedu.domain.RootDomainObject;

public class Email extends Email_Base {

    public Email() {
	super();
	setRootDomainObject(RootDomainObject.getInstance());
    }

    public Email(final String fromName, final String fromAddress, final String subject, final String body, String... toAddresses) {
	this(fromName, fromAddress, null, Arrays.asList(toAddresses), null, null, subject, body);  
    }
    
    public Email(final String fromName, final String fromAddress, final String[] replyTos, final Collection<String> toAddresses,
	    final Collection<String> ccAddresses, final Collection<String> bccAddresses, final String subject, final String body) {

	this();
	setFromName(fromName);
	setFromAddress(fromAddress);
	setReplyTos(new EmailAddressList(replyTos == null ? null : Arrays.asList(replyTos)));
	setToAddresses(new EmailAddressList(toAddresses));
	setCcAddresses(new EmailAddressList(ccAddresses));
	setBccAddresses(new EmailAddressList(bccAddresses));
	setSubject(subject);
	setBody(body);
    }

    public void delete() {
	removeRootDomainObject();
	super.deleteDomainObject();
    }

    public String[] replyTos() {
	return getReplyTos() == null ? null : getReplyTos().toArray();
    }

    public Collection<String> toAddresses() {
	return getToAddresses() == null ? null : getToAddresses().toCollection();
    }

    public Collection<String> ccAddresses() {
	return getCcAddresses() == null ? null : getCcAddresses().toCollection();
    }

    public Collection<String> bccAddresses() {
	return getBccAddresses() == null ? null : getBccAddresses().toCollection();
    }

}
