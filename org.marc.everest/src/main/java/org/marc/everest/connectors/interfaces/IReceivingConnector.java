/**
 * Copyright 2008-2013 Mohawk College of Applied Arts and Technology
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you 
 * may not use this file except in compliance with the License. You may 
 * obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations under 
 * the License.
 * 
 * User: Justin Fyfe
 * Date: 12-14-2012
 */
package org.marc.everest.connectors.interfaces;

/**
 * Represents an IConnector that is capable of receiving data from remote systems
 */
public interface IReceivingConnector extends IConnector {

	/**
	 * Receive a message from the connector 
	 * in a blocking manner
	 */
	IReceiveResult receive();
	
	/**
	 * True if the connector currently has data that can be received using the receive method 
	 */
	boolean hasData();
	
}
