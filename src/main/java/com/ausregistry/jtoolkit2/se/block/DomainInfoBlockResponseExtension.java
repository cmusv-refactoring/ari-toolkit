package com.ausregistry.jtoolkit2.se.block;

import javax.xml.xpath.XPathExpressionException;

import com.ausregistry.jtoolkit2.se.ExtendedObjectType;
import com.ausregistry.jtoolkit2.se.ResponseExtension;
import com.ausregistry.jtoolkit2.xml.XMLDocument;

/**
 * <p>Extension for the EPP Domain Info response, representing the Block Info aspects of the Block extension.</p>
 *
 * <p>Use this to access info data for a Block as provided in an EPP Domain Info response compliant
 * with RFC5730 and RFC5731. Such a service element is sent by a compliant EPP server in response to a valid
 * Domain Info command with the Block extension.</p>
 *
 * <p>For flexibility, this implementation extracts the data from the response using XPath queries, the expressions
 * for which are defined statically.</p>
 *
 * @see com.ausregistry.jtoolkit2.se.DomainInfoCommand
 * @see com.ausregistry.jtoolkit2.se.block.DomainInfoBlockCommandExtension
 * @see <a href="http://ausregistry.github.io/doc/block-1.0/block-1.0.html">Block Extension Mapping for the
 * Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainInfoBlockResponseExtension extends ResponseExtension {
    private static final long serialVersionUID = -4403413192868009866L;

    private static final String BLO_PREFIX = ExtendedObjectType.BLOCK.getName();
    private static final String BLO_XPATH_PREFIX = ResponseExtension.EXTENSION_EXPR + "/" + BLO_PREFIX
            + ":RESPONSE_TYPE/" + BLO_PREFIX;
    private static final String ID = BLO_XPATH_PREFIX + ":id/text()";
    private static final String RESPONSE_TYPE = ResponseExtension.INFO;

    private String id;
    private boolean initialised;

    @Override
    public void fromXML(XMLDocument xmlDoc) throws XPathExpressionException {
        id = xmlDoc.getNodeValue(replaceResponseType(ID, RESPONSE_TYPE));
        initialised = id != null;
    }

    @Override
    public boolean isInitialised() {
        return initialised;
    }

    public String getId() {
        return id;
    }
}
