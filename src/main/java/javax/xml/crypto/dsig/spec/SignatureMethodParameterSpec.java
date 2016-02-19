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
/*
 * Copyright 2005 Sun Microsystems, Inc. All rights reserved.
 */
/*
 * $Id: SignatureMethodParameterSpec.java 1092655 2011-04-15 10:24:18Z coheigea $
 */
package javax.xml.crypto.dsig.spec;

import javax.xml.crypto.dsig.SignatureMethod;
import java.security.spec.AlgorithmParameterSpec;

/**
 * A specification of algorithm parameters for an XML {@link SignatureMethod} 
 * algorithm. The purpose of this interface is to group (and provide type 
 * safety for) all signature method parameter specifications. All signature
 * method parameter specifications must implement this interface.
 *
 * @author Sean Mullan
 * @author JSR 105 Expert Group
 * @see SignatureMethod
 */
public interface SignatureMethodParameterSpec extends AlgorithmParameterSpec {}