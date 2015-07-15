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

import org.marc.everest.formatters.interfaces.IStructureFormatter;

/**
 * Represents a connector that has a formatter attached to it. Formatters are associated with a connector
 * via the setFormatter method.
 */
public interface IFormattedConnector {

	/**
	 * Get the currently assigned formatter
	 */
	IStructureFormatter getFormatter();

	/**
	 * Sets the currently assigned formatter
	 */
	void setFormatter(IStructureFormatter fmtr);
}
