/* 
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
 * Date: 10-02-2012
 */
package org.marc.everest.datatypes.interfaces;

import java.util.Calendar;

/**
 * Represents a point in time
 * NB: This differs from the .NET implementation in that it does not extend PDV
 */
public interface IPointInTime extends IPrimitiveDataValue<String>
{

	/**
	 * Gets the value of the point in time as a Calendar object
	 */
	Calendar getDateValue();
	/**
	 * Sets the date value in the point in time object using the Calendar object
	 */
	void setDateValue(Calendar value);
	
}
