/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.xml.security.keys.keyresolver.implementations;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import org.apache.xml.security.keys.keyresolver.KeyResolverException;
import org.apache.xml.security.keys.keyresolver.KeyResolverSpi;
import org.apache.xml.security.keys.storage.StorageResolver;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Resolves a single Key based on the KeyName.
 */
public class SingleKeyResolver extends KeyResolverSpi
{
    /** {@link org.apache.commons.logging} logging facility */
    private static Logger log = Logger.getLogger(SingleKeyResolver.class.getName());

    private String keyName;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private SecretKey secretKey;

    /**
     * Constructor.
     * @param keyName
     * @param publicKey
     */
    public SingleKeyResolver(String keyName, PublicKey publicKey) {
        this.keyName = keyName;
        this.publicKey = publicKey;
    }

    /**
     * Constructor.
     * @param keyName
     * @param privateKey
     */
    public SingleKeyResolver(String keyName, PrivateKey privateKey) {
        this.keyName = keyName;
        this.privateKey = privateKey;
    }

    /**
     * Constructor.
     * @param keyName
     * @param secretKey
     */
    public SingleKeyResolver(String keyName, SecretKey secretKey) {
        this.keyName = keyName;
        this.secretKey = secretKey;
    }

    /**
     * This method returns whether the KeyResolverSpi is able to perform the requested action.
     *
     * @param element
     * @param BaseURI
     * @param storage
     * @return whether the KeyResolverSpi is able to perform the requested action.
     */
    public boolean engineCanResolve(Element element, String baseURI, StorageResolver storage) {
        return XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME);
    }

    /**
     * Method engineLookupAndResolvePublicKey
     *
     * @param element
     * @param baseURI
     * @param storage
     * @return null if no {@link PublicKey} could be obtained
     * @throws KeyResolverException
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String baseURI, StorageResolver storage
    ) throws KeyResolverException {
        if (log.isLoggable(Level.FINE)) {
            log.log(Level.FINE, "Can I resolve " + element.getTagName() + "?");
        }

        if (publicKey != null 
            && XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME)) {
            String name = element.getFirstChild().getNodeValue();
            if (keyName.equals(name)) {
                return publicKey;
            }
        }

        log.log(Level.FINE, "I can't");
        return null;
    }

    /**
     * Method engineResolveX509Certificate
     * @inheritDoc
     * @param element
     * @param baseURI
     * @param storage
     * @throws KeyResolverException
     */
    public X509Certificate engineLookupResolveX509Certificate(
        Element element, String baseURI, StorageResolver storage
    ) throws KeyResolverException {
        return null;
    }

    /**
     * Method engineResolveSecretKey
     *
     * @param element
     * @param baseURI
     * @param storage
     * @return resolved SecretKey key or null if no {@link SecretKey} could be obtained
     *
     * @throws KeyResolverException
     */
    public SecretKey engineResolveSecretKey(
        Element element, String baseURI, StorageResolver storage
    ) throws KeyResolverException {
        if (log.isLoggable(Level.FINE)) {
            log.log(Level.FINE, "Can I resolve " + element.getTagName() + "?");
        }

        if (secretKey != null
            && XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME)) {
            String name = element.getFirstChild().getNodeValue();
            if (keyName.equals(name)) {
                return secretKey;
            }
        }

        log.log(Level.FINE, "I can't");
        return null;
    }

    /**
     * Method engineResolvePrivateKey
     * @inheritDoc
     * @param element
     * @param baseURI
     * @param storage
     * @return resolved PrivateKey key or null if no {@link PrivateKey} could be obtained
     * @throws KeyResolverException
     */
    public PrivateKey engineLookupAndResolvePrivateKey(
        Element element, String baseURI, StorageResolver storage
    ) throws KeyResolverException {
        if (log.isLoggable(Level.FINE)) {
            log.log(Level.FINE, "Can I resolve " + element.getTagName() + "?");
        }

        if (privateKey != null
            && XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME)) {
            String name = element.getFirstChild().getNodeValue();
            if (keyName.equals(name)) {
                return privateKey;
            }
        }

        log.log(Level.FINE, "I can't");
        return null;
    }
}